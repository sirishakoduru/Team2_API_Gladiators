package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
import pojo.UserRequest;
import pojo.JsonTestData;
import utilities.ConfigReader;
import utilities.JsonReader;
import utilities.ScenarioContext;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertNotNull;

public class PostUserByRole_Step extends BaseClass {
	
	 RequestSpecification request;
	    Response response;
	    JsonTestData testData;
	    private UserRequest userRequest;

	       
	    private static final Logger log = LoggerFactory.getLogger(PostUserByRole_Step.class);
	    
	    @Given("Admin creates POST request with valid request body for Role id {string}, Role name {string}, and Role desc {string}")
	    public void admin_creates_post_request_with_valid_request_body_for_role(String roleId, String roleName, String roleDesc)
	    {
	        if (request == null) 
	        {
	            request = createRequest();
	        }		
	        
	        TestcaseWrapper wrapper = getTestData();
	        
	        String testCaseName = "";
	        
	        switch (roleId) {
	        
	            case "R01":
	                testCaseName = "Admin creates a admin user with valid request body and authorization";
	                break;
	                
	            case "R02":
	                testCaseName = "Admin creates a staff user with valid request body and authorization";
	                break;
	                
	            case "R03":
	                testCaseName = "Admin creates a student user with valid request body and authorization";
	                break;
	                
	            default:
	                testCaseName = Hooks.scenario.getName();
	                break;
	        }
	        
	        testData = JsonReader.getTestDataByScenarioName(testCaseName, wrapper.getPostRequest());
	        
	        userRequest = testData.getUserRequest();
	        
	        request.contentType(testData.getContentType()).body(userRequest);
	        
	        log.info("Request payload prepared for Role ID: {}, Role Name: {}, Role Desc: {}\n{}", roleId, roleName, roleDesc, userRequest);
	        
	    }


@When("Admin sends a HTTPS request to the valid user endpoint")
public void admin_sends_a_https_request_to_the_valid_user_endpoint() {
	
    if (request == null) {
        request = createRequest();
    }
    
    String endpoint = (testData != null && testData.getEndpoint() != null) ? testData.getEndpoint() : "/users/roleStatus";
    
    response = request.when().log().all().post(endpoint);
    
    Hooks.response = this.response;
}

@Then("Admin receives {int} Created Status with response body.")

	public void admin_receives_created_status_with_response_body(Integer expectedStatusCode) {
	
        response.then().log().all()
            .statusCode(expectedStatusCode)
            .body("user.userId", notNullValue());
        
        String userId = response.jsonPath().getString("user.userId");
        
//        assertNotNull(userId, "Response body should contain generated userId");
        
//        assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch!");
        
        String role = response.jsonPath().getString("roles[0].roleId");

        switch(role) {
            case "R01":
                ScenarioContext.set("adminUserId", userId);
                break;
            case "R02":
                ScenarioContext.set("staffUserId", userId);
                break;
            case "R03":
                ScenarioContext.set("studentUserId", userId);
                break;
        }
        
        log.info("User created successfully with User ID: {}", userId);
        
//        ScenarioContext.set("userId", userId);
        
        log.info("User ID {} stored in ScenarioContext successfully", userId);
    }


@Given("Admin creates POST request with only mandatory field")

public void admin_creates_post_request_with_only_mandatory_field() {
	
    if (request == null) {
        request = createRequest();
    }
    
    TestcaseWrapper wrapper = getTestData();
    
    testData = JsonReader.getTestDataByScenarioName("Admin creates a user with only mandatory field", wrapper.getPostRequest());
    
    userRequest = testData.getUserRequest();
    
    request.contentType(testData.getContentType()).body(userRequest);
    
    log.info("Request payload prepared for mandatory fields: \n{}", userRequest);
}

@Given("Admin creates POST request with FirstName field empty")
public void admin_creates_post_request_with_first_name_field_empty() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("Admin receives {int} Bad Request Status with valid error message")
public void admin_receives_bad_request_status_with_valid_error_message(Integer int1) {
   
	
}

@Given("Admin creates POST request with LastName field empty")
public void admin_creates_post_request_with_last_name_field_empty() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST request with Location field empty")
public void admin_creates_post_request_with_location_field_empty() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST request with Time Zone field empty")
public void admin_creates_post_request_with_time_zone_field_empty() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST request with Visa Status field empty")
public void admin_creates_post_request_with_visa_status_field_empty() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST request with Role Id field empty")
public void admin_creates_post_request_with_role_id_field_empty() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST request with Role Status field empty")
public void admin_creates_post_request_with_role_status_field_empty() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST request with Login Status field empty")
public void admin_creates_post_request_with_login_status_field_empty() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST request with email field empty")
public void admin_creates_post_request_with_email_field_empty() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST request with Phone Number field empty")
public void admin_creates_post_request_with_phone_number_field_empty() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST request with email id that is already associated with another user")
public void admin_creates_post_request_with_email_id_that_is_already_associated_with_another_user() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST request with phone number that is already associated with another user")
public void admin_creates_post_request_with_phone_number_that_is_already_associated_with_another_user() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST request with invalid userEduPg")
public void admin_creates_post_request_with_invalid_user_edu_pg() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST request with invalid userEduUg")
public void admin_creates_post_request_with_invalid_user_edu_ug() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST request with invalid first name")
public void admin_creates_post_request_with_invalid_first_name() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST request with invalid last name")
public void admin_creates_post_request_with_invalid_last_name() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST request with invalid middle name")
public void admin_creates_post_request_with_invalid_middle_name() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST request with invalid userLinkedinUrl")
public void admin_creates_post_request_with_invalid_user_linkedin_url() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST request with invalid userLocation")
public void admin_creates_post_request_with_invalid_user_location() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST request with invalid email format")
public void admin_creates_post_request_with_invalid_email_format() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST request with invalid phone number format")
public void admin_creates_post_request_with_invalid_phone_number_format() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST request with invalid user Role Id")
public void admin_creates_post_request_with_invalid_user_role_id() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST request with invalid user Role Status")
public void admin_creates_post_request_with_invalid_user_role_status() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST request with invalid user time zone")
public void admin_creates_post_request_with_invalid_user_time_zone() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST request with invalid user visa status")
public void admin_creates_post_request_with_invalid_user_visa_status() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST request with empty payload")
public void admin_creates_post_request_with_empty_payload() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("Admin receives a {int} Bad Request")
public void admin_receives_bad_request(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST request with invalid token")
public void admin_creates_post_request_with_invalid_token() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("Admin receives a {int} Unauthorized")
public void admin_receives_unauthorized(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST request with valid request body")
public void admin_creates_post_request_with_valid_request_body() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@When("Admin sends  HTTPS request to the invalid endpoint")
public void admin_sends_a_https_request_to_the_invalid_endpoint() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("Admin receives a {int} not found Status with message and boolean success details")
public void admin_receives_not_found_status_with_message_and_boolean_success_details(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST request with invalid content type")
public void admin_creates_post_request_with_invalid_content_type() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("Admin receives a {int} unsupported media type")
public void admin_receives_unsupported_media_type(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates invalid request with valid request body")
public void admin_creates_invalid_request_with_valid_request_body() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("Admin receives a {int} Method Not Allowed")
public void admin_receives_method_not_allowed(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}


}