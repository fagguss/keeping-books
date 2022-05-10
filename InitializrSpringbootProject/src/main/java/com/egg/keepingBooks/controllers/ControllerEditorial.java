package com.egg.keepingBooks.controllers;

import com.egg.keepingBooks.exeptions.ErrorService;
import com.egg.keepingBooks.services.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/editorial")
public class ControllerEditorial {

    @Autowired
    private EditorialService editorialService;

    @GetMapping("/register")
    public String editorialRegister() {
        return "editorial_register";
    }

    @PostMapping("/register")
    public String form(ModelMap model, @RequestParam String name) throws ErrorService {

        try {
            editorialService.register(name);
            model.addAttribute("Exito", "La editorial fue registrada con exito!");
        }catch (Exception e){
            model.addAttribute("Error","La editorial no se a registrado correctamente"); 
        }
        
        return "editorial_register"; 
    }
}
