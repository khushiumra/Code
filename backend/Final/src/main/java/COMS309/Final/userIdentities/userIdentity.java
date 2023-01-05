package COMS309.Final.userIdentities;


import COMS309.Final.groups.group;
import COMS309.Final.users.user;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;

/**
 * @author Madhav Dhimal
 */

@Entity
@Table(name = "CyUserIdentity")
public class userIdentity {

    /**
     * Auto generated id for each userIdentity created
     */
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    /**
     * User's role
     */
    @Column(name = "Role")
    private String role;

    /**
     * User the user identity belongs to
     */
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "user_id")
    private user user;

    /**
     * Group the user is in
     */
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "group_id")
    private group group;

    /**
     * Constructs a new user identity
     */
    public userIdentity(){

    }

    /**
     * Gets the user identity's id
     */
    public int getId(){
        return id;
    }

    /**
     *  Sets the user identity's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *  Sets the user identity to a given user
     */
    public void setUser(user user) {
        this.user = user;
    }

    /**
     *  Gets the user
     */
    public user getUser() {
        return user;
    }

    /**
     *  Sets the group
     */
    public void setGroup(group group) {
        this.group = group;
    }

    /**
     *  Gets the group
     */
    public group getGroup() {
        return group;
    }

    /**
     *  Gets the user's role
     */
    public String getRole() {
        return role;
    }

    /**
     *  Sets the user's role
     */
    public void setRole(String role) {
        this.role = role;
    }


}
