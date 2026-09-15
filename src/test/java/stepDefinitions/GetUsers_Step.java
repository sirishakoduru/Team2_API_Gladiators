package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import base.BaseClass;
import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.TestcaseWrapper;
import pojo.UserRequest;
import pojo.UserResponse;
import pojo.UserRoleProgramBatchStatusRequest;
import pojo.UserRoleRequest;
import pojo.UserRoleUpdate;
import pojo.JsonTestData;
import utilities.ConfigReader;
import utilities.JsonReader;
import utilities.ScenarioContext;

public class GetUsers_Step extends BaseClass {
	
	Response response;
	RequestSpecification request;
	JsonTestData testData;
	UserRequest userData;
	
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
		
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getGetRequest()
    	);
     String userId = ScenarioContext.get("adminUserId", String.class);
   	 String endpoint = "/users/" + userId;
   	 ScenarioContext.set("endpoint", endpoint);
	    
	}
	
	@When("Admin sends a HTTPS request to the valid endpoint with valid userId")
	public void admin_sends_a_https_request_to_the_valid_endpoint_with_valid_user_id() {

		String endpoint = ScenarioContext.get("endpoint", String.class);
		response = request.when().get(endpoint);
		log.info("Reponse Body:\n" +response.getBody().asString());
	}
	
	@Then("Admin receives {int} OK Status with response body of user id")
	public void admin_receives_ok_status_with_response_body_of_user_id(Integer int1) {
	    
		log.info("Response Body:\n{}", response.asPrettyString());
		assertEquals(response.getStatusCode(), testData.getexpectedStatusCode());
		
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
	
//-------------------------------------------Update user by userId-----------------------------------------------------
	
	@Given("Admin creates PUT request with valid user id")
	public void admin_creates_put_request_with_valid_user_id() {
		
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getPutRequest()
    	);
     userData = testData.getUserRequest();
     String userId = ScenarioContext.get("adminUserId", String.class);
     userData.setuserId(userId);
   	 String endpoint = "/users/" + userId;
   	 ScenarioContext.set("endpoint", endpoint);
   	 request.contentType(testData.getContentType())
	.body(userData);
		
	}

	@When("Admin sends a HTTPS request to the valid endpoint for updating user")
	public void admin_sends_a_https_request_to_the_valid_endpoint_for_updating_user() {
	   
		String endpoint = ScenarioContext.get("endpoint", String.class);
		response = request.when().put(endpoint);
		log.info("Reponse Body:\n" +response.getBody().asString());
	}
	
	@Then("Admin receives {int} OK Status with updated value in response body.")
	public void admin_receives_ok_status_with_updated_value_in_response_body(Integer int1) {
	   
		log.info("Response Body:\n{}", response.asPrettyString());
		assertEquals(response.getStatusCode(), testData.getexpectedStatusCode());
		UserResponse actual = response.as(UserResponse.class);
		UserRequest expected = testData.getUserRequest();
		
		assertEquals(actual.getUserFirstName(), expected.getUserFirstName());
	    assertEquals(actual.getUserLastName(), expected.getUserLastName());
	    assertEquals(actual.getUserMiddleName(), expected.getUserMiddleName());
	    assertEquals(actual.getUserPhoneNumber(), expected.getUserPhoneNumber());
	    assertEquals(actual.getUserLocation(), expected.getUserLocation());
	    assertEquals(actual.getUserTimeZone(), expected.getUserTimeZone());
	    assertEquals(actual.getUserComments(), expected.getUserComments());
	    assertEquals(actual.getUserVisaStatus(), expected.getUserVisaStatus());

	    log.info("PUT update validation completed successfully.");
	}

	@Given("Admin creates PUT request with invalid user id")
	public void admin_creates_put_request_with_invalid_user_id() {
	    
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getPutRequest()
    	);
     userData = testData.getUserRequest();
   	 request.contentType(testData.getContentType())
	.body(userData);
		
	}
	@When("Admin sends a HTTPS request to the valid endpoint for update")
	public void admin_sends_a_https_request_to_the_valid_endpoint_for_update() {
	   
		response = request.when().put(testData.getEndpoint());
		
	}

	@Then("Admin receives {int} Not Found Status with error message")
	public void admin_receives_not_found_status_with_error_message(Integer int1) {
	   
		log.info("Response Body:\n{}", response.asPrettyString());
		assertEquals(response.getStatusCode(), testData.getexpectedStatusCode());
		
		String actualMessage = response.jsonPath().getString("message");
	    assertEquals(actualMessage, testData.getExpectedMessage(), "Message mismatch");

	    Boolean actualSuccess = response.jsonPath().getBoolean("success");
	    assertEquals(actualSuccess, testData.getBooleanMessage(), "Success message mismatch");
	}
	
	@Given("Admin creates PUT request with missing mandatory fields in the request body")
	public void admin_creates_put_request_with_missing_mandatory_fields_in_the_request_body() {
	    
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getPutRequest()
    	);
     userData = testData.getUserRequest();
   	 request.contentType(testData.getContentType())
	.body(userData);
	}
	
	@Then("Admin receives {int} Bad Request Status with valid error message for update")
	public void admin_receives_bad_request_status_with_valid_error_message_for_update(Integer int1) {
	
		assertEquals(response.getStatusCode(), testData.getexpectedStatusCode());
	    String errorMessage = response.asPrettyString();
	    assertNotNull(errorMessage);
	    
	}
	@Given("Admin creates PUT request with email id that is already associated with another user")
	public void admin_creates_put_request_with_email_id_that_is_already_associated_with_another_user() {
	    
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getPutRequest()
    	);
     userData = testData.getUserRequest();
   	 request.contentType(testData.getContentType())
	.body(userData);
   	 
	}
	@Then("Admin receives {int} Bad Request Status with valid error message and boolean success details")
	public void admin_receives_bad_request_status_with_valid_error_message_and_boolean_success_details(Integer int1) {
	   
		assertEquals(response.getStatusCode(), testData.getexpectedStatusCode());

	    String errorMessage = response.asPrettyString();
	    assertNotNull(errorMessage);
	    Boolean actualSuccess = response.jsonPath().getBoolean("success");
	    assertEquals(actualSuccess, testData.getBooleanMessage(), "Success message mismatch");
	}
	@Given("Admin creates PUT request with phone number that is already associated  with another user")
	public void admin_creates_put_request_with_phone_number_that_is_already_associated_with_another_user() {
	    
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getPutRequest()
    	);
     userData = testData.getUserRequest();
   	 request.contentType(testData.getContentType())
	.body(userData);
	}
	@Given("Admin creates PUT request with invalid userEduPg")
	public void admin_creates_put_request_with_invalid_user_edu_pg() {
	   
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getPutRequest()
    	);
     userData = testData.getUserRequest();
   	 request.contentType(testData.getContentType())
	.body(userData);
   	 
	}
	@Given("Admin creates PUT request with invalid userEduUg")
	public void admin_creates_put_request_with_invalid_user_edu_ug() {
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getPutRequest()
    	);
     userData = testData.getUserRequest();
   	 request.contentType(testData.getContentType())
	.body(userData);
   	 
	}
	@Given("Admin creates PUT request with invalid first name")
	public void admin_creates_put_request_with_invalid_first_name() {
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getPutRequest()
    	);
     userData = testData.getUserRequest();
   	 request.contentType(testData.getContentType())
	.body(userData);
   	 
	}
	
	@Given("Admin creates PUT request with invalid last name")
	public void admin_creates_put_request_with_invalid_last_name() {
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getPutRequest()
    	);
     userData = testData.getUserRequest();
   	 request.contentType(testData.getContentType())
	.body(userData);
   	 
	}
	
	@Given("Admin creates PUT request with invalid middle name")
	public void admin_creates_put_request_with_invalid_middle_name() {
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getPutRequest()
    	);
     userData = testData.getUserRequest();
   	 request.contentType(testData.getContentType())
	.body(userData);
   	 
	}
	@Given("Admin creates PUT request with invalid userLinkedinUrl")
	public void admin_creates_put_request_with_invalid_user_linkedin_url() {
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getPutRequest()
    	);
     userData = testData.getUserRequest();
   	 request.contentType(testData.getContentType())
	.body(userData);
   	 
	}
	@Given("Admin creates PUT request with invalid userLocation")
	public void admin_creates_put_request_with_invalid_user_location() {
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getPutRequest()
    	);
     userData = testData.getUserRequest();
   	 request.contentType(testData.getContentType())
	.body(userData);
   	 
	}
	@Given("Admin creates PUT request with invalid email format")
	public void admin_creates_put_request_with_invalid_email_format() {
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getPutRequest()
    	);
     userData = testData.getUserRequest();
   	 request.contentType(testData.getContentType())
	.body(userData);
   	 
	}
	@Given("Admin creates PUT request with invalid phone number format")
	public void admin_creates_put_request_with_invalid_phone_number_format() {
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getPutRequest()
    	);
     userData = testData.getUserRequest();
   	 request.contentType(testData.getContentType())
	.body(userData);
   	 
	}
	@Given("Admin creates PUT request with invalid user time zone")
	public void admin_creates_put_request_with_invalid_user_time_zone() {
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getPutRequest()
    	);
     userData = testData.getUserRequest();
   	 request.contentType(testData.getContentType())
	.body(userData);
   	 
	}
	@Given("Admin creates PUT request with invalid user visa status")
	public void admin_creates_put_request_with_invalid_user_visa_status() {
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getPutRequest()
    	);
     userData = testData.getUserRequest();
   	 request.contentType(testData.getContentType())
	.body(userData);
   	 
	}
	@Given("Admin creates PUT request with valid request body")
	public void admin_creates_put_request_with_valid_request_body() {
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getPutRequest()
    	);
     userData = testData.getUserRequest();
   	 request.contentType(testData.getContentType())
	.body(userData);
   	 
	}

	@When("Admin sends a HTTPS request to the invalid endpoint of user")
	public void admin_sends_a_https_request_to_the_invalid_endpoint_of_user() {
	   
		response = request
                .when()
                .put(testData.getEndpoint());
	}
	@Given("Admin creates POST request with valid request body of update user")
	public void admin_creates_post_request_with_valid_request_body_of_update_user() {
	    
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getPutRequest()
    	);
     userData = testData.getUserRequest();
   	 request.contentType(testData.getContentType())
	.body(userData);
	}

	@When("Admin sends POST HTTPS request to the endpoint")
	public void admin_sends_post_https_request_to_the_endpoint() {
	  
		response = request
                .when()
                .post(testData.getEndpoint());
	}
	
	@Given("Admin creates PUT request with invalid content type")
	public void admin_creates_put_request_with_invalid_content_type() throws JsonProcessingException {
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getPutRequest()
    	);
     userData = testData.getUserRequest();
     String rawData = new ObjectMapper().writeValueAsString(userData);
   	 request.contentType(testData.getContentType())
	.body(rawData);
	}

	@When("Admin sends HTTPS request to the endpoint")
	public void admin_sends_https_request_to_the_endpoint() {
	   
		response = request
                .when()
                .put(testData.getEndpoint());
	}

	@Then("Admin receives {int} unsupported media type")
	public void admin_receives_unsupported_media_type(Integer int1) {
	   
		assertEquals(response.getStatusCode(), testData.getexpectedStatusCode());
	}
	
	@Given("Admin creates PUT request without authorization")
	public void admin_creates_put_request_without_authorization() {
	   
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getPutRequest()
    	);
     userData = testData.getUserRequest();
   	 request.contentType(testData.getContentType())
	.body(userData);
   	 
	}
	
