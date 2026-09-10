package stepDefinitions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import base.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetUsers_Step extends BaseClass {
	
	Response response;
	RequestSpecification request;
	private static final Logger log = LoggerFactory.getLogger(GetUsers_Step.class);
	
	@Given("Admin sets Authorization to Bearer Token")
	public void admin_sets_authorization_to_bearer_token() {
		
		request = createRequest();
	}

	@Given("Admin creates GET request for {string} in User")
	public void admin_creates_get_request_for_in_user(String string) {
	    
	}

	@When("Admin sends GET request for {string} in User")
	public void admin_sends_get_request_for_in_user(String string) {
	   
	}

	@Then("Admin validates GET response for {string} in User")
	public void admin_validates_get_response_for_in_user(String string) {
	   
	}



}
