package com.nst.faculty.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nst.faculty.model.Student;
import com.nst.faculty.model.dto.StudentDto;
import com.nst.faculty.repository.JpaStudentRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentService {

	@Autowired
	JpaStudentRepository jpaStudentRepository;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public List<StudentDto> findAll() {
		return jpaStudentRepository.findAll()
			    .stream()
			    .map(bean -> new StudentDto(bean)).toList();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Optional<Student> findById(Long id) {
	    return jpaStudentRepository.findById(id);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public StudentDto findDtoById(Long id) {
		Student student = findById(id).orElseThrow(() -> new EntityNotFoundException("Student with id " + id + " not found"));
		StudentDto dto = new StudentDto(student);
		return dto;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void save(StudentDto dto) {
		Student student = new Student();
		student.setIme(dto.getIme());
		student.setPrezime(dto.getPrezime());
		student.setBrojIndeksa(dto.getBrojIndeksa());
		student.setJmbg(dto.getJmbg());
		student.setEmail(dto.getEmail());
		student.setTelefon(dto.getTelefon());
		student.setPol(dto.getPol().charAt(0));
		student.setGodinaUpisa(dto.getGodinaUpisa());
		jpaStudentRepository.save(student);
	}

	public void update(StudentDto dto) {
		if (dto == null) {
	        throw new IllegalArgumentException("DTO ne sme biti null!");
	    }
	    if (dto.getId() == null) {
	        throw new IllegalArgumentException("ID ne sme biti null prilikom izmene studenta.");
	    }
	    
	    Student student = jpaStudentRepository.findById(dto.getId()).orElseThrow(() -> new EntityNotFoundException("Student sa ID " + dto.getId() + " ne postoji."));
	    student.setIme(dto.getIme());
	    student.setPrezime(dto.getPrezime());
	    student.setBrojIndeksa(dto.getBrojIndeksa());
	    student.setJmbg(dto.getJmbg());
	    student.setEmail(dto.getEmail());
	    student.setTelefon(dto.getTelefon());
	    student.setPol(dto.getPol().charAt(0));
	    student.setGodinaUpisa(dto.getGodinaUpisa());
	    jpaStudentRepository.save(student);
	    
	    // posle dodati da moze da se radi update samo emaila i telefona!!!
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Long id) throws Exception {
		if (id == null) {
			throw new Exception("Student doesn't exist");
		}
	
		Student student = jpaStudentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Katedra with id " + id + " not found"));
		jpaStudentRepository.delete(student);
	}

	
	
	
	
	
	
	
	
	
}
