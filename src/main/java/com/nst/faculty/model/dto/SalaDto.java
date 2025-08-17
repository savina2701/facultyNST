package com.nst.faculty.model.dto;

import com.nst.faculty.enums.TipSale;
import com.nst.faculty.model.Sala;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalaDto {

	private Long id;
	private TipSale tipSale;
	private Character zgrada;
	private Long sprat;
	private Long brojSale;

	public SalaDto(Sala sala) {
		this.id = sala.getId();
		this.tipSale = sala.getTipSale();
		this.zgrada = sala.getZgrada();
		this.sprat = sala.getSprat();
		this.brojSale = sala.getBrojSale();
	}
	
}
