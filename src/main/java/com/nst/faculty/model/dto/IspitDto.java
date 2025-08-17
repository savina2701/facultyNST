package com.nst.faculty.model.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.nst.faculty.model.Ispit;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IspitDto {

	private Long id;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime datumVremeOdrzavanja;
	private String tipIspita;
	private String napomena;
	private Long predmetFk;
	private String predmetNaziv;
	
	public IspitDto(Ispit ispit) {
		this.id = ispit.getId();
		this.datumVremeOdrzavanja = ispit.getDatumVremeOdrzavanja();
		this.tipIspita = ispit.getTipIspita().name();
		this.napomena = ispit.getNapomena();
		this.predmetFk = ispit.getPredmetFk();
	}
}
