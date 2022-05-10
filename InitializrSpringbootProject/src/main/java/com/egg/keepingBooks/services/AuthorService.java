

package com.egg.keepingBooks.services;

import com.egg.keepingBooks.entities.Author;
import com.egg.keepingBooks.exeptions.ErrorService;
import com.egg.keepingBooks.repositories.AuthorRepo;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    
    @Autowired
    private AuthorRepo authorRepo; 
    
    @Transactional
    public void register(String name) throws ErrorService {

        validate(name);

        Author author = new Author();
        author.setName(name);

        authorRepo.save(author);

    }
    
    @Transactional
    public void modify(String id, String name, Boolean active) throws ErrorService {

        validate(name);

        Optional<Author> resp = authorRepo.findById(id);

        if (resp.isPresent()) {
            Author author= new Author();

            author.setName(name);
            author.setActive(active);
            
            authorRepo.save(author);
            
        }else {
            throw new ErrorService("No author found to modify"); 
        }

    }
    
//    @Transactional
//    public void delate(String author){
//        
//        
//        authorRepo.delete(author);
//    }
//    
     //Validaciones 
    public void validate(String name) throws ErrorService {

        if (name == null | name.isEmpty()) {
            throw new ErrorService("null or empty author name is not allowed");
        }

    }

}
