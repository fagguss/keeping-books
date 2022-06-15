package com.egg.keepingBooks.services;

import com.egg.keepingBooks.entities.Book;
import com.egg.keepingBooks.entities.Author;
import com.egg.keepingBooks.entities.Editorial;
import com.egg.keepingBooks.exeptions.ErrorService;
import com.egg.keepingBooks.repositories.AuthorRepo;
import com.egg.keepingBooks.repositories.BookRepo;
import com.egg.keepingBooks.repositories.EditorialRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;
    
    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    private EditorialRepo editorialRepo;

    @Transactional
    public Book register(Long isbn, String title, Integer year,
            Integer copies, Integer borrowedCopies, Integer remainingCopies,
            String idAuthor, String idEditorial) throws ErrorService {

        validate(isbn, title, year, copies, borrowedCopies, remainingCopies, idAuthor, idEditorial);

        Author author=authorRepo.findAuthorById(idAuthor); 
        Editorial editorial=editorialRepo.findAuthorById(idEditorial); 
        Book book = new Book();
        
        book.setIsbn(isbn);
        book.setTittle(title);
        book.setYear(year);
        book.setCopies(copies);
        book.setBorrowedCopies(borrowedCopies);
        book.setRemainingCopies(remainingCopies);
        book.setActive(true);
        book.setAuthor(author);
        book.setEditorial(editorial);

        return bookRepo.save(book);

    }

//    @Transactional
//    public void consult(String isbn) {
//
//        bookRepo.findBookByIsbn(isbn);
//    }
    
    
    @Transactional
    public List<Book> listBooks() {
        return bookRepo.findAll();
    }

    @Transactional
    public void modify(String id, Long isbn, String title, Integer year, Integer copies,
            Integer borrowedCopies, Integer remainingCopies, String author, String editorial) throws ErrorService {

        validate(isbn, title, year, copies, borrowedCopies, remainingCopies,author, editorial);

        Optional<Book> resp = bookRepo.findById(id);

        if (resp.isPresent()) {
            Book book = new Book();

            book.setIsbn(isbn);
            book.setTittle(title);
            book.setYear(year);
            book.setCopies(copies);
            book.setBorrowedCopies(borrowedCopies);
            book.setRemainingCopies(remainingCopies);

            bookRepo.save(book);

        } else {
            throw new ErrorService("No book found to modify");
        }

    }

    @Transactional
    public void delate(Book book) {

        bookRepo.delete(book);
    }

    //Validaciones 
    public void validate(Long isbn, String title, Integer year, Integer copies,
            Integer borrowedCopies, Integer remainingCopies, String idAuthor, String idEditorial) throws ErrorService {

        if (isbn == null) {
            throw new ErrorService("Has to be a non-null isbn");
        }

        if (title == null | title.isEmpty()) {
            throw new ErrorService("Has to be a non-null title");
        }

        if (year == null ) {
            throw new ErrorService("Has to be a non-null year");
        }

        if (copies == null) {
            throw new ErrorService("Has to be a non-null copies");
        }

        if (borrowedCopies == null) {
            throw new ErrorService("Has to be a non-null borrorwed copies");
        }

        if (remainingCopies == null) {
            throw new ErrorService("Has to be a non-null remaining copies");
        }

        if (idAuthor == null | idAuthor.isEmpty()) {
            throw new ErrorService("Author no designado");
        }
        
        if (idEditorial == null | idEditorial.isEmpty()) {
            throw new ErrorService("Editorial no designada");
        }
    }

}
