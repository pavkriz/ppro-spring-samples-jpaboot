package cz.uhk.ppro.db.jpaboot.dao;

import cz.uhk.ppro.db.jpaboot.model.Student;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class JpaStudentDao {
	@PersistenceContext
    private EntityManager em;

	public void add(final Student s) {
		em.persist(s);
	}

	public List<Student> getAll() {
		return em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
	}

	public Student getById(int id) {
		return em.find(Student.class, id);
	}

	public void remove(final Student s) {
		Student ps = em.merge(s);
		em.remove(ps);
	}

	public void update(Student s) {
		add(s);
	}

}