//------------------------------------------Update user by RoleId-----------------------------------------
	
	@Given("Admin creates PUT request with valid role id")
	public void admin_creates_put_request_with_valid_role_id() {
	   
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getUpdateRoleIDRequest()
    	);
    	List<UserRoleRequest> roleList = testData.getUserRoleList();
    	UserRoleUpdate requestBody = new UserRoleUpdate(roleList);
    	request.body(requestBody);
     String userId = ScenarioContext.get("adminUserId", String.class);
   	 String endpoint = "/users/roleId/" + userId;
   	 ScenarioContext.set("endpoint", endpoint);
   	 request.contentType(testData.getContentType())
	.body(requestBody);
   	 
	}

	@When("Admin sends HTTPS request to the valid endpoint for updating user")
	public void admin_sends_https_request_to_the_valid_endpoint_for_updating_user() {
	   
		String endpoint = ScenarioContext.get("endpoint", String.class);
		response = request.when().put(endpoint);
	}
	@Then("Admin receives {int} OK Status with updated value in response body for update")
	public void admin_receives_ok_status_with_updated_value_in_response_body_for_update(Integer int1) {
		log.info("Response Body:\n{}", response.asPrettyString());
		assertEquals(response.getStatusCode(), testData.getexpectedStatusCode());
	}
	@Given("Admin creates PUT request with valid role status")
	public void admin_creates_put_request_with_valid_role_status() {
	    
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getUpdateRoleIDRequest()
    	);
    	List<UserRoleRequest> roleList = testData.getUserRoleList();
    	UserRoleUpdate requestBody = new UserRoleUpdate(roleList);
    	request.body(requestBody);
     String userId = ScenarioContext.get("adminUserId", String.class);
   	 String endpoint = "/users/roleId/" + userId;
   	 ScenarioContext.set("endpoint", endpoint);
   	 request.contentType(testData.getContentType())
	.body(requestBody);
   	 
	}
	@Given("Admin creates PUT request with invalid role id")
	public void admin_creates_put_request_with_invalid_role_id() {
	    
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getUpdateRoleIDRequest()
    	);
    	List<UserRoleRequest> roleList = testData.getUserRoleList();
    	UserRoleUpdate requestBody = new UserRoleUpdate(roleList);
    	request.body(requestBody);
   	 request.contentType(testData.getContentType())
	.body(requestBody);
   	 
	}
	@Then("Admin receives {int} Bad Request Status with valid error message for roleId")
	public void admin_receives_bad_request_status_with_valid_error_message_for_role_id(Integer int1) {
		log.info("Response Body:\n{}", response.asPrettyString());
		assertEquals(response.getStatusCode(), testData.getexpectedStatusCode());
		
		String actualMessage = response.jsonPath().getString("message");
	    assertEquals(actualMessage, testData.getExpectedMessage(), "Message mismatch");

	    Boolean actualSuccess = response.jsonPath().getBoolean("success");
	    assertEquals(actualSuccess, testData.getBooleanMessage(), "Success message mismatch");
	}
	@Given("Admin creates PUT request with invalid role status")
	public void admin_creates_put_request_with_invalid_role_status() {
	   
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getUpdateRoleIDRequest()
    	);
    	List<UserRoleRequest> roleList = testData.getUserRoleList();
    	UserRoleUpdate requestBody = new UserRoleUpdate(roleList);
    	request.body(requestBody);
   	 request.contentType(testData.getContentType())
	.body(requestBody);
   	 
	}
	@Given("Admin creates PUT request with already existing role id")
	public void admin_creates_put_request_with_already_existing_role_id() {
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getUpdateRoleIDRequest()
    	);
    	List<UserRoleRequest> roleList = testData.getUserRoleList();
    	UserRoleUpdate requestBody = new UserRoleUpdate(roleList);
    	request.body(requestBody);
   	 request.contentType(testData.getContentType())
	.body(requestBody);
	}
	
	@Given("Admin creates PUT request with invalid user id for roleId")
	public void admin_creates_put_request_with_invalid_user_id_for_role_id() {
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getUpdateRoleIDRequest()
    	);
    	List<UserRoleRequest> roleList = testData.getUserRoleList();
    	UserRoleUpdate requestBody = new UserRoleUpdate(roleList);
    	request.body(requestBody);
   	 request.contentType(testData.getContentType())
	.body(requestBody);
   	 
	}
	@When("Admin sends a HTTPS request to the invalid endpoint for roleId")
	public void admin_sends_a_https_request_to_the_invalid_endpoint_for_role_id() {
		
		response = request.when().put(testData.getEndpoint());
	}
	
	@Given("Admin creates PUT request for the LMS API")
	public void admin_creates_put_request_for_the_lms_api() {
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getUpdateRoleIDRequest()
    	);
    	List<UserRoleRequest> roleList = testData.getUserRoleList();
    	UserRoleUpdate requestBody = new UserRoleUpdate(roleList);
    	request.body(requestBody);
   	 request.contentType(testData.getContentType())
	.body(requestBody);
	}
	
	@Given("Admin creates invalid request for the LMS API for roleId")
	public void admin_creates_invalid_request_for_the_lms_api_for_role_id() {
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getUpdateRoleIDRequest()
    	);
    	List<UserRoleRequest> roleList = testData.getUserRoleList();
    	UserRoleUpdate requestBody = new UserRoleUpdate(roleList);
    	request.body(requestBody);
   	 request.contentType(testData.getContentType())
	.body(requestBody);
	}
	
	@Given("Admin creates PUT request with invalid content type for roleId")
	public void admin_creates_put_request_with_invalid_content_type_for_role_id() throws JsonProcessingException {
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getUpdateRoleIDRequest()
    	);
    	List<UserRoleRequest> roleList = testData.getUserRoleList();
    	UserRoleUpdate requestBody = new UserRoleUpdate(roleList);
    	request.body(requestBody);
    String rawData = new ObjectMapper().writeValueAsString(userData);
   	 request.contentType(testData.getContentType())
	.body(rawData);
	}
	
	@Given("Admin creates PUT request without auth")
	public void admin_creates_put_request_without_auth() {
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getUpdateRoleIDRequest()
    	);
    	List<UserRoleRequest> roleList = testData.getUserRoleList();
    	UserRoleUpdate requestBody = new UserRoleUpdate(roleList);
    	request.body(requestBody);
   	 request.contentType(testData.getContentType())
	.body(requestBody);
	}
	
