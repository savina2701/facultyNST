package com.nst.faculty.model;

import java.time.LocalDateTime;

import com.nst.faculty.enums.TipIspita;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "Ispit")
@Table(name = "ISPIT")
public class Ispit {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ispit_seq")
	@SequenceGenerator(name = "ispit_seq", sequenceName = "ispit_seq", allocationSize = 1)
	private Long id;
	
	@Column(name = "DATUM_VREME_ODRZAVANJA", nullable = false, unique = false)
	@NotNull
	private LocalDateTime datumVremeOdrzavanja;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TIP_ISPITA", nullable = false, unique = false)
	private TipIspita tipIspita;

	@Column(name = "NAPOMENA", nullable = true, unique = false)
	private String napomena;
	
	@Column(name = "PREDMET_FK", nullable = false, unique = false)
	@NotNull
	private Long predmetFk;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PREDMET_FK", insertable = false, updatable = false)
	private Predmet predmet;
	
	@Column(name = "SALA_FK", nullable = true, unique = false)
	//@NotNull
	private Long salaFk;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SALA_FK", insertable = false, updatable = false)
	private Sala sala;
	
}
