package com.egg.keepingBooks.controllers;

import com.egg.keepingBooks.entities.Author;
import com.egg.keepingBooks.entities.Editorial;
import com.egg.keepingBooks.exeptions.ErrorService;
import com.egg.keepingBooks.services.AuthorService;
import com.egg.keepingBooks.services.BookService;
import com.egg.keepingBooks.services.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/book")
public class ControllerBook {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private EditorialService editorialService;

    @GetMapping("/register")
    public String bookRegister() {
        return "book_register";
    }

    @PostMapping("/register")
    public String form(ModelMap model, @RequestParam String tittle, @RequestParam Long isbn,
            @RequestParam Integer year, @RequestParam Integer copies, @RequestParam Integer borrowedCopies,
            @RequestParam Integer remainingCopies, @RequestParam Author author, @RequestParam Editorial editorial)
            throws ErrorService {
        try {

            bookService.register(isbn, tittle, year, copies, borrowedCopies, remainingCopies, author, editorial);
            model.addAttribute("Exito", "El libro a sido registrado con exito!");
        }catch (Exception e){
            model.addAttribute("Error", "El libro no a sido registrado correctamente");
        }

        
        return "book_register"; 
    }
}
