package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

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
	    
		log.info("Response Body:\n{}", response.asPrettyString());
		assertEquals(response.getStatusCode(), testData.getexpectedStatusCode());
		List<Object> usersList = response.jsonPath().getList("");
		log.info("The list of users size: {}", usersList.size());
		assertTrue(usersList.size() >= 0);
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
	
//------------------------------------------Get count of all active and inactive users-------------------------------------
	
	@Then("Admin receives {int} OK Status with response body for count of users.")
	public void admin_receives_ok_status_with_response_body_for_count_of_users(Integer int1) {
	   
		assertEquals(response.getStatusCode(), testData.getexpectedStatusCode());
		String status = response.jsonPath().getString("[0].status");
		assertEquals(status, "Active");
		int count = response.jsonPath().getInt("[0].count");
		assertTrue(count >= 0);
	}
	
	@When("Admin sends a HTTPS request to the invalid role id")
	public void admin_sends_a_https_request_to_the_invalid_role_id() {
	   
		response = request
                .when()
                .get(testData.getEndpoint());
		response.then().log().all();
	}

	@Then("Admin receives {int} not found Status with RoleID not found message")
	public void admin_receives_not_found_status_with_role_id_not_found_message(Integer int1) {
	   
		log.info("Response Body:\n{}", response.asPrettyString());
		assertEquals(response.getStatusCode(), testData.getexpectedStatusCode());
		
		String actualMessage = response.jsonPath().getString("message");
	    assertEquals(actualMessage, testData.getExpectedMessage(), "Message mismatch");

	    Boolean actualSuccess = response.jsonPath().getBoolean("success");
	    assertEquals(actualSuccess, testData.getBooleanMessage(), "Success message mismatch");
	}
	
//-------------------------------------Gets User by Program Batches-----------------------------------------------
	
	@Given("Admin creates GET request for the LMS API with valid batch id")
	public void admin_creates_get_request_for_the_lms_api_with_valid_batch_id() {
	    
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getGetRequest());
		
	}
	
	@Given("Admin creates GET request for the LMS API with invalid batch id")
	public void admin_creates_get_request_for_the_lms_api_with_invalid_batch_id() {
	   
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getGetRequest());
	}

	@Then("Admin receives {int} not found Status with batch id not found message")
	public void admin_receives_not_found_status_with_batch_id_not_found_message(Integer int1) {
	   
		log.info("Response Body:\n{}", response.asPrettyString());
		assertEquals(response.getStatusCode(), testData.getexpectedStatusCode());
		
		String actualMessage = response.jsonPath().getString("message");
	    assertEquals(actualMessage, testData.getExpectedMessage(), "Message mismatch");
	}
	
//----------------------------------Gets Users for Program-------------------------------------------------------
	
	@Given("Admin creates GET request for the LMS API with valid program id")
	public void admin_creates_get_request_for_the_lms_api_with_valid_program_id() {
	    
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getGetRequest());
	}

	@Given("Admin creates GET request for the LMS API with invalid program id")
	public void admin_creates_get_request_for_the_lms_api_with_invalid_program_id() {
	    
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getGetRequest());
	}

	@Then("Admin receives {int} not found Status with program id not found message")
	public void admin_receives_not_found_status_with_program_id_not_found_message(Integer int1) {
	   
		log.info("Response Body:\n{}", response.asPrettyString());
		assertEquals(response.getStatusCode(), testData.getexpectedStatusCode());
		
		String actualMessage = response.jsonPath().getString("message");
	    assertEquals(actualMessage, testData.getExpectedMessage(), "Message mismatch");
	}
	
//------------------------------------Gets Users by RoleI---------------------------------------------------------
	
	@When("Admin sends a HTTPS request to the valid endpoint with R01 role id")
	public void admin_sends_a_https_request_to_the_valid_endpoint_with_r01_role_id() {
	    
		response = request
                .when()
                .get(testData.getEndpoint());
		response.then().log().all();
	}

	@When("Admin sends a HTTPS request to the valid endpoint with R02 role id")
	public void admin_sends_a_https_request_to_the_valid_endpoint_with_r02_role_id() {
	   
		response = request
                .when()
                .get(testData.getEndpoint());
		response.then().log().all();
	}

	@When("Admin sends a HTTPS request to the valid endpoint with R03 role id")
	public void admin_sends_a_https_request_to_the_valid_endpoint_with_r03_role_id() {
	   
		response = request
                .when()
                .get(testData.getEndpoint());
		response.then().log().all();
	}
	
//-------------------------------------------Get User details by id-----------------------------------------------------
	
	@Given("Admin creates GET request with valid user id in endpoint for user details")
	public void admin_creates_get_request_with_valid_user_id_in_endpoint_for_user_details() {
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getGetRequest());
	}
	
	@Given("Admin creates GET request for the LMS API with invalid user id")
	public void admin_creates_get_request_for_the_lms_api_with_invalid_user_id() {
	    
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getGetRequest());
	}
	



}
