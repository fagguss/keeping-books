package com.egg.keepingBooks.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ControllerIndex {

    @GetMapping("")
    public String index(ModelMap view) {
        view.addAttribute("name of controller", "Keeping Books"); 

        return "index";
    }
    
//    @GetMapping("/author_register")
//    public String authorRegister() {
//        return "author_register";
//    }
//    
//    @GetMapping("/editorial_register")
//    public String editorialRegister() {
//        return "editorial_register";
//    }
    
    
}
