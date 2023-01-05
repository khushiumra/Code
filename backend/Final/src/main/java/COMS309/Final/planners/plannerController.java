package COMS309.Final.planners;

import COMS309.Final.events.eventRepository;
import COMS309.Final.users.userRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author Madhav Dhimal
 */
@Api(value = "plannerController", tags = {"Planner"})
@RestController
public class plannerController {

    /**
     * Planners database
     */
    @Autowired
    plannerRepository plannerRepository;

    /**
     * Events database
     */
    @Autowired
    eventRepository eventRepository;

    @Autowired
    userRepository userRepository;

    /**
     * Object returned after a success
     */
    private String success = "{\"message\":\"success\"}";

    /**
     * Object returned after a failure
     */
    private String failure = "{\"message\":\"failure\"}";

    /*
     * Gets all the planners form the database
     *
    @ApiOperation(value = "Gets all planners from the database", response = user.class)
    @GetMapping(path = "/planners")
    List<planner> getPlanners(){
        return plannerRepository.findAll();
    }
    */

    /*
     * Adds a planner given a planner body and user the planner will belong to
     *
    @ApiOperation(value = "Sets planner to user", response = user.class)
    @PostMapping(path = "/planners/{user}/{planner}")
    String addPlanner(@PathVariable String user, @PathVariable String planner){
        if(user == null || planner == null){
            return failure;
        }
        else{
            user user1 = userRepository.findByEmail(user);

            planner planner1 = new planner();

            planner1.setName(planner);

            if(user1.getPlanner() == null){
                user1.setPlanner(planner1);
                planner1.setUser(user1);
                plannerRepository.save(planner1);
            }

            return success;
        }

    }
    */
}
