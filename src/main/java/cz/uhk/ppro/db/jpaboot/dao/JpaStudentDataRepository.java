package cz.uhk.ppro.db.jpaboot.dao;

import cz.uhk.ppro.db.jpaboot.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA Repository,
 * Student = Entita, pro kterou vytvarime repository
 * Integer = typ primarniho klice entity
 */
public interface JpaStudentDataRepository extends JpaRepository<Student, Integer> {
}
