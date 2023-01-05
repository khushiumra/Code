package COMS309.Final.Assignments;


import COMS309.Final.Files.*;
import COMS309.Final.users.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author mac whitney
 *
 */
@Entity
public class Assignments {

	/**
	 * name of the assignment, name is the id
	 */
	@Id
	@Column
	private String name;
	
	/**
	 * name of the course the assignment is for
	 */
	@Column
	private String courseName;
	
	/**
	 * due date of the assignment
	 */
	@Column
	private String dueDate;
	
	/**
	 * correlates the assignment to a user
	 */
	@ManyToOne
	@JoinColumn(name = "user_Id")
	@JsonIgnore
	private user user;
	
	
	/**
	 * file object associated with assignment, 
	 */
	@OneToOne
	@JsonIgnore
	private File file; 
	
	
	/**
	 * creates a new assignment object with passed name and course name
	 * @param name
	 * @param course
	 */
	public Assignments(String name, String date, String course) {
		this.name = name;
		this.dueDate = date;
		this.courseName = course;
	}
	
	/**
	 * creates a blank assignment
	 */
	public Assignments() {
		//blank constructor
	}
	
	/**
	 * returns the name of the assignment
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * replaces current assignment name with passed name.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * returns the name of the course this assignments are four
	 * @return courseName
	 */
	public String getCourse() {
		return courseName;
	}
	
	/**
	 * replaces current course name with passed course name
	 * @param course
	 */
	public void setCourse(String course) {
		this.courseName = course;
	}
	
	/**
	 * returns assignment dueDate
	 * @return dueDate
	 */
	public String getDate() {
		return dueDate;
	}
	
	/**
	 * set's the due date for assignment
	 * @param date
	 */
	public void setDate(String date) {
		this.dueDate = date;
	}
	
	/**
	 * returns the user this assignment is associated with
	 * @return user
	 */
	public user getUser() {
		return user;
	}
	
	/**
	 * sets the user that is associated with assignment object
	 * @param user
	 */
	public void setUser(user user) {
		this.user = user;
	}
	
	/**
	 * returns the file associated with assignment
	 * @return file
	 */
	public File getFile() {
		return file;
	}
	
	/**
	 * replaces assignment file with file passed through parameter
	 * @param file
	 */
	public void setFile(File file) {
		this.file = file;
	}

	
}
