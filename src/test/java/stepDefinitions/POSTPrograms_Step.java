package stepDefinitions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import base.BaseClass;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.JsonTestData;
import pojo.ProgramRequest;
import pojo.ProgramResponse;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;

public class POSTPrograms_Step extends BaseClass {
	
	Response response;
    RequestSpecification request = requestWithoutAuth();
	String endpoint;
	private static final Logger log = LoggerFactory.getLogger(POSTPrograms_Step.class);
	private String scenarioName;

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
   
	response.then().statusCode(expectedStatusCode);
	  log.info("Expected Status Code: {}", expectedStatusCode);
      log.info("Actual Status Code: {}", response.statusCode());
}

@Given("Admin creates POST Request with only mandatory field in program API")
public void admin_creates_post_request_with_only_mandatory_field_in_program_api() {
	
	 endpoint = "/saveprogram";
	ProgramRequest programRequest = new ProgramRequest();
	programRequest.setProgramName("RestAssured11");
	programRequest.setProgramDescription("RestAssuredTest");
	programRequest.setProgramStatus("Active");
	
	request = createRequest()
			.body(programRequest);
	 System.out.println("\n========== POST REQUEST ==========");
	      System.out.println("Endpoint       : " + endpoint);
	    System.out.println("Program Name   : " + programRequest.getprogramName());
	    System.out.println("Program Status : " + programRequest.getprogramStatus());
    
}

@Given("Admin creates POST Request with program description length between {int} and {int} characters in program API")
public void admin_creates_post_request_with_program_description_length_between_and_characters_in_program_api(Integer int1, Integer int2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST Request with program name length between {int} and {int} characters in program API")
public void admin_creates_post_request_with_program_name_length_between_and_characters_in_program_api(Integer int1, Integer int2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST Request with invalid token in program API")
public void admin_creates_post_request_with_invalid_token_in_program_api() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("Admin receives {int} Unauthorized in program API")
public void admin_receives_unauthorized_in_program_api(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST Request with valid request body in program API")
public void admin_creates_post_request_with_valid_request_body_in_program_api() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@When("Admin sends a HTTPS request to the invalid endpoint in program API")
public void admin_sends_a_https_request_to_the_invalid_endpoint_in_program_api() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("Admin receives {int} not found  Status with message and boolean success details in program API")
public void admin_receives_not_found_status_with_message_and_boolean_success_details_in_program_api(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
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
public void admin_receives_unsupported_media_type_in_program_api(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates invalid Request with valid request body in program API")
public void admin_creates_invalid_request_with_valid_request_body_in_program_api() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("Admin receives {int} Method Not Allowed in program API")
public void admin_receives_method_not_allowed_in_program_api(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates {string} POST Request in program API")
public void admin_creates_post_request_in_program_api(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@When("Admin sends a POST request to the valid endpoint in program API")
public void admin_sends_a_post_request_to_the_valid_endpoint_in_program_api() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("Admin validates POST response for {string} in program API")
public void admin_validates_post_response_for_in_program_api(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}
}
