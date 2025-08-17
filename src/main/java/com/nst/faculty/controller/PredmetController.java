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

import com.nst.faculty.menu.MenuService;
import com.nst.faculty.model.dto.PredmetDto;
import com.nst.faculty.service.KatedraService;
import com.nst.faculty.service.PredmetService;

@Controller
@RequestMapping("/predmet")
public class PredmetController {

	@Autowired
	MenuService menuService;
	
	@Autowired
	PredmetService predmetService;
	
	@Autowired
	KatedraService katedraService;
	
	@GetMapping
    public String getPredmetList(Model model) {
		try {
			model.addAttribute("menuItems", menuService.getMenuItems());
			model.addAttribute("predmeti", predmetService.findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "views/predmet/predmet-list"; 
    }

	//Forma za novi entitet
	@GetMapping("/add")
	public String createForm(Model model) throws IOException {
		model.addAttribute("dto", new PredmetDto());
	    model.addAttribute("menuItems", menuService.getMenuItems());
	    model.addAttribute("katedre", katedraService.findAll());
	    return "views/predmet/predmet-form";
	}
	
	//Forma za izmenu
	@GetMapping("/{id}")
	public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redir) throws Exception{
		model.addAttribute("katedre", katedraService.findAll());
		try {
			PredmetDto dto = predmetService.findDtoById(id);
			if(dto != null) {
				model.addAttribute("dto", dto);
				model.addAttribute("menuItems", menuService.getMenuItems());
				return "views/predmet/predmet-form";
			} else {
				System.out.println("Couldn't find Predmet with id: " + id);
				return "redirect:/predmet";
			}
				
			} catch (Exception e) {
				 System.out.println(e.getMessage());
				 e.printStackTrace();
		         return "redirect:/predmet";
			}
	}

	// Metod za čuvanje (kreiranje ili izmenu)
    @PostMapping("/save")
    public String savePredmet(@ModelAttribute("dto") PredmetDto dto, RedirectAttributes redir) throws Exception{
        try {
        	Long id = dto.getId();
        	if(id == null) {
        		predmetService.save(dto);
        		System.out.println("Predmet je uspesno sacuvan.");
        	} else {
        		 predmetService.update(dto);
        		 System.out.println("Predmet je uspesno azuriran.");
        	}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
	         return "redirect:/predmet";
		}
        
        return "redirect:/predmet";
    }
    
    @PostMapping("/delete")
    public String deletePredmet(@RequestParam Long id, RedirectAttributes redir) {
    	try {
			predmetService.delete(id);
			System.out.println("Predmet je uspesno obrisan");
			return "redirect:/predmet";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/predmet";
		}
    	
    }
}


