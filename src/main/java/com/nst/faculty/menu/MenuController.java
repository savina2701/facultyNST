package com.nst.faculty.menu;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

	@Autowired
	MenuService menuService;
	
	@GetMapping("/")
    public String homePage(Model model) {
        try {
            List<MenuItem> menuItems = menuService.getMenuItems();
            model.addAttribute("menuItems", menuItems);
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Došlo je do greške prilikom učitavanja menija.");
            System.out.println("Doslo je do greske");
        }
        
        return "layout/layout";		//pre je bilo return index.html
	}
}
