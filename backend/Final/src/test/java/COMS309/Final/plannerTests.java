package COMS309.Final;

import COMS309.Final.planners.plannerRepository;
import COMS309.Final.users.userRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class plannerTests {

    @LocalServerPort
    int port;

    @Autowired
    userRepository userRepository;

    @Autowired
    plannerRepository plannerRepository;

    @Before
    public void setUp(){
        RestAssured.port = port; RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void test(){

    }

    /*
    @Test
    public void createPlannerTest(){
        Response response = RestAssured.given().
                contentType(ContentType.JSON).
                pathParam("user", "foo@iastate.edu").
                pathParam("planner", "test").
                when().
                post("/planners/{user}/{planner}").
                then().
                log().
                all().
                assertThat().
                statusCode(200).
                extract().
                response();
    }
     */

}
