package COMS309.Final;

import COMS309.Final.Files.File;
import COMS309.Final.notes.Notes;
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

import COMS309.Final.notes.NotesDatabase;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class notesTest {

    @LocalServerPort int port;
    
    @Autowired
    NotesDatabase db;

    @Before public void setUp(){
        RestAssured.port = port; RestAssured.baseURI = "http://localhost";
    }

    
    
    
    @Test public void getAllTest(){
    	Response response = RestAssured.given().
                contentType(ContentType.JSON).
                when().
                get("/notes").
                then().
                log().
                all().
                assertThat().
                statusCode(200).
                extract().
                response();
    	
    
    }
    
    @Test public void getTest(){
 

        Response response = RestAssured.given().
                contentType(ContentType.JSON).
                pathParam("title", "notes"). when().
                get("/notes/{title}").
                then().
                log().
                all().
                assertThat().
                statusCode(200).
                extract().
                response();
    	
    
    }
    
    
    
    
  @Test public void postTest(){
    	JSONObject notesBody = new JSONObject();
    	try {
			notesBody.put("title", "notes");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        Response response = RestAssured.given().
                contentType(ContentType.JSON).
                body(notesBody.toString()).
                when().
                post("/notes");
               
        int statusCode = (response.getStatusCode());
        assertEquals(200, statusCode);
        
        String returnString = response.getBody().asString();
        assertEquals(true, db.findByTitle("notes").getTitle().equals("notes"));
        
    }
  
  @Test public void deleteTest() {
      JSONObject notesBody = new JSONObject();
      try {
          notesBody.put("title", "notes1");
      } catch (JSONException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }
  	
      Response response = RestAssured.given().
              contentType(ContentType.JSON).
              body(notesBody.toString()).
              when().
              post("/notes");
             
      int statusCode = (response.getStatusCode());
      assertEquals(200, statusCode);
      
      Response response2 = RestAssured.given().
     		 contentType(ContentType.JSON).
              pathParam("notesTitles", "notes1"). when().
              delete("/notes/{notesTitles}").
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
                delete("/notes/delete").
                then().
                log().
                all().
                assertThat().
                statusCode(200).
                extract().
                response();
    }
    @Test
    public void BlankConstructorTest(){
        Notes notes = new Notes();
        assertEquals(null, notes.getTitle());
    }

    @Test
    public void setTitleTest(){
        Notes notes = new Notes("title");
        assertEquals("title", notes.getTitle());
    }
}