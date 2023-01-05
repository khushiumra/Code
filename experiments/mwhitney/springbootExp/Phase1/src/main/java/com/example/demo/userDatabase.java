package com.example.demo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface userDatabase extends JpaRepository<User, String> {


	
	User findByEmail(String email);
	
	



}