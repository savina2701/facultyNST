package com.nst.faculty.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nst.faculty.enums.VrstaTitule;
import com.nst.faculty.model.Katedra;
import com.nst.faculty.model.Profesor;
import com.nst.faculty.model.dto.ProfesorDto;
import com.nst.faculty.repository.JpaKatedraRepository;
import com.nst.faculty.repository.JpaProfesorRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProfesorService {

	@Autowired
	JpaProfesorRepository jpaProfesorRepository;
	
	@Autowired
	JpaKatedraRepository jpaKatedraRepository;
	
	@Autowired
	KatedraService katedraService;
	
	/*@Transactional(propagation = Propagation.REQUIRED)
	public List<ProfesorDto> findAll() {
		return jpaProfesorRepository.findAll()
				.stream()
			    .map(bean -> new ProfesorDto(bean)).toList();
	}*/
	
	@Transactional(propagation = Propagation.REQUIRED)
	public List<ProfesorDto> findAll() {
		List<ProfesorDto> list = jpaProfesorRepository.findAll().stream().map(bean -> new ProfesorDto(bean)).toList();
		
		for (ProfesorDto profesorDto : list) {
			Katedra katedra = katedraService.findById(profesorDto.getKatedraFk()).orElse(null);
			if(katedra != null) {
				profesorDto.setKatedraNaziv(katedra.getNaziv());
			}
		}
		
		return list;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Optional<Profesor> findById(Long id){
		return jpaProfesorRepository.findById(id);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public ProfesorDto findDtoById(Long id) {
		Profesor profesor = findById(id).orElseThrow(() -> new EntityNotFoundException("Profesor with id " + id + " not found"));
		ProfesorDto dto = new ProfesorDto(profesor);
		return dto;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(ProfesorDto dto) {
		Profesor profesor = new Profesor();
		profesor.setIme(dto.getIme());
		profesor.setPrezime(dto.getPrezime());
		profesor.setTitula(VrstaTitule.valueOf(dto.getTitula()));
		profesor.setEmail(dto.getEmail());
		profesor.setKatedraFk(dto.getKatedraFk());
		jpaProfesorRepository.save(profesor);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(ProfesorDto dto) {
		if (dto == null) {
	        throw new IllegalArgumentException("DTO ne sme biti null!");
	    }
	    if (dto.getId() == null) {
	        throw new IllegalArgumentException("ID ne sme biti null prilikom izmene profesora.");
	    }
	    
	    Profesor profesor = jpaProfesorRepository.findById(dto.getId()).orElseThrow(() -> new EntityNotFoundException("Profesor sa ID " + dto.getId() + " ne postoji."));
	    
	    profesor.setIme(dto.getIme());
	    profesor.setPrezime(dto.getPrezime());
	    profesor.setTitula(VrstaTitule.valueOf(dto.getTitula()));
	    profesor.setEmail(dto.getEmail());
	    profesor.setKatedraFk(dto.getKatedraFk());
	    jpaProfesorRepository.save(profesor);	    
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Long id) throws Exception{
		if(id == null) {
			throw new Exception("Porfeosr doesn't exist");
		}
		
		Profesor profesor = jpaProfesorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Profesor with id " + id + " not found"));
		jpaProfesorRepository.delete(profesor);
	}

	

}
