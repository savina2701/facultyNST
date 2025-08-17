package com.nst.faculty.model.dto;

import com.nst.faculty.model.Katedra;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class KatedraDto {

	private Long id;
	private String naziv;
	private String zgrada;
	private Long sprat;
	private Long kabinet;
	private String telefon;
	private Long brojZaposlenih;

	public KatedraDto(Katedra katedra){
		this.id = katedra.getId();
		this.naziv = katedra.getNaziv();
		this.zgrada = katedra.getZgrada();
		this.sprat = katedra.getSprat();
		this.kabinet = katedra.getKabinet();
		this.telefon = katedra.getTelefon();
		this.brojZaposlenih = katedra.getBrojZaposlenih();
	}

	
	
}
