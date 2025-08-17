package com.nst.faculty.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nst.faculty.enums.TipIspita;
import com.nst.faculty.model.Ispit;
import com.nst.faculty.model.Predmet;
import com.nst.faculty.model.dto.IspitDto;
import com.nst.faculty.repository.JpaIspitRepository;
import com.nst.faculty.repository.JpaPredmetRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class IspitService {

	@Autowired
	JpaIspitRepository jpaIspitRepository;
	
	@Autowired
	JpaPredmetRepository jpaPredmetRepository;
	
	/*@Transactional(propagation = Propagation.REQUIRED)
	public List<IspitDto> findAll() {
		return jpaIspitRepository.findAll()
			    .stream()
			    .map(bean -> new IspitDto(bean)).toList();
	}*/
	
	@Transactional(propagation = Propagation.REQUIRED)
	public List<IspitDto> findAll() {
		List<IspitDto> list = jpaIspitRepository.findAll().stream().map(bean -> new IspitDto(bean)).toList();
		
		for (IspitDto ispitDto : list) {
			Predmet predmet = jpaPredmetRepository.findById(ispitDto.getPredmetFk()).orElse(null);
			if(predmet != null) {
				ispitDto.setPredmetNaziv(predmet.getNaziv());
			}
		}
		return list;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Optional<Ispit> findById(Long id){
		return jpaIspitRepository.findById(id);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public IspitDto findDtoById(Long id) {
		Ispit ispit = findById(id).orElseThrow(() -> new EntityNotFoundException("Ispit with id " + id + " not found"));
		IspitDto dto = new IspitDto(ispit);
		return dto;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(IspitDto dto) throws Exception{
		Ispit ispit = new Ispit();
		ispit.setDatumVremeOdrzavanja(dto.getDatumVremeOdrzavanja());
		ispit.setTipIspita(TipIspita.valueOf(dto.getTipIspita()));
		ispit.setNapomena(dto.getNapomena());
		ispit.setPredmetFk(dto.getPredmetFk());
		jpaIspitRepository.save(ispit);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(IspitDto dto) {
		if (dto == null) {
			throw new IllegalArgumentException("DTO ne sme biti null!");
		}
		if (dto.getId() == null) {
			throw new IllegalArgumentException("ID ne sme biti null prilikom izmene ispita.");
		}

		Ispit ispit = jpaIspitRepository.findById(dto.getId())
				.orElseThrow(() -> new EntityNotFoundException("Ispit sa ID " + dto.getId() + " ne postoji."));
		
		ispit.setDatumVremeOdrzavanja(dto.getDatumVremeOdrzavanja());
		ispit.setTipIspita(TipIspita.valueOf(dto.getTipIspita()));
		ispit.setNapomena(dto.getNapomena());
		ispit.setPredmetFk(dto.getPredmetFk());
		jpaIspitRepository.save(ispit);
		
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Long id) throws Exception{
		if (id == null) {
			throw new Exception("Ispit doesn't exist");
		}
		
		Ispit ispit = jpaIspitRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ispit with id " + id + " not found"));
		jpaIspitRepository.delete(ispit);
	}

	


}
