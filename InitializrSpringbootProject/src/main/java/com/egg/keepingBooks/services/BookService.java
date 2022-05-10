package com.egg.keepingBooks.services;

import com.egg.keepingBooks.entities.Book;
import com.egg.keepingBooks.entities.Author;
import com.egg.keepingBooks.entities.Editorial;
import com.egg.keepingBooks.exeptions.ErrorService;
import com.egg.keepingBooks.repositories.BookRepo;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    @Transactional
    public void register(Long isbn, String title, Integer year,
            Integer copies, Integer borrowedCopies, Integer remainingCopies,
            Author author, Editorial editorial) throws ErrorService {

        validate(isbn, title, year, copies, borrowedCopies, remainingCopies);

        Book book = new Book();
        book.setIsbn(isbn);
        book.setTittle(title);
        book.setYear(year);
        book.setCopies(copies);
        book.setBorrowedCopies(borrowedCopies);
        book.setRemainingCopies(remainingCopies);

        bookRepo.save(book);

    }

    @Transactional
    public void consult(String isbn) {

        bookRepo.findBookByIsbn(isbn);
    }

    @Transactional
    public void modify(String id, Long isbn, String title, Integer year, Integer copies, Integer borrowedCopies, Integer remainingCopies) throws ErrorService {

        validate(isbn, title, year, copies, borrowedCopies, remainingCopies);

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
    public void validate(Long isbn, String title, Integer year, Integer copies, Integer borrowedCopies, Integer remainingCopies) throws ErrorService {

        if (isbn == null) {
            throw new ErrorService("Has to be a non-null isbn");
        }

        if (title == null | title.isEmpty()) {
            throw new ErrorService("Has to be a non-null title");
        }

        if (year == null) {
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

    }

}
