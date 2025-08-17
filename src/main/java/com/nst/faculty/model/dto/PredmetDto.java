package com.nst.faculty.model.dto;

import com.nst.faculty.enums.GodinaStudija;
import com.nst.faculty.model.Predmet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PredmetDto {

	private Long id;
	private String naziv;
	private Long espb;
	private GodinaStudija godinaStudija; // proveri tip 
	private String semestar;
	private String opis;
	private Boolean obavezan;   // proveri da li radi
	private Long katedraFk;    // proveri da li treba u dto da bude tipa Katedra ili Long
	private String katedraNaziv;
	
	public PredmetDto(Predmet predmet) {
		this.id = predmet.getId();
		this.naziv = predmet.getNaziv();
		this.espb = predmet.getEspb();
		this.godinaStudija = predmet.getGodinaStudija();
		this.semestar = predmet.getSemestar();
		this.opis = predmet.getOpis();
		this.obavezan = predmet.getObavezan();
		this.katedraFk = predmet.getKatedraFk();
	}
	
}
