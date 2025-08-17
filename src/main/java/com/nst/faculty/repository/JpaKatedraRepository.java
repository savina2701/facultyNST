package com.nst.faculty.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nst.faculty.model.Katedra;

public interface JpaKatedraRepository extends JpaRepository<Katedra, Long>{

	@Override
	@Query("SELECT k FROM Katedra k ORDER BY k.id ASC")
	List<Katedra> findAll();
	
	@Override
	@Query("SELECT k FROM Katedra k WHERE k.id = :id")
	Optional<Katedra> findById(Long id);
	
	@Query("SELECT MAX(k.id) FROM Katedra k")
	Long findMaxId(); //ne treba nam na kraju
	
}
