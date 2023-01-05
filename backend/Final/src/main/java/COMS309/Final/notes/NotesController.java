package COMS309.Final.notes;

import java.io.File;
import java.io.FileWriter;
import java.sql.Clob;
import java.util.List;
import javax.sql.rowset.serial.SerialBlob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;


import COMS309.Final.Assignments.Assignments;
import COMS309.Final.Files.FileDatabase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value = "NotesController", description = "Rest APIs related to Notes creation and association")
@RestController
public class NotesController {
	
	@Autowired
    NotesDatabase db;
	
	@Autowired
	FileDatabase filesDb;
	
	private String success = "{\"message\":\"success\"}";
	private String failure = "{\"message\":\"failure\"}";
	
	@ApiOperation(value = "Get list of Notes")
	@GetMapping(path = "/notes")
    List<Notes> getAllNotes(){
        return db.findAll();
    }
	
	
	@ApiOperation(value = "Creates new notes object")
	@GetMapping(path = "/notes/{title}")
	Notes getNotes(@PathVariable String title) {
		Notes notes = db.findByTitle(title);
		return notes;
		
	}
	
	@ApiOperation(value = "Creates new notes object")
	@PostMapping(path = "/notes")
	String createNotes(@RequestBody Notes notes) {
		if (notes.equals(null)) {
			return failure;
		}
		Notes noteObj = notes;
		db.save(noteObj);
		return success;
		
	}
	

	
	@ApiOperation(value = "delete's file but not the notes or assignment associated with it")
	@DeleteMapping("/notes/{notesTitles}")
    String deleteNotes(@PathVariable String notesTitles) {
		Notes notes = db.findByTitle(notesTitles);
		if (notes == null) {
    		return failure; //checks that neither is null
    	}
		COMS309.Final.Files.File file = notes.getFile();
		if (file == null) {
			
		}
		else {
			filesDb.deleteByFileName(file.getName());
		}
		
		
		
		db.deleteByTitle(notesTitles);
		
		return success;
		
	}
	@ApiOperation(value = "delete all notes")
	@DeleteMapping("/notes/delete")
	String deleteAllNotes() {
		db.deleteAll();
		return success;
	}
	
	
}