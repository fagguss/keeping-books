package com.egg.keepingBooks.controllers;

import com.egg.keepingBooks.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/MyBooks")
public class ControllerMyBooks {

    @Autowired
    private BookService bookService;
    
    @GetMapping("")
    public String show(ModelMap model) {
        model.addAttribute("books", bookService.listBooks()); 
        return "my_books"; 
    }
    

}
