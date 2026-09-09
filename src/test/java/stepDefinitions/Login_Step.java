package stepDefinitions;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import base.BaseClass;
import constants.Endpoints;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.LoginRequest;
import utilities.ConfigReader;
import utilities.TokenManager;

public class Login_Step {
	
	Response response;
	RequestSpecification request;
	private static final Logger log = LoggerFactory.getLogger(Login_Step.class);
	
	@Given("Admin creates POST request with valid credentials")
	public void admin_creates_post_request_with_valid_credentials() throws IOException {
	   
		BaseClass.init();
		request = BaseClass.requestWithoutAuth();
		LoginRequest login = new LoginRequest();
		login.setUserLoginEmailId(ConfigReader.getProperty("userLoginEmailId"));
		login.setPassword(ConfigReader.getProperty("password"));
		request.body(login);
	}

	@When("Admin sends a HTTPS request to the valid endpoint")
	public void admin_sends_a_https_request_to_the_valid_endpoint() {
	   response = request.when().log().all().post(Endpoints.login);
	   
	}

	@Then("Admin receives {int} ok with auto generated token")
	public void admin_receives_ok_with_auto_generated_token(Integer expectedStatusCode) {
	   
		response.then().statusCode(expectedStatusCode);
		assertEquals(expectedStatusCode.intValue(), response.getStatusCode());
		String token = response.jsonPath().getString("token");
		TokenManager.setToken(token);
		System.out.println("Token saved successfully: " + token);
		log.info("Token saved successfully: {}", token);
	    log.info("Actual Status Code: {}", response.getStatusCode());
	    log.info("Expected Status Code: {}", expectedStatusCode);
	    log.info(response.asPrettyString());
	    log.info("Response Body:\n" + response.asPrettyString());
	}

}
