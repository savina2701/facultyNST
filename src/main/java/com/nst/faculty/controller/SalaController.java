package com.nst.faculty.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sala")
public class SalaController {
	
	@GetMapping
	public String getSalaList() {
		return "views/sala/sala-list"; 
	}
	
}
