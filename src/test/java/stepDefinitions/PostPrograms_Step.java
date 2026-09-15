package stepDefinitions;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import base.BaseClass;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.JsonTestData;
import pojo.ProgramRequest;

import pojo.TestcaseWrapper;
import utilities.JsonReader;
import utilities.ScenarioContext;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;

public class PostPrograms_Step extends BaseClass {
	
	Response response;
    RequestSpecification request = requestWithoutAuth();
	String endpoint;
    JsonTestData testData;
	private static final Logger log = LoggerFactory.getLogger(PostPrograms_Step.class);
	

@Given("Admin creates POST request with valid credentials in program API")
public void admin_creates_post_request_with_valid_credentials_in_program_api() {
	
	JsonTestData postData = getTestData()
			.getPostRequest()
			.get(0);
		
	 endpoint = postData.getEndpoint();
	 
	  if (endpoint == null || endpoint.trim().isEmpty()) {
	        throw new IllegalStateException(
	            "Endpoint missing from TestDataforLMS.json");
	    }

	System.out.println("ENDPOINT = [" + endpoint + "]");
	ProgramRequest programRequest = postData.getProgramRequest();
	
	  System.out.println("PROGRAM REQUEST = " + programRequest);
	
	request =createRequest()
			.body(programRequest);
	  
	    log.info("Program Name: {}", programRequest.getprogramName());
	    log.info("Program Description: {}",
	    		programRequest.getprogramDescription());
	    log.info("Program Status: {}",
	    		programRequest.getprogramStatus());
	
}

@Then("Admin receives {int} Created Status with response body in program API")
public void admin_receives_created_status_with_response_body_in_program_api(Integer expectedStatusCode){
	log.info("Response Body:\n{}", response.asString());
	
	
	if (response.getStatusCode() == testData.getexpectedStatusCode()) {
		int programId = response.jsonPath().getInt("programId");
		String programName = response.jsonPath().getString("programName");
		ScenarioContext.set("programId", programId);
		ScenarioContext.set("programName", programName);
	}
	assertEquals(response.getStatusCode(),testData.getexpectedStatusCode());
     response.then().statusCode(expectedStatusCode);
	  log.info("Expected Status Code: {}", expectedStatusCode);
      log.info("Actual Status Code: {}", response.statusCode());
}

@Given("Admin creates POST Request with only mandatory field in program API")
public void admin_creates_post_request_with_only_mandatory_field_in_program_api() {
	
	endpoint = "/saveprogram";
	ProgramRequest programRequest = new ProgramRequest();
	request = createRequest()
			.body(programRequest);
    	System.out.println("\n========== POST REQUEST ==========");
	    System.out.println("Endpoint       : " + endpoint);
	    
	    System.out.println("Program Name   : " + programRequest.getprogramName());
	    System.out.println("Program Status : " + programRequest.getprogramStatus());
    
}

@Given("Admin creates POST Request with program description length between {int} and {int} characters in program API")
public void admin_creates_post_request_with_program_description_length_between_and_characters_in_program_api(Integer int1, Integer int2) {
	
	log.info("Creating POST request with program description length between {} and {} characters", int1, int2);
	String programDescription = "A".repeat(int1);
	String requestBody = "{" + "\"programName\":\"TestProgram\"," 
			+ "\"programDescription\":\"" + programDescription + "\","
			+ "\"programStatus\":\"Active\"" + "}";
	request = BaseClass.createRequest(); 
	response = request .body(requestBody) .when() .post("/saveprogram");
	log.info("Program Description Length: {}", programDescription.length());
	log.info("Response Status Code: {}", response.getStatusCode()); 
	log.info("Response Body: {}", response.asPrettyString());
	
}

@Given("Admin creates POST Request with program name length between {int} and {int} characters in program API")
public void admin_creates_post_request_with_program_name_length_between_and_characters_in_program_api(Integer int1, Integer int2) {
    
	log.info("Creating POST request with program name length between {} and {} characters", int1, int2);
	String programName = "A".repeat(int1);
	String requestBody = "{" + "\"programName\":\"ValidProgramName\"," 
			+ "\"programDescription\":\"Test Description\","
			+ "\"programStatus\":\"Active\"" + "}";
	request = BaseClass.createRequest(); 
	response = request .body(requestBody) .when() .post("/saveprogram");
	log.info("Program Name Length: {}", programName.length());
	log.info("Response Status Code: {}", response.getStatusCode()); 
	log.info("Response Body: {}", response.asPrettyString());
	
	
}

@Given("Admin creates POST Request with invalid token in program API")
public void admin_creates_post_request_with_invalid_token_in_program_api() {
	log.info("Creating POST request with invalid authorization token"); 
	   endpoint = "/saveprogram";
	   
	String requestBody = "{" + "\"programName\":\"InvalidTokenProgram\","
			+ "" + "\"programDescription\":\"Test Description\"," 
					+ "\"programStatus\":\"Active\"" + "}"; 

	 request = RestAssured
	            .given()
	            .baseUri(RestAssured.baseURI)
	            .header("Authorization", "Bearer invalid_token")
	            .header("Content-Type", "application/json")
	            .body(requestBody);
	 log.info("Endpoint: {}", endpoint);
	 log.info("Request Body: {}", requestBody);

}

@Then("Admin receives {int} Unauthorized in program API")
public void admin_receives_unauthorized_in_program_api(Integer int1) {
	log.info("Validating Unauthorized response");
	log.info("Expected Status Code: {}", int1);
	log.info("Actual Status Code: {}", response.getStatusCode());
	response.then().statusCode(int1);
    
}

@Given("Admin creates POST Request with valid request body in program API")
public void admin_creates_post_request_with_valid_request_body_in_program_api() {
	
	log.info("Creating POST request with invalid authorization token"); 
	   endpoint = "/saveprogram";
	   
	   String requestBody = "{" + "\"programName\":\"TestProgram\","
				+ "" + "\"programDescription\":\"Test Description\"," 
						+ "\"programStatus\":\"Active\"" + "}"; 
	   
	   request= BaseClass.createRequest().
			   header("Content-Type","application/json")
			   .body(requestBody);
	     log.info("Endpoint: {}", endpoint);
		 log.info("Request Body: {}", requestBody);

   }

@When("Admin sends a HTTPS request to the invalid endpoint in program API")
public void admin_sends_a_https_request_to_the_invalid_endpoint_in_program_api() {
	
	String invalidEndpoint = "/invalidEndpoint";
	log.info("Sending POST request to invalid Endpoint:{}",invalidEndpoint);
	
	response = request
			.log().all().when().post(invalidEndpoint);
	
	log.info("Response StatusCode:{}",response.getStatusCode());
	log.info("Response Body:{}",response.asPrettyString());
	
    }

@Then("Admin receives {int} not found  Status with message and boolean success details in program API")
public void admin_receives_not_found_status_with_message_and_boolean_success_details_in_program_api(Integer expectedStatusCode) {
	  int actualStatusCode = response.getStatusCode();
	     System.out.println("Expected Status Code : " +expectedStatusCode);
	     System.out.println(" Actual Status Code : " +actualStatusCode);
	    
	     Assert.assertEquals(
	             actualStatusCode,
	             expectedStatusCode.intValue(),
	             "Expected 404 Not Found status code"
	     );
	 
	     String message = response.jsonPath().getString("message");
	     Assert.assertNull(message,"Response message should be Null");
	     
	    	     	     Object success = response.jsonPath().get("success");

	     Assert.assertNotNull(
	             success,
	             "Response success field should not be null"
	     );

	     Assert.assertTrue(
	             success instanceof Boolean,
	             "Response success field should be boolean"
	     );

	     System.out.println("Message : " + message);
	     System.out.println("Success : " + success);
	     

}

@When("Admin sends a HTTPS request to the valid endpoint in program API")
public void admin_sends_a_https_request_to_the_valid_endpoint_in_program_api() {
	
	 if (endpoint == null || endpoint.trim().isEmpty()) {
	        throw new IllegalStateException(
	                "Endpoint is NULL before POST. Check TestDataforLMS.json and JsonTestData mapping.");
	    }
	 System.out.println("\n========== POST REQUEST ==========");
	    System.out.println("Base URI : " + RestAssured.baseURI);
	    System.out.println("Endpoint : " + endpoint);
	    System.out.println("Request  : " + request);
  response = request
		  .log()
		  .all()
		  .when()
		  .post(endpoint);
  
  System.out.println("\n========== POST RESPONSE ==========");

  response.then()
          .log()
          .all();
  
  System.out.println("Status Code : " + response.getStatusCode());
  System.out.println("Response Body:");
  System.out.println(response.asPrettyString());
  
  log.info("Response StatusCode: {}",response.statusCode());
  log.info("Response Body: {}",response.body());
  
  
  }

@Then("Admin receives {int} unsupported media type in program API")
public void admin_receives_unsupported_media_type_in_program_api(Integer expectedStatusCode) {
     int actualStatusCode = response.getStatusCode();
     System.out.println("Expected Status Code : " +expectedStatusCode);
     System.out.println(" Actual Status Code : " +actualStatusCode);
     
     Assert.assertEquals(
             actualStatusCode,
             expectedStatusCode.intValue(),
             "Expected Unsupported Media Type status code"
     );
}

@Given("Admin creates invalid Request with valid request body in program API")
public void admin_creates_invalid_request_with_valid_request_body_in_program_api() {
	
	
	   String requestBody = "{" + "\"programName\":\"TestProgram\","
				+ "" + "\"programDescription\":\"Test Description\"," 
						+ "\"programStatus\":\"Active\"" + "}"; 
	   endpoint = "/saveprogram";
	   
	   request= BaseClass.createRequest().
			   header("Content-Type","application/json")
			   .body(requestBody);
	   System.out.println("******Invalid Request***");
	   System.out.println("Endpoint     : " + endpoint);
	    System.out.println("Content-Type : application/json");
	    System.out.println("Request Body : " + requestBody);
	}
	   


@Then("Admin receives {int} Method Not Allowed in program API")
public void admin_receives_method_not_allowed_in_program_api(Integer expectedStatusCode) {
	  int actualStatusCode = response.getStatusCode();
	     System.out.println("Expected Status Code : " +expectedStatusCode);
	     System.out.println(" Actual Status Code : " +actualStatusCode);
	     
	     Assert.assertEquals(
	             actualStatusCode,
	             expectedStatusCode.intValue(),
	             "Expected Method Not Allowed status code");
}

@Given("Admin creates {string} POST Request in program API")
public void admin_creates_post_request_in_program_api(String scenarioName) {
	
	log.info("Creating POST Request for scenario:{}", scenarioName);
	
	TestcaseWrapper testData = JsonReader.readAllModules("src/test/resources/TestDataforLMS.json");
	
	List<JsonTestData> testCases = testData.getPostRequest();
	
	JsonTestData data = JsonReader.getTestDataByScenarioName(scenarioName,testCases);
	
	endpoint = data.getEndpoint();
	
	String requestBody = "{"
			+"\"programName\":\"" + data.getProgramRequest().getprogramName() + "\","
			+"\"programDescription\":\"" + data.getProgramRequest().getprogramDescription()+"\","
			+"\"programStatus\":\"" + data.getProgramRequest().getprogramStatus()+"\","
			+"}";
	
	request =BaseClass.createRequest()
			.header("Content-Type", data.getContentType())
			.body(requestBody);
	 log.info("Scenario: {}", scenarioName);
	 log.info("Endpoint: {}", endpoint);
	 log.info("Request Body: {}", requestBody);
	
}

@When("Admin sends a POST request to the valid endpoint in program API")
public void admin_sends_a_post_request_to_the_valid_endpoint_in_program_api() {
	
	    System.out.println("\n**************POST REQUEST *************");
	    System.out.println("Base URI : " + RestAssured.baseURI);
	    System.out.println("Endpoint : " + endpoint);
	    System.out.println("Request  : " + request);
   response = request
		  .log()
		  .all()
		  .when()
		  .post(endpoint);
   
System.out.println("\n************** POST RESPONSE**************");
System.out.println("Status Code : " + response.getStatusCode());
System.out.println("Response Body:");
System.out.println(response.asPrettyString());
}

@Then("Admin validates POST response for {string} in program API")
public void admin_validates_post_response_for_in_program_api(String scenarioName) {
	
	log.info("Validating POST Response for Scenario :{}" ,scenarioName);
	
	TestcaseWrapper testData = JsonReader.readAllModules("src/test/resources/TestDataforLMS.json");
	
    List<JsonTestData> testCases = testData.getPostRequest();
	
	JsonTestData data = JsonReader.getTestDataByScenarioName(scenarioName,testCases);
	
	log.info("Expected status message:{}", data.getStatusmessage());
	log.info(" status code: {}",data.getexpectedStatusCode());
	log.info("Response body: {}" ,response.asPrettyString());
	
  }
}