//----------------------------------------Update User login  status----------------------------------------
	
	@Given("Admin creates PUT request for the LMS API for user login")
	public void admin_creates_put_request_for_the_lms_api_for_user_login() {
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getPutRequest()
    	);
     userData = testData.getUserRequest();
   	 request.contentType(testData.getContentType())
	.body(userData);
	}
	
	@Then("Admin receives {int} OK Status with response body for user login")
	public void admin_receives_ok_status_with_response_body_for_user_login(Integer int1) {
		log.info("Response Body:\n{}", response.asPrettyString());
		assertEquals(response.getStatusCode(), testData.getexpectedStatusCode());
		String actualMessage = response.asString();
		assertEquals(actualMessage, testData.getExpectedMessage(), "Message mismatch");
	}
	@Given("Admin creates PUT request for the LMS API with invalid user id")
	public void admin_creates_put_request_for_the_lms_api_with_invalid_user_id() {
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getPutRequest()
    	);
     userData = testData.getUserRequest();
   	 request.contentType(testData.getContentType())
	.body(userData);
	}
	
	@Given("Admin creates PUT request with email field empty")
	public void admin_creates_put_request_with_email_field_empty() {
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getPutRequest()
    	);
     userData = testData.getUserRequest();
   	 request.contentType(testData.getContentType())
	.body(userData);
	}
	
	@Given("Admin creates PUT request with login status field empty")
	public void admin_creates_put_request_with_login_status_field_empty() {
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getPutRequest()
    	);
     userData = testData.getUserRequest();
   	 request.contentType(testData.getContentType())
	.body(userData);
	}
	
	@Given("Admin creates PUT request with status field empty")
	public void admin_creates_put_request_with_status_field_empty() {
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getPutRequest()
    	);
     userData = testData.getUserRequest();
   	 request.contentType(testData.getContentType())
	.body(userData);
	}
	
	@Given("Admin creates PUT request with invalid email")
	public void admin_creates_put_request_with_invalid_email() {
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getPutRequest()
    	);
     userData = testData.getUserRequest();
   	 request.contentType(testData.getContentType())
	.body(userData);
	}
	
	@Given("Admin creates PUT request with invalid login status")
	public void admin_creates_put_request_with_invalid_login_status() {
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getPutRequest()
    	);
     userData = testData.getUserRequest();
   	 request.contentType(testData.getContentType())
	.body(userData);
	}
	
	@Given("Admin creates PUT request with invalid status")
	public void admin_creates_put_request_with_invalid_status() {
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getPutRequest()
    	);
     userData = testData.getUserRequest();
   	 request.contentType(testData.getContentType())
	.body(userData);
	}
	
	@Given("Admin creates invalid request for the LMS API for user")
	public void admin_creates_invalid_request_for_the_lms_api_for_user() {
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getPutRequest()
    	);
     userData = testData.getUserRequest();
   	 request.contentType(testData.getContentType())
	.body(userData);
	}
//----------------------------------------------Update User Role Program Batch Status-------------------
	
	@Given("Admin creates PUT request for the LMS API for user role program batch")
	public void admin_creates_put_request_for_the_lms_api_for_user_role_program_batch() {
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getProgramBatchRequest()
    	);
    UserRoleProgramBatchStatusRequest requestBody = testData.getProgramBatchRequest();
   	 request.contentType(testData.getContentType())
	.body(requestBody);
	}

	@When("Admin sends HTTPS request to the valid endpoint")
	public void admin_sends_https_request_to_the_valid_endpoint() {
		response = request.when().put(testData.getEndpoint());
	}



















	



}
