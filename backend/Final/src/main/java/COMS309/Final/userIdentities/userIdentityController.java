package COMS309.Final.userIdentities;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;



/**
 * @author Madhav Dhimal
 */
@Api(value = "userIdentityController", tags = {"User Identity"})
@RestController
public class userIdentityController {

    /**
     *  database for the userIdentities
     */
    @Autowired
    userIdentityRepository userIdentityRepository;

}
