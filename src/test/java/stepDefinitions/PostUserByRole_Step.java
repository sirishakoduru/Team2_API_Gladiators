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
//import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertNotNull;

public class PostUserByRole_Step extends BaseClass {
	
	 RequestSpecification request;
	 
	    Response response;
	    
	    JsonTestData testData;
	    
	    private UserRequest userRequest;
	    
	    private boolean isInvalidMethod = false;

	       
	    private static final Logger log = LoggerFactory.getLogger(PostUserByRole_Step.class);
	    
	    private static final Logger apiLog = LoggerFactory.getLogger("API_RESPONSE_LOG");
	    
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
	        
	        if (isInvalidMethod) {
	        	
	            response = request.when().log().all().patch("/users/roleStatus");
	            
	            isInvalidMethod = false; // reset flag
	        }
	        else {
	            String endpoint = (testData != null && testData.getEndpoint() != null) ? testData.getEndpoint() : "/users/roleStatus";
	            
	            response = request.when().log().all().post(endpoint);
	        }
	        
	        Hooks.response = this.response;
	    }

@Then("Admin receives {int} Created Status with response body.")

	public void admin_receives_created_status_with_response_body(Integer expectedStatusCode) 
	
      {
	
        response.then().log().all().statusCode(expectedStatusCode).body("user.userId", notNullValue());
        
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


@Given("Admin creates POST request with {string} field empty")

public void admin_creates_post_request_with_field_empty(String fieldName) {
    
    if (request == null) {
        request = createRequest();
    }
    
    TestcaseWrapper wrapper = getTestData();
    
  
    String testcaseName = "Admin creates a user with empty " + fieldName;
    
    testData = JsonReader.getTestDataByScenarioName(testcaseName, wrapper.getPostRequest());
    
    userRequest = testData.getUserRequest();
    
    request.contentType(testData.getContentType()).body(userRequest);
    
    log.info("Prepared hardcoded request for testcase [{}]:\n{}", testcaseName, userRequest);
}



@Then("Admin receives {int} Bad Request Status with valid error message")
public void admin_receives_bad_request_status_with_valid_error_message(Integer expectedStatusCode) {
    
    response.then().log().all().statusCode(expectedStatusCode);
    
    assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch!");
    
    String responseBody = response.asString();
    
    if (testData != null) {
    	
        if (testData.getExpectedMessages() != null && !testData.getExpectedMessages().isEmpty()) {
        	
            boolean isMatched = false;
            
            for (String expected : testData.getExpectedMessages()) {
            	
                if (responseBody.toLowerCase().contains(expected.toLowerCase())) {
                	
                    isMatched = true;
                    
                    break;
                }
            }
            org.testng.Assert.assertTrue(
            		
                isMatched,
                
                "None of the expected messages " + testData.getExpectedMessages() + " found in response: " + responseBody
            );
        } else if (testData.getExpectedMessage() != null) {
        	
            org.testng.Assert.assertTrue(
            		
                responseBody.toLowerCase().contains(testData.getExpectedMessage().toLowerCase()),
                
                "Expected message '" + testData.getExpectedMessage() + "' not found in response: " + responseBody
            );
        }
    }
    
    log.info("Actual: {}, Expected: {}", response.getStatusCode(), expectedStatusCode);
    
    log.info("---------- Framework Log [Bad Request Validation] ----------");
    
    log.info("Scenario Name: {}", Hooks.scenario.getName());
    
    log.info("Actual Status Code: {}, Expected Status Code: {}", response.getStatusCode(), expectedStatusCode);
    
    log.info("Response Status Line: {}", response.asPrettyString());
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
	
    if (request == null) {
    	
        request = createRequest();
    }
    
    TestcaseWrapper wrapper = getTestData();
    
    testData = JsonReader.getTestDataByScenarioName("Admin creates a user with duplicate email id", wrapper.getPostRequest());
    
    userRequest = testData.getUserRequest();
    
    request.contentType(testData.getContentType()).body(userRequest);
    
    log.info("Prepared request for duplicate email scenario:\n{}", userRequest);
    
}
@Given("Admin creates POST request with phone number that is already associated with another user")
public void admin_creates_post_request_with_phone_number_that_is_already_associated_with_another_user() {
	
    if (request == null) {
    	
        request = createRequest();
    }
    
    TestcaseWrapper wrapper = getTestData();
    
    testData = JsonReader.getTestDataByScenarioName("Admin creates a user with duplicate phone number", wrapper.getPostRequest());
    
    userRequest = testData.getUserRequest();
    
    request.contentType(testData.getContentType()).body(userRequest);
    
    log.info("Prepared request for duplicate phone number scenario:\n{}", userRequest);
}

@Given("^Admin creates POST request with invalid (userEduPg|userEduUg|first name|last name|middle name|userLinkedinUrl|userLocation|email format|phone number format|user Role Id|user Role Status|user time zone|user visa status)$")

public void admin_creates_post_request_with_invalid(String fieldType) {
	
    if (request == null) {
        request = createRequest();
    }
    
    TestcaseWrapper wrapper = getTestData();
    
    String testcaseName = "Admin creates a user with invalid " + fieldType.trim();
    
    testData = JsonReader.getTestDataByScenarioName(testcaseName, wrapper.getPostRequest());
    
    userRequest = testData.getUserRequest();
    
    request.contentType(testData.getContentType()).body(userRequest);
    
    log.info("Prepared request for invalid [{}] scenario:\n{}", fieldType, userRequest);
}

@Given("Admin creates POST request with empty payload")

public void admin_creates_post_request_with_empty_payload() {
	
    if (request == null) {
    	
        request = createRequest();
    }
    request.contentType("application/json").body("{}");
    
    log.info("Prepared request with empty payload: {}");
}

@Then("Admin receives a {int} Bad Request")

 public void admin_receives_a_bad_request(Integer expectedStatusCode) {
	
	        response.then().log().all().statusCode(expectedStatusCode);
	        
	        assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch for empty payload!");
	        
	        log.info("Actual: {}, Expected: {}", response.getStatusCode(), expectedStatusCode);
	        
	        log.info("---------- Framework Log [Empty Payload Validation] ----------");
	        
	        log.info("Scenario Name: {}", Hooks.scenario.getName());
	        
	        log.info("Actual Status Code: {}, Expected Status Code: {}", response.getStatusCode(), expectedStatusCode);
	        
	        log.info("Response Status Line: {}", response.getStatusLine());
}

@Given("Admin creates POST request with invalid token")

public void admin_creates_post_request_with_invalid_token() {
	
    request = requestWithoutAuth()
            .header("Authorization", "Bearer invalid_token_xyz123")
            .contentType("application/json");
    
    TestcaseWrapper wrapper = getTestData();
    
    testData = JsonReader.getTestDataByScenarioName("Admin creates a admin user with valid request body and authorization", wrapper.getPostRequest());
    
    userRequest = testData.getUserRequest();
    
    request.body(userRequest);
    
    log.info("Prepared request with invalid Authorization token.");
}


@Then("Admin receives a {int} Unauthorized")

public void admin_receives_a_unauthorized(Integer expectedStatusCode) {
	
    response.then().log().all().statusCode(expectedStatusCode);
    
    assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch for invalid token!");
    
    log.info("Actual: {}, Expected: {}", response.getStatusCode(), expectedStatusCode);
    
    log.info("---------- Framework Log [Invalid Token Validation] ----------");
    
    log.info("Scenario Name: {}", Hooks.scenario.getName());
    
    log.info("Actual Status Code: {}, Expected Status Code: {}", response.getStatusCode(), expectedStatusCode);
    
    log.info("Response Status Line: {}", response.getStatusLine());
}

@Given("Admin creates POST request with valid request body")

public void admin_creates_post_request_with_valid_request_body() {
	
    if (request == null) {
        request = createRequest();
    }
    TestcaseWrapper wrapper = getTestData();
    
    testData = JsonReader.getTestDataByScenarioName("Admin creates a admin user with valid request body and authorization", wrapper.getPostRequest());
    
    userRequest = testData.getUserRequest();
    
    request.contentType(testData.getContentType()).body(userRequest);
    
    log.info("Prepared request with valid body for invalid endpoint scenario.");
}

@When("Admin sends a HTTPS request to the invalid user endpoint")

public void admin_sends_a_https_request_to_the_invalid_user_endpoint() {
	
    response = request.when().log().all().post("/users/roleStatus/invalid");
    
    Hooks.response = this.response;
}

//@Then("^Admin receives a (\\d+) not found Status with message and boolean success details$")

@Then("Admin receives a {int} not found Status with message and boolean success details")

public void admin_receives_a_not_found_status_with_message_and_boolean_success_details(Integer expectedStatusCode) {
	
    response.then().log().all().statusCode(expectedStatusCode);
    
    assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch for invalid endpoint!");
    
    log.info("Actual: {}, Expected: {}", response.getStatusCode(), expectedStatusCode);
    
    log.info("---------- Framework Log [Invalid Endpoint Validation] ----------");
    
    log.info("Scenario Name: {}", Hooks.scenario.getName());
    
    log.info("Actual Status Code: {}, Expected Status Code: {}", response.getStatusCode(), expectedStatusCode);
    
    log.info("Response Status Line: {}", response.getStatusLine());
}


@Given("Admin creates POST request with invalid content type")

public void admin_creates_post_request_with_invalid_content_type() {
	
    if (request == null) {
        request = createRequest();
    }
    
    request.contentType("text/plain").body("Invalid Content Type Body");
    
    log.info("Prepared request with invalid content type: text/plain");
}

@Then("Admin receives a {int} unsupported media type")

public void admin_receives_a_unsupported_media_type(Integer expectedStatusCode) {
	

    response.then().log().all().statusCode(expectedStatusCode);
    
    assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch for invalid content type!");
    
    log.info("Actual: {}, Expected: {}", response.getStatusCode(), expectedStatusCode);
    
    log.info("---------- Framework Log [Invalid Content Type Validation] ----------");
    
    log.info("Scenario Name: {}", Hooks.scenario.getName());
    
    log.info("Actual Status Code: {}, Expected Status Code: {}", response.getStatusCode(), expectedStatusCode);
    
    log.info("Response Status Line: {}", response.getStatusLine());
}

@Given("Admin creates invalid request with valid request body")

public void admin_creates_invalid_request_with_valid_request_body() {
	
    if (request == null) {
        request = createRequest();
    }
    TestcaseWrapper wrapper = getTestData();
    
    testData = JsonReader.getTestDataByScenarioName("Admin creates a admin user with valid request body and authorization", wrapper.getPostRequest());
    
    userRequest = testData.getUserRequest();
    
    request.contentType("application/json").body(userRequest);
    
    isInvalidMethod = true;
    
    log.info("Prepared request with invalid HTTP method (PATCH).");
}


@Then("Admin receives a {int} Method Not Allowed")

public void admin_receives_a_method_not_allowed(Integer expectedStatusCode) {
    
    
    response.then().log().all()
    
            .statusCode(expectedStatusCode)            
            .body("success", org.hamcrest.Matchers.equalTo(false))
            .body("message", org.hamcrest.Matchers.containsString("not supported"));
    

    assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch for invalid HTTP method!");
    

    boolean actualSuccess = response.jsonPath().getBoolean("success");
    
    String actualMessage = response.jsonPath().getString("message");
    
    org.testng.Assert.assertFalse(actualSuccess, "Expected success field to be false, but found true!");
    
    org.testng.Assert.assertTrue(actualMessage.contains("not supported"), "Unexpected error message: " + actualMessage);
    
    
    log.info("Actual Status Code: {}, Expected Status Code: {}", response.getStatusCode(), expectedStatusCode);
    
    log.info("Validated Response -> message: '{}', success: {}", actualMessage, actualSuccess);
    
    log.info("---------- Framework Log [Invalid Method Validation] ----------");
    
    log.info("Scenario Name: {}", Hooks.scenario.getName());
    
    log.info("Response Status Line: {}", response.getStatusLine());   
}


}