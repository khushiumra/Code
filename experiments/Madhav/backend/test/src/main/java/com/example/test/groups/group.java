package com.example.test.groups;



import com.example.test.users.user;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class group {

    @Id
    private String name;

    private user User;

    @OneToMany
    @JoinColumn(name = "users")
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

    public void addUsers(user User){
        this.users.add(User);
    }

}
