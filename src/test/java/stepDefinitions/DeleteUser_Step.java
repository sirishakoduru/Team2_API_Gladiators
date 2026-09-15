package stepDefinitions;

import static org.testng.Assert.assertEquals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import base.BaseClass;
import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.ScenarioContext;

public class DeleteUser_Step extends BaseClass {

    RequestSpecification request;
    Response response;

    private String userId;
    private boolean isInvalidMethod = false;

    private static final Logger log = LoggerFactory.getLogger(DeleteUser_Step.class);

    @Given("Admin sets Authorization to Bearer Token for delete user")
    
    public void admin_sets_authorization_to_bearer_token_for_delete_user() {
        request = createRequest();
        log.info("Request initialized with Bearer token for Delete User.");
        
    }


    @Given("Admin creates DELETE request with valid user ID in endpoints")
    
    public void admin_creates_delete_request_with_valid_user_id_in_endpoints() {
        if (request == null) {
            request = createRequest();
        }

        userId = ScenarioContext.get("userId", String.class);
        
        if (userId == null || userId.isEmpty()) 
        {
            Response getResp = createRequest().when().get("/users/roles");
            
            if (getResp.getStatusCode() == 200) {
            	
                userId = getResp.jsonPath().getString("[0].userId");
            }
        }

        log.info("Prepared DELETE request for valid User ID: {}", userId);
    }

    @Given("Admin creates DELETE request invalid user ID in endpoints")
    
    public void admin_creates_delete_request_invalid_user_id_in_endpoints() {
    	
        if (request == null) {
        	
            request = createRequest();
        }

        userId = "99999999";
        
        log.info("Prepared DELETE request with invalid User ID: {}", userId);
    }

    @Given("Admin creates DELETE request for invalid endpoint")
    
    public void admin_creates_delete_request_for_invalid_endpoint() {
    	
        if (request == null) {
        	
            request = createRequest();
        }
        
        log.info("Prepared DELETE request for invalid endpoint testing");
    }

    @Given("Admin creates invalid request for delete user")
    
    public void admin_creates_invalid_request_for_delete_user() {
    	
        if (request == null) {
        	
            request = createRequest();
        }

        userId = "123";
        
        isInvalidMethod = true;
        
        log.info("Prepared request with invalid HTTP method flag (PATCH)");
    }


    @When("Admin sends a HTTPS request to the valid delete user endpoint")
    
    public void admin_sends_a_https_request_to_the_valid_delete_user_endpoint() {
    	
        if (request == null) {
            request = createRequest();
        }

        String endpoint = (userId != null && !userId.isEmpty()) ? "/users/" + userId : "/users/123";
        

        if (isInvalidMethod)
        {
            log.info("Sending invalid PATCH method to endpoint: {}", endpoint);
            
            response = request.when().log().all().patch(endpoint);
            
            isInvalidMethod = false;
            
        } else {
            log.info("Sending DELETE request to endpoint: {}", endpoint);
            
            response = request.when().log().all().delete(endpoint);
        }

        Hooks.response = this.response;
    }

    @When("Admin sends a HTTPS request to the invalid delete user endpoint")
    
    public void admin_sends_a_https_request_to_the_invalid_delete_user_endpoint() {
        if (request == null) {
            request = createRequest();
        }
        

        String invalidEndpoint = "/usersinvalid/123";
        log.info("Sending DELETE request to invalid endpoint: {}", invalidEndpoint);
        response = request.when().log().all().delete(invalidEndpoint);

        Hooks.response = this.response;
    }

 
    @Then("Admin receives {int} OK Status with response body for delete user")
    
    public void admin_receives_ok_status_with_response_body_for_delete_user(Integer expectedStatusCode) {
    	
        response.then().log().all().statusCode(expectedStatusCode);
        
        assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch!");

        log.info("---------- Framework Log [Delete User Success Validation] ----------");
        
        log.info("Scenario Name: {}", Hooks.scenario.getName());
        
        log.info("Actual Status Code: {}, Expected Status Code: {}", response.getStatusCode(), expectedStatusCode);
    }

    @Then("Admin receives {int} Not Found Status for delete user")
    
    public void admin_receives_not_found_status_for_delete_user(Integer expectedStatusCode) {
    	
        response.then().log().all().statusCode(expectedStatusCode);
        
        assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch!");

        log.info("---------- Framework Log [404 Not Found Validation] ----------");
        
        log.info("Scenario Name: {}", Hooks.scenario.getName());
        
        log.info("Actual Status Code: {}, Expected Status Code: {}", response.getStatusCode(), expectedStatusCode);
    }

    @Then("Admin receives {int} Method Not Allowed for delete user")
    
    public void admin_receives_method_not_allowed_for_delete_user(Integer expectedStatusCode) {
    	
        response.then().log().all().statusCode(expectedStatusCode);
        
        assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch for invalid method!");

        log.info("---------- Framework Log [405 Method Not Allowed Validation] ----------");
        
        log.info("Scenario Name: {}", Hooks.scenario.getName());
        
        log.info("Actual Status Code: {}, Expected Status Code: {}", response.getStatusCode(), expectedStatusCode);
    }
}
