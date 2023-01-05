package COMS309.Final.groups;


import COMS309.Final.userIdentities.userIdentity;
import COMS309.Final.userIdentities.userIdentityRepository;
import COMS309.Final.users.user;
import COMS309.Final.users.userRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Madhav Dhimal
 */
@Api(value = "groupController", tags = {"Group"})
@RestController
public class groupController {

    /**
     *  Database of Groups
     */
    @Autowired
    groupRepository GroupRepository;

    /**
     *  Database of users in group
     */
    @Autowired
    userIdentityRepository userIdentityRepository;

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
     * Gets all the groups from the user
     */
    @ApiOperation(value = "Gets all groups from the user", response = user.class)
    @GetMapping(path = "/groups/{user}")
    List<group> getAllGroups(@PathVariable String user){
        List<group> groups = new ArrayList<>();

        List<userIdentity> list = userRepository.findByEmail(user).getUserIdentities();

        for (int i = 0; i < list.size(); i++) {
            groups.add(list.get(i).getGroup());
        }

        return groups;
    }


    /**
     * Gets the group given the group's name
     */
    @ApiOperation(value = "Gets group by name", response = user.class)
    @GetMapping(path = "/groups/{name}")
    group getGroup(@PathVariable String name){
        return GroupRepository.findByName(name);
    }


    /**
     *  Creates a new group with the given group and user that created it
     */
    @ApiOperation(value = "Creates a new group", response = user.class)
    @PostMapping(path = "/groups/{user}/{group}")
    String CreateGroup(@PathVariable String user, @PathVariable String group){

        group group1 = new group();

        group1.setName(group);

        if (user.equals(null) || group.equals(null) || GroupRepository.findByName(group) != null) {
            return failure;
        }
        else {
            userIdentity identity = new userIdentity();

            identity.setRole("owner");

            user user1 = userRepository.findByEmail(user);

            identity.setUser(user1);
            identity.setGroup(group1);

            user1.getUserIdentities().add(identity);

            group1.getUserIdentities().add(identity);

            GroupRepository.save(group1);
            userIdentityRepository.save(identity);

            return success;
        }
    }

    /**
     *  Joins an existing group given the group and user.
     */
    @ApiOperation(value = "Joins a already created group", response = user.class)
    @PostMapping(path = "/groups/join")
    String JoinGroup(@PathVariable String user, @PathVariable String group) {

        if (user.equals(null) || group.equals(null)) {
            return failure;
        } else {
            userIdentity identity = new userIdentity();
            identity.setRole("member");

            user user1 = userRepository.findByEmail(user);
            group group1 = GroupRepository.findByName(group);

            identity.setUser(user1);
            identity.setGroup(group1);

            user1.getUserIdentities().add(identity);

            group1.getUserIdentities().add(identity);

            userIdentityRepository.save(identity);

            return success;
        }
    }

    @DeleteMapping(path = "/groups/delete/{user}/{group}")
    String deleteGroup(@PathVariable String user, @PathVariable String group){

        user user1 = userRepository.findByEmail(user);
        userIdentity identity = null;

        List<userIdentity> userIdentities = user1.getUserIdentities();

        for (int i = 0; i < userIdentities.size(); i++) {
            if (userIdentities.get(i).getGroup() != null && userIdentities.get(i).getGroup().getName().equals(group)) {
                identity = userIdentities.get(i);
                userIdentities.remove(identity);
                break;
            }
        }

        if(identity == null) {
            return failure;
        }
        else{
            userIdentityRepository.delete(identity);
            GroupRepository.deleteByName(group);
            return success;
        }
    }
}
