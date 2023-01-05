package COMS309.Final.notes;


import java.sql.Clob;
import java.util.List;
import java.io.FileWriter;
import COMS309.Final.Files.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author macwhitney
 *
 */

@Entity
public class Notes {
	
	
	
	/**
	 * Id
	 * 
	 */
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;
	/**
	 * title of the notes
	 * 
	 */
	@Column(name = "Title")
	private String title;
	
	/**
	 * file object associated with File
	 */
	@OneToOne(mappedBy = "notes")
	@JsonIgnore
	private File file; //relationship with the file table which holds the actual file
	

	/**
	 * creates a new note object with the passed title 
	 * @param title
	 */
	public Notes(String title) {
		this.title = title;
		
		
	}
	
	/**
	 * blank constructor for a blank notes object
	 */
	public Notes() {
	}
	
	
	
	

	public String getTitle() {
		return title;
	}
	
	/**
	 * sets the title of the current note's object to the passed title
	 * @param title
	 */
	public void setTitle(String title) {
		 this.title = title;
	}
	
	/**
	 * returns the file object associated with this notes object
	 * @return file
	 */
	public File getFile() {
		return file;
	}
	
	/**
	 * replaces the current file object associated with the notes
	 * with the passed file object
	 * @param file
	 */
	public void setFile(File file) {
		this.file = file;
	}

	
}
