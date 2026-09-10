package stepDefinitions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import base.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostUserByRole_NoAuth_Step extends BaseClass {
	
	Response response;
	RequestSpecification request;
	private static final Logger log = LoggerFactory.getLogger(PostUserByRole_NoAuth_Step.class);
	
@Given("Admin sets Authorization to No Auth.")
public void admin_sets_authorization_to_no_auth() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("Then  Admin receives {int} unauthorized for request without authorization")
public void admin_receives_unauthorized(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}
}
