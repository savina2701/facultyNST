package com.nst.faculty.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nst.faculty.enums.VrstaTitule;
import com.nst.faculty.menu.MenuService;
import com.nst.faculty.model.dto.ProfesorDto;
import com.nst.faculty.service.KatedraService;
import com.nst.faculty.service.ProfesorService;

@Controller
@RequestMapping("/profesor")
public class ProfesorController {

	@Autowired
	MenuService menuService;
	
	@Autowired
	ProfesorService profesorService;
	
	@Autowired
	KatedraService katedraService;
	
	@GetMapping
	public String getProfesorList(Model model) {
		try {
			model.addAttribute("menuItems", menuService.getMenuItems());
			model.addAttribute("profesori", profesorService.findAll());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(model);
		return "views/profesor/profesor-list";
	}
	
	//Forma za novi entitet
	@GetMapping("/add")
	public String createForm(Model model) throws IOException {
		model.addAttribute("dto", new ProfesorDto());
        model.addAttribute("menuItems", menuService.getMenuItems());
	    model.addAttribute("katedre", katedraService.findAll());
	    model.addAttribute("vrsteTitula", VrstaTitule.values());
	    return "views/profesor/profesor-form";
	}
	
	//Forma za izmenu
	@GetMapping("/{id}")
	public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redir) throws Exception{
		model.addAttribute("katedre", katedraService.findAll());
		model.addAttribute("vrsteTitula", VrstaTitule.values());
		try {
			ProfesorDto dto = profesorService.findDtoById(id);
			if(dto != null) {
				model.addAttribute("dto", dto);
				model.addAttribute("menuItems", menuService.getMenuItems());
				return "views/profesor/profesor-form";
			} else {
				System.out.println("Couldn't find Profesor with id: " + id);
				return "redirect:/profesor";
			}
					
			} catch (Exception e) {
				 System.out.println(e.getMessage());
				 e.printStackTrace();
		         return "redirect:/profesor";
			}
	}
	
	// Metod za čuvanje (kreiranje ili izmenu)
    @PostMapping("/save")
    public String saveProfesor(@ModelAttribute("dto") ProfesorDto dto, RedirectAttributes redir) throws Exception{
        try {
        	Long id = dto.getId();
        	if(id == null) {
        		profesorService.save(dto);
        		System.out.println("Profesor je uspesno sacuvan.");
        	} else {
        		 profesorService.update(dto);
        		 System.out.println("Profesor je uspesno azuriran.");
        	}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
	         return "redirect:/profesor";
		}
        
        return "redirect:/profesor";
    }
    

    @PostMapping("/delete")
    public String deleteProfesor(@RequestParam Long id, RedirectAttributes redir) {
    	try {
			profesorService.delete(id);
			System.out.println("Profesor je uspesno obrisan.");
			return "redirect:/profesor";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/profesor";
		}
    	
    }
    
}
