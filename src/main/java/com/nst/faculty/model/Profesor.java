package com.nst.faculty.model;

import org.hibernate.validator.constraints.Length;

import com.nst.faculty.enums.VrstaTitule;

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
@Entity(name = "Profesor")
@Table(name = "PROFESOR")
public class Profesor {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profesor_seq")
	@SequenceGenerator(name = "profesor_seq", sequenceName = "profesor_seq", allocationSize = 1)
	private Long id;
	
	@Column(name = "IME", nullable = false, unique = false)
	@Length(max = 50)
	@NotEmpty
	private String ime;
	
	@Column(name = "PREZIME", nullable = false, unique = false)
	@Length(max = 50)
	@NotEmpty
	private String prezime;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TITULA", nullable = false, unique = false)
	private VrstaTitule titula;
	
	@Column(name = "EMAIL", nullable = false, unique = true)
	@Length(max = 100)
	//@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email mora biti validan i završavati se sa @gmail.com")
	@NotEmpty
	private String email;

	//ovo srediti
	@Column(name = "KATEDRA_FK", nullable = false, unique = false)
	@NotNull
	private Long katedraFk;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "KATEDRA_FK", insertable = false, updatable = false)
	private Katedra katedra;
	
}
