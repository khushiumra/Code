package com.example.demo.notes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Mac Whitney
 * 
 */ 
@Repository
public interface NotesDatabase extends JpaRepository<Notes, String> {


	Notes findByTitle(String title);
	
}
