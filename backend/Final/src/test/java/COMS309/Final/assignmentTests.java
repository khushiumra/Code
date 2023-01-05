package COMS309.Final;

import COMS309.Final.Assignments.Assignments;
import COMS309.Final.Assignments.AssignmentsDatabase;
import COMS309.Final.Files.File;
import COMS309.Final.users.userRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

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

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class assignmentTests {

    @LocalServerPort int port;

    @Autowired
    AssignmentsDatabase db;

    @Before public void setUp(){
        RestAssured.port = port; RestAssured.baseURI = "http://localhost";
    }

    
    @Test public void creationTest(){
    	JSONObject assignBody = new JSONObject();
    	try {
			assignBody.put("name", "assignment1");
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	try {
			assignBody.put("date", "2022-10-10");
		} catch (JSONException e) {
		
			e.printStackTrace();
		}
    	try {
			assignBody.put("course", "coms309");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Response response = RestAssured.given().
                contentType(ContentType.JSON).
                body(assignBody.toString()).
                when().
                post("/assignments");
               
        int statusCode = (response.getStatusCode());
        assertEquals(200, statusCode);
        
        String returnString = response.getBody().asString();
        
        assertEquals(true, db.findByname("assignment1").getName().equals("assignment1"));
        assertEquals(true, db.findByname("assignment1").getCourse().equals("coms309"));
 
        assertEquals("{\"message\":\"success\"}", returnString);
        
    }

     
    
    
    @Test public void getTest(){
    	Response response = RestAssured.given().
                contentType(ContentType.JSON).
                pathParam("name", "assignment1"). when().
                get("/assignments/get/{name}").
                then().
                log().
                all().
                assertThat().
                statusCode(200).
                extract().
                response();
    	
    
    }
    
    @Test public void getdueDate(){
		JSONObject assignBody = new JSONObject();
		try {
			assignBody.put("name", "assignment1");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			assignBody.put("date", "2022-10-10");
		} catch (JSONException e) {

			e.printStackTrace();
		}
		try {
			assignBody.put("course", "coms309");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Response response = RestAssured.given().
				contentType(ContentType.JSON).
				body(assignBody.toString()).
				when().
				post("/assignments");

		int statusCode = (response.getStatusCode());
		assertEquals(200, statusCode);


    	Response response2 = RestAssured.given().
                contentType(ContentType.JSON).
                pathParam("name", "assignment1"). when().
                get("/assignments/date/{name}").
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
				delete("/assignments/delete").
				then().
				log().
				all().
				assertThat().
				statusCode(200).
				extract().
				response();
	}




	@Test public void setDate(){
    	Response response = RestAssured.given().
                contentType(ContentType.JSON).
                pathParam("name", "assignment1").
                pathParam("Date", "2022-10-4"). when().
                put("/assignments/setDate/{name}/{Date}").
                then().
                log().
                all().
                assertThat().
                statusCode(200).
                extract().
                response();
    	
    
    }
    
    @Test public void deleteTest(){
    	JSONObject assignBody = new JSONObject();
    	try {
			assignBody.put("name", "assignment2");
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	try {
			assignBody.put("date", "2022-10-10");
		} catch (JSONException e) {
		
			e.printStackTrace();
		}
    	try {
			assignBody.put("course", "coms309");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Response response = RestAssured.given().
                contentType(ContentType.JSON).
                body(assignBody.toString()).
                when().
                post("/assignments");
               
        int statusCode = (response.getStatusCode());
        assertEquals(200, statusCode);
    	
        Response response2 = RestAssured.given().
        		 contentType(ContentType.JSON).
                 pathParam("name", "assignment2"). when().
                 delete("/assignments/{name}").
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
		Assignments assignment = new Assignments();
		assertEquals(null, assignment.getName());
	}
	@Test
	public void getNameTest(){
		Assignments assignment = new Assignments("assignment1", "2022-10-10", "Coms309");
		assertEquals("assignment1", assignment.getName());
	}
	@Test
	public void getDateTest(){
		Assignments assignment = new Assignments("assignment1", "2022-10-10", "Coms309");
		assertEquals("2022-10-10", assignment.getDate());
	}
	@Test
	public void getCourseTest(){
		Assignments assignment = new Assignments("assignment1", "2022-10-10", "Coms309");
		assertEquals("Coms309", assignment.getCourse());
	}
	@Test
	public void setNameTest(){
		Assignments assignment = new Assignments();
		assignment.setName("assignment1");
		assertEquals("assignment1", assignment.getName());
	}
	@Test
	public void setDateTest(){
		Assignments assignment = new Assignments();
		assignment.setDate("2022-10-10");
		assertEquals("2022-10-10", assignment.getDate());
	}
	@Test
	public void setCourseTest(){
		Assignments assignment = new Assignments();
		assignment.setCourse("Coms309");
		assertEquals("Coms309", assignment.getCourse());
	}

}