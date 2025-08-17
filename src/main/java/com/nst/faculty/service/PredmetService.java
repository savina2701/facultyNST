package com.nst.faculty.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nst.faculty.model.Katedra;
import com.nst.faculty.model.Predmet;
import com.nst.faculty.model.dto.PredmetDto;
import com.nst.faculty.repository.JpaKatedraRepository;
import com.nst.faculty.repository.JpaPredmetRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PredmetService {

	@Autowired
	JpaPredmetRepository jpaPredmetRepository;
	
	@Autowired
	KatedraService katedraService;
	
	@Autowired
	JpaKatedraRepository jpaKatedraRepository;
	
	/*@Transactional(propagation = Propagation.REQUIRED)
	public List<PredmetDto> findAll() {
		return jpaPredmetRepository.findAll().stream().map(bean -> new PredmetDto(bean)).toList();
	}*/

	@Transactional(propagation = Propagation.REQUIRED)
	public List<PredmetDto> findAll() {
		List<PredmetDto> list = jpaPredmetRepository.findAll().stream().map(bean -> new PredmetDto(bean)).toList();
		
		for (PredmetDto predmetDto : list) { //prodji kroz svaki predmet i za njegov katedraFk nadji tu katedru i setuj mu naziv
			Katedra katedra = jpaKatedraRepository.findById(predmetDto.getKatedraFk()).orElse(null);
			if(katedra != null) {
				predmetDto.setKatedraNaziv(katedra.getNaziv());
			}
		}
		
		return list;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Optional<Predmet> findById(Long id){
		return jpaPredmetRepository.findById(id);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public PredmetDto findDtoById(Long id) throws Exception {
		Predmet predmet = findById(id).orElseThrow(() -> new EntityNotFoundException("Predmet with id " + id + " not found"));
		Optional<Katedra> katedra = katedraService.findById(predmet.getKatedraFk());

		PredmetDto dto = new PredmetDto(predmet);
		/*if (katedra.isPresent()) {
			dto.setKatedraFk(katedra.get().getId());
		}else {
			dto.setKatedraFk(null);
		}*/
		
		// ako katedra sa tim id-jem postoji, postavi je u predmetu dto, ako ne postoji nek bude null
		if (katedra.isPresent()) {
			dto.setKatedraFk(katedra.get().getId());
		} else {
			throw new Exception("Trazeni predmet nema Katedru!");
		} //ovo je radilo dok u Dto nisam izmenila da ne postoji vise Katedra katedra, nego Long katedraFk*/

		return dto;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void save(PredmetDto dto) {
		Predmet predmet = new Predmet();
		predmet.setNaziv(dto.getNaziv());
		predmet.setEspb(dto.getEspb());
		predmet.setGodinaStudija(dto.getGodinaStudija());
		predmet.setSemestar(dto.getSemestar());
		predmet.setOpis(dto.getOpis());
		predmet.setObavezan(dto.getObavezan());
		predmet.setKatedraFk(dto.getKatedraFk());
		jpaPredmetRepository.save(predmet);
	}

	public void update(PredmetDto dto) {
		if (dto == null) {
	        throw new IllegalArgumentException("DTO ne sme biti null!");
	    }
	    if (dto.getId() == null) {
	        throw new IllegalArgumentException("ID ne sme biti null prilikom izmene predmeta.");
	    }		
	    
	    Predmet predmet = jpaPredmetRepository.findById(dto.getId()) .orElseThrow(() -> new EntityNotFoundException("Predmet sa ID " + dto.getId() + " ne postoji."));
	    predmet.setNaziv(dto.getNaziv());
	    predmet.setEspb(dto.getEspb());
	    predmet.setGodinaStudija(dto.getGodinaStudija());
	    predmet.setSemestar(dto.getSemestar());
	    predmet.setOpis(dto.getOpis());
	    predmet.setObavezan(dto.getObavezan());
	    predmet.setKatedraFk(dto.getKatedraFk());
	    
	    jpaPredmetRepository.save(predmet);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Long id) throws Exception{
		if(id == null) {
			throw new Exception("Predmet doesn't exist");
		}
		
		Predmet predmet = jpaPredmetRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Predmet with id " + id + " not found"));
		jpaPredmetRepository.delete(predmet);
	}

	
	
	
	
	
	
	
	
	
	
}
