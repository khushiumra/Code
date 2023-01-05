package COMS309.Final;

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
public class userTests {

    @LocalServerPort
    int port;

    @Autowired
    userRepository userRepository;

    @Before public void setUp(){
        RestAssured.port = port; RestAssured.baseURI = "http://localhost";
    }


    @Test public void loginTest(){
        Response response = RestAssured.given().
                contentType(ContentType.JSON).
                pathParam("email", "bruh@iastate.edu").
                pathParam("password", "hurb"). when().
                get("/users/{email}/{password}").
                then().
                log().
                all().
                assertThat().
                statusCode(200).
                extract().
                response();
    }

    @Test
    public void CreateAndDeleteTest(){
        JSONObject assignBody = new JSONObject();
        try {
            assignBody.put("email", "test@iastate.edu");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            assignBody.put("username", "testing");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            assignBody.put("password", "TestTest");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        assertEquals(null, userRepository.findByEmail("test@iastate.edu"));

        Response response = RestAssured.given().
                contentType(ContentType.JSON).
                body(assignBody.toString()).
                when().
                post("/users");

        int statusCode = (response.getStatusCode());
        assertEquals(200, statusCode);

        String returnString = response.getBody().asString();

        assertEquals("test@iastate.edu", userRepository.findByEmail("test@iastate.edu").getEmail());
        assertEquals("testing", userRepository.findByEmail("test@iastate.edu").getUsername());
        assertEquals("TestTest", userRepository.findByEmail("test@iastate.edu").getPassword());

        assertEquals("{\"message\":\"success\"}", returnString);

        Response response2 = RestAssured.given().
                contentType(ContentType.JSON).
                pathParam("email", "test@iastate.edu").
                when().
                delete("/users/{email}").
                then().
                log().
                all().
                assertThat().
                statusCode(200).
                extract().
                response();

        assertEquals(null, userRepository.findByEmail("test@iastate.edu"));
    }

    @Test
    public void getUserByEmailTest(){
        Response response = RestAssured.given().
                contentType(ContentType.JSON).
                pathParam("email", "bruh@iastate.edu").
                when().
                get("/users/{email}").
                then().
                log().
                all().
                assertThat().
                statusCode(200).
                extract().
                response();
    }


}
