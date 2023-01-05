package com.example.test.groups;


import com.example.test.users.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class groupController {

    @Autowired
    groupRepository GroupRepository;

    @GetMapping(path = "/groups")
    List<group> getAllGroups(){
        return GroupRepository.findAll();
    }

    @GetMapping(path = "/groups/{name}")
    List<user> getUsersFromGroup(@PathVariable String name){
        return GroupRepository.findByName(name);
    }



}
