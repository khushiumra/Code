package COMS309.Final.groups;

import COMS309.Final.userIdentities.userIdentity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Madhav Dhimal
 */
@Entity
@Table(name="CyGroup")
public class group {

    /**
     * Auto generated id for each group created
     */
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    /**
     * Group's given name
     */
    @Column(name = "name")
    private String name;

    /**
     * User's in the group
     */
    @OneToMany(mappedBy = "group",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<userIdentity> userIdentities;

    /**
     *  Creates a new group with an empty list is users in it
     */
    public group(){
        userIdentities = new ArrayList<>();
    }

    /**
     *  Gets the id of the group
     */
    public int getID() {
        return id;
    }

    /**
     *  Sets the Group's id
     */
    public void setID(int id){
        this.id = id;
    }

    /**
     * Gets the Group's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the Group's name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     *  Gets the list of user's in the group
     */
    public List<userIdentity> getUserIdentities() {
        return userIdentities;
    }

    /**
     *  Sets the list of user's in the group
     */
    public void setUserIdentities(List<userIdentity> userIdentities) {
        this.userIdentities = userIdentities;
    }

}
