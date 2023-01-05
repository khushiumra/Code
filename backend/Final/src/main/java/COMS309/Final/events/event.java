package COMS309.Final.events;

import COMS309.Final.planners.planner;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Madhav Dhimal
 */
@Entity
@Table(name = "CyEvent")
public class event {

    /**
     * Auto generated id for each event created
     */
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    /**
     *  Name of event
     */
    @Column(name = "name")
    private String name;

    /**
     *  Description of event
     */
    @Column(name = "description")
    private String description;

    /**
     *  Start date of event
     */
    @Column(name = "start")
    private Date start;

    /**
     *  End date of event
     */
    @Column(name = "end")
    private Date end;

    /**
     *  Planner the event is in
     */
    @ManyToOne
    @JsonIgnore
    private planner planner;

    /**
     *  Constructor for event
     */
    public event(){
    }

    public event(String name,String description,Date start,Date end){
        this.name = name;
        this.description = description;
        this.start = start;
        this.end = end;
    }



    /**
     *  Gets the id of the event
     */
    public int getId() {
        return id;
    }

    /**
     *  Sets the id of the event
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *  Gets the name of the event
     */
    public String getName() {
        return name;
    }

    /**
     *  Sets the name of the event
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *  Gets the description of the event
     */
    public String getDescription() {
        return description;
    }

    /**
     *  Sets teh description of the event
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *  Gets the start date of the event
     */
    public Date getStart() {
        return start;
    }

    /**
     *  Sets the start date of the event
     */
    public void setStart(Date start) {
        this.start = start;
    }

    /**
     *  Gets the end date of the event
     */
    public Date getEnd() {
        return end;
    }

    /**
     *  Sets the end date of the event
     */
    public void setEnd(Date end) {
        this.end = end;
    }

    /**
     *  Gets the planner the event is in
     */
    public planner GetPlanner() {
        return planner;
    }

    /**
     *  Sets the planner the event is in
     */
    public void setPlanner(planner planner) {
        this.planner = planner;
    }
}
