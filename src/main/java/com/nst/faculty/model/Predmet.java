package com.nst.faculty.model;

import org.hibernate.validator.constraints.Length;

import com.nst.faculty.enums.GodinaStudija;

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
import jakarta.validation.constraints.NotEmpty;
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
@Entity(name = "Predmet")
@Table(name = "PREDMET")
public class Predmet {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "predmet_seq")
	@SequenceGenerator(name = "predmet_seq", sequenceName = "predmet_seq", allocationSize = 1)
	private Long id;
	
	@Column(name = "NAZIV", nullable = false, unique = true)
	@Length(max = 100)
	@NotEmpty(message = "Name must not be empty")
	private String naziv;
	
	@Column(name = "ESPB", nullable = false, unique = false)
	@NotNull
	private Long espb;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "GODINA_STUDIJA", nullable = false, unique = false)
	@NotNull
	private GodinaStudija godinaStudija;
	
	@Column(name = "SEMESTAR", nullable = false, unique = false)
	@NotEmpty
	private String semestar;
	
	@Column(name = "OPIS", nullable = true, unique = false)
	private String opis;
	
	@Column(name = "OBAVEZAN", nullable = false, unique = false)
	@NotNull
	private Boolean obavezan;
	
	@Column(name = "KATEDRA_FK", nullable = false, unique = false)
	@NotNull
	private Long katedraFk;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "KATEDRA_FK", insertable = false, updatable = false)
	private Katedra katedra;
}
