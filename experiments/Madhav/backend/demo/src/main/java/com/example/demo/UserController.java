package com.example.demo;

import java.util.Arrays;
import java.util.List;

import com.example.demo.user.Instructor;
import com.example.demo.user.TA;
import com.example.demo.user.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    
    //http://localhost:8080/user/student
    @GetMapping("/user/student")
    public List<student> getStudent(){ 
        student s1234567 = new student(1234567, 20, 2, "Madhav", "mpdhimal@iastate.edu");
        student s9876564 = new student(9876564, 21, 3, "BOB", "BOB@iastate.edu");
        return Arrays.asList(s1234567, s9876564);
        
    }

    //http://localhost:8080/user/Instructor
    @GetMapping("/user/Instructor")
    public List<Instructor> getInstructors(){ 
        return Arrays.asList(new Instructor(1234567, 20, 2, "Madhav", "mpdhimal@iastate.edu"));
    }

    //http://localhost:8080/user/TA
    @GetMapping("/user/TA")
    public List<TA> getTAs(){ 
        return Arrays.asList(new TA(1234567, 20, 2, "Madhav", "mpdhimal@iastate.edu"));
    }

    
    @RequestMapping("/email")
    public String send(){
        return "";
    }
    
}
