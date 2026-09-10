package stepDefinitions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import base.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostUserByRole_Step extends BaseClass {
	
	Response response;
	RequestSpecification request;
	private static final Logger log = LoggerFactory.getLogger(PostUserByRole_Step.class);
@Given("Admin creates POST request with valid request body for Role id {int}")
public void admin_creates_post_request_with_valid_request_body_for_role_id(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("Admin receives {int} Created Status with response body.")
public void admin_receives_created_status_with_response_body(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST request with only mandatory field")
public void admin_creates_post_request_with_only_mandatory_field() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST request with FirstName field empty")
public void admin_creates_post_request_with_first_name_field_empty() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("Admin receives {int} Bad Request Status with valid error message")
public void admin_receives_bad_request_status_with_valid_error_message(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
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

@Then("Admin receives {int} Bad Request")
public void admin_receives_bad_request(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST request with invalid token")
public void admin_creates_post_request_with_invalid_token() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("Admin receives {int} Unauthorized")
public void admin_receives_unauthorized(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST request with valid request body")
public void admin_creates_post_request_with_valid_request_body() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@When("Admin sends a HTTPS request to the invalid endpoint")
public void admin_sends_a_https_request_to_the_invalid_endpoint() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("Admin receives {int} not found Status with message and boolean success details")
public void admin_receives_not_found_status_with_message_and_boolean_success_details(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates POST request with invalid content type")
public void admin_creates_post_request_with_invalid_content_type() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("Admin receives {int} unsupported media type")
public void admin_receives_unsupported_media_type(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("Admin creates invalid request with valid request body")
public void admin_creates_invalid_request_with_valid_request_body() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("Admin receives {int} Method Not Allowed")
public void admin_receives_method_not_allowed(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}
}