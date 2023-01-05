package COMS309.Final.events;

import COMS309.Final.planners.planner;
import COMS309.Final.users.user;
import COMS309.Final.users.userRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @author Madhav Dhimal
 */
@Api(value = "eventController", tags = {"Event"})
@RestController
public class eventController {

    /**
     *  Database for the events
     */
    @Autowired
    eventRepository eventRepository;

    @Autowired
    userRepository userRepository;

    /**
     *  Object returned after a success
     */
    private final String success = "{\"message\":\"success\"}";

    /**
     *  Object returned after a failure
     */
    private final String failure = "{\"message\":\"failure\"}";

    /**
     *  Gets a list of all events in the user's planner
     */
    @GetMapping(path = "/events/{user}")
    List<event> getEvents(@PathVariable String user){

        return userRepository.findByEmail(user).getPlanner().getEvents();

    }


    /**
     *  Adds an event given the event body and planner the event is going to be in
     *  Checks for any overlapping events and will only add events that do not overlap with other events
     */
    @ApiOperation(value = "Adds an event to a planner", response = user.class)
    @PostMapping(path = "/events/{user}/{name}/{description}/{start}/{end}")
    String addEvent(@PathVariable String user, @PathVariable String name, @PathVariable String description, @PathVariable String start, @PathVariable String end) throws ParseException {

        if(user.equals(null) || name.equals(null) || description.equals(null) || start.equals(null) || end.equals(null)) {
            return failure;
        }

        String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

        DateFormat formatter = new SimpleDateFormat(DEFAULT_PATTERN);
        Date start1 = formatter.parse(start);
        Date end1 = formatter.parse(end);

        user user1 = userRepository.findByEmail(user);

        planner planner = user1.getPlanner();

        event event1 = new event(name, description, start1, end1);

        event1.setPlanner(planner);

        planner.getEvents().add(event1);

        if(event1 == null || planner == null){
            return failure;
        }

        //Check Dates and times
        //TODO clean it up
        for (int i = 0; i < planner.getEvents().size(); i++) {
            if(event1.getStart().before(planner.getEvents().get(i).getStart()) && event1.getEnd().after(planner.getEvents().get(i).getStart())){
                return failure;
            }
            else if(event1.getStart().before(planner.getEvents().get(i).getEnd()) && event1.getEnd().before(planner.getEvents().get(i).getEnd())){
                return failure;
            }
            else if(event1.getStart().before(planner.getEvents().get(i).getStart()) && event1.getEnd().after(planner.getEvents().get(i).getEnd())){
                return failure;
            }
            else if(event1.getStart().after(planner.getEvents().get(i).getStart()) && event1.getEnd().before(planner.getEvents().get(i).getEnd())) {
                return failure;
            }
        }


        eventRepository.save(event1);

        return success;

    }

}
