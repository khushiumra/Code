package com.example.demo.file;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;

import com.example.demo.notes.Notes;
import com.example.demo.notes.NotesDatabase;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


public class FileController {

	@Autowired
    FileDatabase db;
	
	@Autowired
	NotesDatabase notesDb;
	private String success = "{\"message\":\"success\"}";
	private String failure = "{\"message\":\"failure\"}";
	
	@GetMapping(path = "/files")
    List<File> getAllFiles(){
        return db.findAll();
    }
    @GetMapping(path = "/files/{filename}")
	    File getFileByName(@PathVariable String fileName){
        return db.findByFileName(fileName);
	}
    
    @PostMapping(path = "/files")
    String createFile(@RequestParam("file") MultipartFile file, @RequestParam("fileName") String fileString) throws Exception{
    	
    	
    	if(fileString == null) {
    		return failure;
    	}
    	
    	//converts the fileString containing filename to a file object
    	ObjectMapper objectMapper = new ObjectMapper();
    	File fileName = objectMapper.readValue(fileString, File.class); 
    	
    	fileName.setExtension(file.getOriginalFilename());
    	
    	db.save(fileName); //saves filename to file db
    	
    	if(file != null) {
    		byte[] byteFile = file.getBytes();
    		String conversion = new String(byteFile); //converts bytefile to string
    		SerialClob clob = new SerialClob(conversion.toCharArray()); //converts string to a char array and makes it a CLOB
    		Clob contents = clob;
    		fileName.setFile(clob);
    		db.save(fileName);
    		
    	}
    	return success;
    }
    
    
    @PutMapping("/file/{fileName}/notes/{title}")
    String assignLaptopToUser(@PathVariable String fileName, @PathVariable String title) {
    	File file = db.findByFileName(fileName);
    	Notes notes = notesDb.findByTitle(title);
    	if (file == null || notes == null) {
    		return failure;
    	}
    	notes.setFile(fileName);
    	file.setNotes(notes);
    	db.save(file);
    	return success;
    }
    
    
	
}
