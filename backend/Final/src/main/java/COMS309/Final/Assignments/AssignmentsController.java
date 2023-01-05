package COMS309.Final.Assignments;

import java.io.FileWriter;
import java.sql.Clob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.sql.rowset.serial.SerialBlob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;

import COMS309.Final.Files.File;
import COMS309.Final.Files.FileDatabase;
import COMS309.Final.users.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "AssignmentController", description = "Rest APIs related to assinment creation and association")
@RestController
public class AssignmentsController {

	@Autowired
    AssignmentsDatabase db;
	
	@Autowired
	userRepository userRepository;
	
	@Autowired
	FileDatabase fileDb;
	
	private String success = "{\"message\":\"success\"}";
	private String failure = "{\"message\":\"failure\"}";
	
	@ApiOperation(value = "Get list of Assignments")
	@GetMapping(path = "/assignments")
    List<Assignments> getAllAssignments(){
        return db.findAll();
    }
	@ApiOperation(value = "Get assignment")
	@GetMapping(path = "/assignments/get/{name}")
    Assignments getAssignments(@PathVariable String name){
        return db.findByname(name);
    }
	
	@ApiOperation(value = "Get assignment name")
	@GetMapping(path = "/assignments/name/{name}")
    String getname(@PathVariable String name){
        Assignments temp = db.findByname(name);
        return temp.getName();
    }
	
	@ApiOperation(value = "Get assignment date")
	@GetMapping(path = "/assignments/date/{name}")
    String getAssignmentdate(@PathVariable String name){
        Assignments temp = db.findByname(name);
        String date = temp.getDate();
       
        return date;
    }
	
	
	@ApiOperation(value = "Creates a new assignment object")
	@PostMapping(path = "/assignments")
	String createAssignment(@RequestBody Assignments assignment) { //creates a new assignment
		if (assignment == null) {
			return failure; //checks if null
		}
		db.save(assignment); //saves to db
		return success;
		
	}
	@ApiOperation(value = "sets due date for assignment")
	@PutMapping(path = "/assignments/setDate/{name}/{Date}")
	String setDueDate(@PathVariable String name, @PathVariable String Date) { //used to set date on assignments
		if(Date.equals(null)) {
			return failure; //checks to see if date is null
		}
		Assignments dated = db.findByname(name); //finds assignmnet to be assigned a date
		
		
		dated.setDate(Date);
		
		return success;
		
		}
	
	@ApiOperation(value = "Get assigns the assignment for a user")
	@PutMapping(path = "/setDate/{name}")
	String setUser(@PathVariable String name, @RequestParam String email) { //used to set user for assignments
		if(email.equals(null)) {
			return failure; //checks to see if email is null
		}
		Assignments assignment = db.findByname(name); //finds assignmnet to be assigned a user
		
		user user = userRepository.findByEmail(email);
		
		assignment.setUser(user);
	
		
		return success;


		}
	
	@ApiOperation(value = "delete's file but not the notes or assignment associated with it")
	@DeleteMapping("/assignments/{name}")
    String deleteAssignment(@PathVariable String name) {
		Assignments assignment = db.findByname(name);
		if (assignment == null) {
    		return failure; //checks that neither is null
    	}
		File file = assignment.getFile();
		if (file == null) {
			
		}
		else {
			fileDb.deleteById(file.getName());
		}
		
		
		
		db.deleteByname(name);
		
		return success;
		
	}
	@ApiOperation(value = "deletes all assignments")
	@DeleteMapping("/assignments/delete")
	String deleteAllNotes() {
		db.deleteAll();
		return success;
	}
	
	}

