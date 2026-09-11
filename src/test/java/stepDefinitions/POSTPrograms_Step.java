package stepDefinitions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import base.BaseClass;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.cucumber.java.en.*;
public class POSTPrograms_Step extends BaseClass {
	
	Response response;
	RequestSpecification request;
	private static final Logger log = LoggerFactory.getLogger(POSTPrograms_Step.class);
	private String scenarioName;
	
@Then("Admin receives {int} Created Status with response body")
public void admin_receives_created_status_with_response_body(Integer int1) {
   
}

@Given("Admin creates POST Request with only mandatory field")
public void admin_creates_post_request_with_only_mandatory_field() {
	
}

@Given("Admin creates POST Request with program description length between {int} and {int} characters")
public void admin_creates_post_request_with_program_description_length_between_and_characters(Integer int1, Integer int2) {
 
}

@Given("Admin creates POST Request with program name length between {int} and {int} characters")
public void admin_creates_post_request_with_program_name_length_between_and_characters(Integer int1, Integer int2) {
  
}

@Given("Admin creates POST Request with invalid token")
public void admin_creates_post_request_with_invalid_token() {
}

@Then("Admin receives {int} Unauthorized")
public void admin_receives_unauthorized(Integer int1) {
  
}

@Given("Admin creates POST Request with valid request body")
public void admin_creates_post_request_with_valid_request_body() {
 
}

@When("Admin sends a HTTPS request to the invalid endpoint")
public void admin_sends_a_https_request_to_the_invalid_endpoint() {
  
}

@Then("Admin receives {int} not found  Status with message and boolean success details")
public void admin_receives_not_found_status_with_message_and_boolean_success_details(Integer int1) {
  
}

@Then("Admin receives {int} unsupported media type")
public void admin_receives_unsupported_media_type(Integer int1) {
  
}

@Given("Admin creates invalid Request with valid request body")
public void admin_creates_invalid_request_with_valid_request_body() {
  
}

@Then("Admin receives {int} Method Not Allowed")
public void admin_receives_method_not_allowed(Integer int1) {
    
}

@Given("Admin creates {string} POST Request in program API")
public void admin_creates_post_request_in_program_api(String string) {
  
}

@When("Admin sends a POST request to the valid endpoint")
public void admin_sends_a_post_request_to_the_valid_endpoint() {
 
}

@Then("Admin validates POST response for {string} in program API")
public void admin_validates_post_response_for_in_program_api(String string) {
   
}

}