package COMS309.Final.Files;


import java.sql.Clob;
import java.util.List;
import java.io.FileWriter;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;

import COMS309.Final.notes.Notes;
import COMS309.Final.Assignments.*;



/**
 * 
 * @author macwh
 *
 */

/**
 * 
 * Class is to create a file of the lob type to hold in the database
 *
 */
@Entity
public class File {

	/**
	 * holds the name of the file, used as ID.
	 */
	@Id
	private String fileName;
	
	
	/**
	 * holds the file extension
	 */
	@Column
	private String extension;
	
	
	/**
	 * holds the actual text file.
	 */
	@Column
	@Lob
	private Clob file; //actual file that gets uploaded
	
	/**
	 * Maps file to notes for classification
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "notes_id")
	private Notes notes; //relationship with notes
	
	/**
	 * Maps file to Assignments for classification
	 */
	@OneToOne
    @JoinColumn(name = "assignments_id")
	private Assignments assignment;

	
	
	/**
	 * creates a new file 
	 * @param fileName
	 * 	parameter to be used as a name.
	 */
	public File(String fileName) {
		this.fileName = fileName; //sets filename of file
	}
	
	/**
	 * creates a file object with nothing assigned
	 */
	public File() {
		 //blank constructor
	}
	
	/**
	 *gets the file name 
	 * @return filename
	 */
	public String getName() {
		return fileName;
	}
	
	/**
	 * changes name of file to passed parameter
	 * @param fileName
	 */
	public void setName(String fileName) {
		this.fileName = fileName;
	}
	
	
	/**
	 * returns the clob file (text)
	 * @return file
	 */
	public Clob file() {
		return file;
	}

	/**
	 * replaces file with the passed text file
	 * @param file
	 */
	public void setFile(Clob file) {
		this.file = file;
	}
	
	/**
	 * if notes have been assigned then returns the note object associated with file
	 * @return Notes
	 */
	public Notes getNotes() {
		return notes;
	}
	
	/**
	 * replaces the current notes object with the passed notes object
	 * @param notes
	 */
	public void setNotes(Notes notes) {
		this.notes = notes;
	}
	
	/**
	 * if assignment has been assigned then returns the assignment object associated with file
	 * @return assignment
	 */
	public Assignments getAssignment() {
		return assignment;
	}
	
	/**
	 * sets passed assignment object as current file's assignment object
	 * @param assignment
	 */
	public void setAssignment(Assignments assignment) {
		this.assignment = assignment;
	}
	

	
	/**
	 * replaces current extension with passed extension.
	 * @param extension
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}
	
}
