package com.nst.faculty.model;

import com.nst.faculty.enums.TipSale;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name = "Sala")
@Table(name = "SALA")
public class Sala {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TIP_SALE", nullable = false, unique = false)
	@NotEmpty
	private TipSale tipSale;
	
	@Column(name = "ZGRADA", nullable = false, unique = false)
	@Size(min = 1, max = 1)
	@Pattern(regexp = "[AB]", message = "Zgrada mora biti ili 'A' ili 'B'")
	@NotEmpty
	private Character zgrada;
	
	@Column(name = "SPRAT", nullable = false, unique = false)
	@NotNull
	private Long sprat;
	
	@Column(name = "BROJ_SALE", nullable = false, unique = false)
	@NotNull
	private Long brojSale;
	
}
