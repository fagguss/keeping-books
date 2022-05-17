

package com.egg.keepingBooks.controllers;

import com.egg.keepingBooks.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authors")
public class ControllerMyAuthors {

    @Autowired
    private AuthorService authorService; 
    
    @GetMapping("")
    public String show(ModelMap model){
        model.addAttribute("authors",authorService.listAuthors());
        return "my_authors"; 
    }
}
