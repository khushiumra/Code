package com.example.demo.notes;

import java.io.File;
import java.io.FileWriter;
import java.sql.Clob;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;



@RestController
public class NotesController {
	
	@Autowired
    NotesDatabase db;
	
	private String success = "{\"message\":\"success\"}";
	private String failure = "{\"message\":\"failure\"}";
	
	@GetMapping(path = "/notes")
    List<Notes> getAllNotes(){
        return db.findAll();
    }
	
	@PostMapping(path = "/notes")
	String createNotes(@RequestBody Notes notes) {
		if (notes.equals(null)) {
			return failure;
		}
		db.save(notes);
		return success;
		
	}
	
	@PutMapping(path = "/notes/{title}")
	Notes updateNotes(@PathVariable String title, @RequestParam String contents) {
		Notes notes =  db.findByTitle(title);
		if(notes == null) {
			return null;
		}
		String fileName = title + "txt";
		
		try{
			File file = new File(fileName);
			FileWriter writer = new FileWriter(fileName);
			writer.write(contents);
			writer.close();
		}
		
		catch(Exception e) {
		
		}
		
	
	
	}
}