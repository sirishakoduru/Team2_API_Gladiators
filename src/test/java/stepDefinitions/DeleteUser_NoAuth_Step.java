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

public class DeleteUser_NoAuth_Step extends BaseClass {

    RequestSpecification request;
    Response response;

    private static final Logger log = LoggerFactory.getLogger(DeleteUser_NoAuth_Step.class);

    @Given("Admin sets Authorization to No Auth for delete")
    
    public void admin_sets_authorization_to_no_auth_for_delete() throws IOException {
    	
        BaseClass.init();
        request = requestWithoutAuth();
        log.info("Request initialized without Authorization header for Delete User.");
        
    }

    @Given("Admin creates DELETE request without auth")
    
    public void admin_creates_delete_request_without_auth() {
    	
        if (request == null) {
            request = requestWithoutAuth();
        }
        log.info("Prepared DELETE request without authorization");
        
    }

    @When("Admin sends a HTTPS request to the valid user endpoint without auth for delete")
    
    public void admin_sends_a_https_request_to_the_valid_user_endpoint_without_auth_for_delete() {
    	
        if (request == null) {
            request = requestWithoutAuth();
        }

        String endpoint = "/users/123";
        
        log.info("Sending DELETE request without auth to endpoint: {}", endpoint);
        
        response = request.when().log().all().delete(endpoint);

        Hooks.response = this.response;
    }

    @Then("Admin receives {int} Unauthorized for delete user without authorization")
    
    public void admin_receives_unauthorized_for_delete_user_without_authorization(Integer expectedStatusCode) {
    	
        response.then().log().all().statusCode(expectedStatusCode);
        
        assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch for No Auth!");

        log.info("---------- Framework Log [Delete User No Auth Validation] ----------");
        
        log.info("Scenario Name: {}", Hooks.scenario.getName());
        
        log.info("Actual Status Code: {}, Expected Status Code: {}", response.getStatusCode(), expectedStatusCode);
        
        log.info("Response Status Line: {}", response.getStatusLine());
    }
}
