package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
	
	@Autowired
	userDatabase db;
	
	
	@GetMapping("/User/{id}")
	User getUser(@PathVariable String email) {
    return db.findByEmail(email);
	}
	@RequestMapping("/Users")
	List<User> hello() {
		return db.findAll();
	}

	@PostMapping("/register")
	User createUser(@RequestBody User x) {
	db.save(x);
	return x;
	}


	@DeleteMapping("/User/{id}")
	String deletePerson(@PathVariable String username) {
		db.deleteById(username);
		return "deleted " + username;
	}
	@GetMapping("/login")
	String login(@RequestParam String email, @RequestParam String password) {
	if (db.findByEmail(email).equals(null)) { //email
		return "username does not exits";
	}
	else {
		User temp = db.findByEmail(email);
		if (temp.getPassword().equals(password)){ //checks to see if password matches
			return "welcome to Cylearn";
		}
		else {
			return "email and password do not match, try again";
		}
	}
    
	}
	
	/*@PutMapping("/Users")
	Optional<User> updateUser(@PathVariable String id, @PathVariable String password) {
		Optional<User> old_x = db.findById(id);
		old_x.setPassword(password);
		db.save(old_x);
		return old_x;
	}
*/
}
	
