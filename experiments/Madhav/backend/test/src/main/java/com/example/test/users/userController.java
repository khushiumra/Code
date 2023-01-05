package com.example.test.users;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.events.Event;

@RestController
public class userController {

    @Autowired
    userRepository UserRepository;

    @GetMapping(path = "/users")
    List<user> getUsers(){
        return UserRepository.findAll();
    }

    @GetMapping(path = "/users/{email}")
    user getUserByEmail(@PathVariable String email){
        return UserRepository.findByEmail(email);
    }

    @PostMapping(path = "/users")
    String createUser(@RequestBody user User) {
        if (User == null) {
            return "not enough information";
        } else if (UserRepository.findByEmail(User.getEmail()) != null) {
            return "user already exists";
        } else {
            UserRepository.save(User);
            return "user created";
        }
    }

    @GetMapping(path = "/users/{email}/{password}")
    String login(@PathVariable String email, @PathVariable String password){
        if(UserRepository.findByEmail(email) == null){
            return "account does not exist";
        }
        else if(!UserRepository.findByEmail(email).getPassword().equals(password)){
            return "password does not match";
        }
        else{
            return "Logged in";
        }
    }

    @DeleteMapping(path = "/users/{email}")
    String deleteUserByEmail(@PathVariable String email){
        if(UserRepository.findByEmail(email) == null){
            return "account does not exist";
        }
        else {
            UserRepository.deleteByEmail(email);
        }
        return "deleted " + email + "'s account";
    }

    @DeleteMapping(path = "/users")
    String deleteUsers(){
        UserRepository.deleteAll();
        return "Deleted all users";
    }
}
