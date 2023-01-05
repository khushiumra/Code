package com.example.demo;

import javax.persistence.*;
import java.util.Optional;

@Entity
public class User {
   
	@Id
    @Column(name = "email", unique = true, nullable = false)
	String email;
		
	@Column(name = "username", unique = false, nullable = false)
	String username;
	
	@Column(name = "password", unique = false, nullable = false)
	String password;
	
	@Column(name = "role", unique = false, nullable = false)
	String role;
	
		
	
	
	public User(){
		
	}
	
	public User(String email, String username, String password, String role) {
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	


	
	public String getemail() { return email; }
	public String getUsername() { return username; }
	public String getPassword() { return password; }
	public String getRole(){ return role; }
    @Override
    public String toString() {
        return email + " "+ "username" + " " 
               + password + " "
               + role;
    }
	
}
