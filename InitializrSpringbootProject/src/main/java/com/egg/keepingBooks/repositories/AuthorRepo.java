
package com.egg.keepingBooks.repositories;

import com.egg.keepingBooks.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends JpaRepository<Author, String>{
    
    @Query("SELECT a FROM Author a WHERE a.id=:id")
    public Author findAuthorById(@Param("id") String id); 
    
}
