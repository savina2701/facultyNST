package com.nst.faculty.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nst.faculty.model.Student;

public interface JpaStudentRepository extends JpaRepository<Student, Long>{

	@Override
	@Query("SELECT s FROM Student s ORDER BY s.id ASC")
	List<Student> findAll();
	
	@Override
	@Query("SELECT s FROM Student s WHERE s.id = :id")
	Optional<Student> findById(Long id);
	
}
