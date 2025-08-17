package com.nst.faculty.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nst.faculty.model.Katedra;
import com.nst.faculty.model.dto.KatedraDto;
import com.nst.faculty.repository.JpaKatedraRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class KatedraService {

	@Autowired
	JpaKatedraRepository jpaKatedraRepository;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public List<KatedraDto> findAll() {
		return jpaKatedraRepository.findAll()
			    .stream()
			    .map(bean -> new KatedraDto(bean)).toList();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Optional<Katedra> findById(Long id) {
	    return jpaKatedraRepository.findById(id);
	}
	
	@Cacheable("katedra")
	@Transactional(propagation = Propagation.REQUIRED)
	public Katedra findCacheById(Long id) {
		return jpaKatedraRepository.findById(id).orElse(null);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public KatedraDto findDtoById(Long id) {
		Katedra katedra = findById(id).orElseThrow(() -> new EntityNotFoundException("Katedra with id " + id + " not found"));
		KatedraDto dto = new KatedraDto(katedra);
		return dto;
	}

	/*@Transactional(propagation = Propagation.REQUIRED)
	public KatedraDto findDtoById(Long id) {
		System.out.println("******************ID ZA KOJI TRAZIMO KATEDRU JE " + id);
		Optional<Katedra> optionalKatedra = findById(id);
		System.out.println("*****USPESNO PRONADJENA KATEDRA!!!");
		if(optionalKatedra.isPresent()) {
			Katedra katedra = optionalKatedra.get();
			KatedraDto dto = new KatedraDto(katedra);
			return dto;
		}else {
		    throw new EntityNotFoundException("Nema te katedre, id: " + id);
		}
	}*/
	
	/*@Transactional(propagation = Propagation.REQUIRED)
	public void save(KatedraDto dto) {
		Katedra katedra;
		System.out.println("DTO JE: " + dto.getId());
		if (dto.getId() != null) {
	        katedra = jpaKatedraRepository.findById(dto.getId())
	                .orElseThrow(() -> new EntityNotFoundException("Katedra sa ID " + dto.getId() + " ne postoji"));
	    } else {
	        katedra = new Katedra();
	    }
		
		System.out.println("**** ID U SAVE METODI POSLE ... JE: " + dto.getId());
		katedra.setNaziv(dto.getNaziv());
		katedra.setZgrada(dto.getZgrada());
		katedra.setSprat(dto.getSprat());
		katedra.setKabinet(dto.getKabinet());
		katedra.setTelefon(dto.getTelefon());
		katedra.setBrojZaposlenih(dto.getBrojZaposlenih());
		jpaKatedraRepository.save(katedra);
	}*/
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(KatedraDto dto) {
	    /*if (dto == null) {
	        throw new IllegalArgumentException("DTO ne sme biti null!");
	    }
	    
	    try {
		    if (dto.getId() != null) {
		        throw new IllegalArgumentException("ID mora biti null prilikom kreiranja nove katedre.");
		    }
		    System.out.println("Ajde test: dto id u save metodi je: " + dto.getId() + ", naziv: " + dto.getNaziv());*/
		  
			System.out.println("Id = " + dto.getId());
			
			Katedra katedra = new Katedra();
		    katedra.setNaziv(dto.getNaziv());
		    katedra.setZgrada(dto.getZgrada());
		    katedra.setSprat(dto.getSprat());
		    katedra.setKabinet(dto.getKabinet());
		    katedra.setTelefon(dto.getTelefon());
		    katedra.setBrojZaposlenih(dto.getBrojZaposlenih());
	
		    jpaKatedraRepository.save(katedra);
	    /*} catch (Exception e) {
	        e.printStackTrace();
	        throw e;  
	    }*/
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(KatedraDto dto) {
	    if (dto == null) {
	        throw new IllegalArgumentException("DTO ne sme biti null!");
	    }
	    if (dto.getId() == null) {
	        throw new IllegalArgumentException("ID ne sme biti null prilikom izmene katedre.");
	    }

	    Katedra katedra = jpaKatedraRepository.findById(dto.getId())
	            .orElseThrow(() -> new EntityNotFoundException("Katedra sa ID " + dto.getId() + " ne postoji."));

	    katedra.setNaziv(dto.getNaziv());
	    katedra.setZgrada(dto.getZgrada());
	    katedra.setSprat(dto.getSprat());
	    katedra.setKabinet(dto.getKabinet());
	    katedra.setTelefon(dto.getTelefon());
	    katedra.setBrojZaposlenih(dto.getBrojZaposlenih());

	    jpaKatedraRepository.save(katedra);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Long id) throws Exception {
		if(id == null) {
			throw new Exception("Katedra doesn't exist");
		}
		
		Katedra katedra = jpaKatedraRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Katedra with id " + id + " not found"));
		jpaKatedraRepository.delete(katedra);
	}



	
	
	
	
}
