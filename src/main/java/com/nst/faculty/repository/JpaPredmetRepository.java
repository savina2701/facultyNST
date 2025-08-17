package com.nst.faculty.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nst.faculty.model.Predmet;

public interface JpaPredmetRepository extends JpaRepository<Predmet, Long>{

	@Override
	@Query("SELECT p FROM Predmet p ORDER BY p.id ASC")
	List<Predmet> findAll();
	
	@Override
	@Query("SELECT p FROM Predmet p WHERE p.id = :id")
	Optional<Predmet> findById(Long id);
	
}
