package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import base.BaseClass;
import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.TestcaseWrapper;
import pojo.JsonTestData;
import utilities.ConfigReader;
import utilities.JsonReader;
import utilities.ScenarioContext;

public class GetUsers_Step extends BaseClass {
	
	Response response;
	RequestSpecification request;
	JsonTestData testData;
	private static final Logger log = LoggerFactory.getLogger(GetUsers_Step.class);
	
	@Given("Admin sets Authorization to Bearer Token")
	public void admin_sets_authorization_to_bearer_token() {
		
		request = createRequest();
	}

	@Given("Admin creates GET request for {string} in User")
	public void admin_creates_get_request_for_in_user(String scenarioName) {
	    
		TestcaseWrapper wrapper = getTestData();
		testData = JsonReader.getTestDataByScenarioName(scenarioName, wrapper.getGetRequest());
	}

	@When("Admin sends GET request for {string} in User")
	public void admin_sends_get_request_for_in_user(String string) {
	   
		String endpoint = testData.getEndpoint();
		String method = testData.getMethod();
		log.info("Sending {} request to endpoint: {}", method, endpoint);
		response = request
		        .log().all()
		        .request(method, endpoint);

		response.then().log().all();
	}

	@Then("Admin validates GET response for {string} in User")
	public void admin_validates_get_response_for_in_user(String string) {
	   
		log.info("Response Body:\n{}", response.asPrettyString());	 
		 
		  assertEquals(response.getStatusCode(), testData.getexpectedStatusCode());
	}
	
//-----------------------------------Get request with no Auth-------------------------------------------------
	
	@Given("Admin sets to no Authorization")
	public void admin_sets_to_no_authorization() throws IOException {
		request = given()
	            .baseUri(ConfigReader.getProperty("BaseURL"));
		
	}
	@Given("Admin creates GET request without auth")
	public void admin_creates_get_request_without_auth() throws IOException {
		TestcaseWrapper wrapper = getTestData();

    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getGetRequest()
    	);
   
	}
	
	@When("Admin sends a HTTPS request to the valid endpoint of user")
	public void admin_sends_a_https_request_to_the_valid_endpoint_of_user() {
		response = request
                .when()
                .get(testData.getEndpoint());
		response.then().log().all();
	}

	@Then("Admin receives {int} Unauthorized")
	public void admin_receives_unauthorized(Integer statusCode) {
	    
		log.info("Response Body:\n{}", response.asPrettyString());
		assertEquals(response.getStatusCode(), testData.getexpectedStatusCode());
		
	}
	
//----------------------------------------Get User by userId---------------------------

	@Given("Admin creates GET request with valid user id in endpoint")
	public void admin_creates_get_request_with_valid_user_id_in_endpoint() {
	    
	    
	}

	@Then("Admin receives {int} OK Status with response body.")
	public void admin_receives_ok_status_with_response_body(Integer int1) {
	    
	    
	}

	@Then("Admin receives {int} Not Found Status with user inactive message and boolean success details")
	public void admin_receives_not_found_status_with_user_inactive_message_and_boolean_success_details(Integer int1) {
	    
	    
	}

	@Given("Admin creates GET request with invalid user id in endpoint")
	public void admin_creates_get_request_with_invalid_user_id_in_endpoint() {
	    
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getGetRequest()
    	);
	    
	}

	@Then("Admin receives {int} Not Found Status with message and boolean success details")
	public void admin_receives_not_found_status_with_message_and_boolean_success_details(Integer int1) {
		
		log.info("Response Body:\n{}", response.asPrettyString());
		assertEquals(response.getStatusCode(), testData.getexpectedStatusCode());
		
		String actualMessage = response.jsonPath().getString("message");
	    assertEquals(actualMessage, testData.getExpectedMessage(), "Message mismatch");

	    Boolean actualSuccess = response.jsonPath().getBoolean("success");
	    assertEquals(actualSuccess, testData.getBooleanMessage(), "Success message mismatch");
	    
	}

	@Given("Admin creates GET request with invalid baseURI")
	public void admin_creates_get_request_with_invalid_base_uri() throws IOException {
		request = given()
	            .baseUri(ConfigReader.getProperty("InvalidBaseURL"));
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getGetRequest()
    	);
	    
	}

	@Then("Admin receives {int} Not Found")
	public void admin_receives_not_found(Integer int1) {
	    
		log.info("Response Body:\n{}", response.asPrettyString());
		assertEquals(response.getStatusCode(), testData.getexpectedStatusCode());
	    
	}

	@Given("Admin creates GET request for the LMS API")
	public void admin_creates_get_request_for_the_lms_api() {
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getGetRequest()
    	);
	    
	}

	@When("Admin sends a HTTPS request to the invalid endpoint")
	public void admin_sends_a_https_request_to_the_invalid_endpoint() {
		response = request
                .when()
                .get(testData.getEndpoint());
		response.then().log().all();
	    
	}

	@Given("Admin creates invalid request for the LMS API")
	public void admin_creates_invalid_request_for_the_lms_api() {
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getGetRequest()
    	);
	}
    	
    	@When("Admin sends a HTTPS request to the valid endpoint and invalid method of user")
    	public void admin_sends_a_https_request_to_the_valid_endpoint_and_invalid_method_of_user() {
    	   
    	String endpoint = testData.getEndpoint();
		String method = testData.getMethod();
		log.info("Sending {} request to endpoint: {}", method, endpoint);
		response = request
		        .log().all()
		        .request(method, endpoint);

		response.then().log().all();
	    
	}

	@Then("Admin receives {int} Method Not Allowed")
	public void admin_receives_method_not_allowed(Integer int1) {
		log.info("Response Body:\n{}", response.asPrettyString());
		assertEquals(response.getStatusCode(), testData.getexpectedStatusCode());
	    
	}



}
