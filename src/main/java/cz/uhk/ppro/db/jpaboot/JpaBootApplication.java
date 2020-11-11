package cz.uhk.ppro.db.jpaboot;

import cz.uhk.ppro.db.jpaboot.dao.JpaStudentDao;
import cz.uhk.ppro.db.jpaboot.dao.JpaStudentDataRepository;
import cz.uhk.ppro.db.jpaboot.model.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class JpaBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaBootApplication.class, args);
    }

    /**
     * Beana typu CommandLineRunner je "spustena" pri startu SpringBootove aplikace.
     * Oproti metode main(...) zde mame k dispozici Dependency Injection a muzeme si do metody (beany)
     * nechat injektovat jine beany, ktere chceme vyuzit.
     * @param studentDao repozitory napsany "rucne" s pouzitim JPA (EntityManager)
     * @param studentDataRepository repozitory s pouzitim modulu Spring Data JPA, implementaci interfacu "doda" Spring za behu aplikace
     * @return
     */
    @Bean
    public CommandLineRunner demo(JpaStudentDao studentDao, JpaStudentDataRepository studentDataRepository) {
        return (args) -> {
            //useJpaStudentDao(studentDao);
            useJpaStudentDataRepository(studentDataRepository);
        };
    }

    private void useJpaStudentDataRepository(JpaStudentDataRepository studentDataRepository) {
        List<Student> studenti = studentDataRepository.findAll();

        for (Student s : studenti) {
            if (s.getJmeno().equals("Josef")) {
                studentDataRepository.delete(s);
            }
            System.out.println(s);
        }

        System.out.println("============================================================");

        Student ns = new Student("Hynek", "Josef");
        studentDataRepository.save(ns);
        System.out.println("New student id=" + ns.getCisloStudia());

        System.out.println("============================================================");

        studenti = studentDataRepository.findAll();

        for (Student s : studenti) {
            System.out.printf("%4d | %-30s | %-30s\n", s.getCisloStudia(),
                    s.getPrijmeni(), s.getJmeno());
        }
    }

    private void useJpaStudentDao(JpaStudentDao studentDao) {
        List<Student> studenti = studentDao.getAll();

        for (Student s : studenti) {
            if (s.getJmeno().equals("Josef")) {
                studentDao.remove(s);
            }
            System.out.println(s);
        }

        System.out.println("============================================================");

        Student ns = new Student("Hynek", "Josef");
        studentDao.add(ns);
        System.out.println("New student id=" + ns.getCisloStudia());

        System.out.println("============================================================");

        studenti = studentDao.getAll();

        for (Student s : studenti) {
            System.out.printf("%4d | %-30s | %-30s\n", s.getCisloStudia(),
                    s.getPrijmeni(), s.getJmeno());
        }
    }
}
