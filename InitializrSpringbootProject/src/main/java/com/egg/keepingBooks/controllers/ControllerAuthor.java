package com.egg.keepingBooks.controllers;

import com.egg.keepingBooks.exeptions.ErrorService;
import com.egg.keepingBooks.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/author")
public class ControllerAuthor {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/register")
    public String authorRegister(ModelMap view) {
        view.addAttribute("name of controller", "Keeping Books"); 

        return "author_register";
    }

    
    @PostMapping("/register")
    public String register(ModelMap model, @RequestParam String name) throws ErrorService {

        try {
            authorService.register(name);
            model.put("exito", "registro exitoso");

        }
        catch(Exception e){
            model.put("error", "el registro no se ha llevado a cabo correctamente");
        }

        return "author_register";

    }
}
