package com.nst.faculty.model;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "Katedra")
@Table(name = "KATEDRA")
public class Katedra {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "katedra_seq")
	@SequenceGenerator(name = "katedra_seq", sequenceName = "katedra_seq", allocationSize = 1)
	private Long id;
	
	@Column(name = "NAZIV", nullable = false, unique = true)
	@Length(max = 100)
	@NotEmpty
	private String naziv;
	
	@Column(name = "ZGRADA", nullable = false, unique = false)
	@NotEmpty
	private String zgrada;
	
	@Column(name = "SPRAT", nullable = false, unique = false)
	@NotNull
	private Long sprat;

	@Column(name = "KABINET", nullable = false, unique = false)
	@NotNull
	private Long kabinet;
	
	@Column(name = "TELEFON", nullable = false, unique = false)
	@Pattern(regexp = "^011\\d{5,9}$", message = "Telefon mora počinjati sa 011 i imati ukupno između 8 i 12 cifara")
	@NotEmpty
	private String telefon;
	
	@Column(name = "BROJ_ZAPOSLENIH", nullable = false, unique = false)
	@NotNull
	private Long brojZaposlenih;
	
	

}
