package COMS309.Final.Files;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;

import COMS309.Final.Assignments.*;
import COMS309.Final.notes.Notes;
import COMS309.Final.notes.NotesDatabase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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

@Api(value = "FileController", description = "Rest APIs related to file creation and association")
@RestController
public class FileController {

	@Autowired
    FileDatabase db;
	
	@Autowired
	NotesDatabase notesDb;
	
	@Autowired 
	AssignmentsDatabase assignmentsDb;
	
	private String success = "{\"message\":\"success\"}";
	private String failure = "{\"message\":\"failure\"}";
	
	@ApiOperation(value = "Get list of Files")
	@GetMapping(path = "/files")
    List<File> getAllFiles(){
        return db.findAll();
    }
	
	@ApiOperation(value = "Get file by filename")
    @GetMapping(path = "/files/{fileName}")
	    File getFileByName(@PathVariable String fileName){
        return db.findByFileName(fileName);
	}


	@ApiOperation(value = "create a new file with type CLOB")
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
    
	@ApiOperation(value = "sets the file as a notes file")
    @PutMapping("/file/{fileName}/notes/{title}")
    String assignFileToNotes(@PathVariable String fileName, @PathVariable String title) {
    	File file = db.findByFileName(fileName);
    	Notes notes = notesDb.findByTitle(title);
    	if (file == null || notes == null) {
    		return failure;
    	}
    	notes.setFile(file);
    	file.setNotes(notes);
    	db.save(file);
    	return success;
    }
    
	@ApiOperation(value = "set file as an assignment files")
    @PutMapping("/file/{fileName}/assignments/{name}")
    String assignFileToAssignment(@PathVariable String fileName, @PathVariable String name) {
    	File file = db.findByFileName(fileName);  //finds file
    	Assignments assignment = assignmentsDb.findByname(name); //finds assignment
    	if (file == null || assignment == null) {
    		return failure; //checks that neither is null
    	}
    	assignment.setFile(file); //sets the file
    	file.setAssignment(assignment); //sets assignment
    	db.save(file); //save
    	return success;
    }
    
	@ApiOperation(value = "delete's file but not the notes or assignment associated with it")
	@DeleteMapping("/file/{fileName}")
    String deleteFile(@PathVariable String fileName) {
		File file = db.findByFileName(fileName);
		if (file == null) {
    		return failure; //checks that neither is null
    	}
		
		db.deleteByFileName(fileName);
		return success;
		
	}
	@ApiOperation(value = "delete all files")
	@DeleteMapping("/files/delete")
	String deleteAllNotes() {
		db.deleteAll();
		return success;
	}
	
}
