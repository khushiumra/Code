package com.example.test.users;
import com.example.test.groups.group;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="users")
public class user {

    @Id
    private String email;

    private String username;

    private String role;

    private String password;

    @ManyToOne
    @JoinColumn(name = "group")
    @JsonIgnore
    private group Group;


    public user(String username,String role, String email, String password){
        this.username = username;
        this.role = role;
        this.email = email;
        this.password = password;
    }

    public user() {

    }

    public group getGroup(){return Group;}

    public void setGroup(group Group){this.Group = Group;}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
