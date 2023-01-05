package com.example.demo.file;


import java.sql.Clob;
import java.util.List;
import java.io.FileWriter;

import javax.persistence.*;

import com.example.demo.notes.Notes;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class File {

	
	@Id
	private String fileName;
	
	private String extension;
	
	@JsonIgnore
	@Lob
	private Clob file;
	
	@OneToOne 
	private Notes notes;
	
/*	@OneToOne
	private Assignment assignments;
*/	
	
	public File(String fileName) {
		this.fileName = fileName; //sets filename of file
	}
	
	public File() {
		 //blank constructor
	}
	
	public String getName() {
		return fileName;
	}
	
	public void setName() {
		this.fileName = fileName;
	}
	
	public Clob file() {
		return file;
	}
	
	public void setFile(Clob file) {
		this.file = file;
	}
	
	public Notes getNotes() {
		return notes;
	}
	
	public void setNotes(Notes notes) {
		this.notes = notes;
	}
	
	/*
	public Assignment getAssignment() {
		return assignment;
	}
	
	public void setAssignment(Assignment assignment) {
		this.assignments = assignment;
	}
	*/
	public String getExtension() {
		return extension;
	}
	
	public void setExtension(String extension) {
		this.extension = extension;
	}
	
}
