/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.keepingBooks.repositories;

import com.egg.keepingBooks.entities.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepo extends JpaRepository<Editorial, String> {
    
    @Query("SELECT e FROM Editorial e WHERE e.id=:id")
    public Editorial findAuthorById (@Param("id") String id); 
}
