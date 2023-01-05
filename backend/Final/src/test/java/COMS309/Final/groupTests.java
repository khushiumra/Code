package COMS309.Final;


import COMS309.Final.groups.groupRepository;
import COMS309.Final.users.userRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class groupTests {

    @LocalServerPort
    int port;

    @Autowired
    userRepository userRepository;

    @Autowired
    groupRepository groupRepository;

    @Before
    public void setUp(){
        RestAssured.port = port; RestAssured.baseURI = "http://localhost";
    }


    @Test
    public void createAndDeleteGroup(){

        assertEquals(null, groupRepository.findByName("test"));

        Response response = RestAssured.given().
                contentType(ContentType.JSON).
                pathParam("user", "foo@iastate.edu").
                pathParam("group", "test").
                when().
                post("/groups/{user}/{group}").
                then().
                log().
                all().
                assertThat().
                statusCode(200).
                extract().
                response();

        assertEquals("test", groupRepository.findByName("test").getName());

        Response response2 = RestAssured.given().
                contentType(ContentType.JSON).
                pathParam("user", "foo@iastate.edu").
                pathParam("group", "test").
                when().
                delete("/groups/delete/{user}/{group}").
                then().
                log().
                all().
                assertThat().
                statusCode(200).
                extract().
                response();

        assertEquals(null, groupRepository.findByName("test"));

    }

    @Test
    public void JoinGroup(){

    }

}
