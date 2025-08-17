package com.nst.faculty.model.dto;

import com.nst.faculty.model.Student;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentDto {

	private Long id;
	private String ime;
	private String prezime;
	private String brojIndeksa;
	private String jmbg;
	private String email;
	private String telefon;
	private String pol;
	private Long godinaUpisa;
	
	public StudentDto(Student student) {
		this.id = student.getId();
		this.ime = student.getIme();
		this.prezime = student.getPrezime();
		this.brojIndeksa = student.getBrojIndeksa();
		this.jmbg = student.getJmbg();
		this.email = student.getEmail();
		this.telefon = student.getTelefon();
		this.pol = String.valueOf(student.getPol());
		this.godinaUpisa = student.getGodinaUpisa();
	}
	
}
