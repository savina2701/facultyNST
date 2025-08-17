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

import com.nst.faculty.enums.TipIspita;
import com.nst.faculty.menu.MenuService;
import com.nst.faculty.model.dto.IspitDto;
import com.nst.faculty.service.IspitService;
import com.nst.faculty.service.PredmetService;

@Controller
@RequestMapping("/ispit")
public class IspitController {

	@Autowired
	MenuService menuService;
	
	@Autowired
	IspitService ispitService;
	
	@Autowired
	PredmetService predmetService;
	
	@GetMapping
	public String getIspitList(Model model) {
		try {
			model.addAttribute("menuItems", menuService.getMenuItems());
			model.addAttribute("ispiti", ispitService.findAll());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(model);
		return "views/ispit/ispit-list";
	}
	
	//Forma za novi entitet
	@GetMapping("/add")
	public String createForm(Model model) throws IOException {
		model.addAttribute("dto", new IspitDto());
	    model.addAttribute("menuItems", menuService.getMenuItems());
	    model.addAttribute("tipoviIspita", TipIspita.values());
	    model.addAttribute("predmeti", predmetService.findAll());
	    return "views/ispit/ispit-form";
	}
	
	//Forma za izmenu
	@GetMapping("/{id}")
	public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redir) throws Exception{
		model.addAttribute("tipoviIspita", TipIspita.values());
		model.addAttribute("predmeti", predmetService.findAll());
		try {
			IspitDto dto = ispitService.findDtoById(id);
			if(dto != null) {
				model.addAttribute("dto", dto);
				model.addAttribute("menuItems", menuService.getMenuItems());
				return "views/ispit/ispit-form";
			} else {
				System.out.println("Couldn't find Ispit with id: " + id);
				return "redirect:/ispit";
			}
				
		} catch (Exception e) {
			 System.out.println(e.getMessage());
			 e.printStackTrace();
			 return "redirect:/ispit";
		}
			
	}
	
	// Metod za čuvanje (kreiranje ili izmenu)
    @PostMapping("/save")
    public String saveIspit(@ModelAttribute("dto") IspitDto dto, RedirectAttributes redir) throws Exception{
        try {
        	Long id = dto.getId();
        	if(id == null) {
        		ispitService.save(dto);
        		System.out.println("Ispit je uspesno sacuvan.");
        	} else {
        		 ispitService.update(dto);
        		 System.out.println("Ispit je uspesno azuriran.");
        	}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
	         return "redirect:/ispit";
		}
        
        return "redirect:/ispit";
    }
    
    @PostMapping("/delete")
    public String deleteIspit(@RequestParam Long id, RedirectAttributes redir) {
    	try {
			ispitService.delete(id);
			System.out.println("Ispit je uspesno obrisan.");
			return "redirect:/ispit";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/ispit";
		}
    	
    }
}
