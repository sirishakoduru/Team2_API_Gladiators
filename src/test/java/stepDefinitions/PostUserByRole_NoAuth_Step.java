package stepDefinitions;
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
import pojo.UserRequest;
import pojo.JsonTestData;
import utilities.JsonReader;

public class PostUserByRole_NoAuth_Step extends BaseClass {
	
	Response response;
	
	RequestSpecification request;
	
	JsonTestData testData;
	
	private static final Logger log = LoggerFactory.getLogger(PostUserByRole_NoAuth_Step.class);
	
	private static final Logger apiLog = LoggerFactory.getLogger("API_RESPONSE_LOG");
	
@Given("Admin sets Authorization to No Auth.")

	public void admin_sets_authorization_to_no_auth()throws IOException {
	
		BaseClass.init(); 
		
	    request = requestWithoutAuth();
	    
	    log.info("Request initialized without Authorization header.");
	}

	
@Given("Admin creates POST request without auth and with valid request body")

	public void admin_creates_post_request_without_auth_and_with_valid_request_body() 
   {
    TestcaseWrapper wrapper = getTestData();
    
    testData = JsonReader.getTestDataByScenarioName(
        "Admin creates POST request without auth and with valid request body", 
        wrapper.getPostRequest()
    );
    
    UserRequest userRequest = testData.getUserRequest();
    
    request.contentType(testData.getContentType()).body(userRequest);
    
    log.info("Request payload prepared without auth:\n{}", userRequest);
}

@When("Admin sends a HTTPS request to the valid user endpoint without auth")

   public void admin_sends_a_https_request_to_the_valid_endpoint() {
	
	 if (request == null) {
	        request = requestWithoutAuth();
	    }
	
   String endpoint = (testData != null && testData.getEndpoint() != null) ? testData.getEndpoint() : "/users/roleStatus";
   
   response = request.when().log().all().post(endpoint);
   
   log.info("Response Status Code: {}", response.getStatusCode());
   
	}


@Then("Admin receives {int} unauthorized for request without authorization")

public void admin_receives_unauthorized(Integer expectedStatusCode) {
	
    response.then().log().all().statusCode(expectedStatusCode);
    
    assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch!");
    
    log.info("Actual: {}, Expected: {}", response.getStatusCode(), expectedStatusCode);

    log.info("---------- Framework Log [No Auth Validation] ----------");
    
    log.info("Actual Status Code: {}, Expected Status Code: {}", response.getStatusCode(), expectedStatusCode);
    
    log.info("Response Status Line: {}", response.getStatusLine());
   
}
}
