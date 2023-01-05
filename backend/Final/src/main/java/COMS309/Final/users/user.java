package COMS309.Final.users;

import COMS309.Final.planners.planner;
import COMS309.Final.userIdentities.userIdentity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The user model from which all the accounts
 * will be based on
 *
 * @author Madhav Dhimal
 */

@Entity
@Table(name = "CyUser")
public class user {

    /**
     * Auto generated id for each user created
     */
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    /**
     * User's email which will be provided
     * by the user when registering.
     * Also used by user to login
     */
    @Column(name = "email")
    private String email;

    /**
     * User's username which will be provided
     * by the user when registering
     */
    @Column(name = "username")
    private String username;

    /**
     * User's password which will be provided
     * by the user when registering.
     * Will be used by user to login
     */
    @Column(name = "password")
    private String password;

    /**
     * List of Identities for the user which
     * is mapped to one group the user is in.
     */
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<userIdentity> userIdentities;

    /**
     * User's planner which will contain
     * all the events the user has added
     */
    @OneToOne(cascade=CascadeType.ALL)
    @JsonIgnore
    private planner planner;

    /**
     * Creates a new user giving them
     * an empty array of identities
     */
    public user() {
        userIdentities = new ArrayList<>();
        planner = new planner();
    }

    /**
     * Creates a new user giving them
     * an empty array of identities
     */
    public user(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
        userIdentities = new ArrayList<>();
        planner = new planner();
    }

    /**
     * Gets the user's id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * sets the user's id with the given one
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns a List of all of the user's Identities
     * @return userIdentities
     */
    public List<userIdentity> getUserIdentities(){return userIdentities;}

    /**
     * Gets planner for the user
     * @return planner
     */
    public planner getPlanner() {
        return planner;
    }



    /**
     * Sets a planner for the user with the given one
     * @param planner
     */
    public void setPlanner(planner planner) {
        this.planner = planner;
    }

    /**
     * Gets the user's username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username with the given one
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the user's email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email with the given email
     * @param Email
     */

    public void setEmail(String Email) {
        this.email = Email;
    }

    /**
     * Gets the user's password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password with the given one
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }


}
