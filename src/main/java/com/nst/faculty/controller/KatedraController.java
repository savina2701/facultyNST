package com.nst.faculty.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nst.faculty.menu.MenuService;
import com.nst.faculty.model.dto.KatedraDto;
import com.nst.faculty.service.KatedraService;

@Controller
@RequestMapping("/katedra")
public class KatedraController {

	@Autowired
	MenuService menuService;
	
	@Autowired
	KatedraService katedraService;
	
	@GetMapping
	public String getKatedraList(Model model) {
		try {
			model.addAttribute("menuItems", menuService.getMenuItems());
			model.addAttribute("katedre", katedraService.findAll());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(model);
		return "views/katedra/katedra-list";
	}
	
	//Forma za novi entitet
	@GetMapping("/add")
	public String createForm(Model model) throws IOException {
		model.addAttribute("dto", new KatedraDto());
        model.addAttribute("menuItems", menuService.getMenuItems());
        return "views/katedra/katedra-form";
	}
	
	//Forma za izmenu
	@GetMapping("/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redir) throws Exception{
		try {
			KatedraDto dto = katedraService.findDtoById(id);
			if(dto != null) {
				model.addAttribute("dto", dto);
				model.addAttribute("menuItems", menuService.getMenuItems());
				return "views/katedra/katedra-form";
			} else {
				System.out.println("Couldn't find Katedra with id: " + id);
				return "redirect:/katedra";
			}
			
		} catch (Exception e) {
			 System.out.println(e.getMessage());
			 e.printStackTrace();
	         return "redirect:/katedra";
		}
		
    }
    
    // Metod za čuvanje (kreiranje ili izmenu)
    @PostMapping("/save")
    public String saveKatedra(@ModelAttribute("dto") KatedraDto dto, RedirectAttributes redir) throws Exception{
        try {
        	Long id = dto.getId();
        	if(id == null) {
        		katedraService.save(dto);
        		System.out.println("Katedra je uspesno sacuvana.");
        	} else {
        		 katedraService.update(dto);
        		 System.out.println("Katedra je uspesno azurirana.");
        	}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
	         return "redirect:/katedra";
		}
        
        return "redirect:/katedra";
    }
    
    @PostMapping("/delete")
    public String deleteKatedra(@RequestParam Long id, RedirectAttributes redir) {
    	try {
			katedraService.delete(id);
			System.out.println("Katedra je uspesno obrisana");
			return "redirect:/katedra";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/katedra";
		}
    	
    }
    
    
    
    /*@ExceptionHandler(Exception.class)
    public String handleException(Exception e, RedirectAttributes redir) {
        e.printStackTrace(); // Štampaj stack trace
        redir.addFlashAttribute("error", "Greška: " + e.getMessage());
        return "redirect:/katedra";
    }*/
	
	
}
