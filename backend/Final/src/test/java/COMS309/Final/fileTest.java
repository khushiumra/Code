package COMS309.Final;

import COMS309.Final.Assignments.Assignments;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.junit.Assert.assertEquals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import COMS309.Final.notes.*;
import COMS309.Final.Files.File;
import COMS309.Final.Files.FileDatabase;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@RunWith(SpringRunner.class)
public class fileTest {

    @LocalServerPort int port;

    @Autowired
    FileDatabase db;
    
    @Before public void setUp(){
        RestAssured.port = port; RestAssured.baseURI = "http://localhost";
    }



    @Test
    public void deleteTest() {
        Response response2 = RestAssured.given().
                contentType(ContentType.JSON).
                pathParam("fileName", "test"). when().
                delete("/file/{fileName}").
                then().
                log().
                all().
                assertThat().
                statusCode(200).
                extract().
                response(); //if we get a response, either didn't exist or deleted
    }




     @Test
     public void getTest(){
    	
    	 Response response = RestAssured.given().
                 contentType(ContentType.JSON).
                 pathParam("fileName", "Test"). when().
                 get("/files/{fileName}").
                 then().
                 log().
                 all().
                 assertThat().
                 statusCode(200).
                 extract().
                 response();
     	
        
    }

     
     @Test public void setNotes(){
     	Response response = RestAssured.given().
                 contentType(ContentType.JSON).
                 pathParam("fileName", "fileString").
                 pathParam("title", "Test"). when().
                 put("/file/{fileName}/notes/{title}").
                 then().
                 log().
                 all().
                 assertThat().
                 statusCode(200).
                 extract().
                 response();
     	
     
     }
     @Test public void setAssignment(){
     	Response response = RestAssured.given().
                 contentType(ContentType.JSON).
                 pathParam("fileName", "fileString").
                 pathParam("name", "assignment2"). when().
                 put("file/{fileName}/assignments/{name}").
                 then().
                 log().
                 all().
                 assertThat().
                 statusCode(200).
                 extract().
                 response();
     	
     
     }
    @Test public void deleteAllTest() {
        Response response2 = RestAssured.given().
                contentType(ContentType.JSON).
                delete("/files/delete").
                then().
                log().
                all().
                assertThat().
                statusCode(200).
                extract().
                response();
    }


    @Test
    public void constructorTest(){
        File file = new File("test");
        assertEquals(true, file.getName().equals("test"));
    }
    @Test
    public void BlankConstructorTest(){
        File file = new File();
        assertEquals(null, file.getName());
    }
    @Test
    public void setNotesTest(){
        Notes notes = new Notes("notes");

        File file = new File();
        file.setNotes(notes);
        assertEquals(notes, file.getNotes());
    }
    @Test
    public void setAssignmentsTest(){
        Assignments assignment = new Assignments("assignment1", "2022-10-10", "Coms309");
        File file = new File();
        file.setAssignment(assignment);

        assertEquals(assignment, file.getAssignment());
    }
}