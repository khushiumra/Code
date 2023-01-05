package COMS309.Final.planners;

import COMS309.Final.events.event;
import COMS309.Final.users.user;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Madhav Dhimal
 */
@Entity
@Table(name = "CyPlanner")
public class planner {

    /**
     * Auto generated id for each planner created
     */
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    /**
     *  Name of planner
     */
    @Column(name = "name")
    private String name;

    /**
     *  User the planner belongs to
     */
    @OneToOne(cascade=CascadeType.ALL)
    private user user;

    /**
     *  List of events the planner contains
     */
    @OneToMany(mappedBy = "planner",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<event> events;

    /**
     *  Constructor for planner
     */
    public planner(){
        events = new ArrayList<>();
    }

    /**
     *  Gets the planner's id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the planner's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *  Gets the planner's name
     */
    public String getName() {
        return name;
    }

    /**
     *  Sets the planner's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *  Gets the user the planner belongs to
     */
    public user getUser() {
        return user;
    }

    /**
     *  Sets planner to a given user
     */
    public void setUser(user user) {
        this.user = user;
    }

    /**
     *  Gets a list of all events in the planner
     */
    public List<event> getEvents() {
        return events;
    }

    /**
     *  Sets the planner with a given list of events
     */
    public void setEvents(List<event> events) {
        this.events = events;
    }
}
