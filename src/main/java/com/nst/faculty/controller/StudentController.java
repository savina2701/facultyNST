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
import com.nst.faculty.model.dto.StudentDto;
import com.nst.faculty.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	MenuService menuService;
	
	@Autowired
	StudentService studentService;
	
	@GetMapping
	public String getKatedraList(Model model) {
		try {
			model.addAttribute("menuItems", menuService.getMenuItems());
			model.addAttribute("studenti", studentService.findAll());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(model);
		return "views/student/student-list";
	}
	
	//Forma za novi entitet
	@GetMapping("/add")
	public String createForm(Model model) throws IOException {
		model.addAttribute("dto", new StudentDto());
	    model.addAttribute("menuItems", menuService.getMenuItems());
	    return "views/student/student-form";
	}
	
	//Forma za izmenu
	@GetMapping("/{id}")
	public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redir) throws Exception{
		try {
			StudentDto dto = studentService.findDtoById(id);
			if(dto != null) {
				model.addAttribute("dto", dto);
				model.addAttribute("menuItems", menuService.getMenuItems());
				return "views/student/student-form";
			} else {
				System.out.println("Couldn't find Student with id: " + id);
				return "redirect:/student";
			}
				
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		    return "redirect:/student";
		}
			
	}
	
	// Metod za čuvanje (kreiranje ili izmenu)
    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("dto") StudentDto dto, RedirectAttributes redir) throws Exception{
        try {
        	Long id = dto.getId();
        	if(id == null) {
        		studentService.save(dto);
        		System.out.println("Student je uspesno sacuvan.");
        	} else {
        		 studentService.update(dto);
        		 System.out.println("Student je uspesno azuriran.");
        	}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
	         return "redirect:/student";
		}
        
        return "redirect:/student";
    }
    
    @PostMapping("/delete")
    public String deleteStudent(@RequestParam Long id, RedirectAttributes redir) {
    	try {
			studentService.delete(id);
			System.out.println("Student je uspesno obrisan");
			return "redirect:/student";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/student";
		}
    	
    }
	
    
    
}
