package COMS309.Final.users;

import java.util.List;

import COMS309.Final.groups.group;
import COMS309.Final.planners.planner;
import COMS309.Final.planners.plannerRepository;
import COMS309.Final.userIdentities.userIdentity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.events.Event;


/**
 * @author Madhav Dhimal
 */
@Api(value = "UserController", tags = {"User"})
@RestController
public class userController {

    /**
     * Database where the user data is stored
     */
    @Autowired
    userRepository UserRepository;

    @Autowired
    plannerRepository plannerRepository;

    /**
     * Object returned after a success
     */
    private String success = "{\"message\":\"success\"}";

    /**
     *  Object returned after a failure
     */
    private String failure = "{\"message\":\"failure\"}";

    /*
     * Finds all the users in the database
     *
     @ApiOperation(value = "Gets all users")
    @GetMapping(path = "/users")
    List<user> getUsers(){
        return UserRepository.findAll();
    }
    /
     */

    /**
     * Returns the user given their email
     */
    @ApiOperation(value = "Gets user by email")
    @GetMapping(path = "/users/{email}")
    user getUserByEmail(@PathVariable String email){
        return UserRepository.findByEmail(email);
    }

    /**
     * Creates a user and adds it to the user database
     * The new user must be given as an Object
     * If user object's email is already used for another user
     * then user will not be added into the database
     */
    @ApiOperation(value = "Creates a new user")
    @PostMapping(path = "/users")
    String createUser(@RequestBody user User) {
        if (User == null) {
            return failure;
        } else if (UserRepository.findByEmail(User.getEmail()) != null) {
            return failure;
        } else {
            planner planner = new planner();
            planner.setUser(User);
            planner.setName(User.getUsername()+"'s Planner");
            User.setPlanner(planner);
            plannerRepository.save(planner);
            UserRepository.save(User);
            return success;
        }
    }

    /**
     * Method to login to the application with a given email and password
     * if the given email or password do not match any in the database, then
     * they will not be login in
     */
    @ApiOperation(value = "login the user")
    @GetMapping(path = "/users/{email}/{password}")
    String login(@PathVariable String email, @PathVariable String password){
        if(UserRepository.findByEmail(email) == null){
            return failure;
        }
        else if(!UserRepository.findByEmail(email).getPassword().equals(password)){
            return failure;
        }
        else{
            return success;
        }
    }

    /**
     * Given an email of the user, the user will be deleted from the database
     */
    @ApiOperation(value = "Deletes a user given their email")
    @DeleteMapping(path = "/users/{email}")
    String deleteUserByEmail(@PathVariable String email){
        if(UserRepository.findByEmail(email) == null){
            return failure;
        }
        else {
            UserRepository.deleteByEmail(email);
        }
        return success;
    }

    /*
     * Will delete all users in the database
     *
    @ApiOperation(value = "Delete all users")
    @DeleteMapping(path = "/users")
    String deleteUsers(){
        UserRepository.deleteAll();
        return success;
    }
    */
}
