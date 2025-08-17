package com.nst.faculty.menu;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MenuService {
	/*Ovaj servis cita main-menu.conf fajl i koristi Jackson biblioteku za deserijalizaciju JSON podataka 
	  u Java objekte*/
	
    private static final String MENU_CONFIG_PATH = "src/main/resources/main-menu.conf";

    public List<MenuItem> getMenuItems() throws IOException {
        // Kreiraj ObjectMapper objekat koji je odgovoran za deserializaciju JSON-a u Java objekte
        ObjectMapper objectMapper = new ObjectMapper();

        // Učitaj JSON fajl iz datog puta i mapiraj ga u MenuConfig objekat
        MenuConfig menuConfig = objectMapper.readValue(new File(MENU_CONFIG_PATH), MenuConfig.class);
        
        // Vrati listu stavki menija
        return menuConfig.getMenuItems();
    }
	
	
	
}
