package com.example.test.groups;



import com.example.test.users.user;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
public class group {

    @Id
    private String name;

    @OneToMany
    private List<user> users;


    public group(String name){
        this.name = name;
        users = new ArrayList<>();
    }

    public group(){
        users = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public List<user> getUsers(){return users;}

    public void setUsers(List<user> users){this.users = users;}

    public void addUsers(user User){
        this.users.add(User);
    }

}
