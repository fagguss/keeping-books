

package com.egg.keepingBooks.services;

import com.egg.keepingBooks.entities.Editorial;
import com.egg.keepingBooks.exeptions.ErrorService;
import com.egg.keepingBooks.repositories.EditorialRepo;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialService {
    
    @Autowired
    private EditorialRepo editorialRepo; 
    
     @Transactional
    public void register(String name) throws ErrorService {

        validate(name);

        Editorial editorial = new Editorial();
        editorial.setName(name);

        editorialRepo.save(editorial);

    }
    
    @Transactional
    public void modify(String id, String name, Boolean active) throws ErrorService {

        validate(name);

        Optional<Editorial> resp = editorialRepo.findById(id);

        if (resp.isPresent()) {
            Editorial editorial= new Editorial();

            editorial.setName(name);
            editorial.setActive(active);
            
            editorialRepo.save(editorial);
            
        }else {
            throw new ErrorService("No editorial found to modify"); 
        }

    }
    
    @Transactional
    public void delate(Editorial editorial){
        
        editorialRepo.delete(editorial);
    }
    
    @Transactional
    public List<Editorial> listEditorials(){
        return editorialRepo.findAll(); 
    }
    
     //Validaciones 
    public void validate(String name) throws ErrorService {

        if (name == null | name.isEmpty()) {
            throw new ErrorService("null or empty editorial name is not allowed");
        }

    }

}
