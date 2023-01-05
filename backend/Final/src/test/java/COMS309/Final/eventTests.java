package COMS309.Final;

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
public class eventTests {

    @LocalServerPort
    int port;

    @Before
    public void setUp(){
        RestAssured.port = port; RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void test(){

    }


    @Test
    public void addEventTest(){

        Response response2 = RestAssured.given().
                contentType(ContentType.JSON).
                pathParam("user", "foo@iastate.edu").
                pathParam("name", "exam1").
                pathParam("description","exam event").
                pathParam("start","2020-01-01 10:50:00").
                pathParam("end","2020-01-01 11:50:00").
                when().
                post("/events/{user}/{name}/{description}/{start}/{end}").
                then().
                log().
                all().
                assertThat().
                statusCode(200).
                extract().
                response();


    }


}
