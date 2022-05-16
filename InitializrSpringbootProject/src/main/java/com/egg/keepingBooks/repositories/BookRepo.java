
package com.egg.keepingBooks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.egg.keepingBooks.entities.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface BookRepo extends JpaRepository<Book, String>{
    
    @Query("SELECT b FROM Book b WHERE b.id=:id")
    public Book findBookById(@Param("id") String id); 
    
}
