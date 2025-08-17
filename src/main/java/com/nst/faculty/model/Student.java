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
import jakarta.validation.constraints.Size;
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
@Entity(name = "Student")
@Table(name = "STUDENT")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
	@SequenceGenerator(name = "student_seq", sequenceName = "student_seq", allocationSize = 1)
	private Long id;
	
	@Column(name = "IME", nullable = false, unique = false)
	@Length(max = 50)
	@NotEmpty
	private String ime;

	@Column(name = "PREZIME", nullable = false, unique = false)
	@Length(max = 50)
	@NotEmpty
	private String prezime;

	@Column(name = "BROJ_INDEKSA", nullable = false, unique = true)
	@Pattern(regexp = "^\\d{4}/\\d{4}$", message = "Broj indeksa mora biti u formatu YYYY/NNNN, npr. 2021/0001")
    @Size(min = 9, max = 9, message = "Broj indeksa mora imati tačno 9 karaktera")
	@NotEmpty
	private String brojIndeksa;

	@Column(name = "JMBG", nullable = false, unique = true)
	@Size(min = 13, max = 13, message = "JMBG mora imati 13 cifara")
	@NotEmpty
	private String jmbg;

	@Column(name = "EMAIL", nullable = false, unique = true)
	@Size(max = 50)
	@NotEmpty
	private String email;

	@Column(name = "TELEFON", nullable = false, unique = true)
	@NotEmpty
	private String telefon;

	@Column(name = "POL", nullable = false, unique = false)
	private Character pol;
	
	@Column(name = "GODINA_UPISA", nullable = false, unique = false)
	@NotNull
	private Long godinaUpisa;
	
	
}
