package com.nst.faculty.model.dto;

import com.nst.faculty.model.Profesor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfesorDto {

	private Long id;
	private String ime;
	private String prezime;
	private String titula;
	private String email;
	private Long katedraFk;
	private String katedraNaziv;
	
	public ProfesorDto(Profesor profesor) {
		this.id = profesor.getId();
		this.ime = profesor.getIme();
		this.prezime = profesor.getPrezime();
		this.titula = profesor.getTitula().name();
		this.email = profesor.getEmail();
		this.katedraFk = profesor.getKatedraFk();
	}
	
}
