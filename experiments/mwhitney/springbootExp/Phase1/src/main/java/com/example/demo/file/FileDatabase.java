package com.example.demo.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Mac Whitney
 * 
 */ 
@Repository
public interface FileDatabase extends JpaRepository<File, String> {

	File findByFileName(String Filename);
	
}
