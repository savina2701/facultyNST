package com.nst.faculty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nst.faculty.model.Ispit;

public interface JpaIspitRepository extends JpaRepository<Ispit, Long>{

	@Override
	@Query("SELECT i FROM Ispit i ORDER BY i.id ASC")
	List<Ispit> findAll();
	
	
}
