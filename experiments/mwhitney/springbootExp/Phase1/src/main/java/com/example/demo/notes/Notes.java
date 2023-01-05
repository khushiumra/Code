package com.example.demo.notes;

import java.io.File;
import java.sql.Clob;
import java.util.List;
import java.io.FileWriter;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Notes {
	
	@Id
	@Column
	String title;
	
    private String contents;
	
	@OneToOne
	@JsonIgnore
	@Lob //denotes file
	private File file; //must be type clob because we are dealing with text files
	

	public Notes(String title) {
		this.title = title;
		
		
	}
	
	public Notes() { //blank constructor
		
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getContents() {
		return contents;
	}

	
	public void setFileName(String title) {
		 this.title = title;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public File getFile() {
		return file;
	}
	
	public void setFile(File file) {
		this.file = file;
	}

	
}
