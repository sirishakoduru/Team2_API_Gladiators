package stepDefinitions;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
import pojo.ForgotPasswordRequest;
import pojo.JsonTestData;
import pojo.LoginRequest;
import pojo.TestcaseWrapper;
import utilities.ConfigReader;
import utilities.JsonReader;
import utilities.TokenManager;

public class Login_Step extends BaseClass {
	
	Response response;
	RequestSpecification request;
	JsonTestData testData;
    ForgotPasswordRequest forgotPasswordRequest;

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

	@Given("Admin creates POST request for {string} for the login API")
	public void admin_creates_post_request_for_for_the_login_api(String scenarioName) throws IOException {
		log.info("Negative Scenario for the login API : {}", scenarioName);
		BaseClass.init();
		request = BaseClass.createRequest();
	    TestcaseWrapper wrapper = getTestData();

	    testData = JsonReader.getTestDataByScenarioName(scenarioName,wrapper.getPostRequest());

	    request.contentType(testData.getContentType());

	    if ("text/plain".equalsIgnoreCase(testData.getContentType())) {
	        request.body(testData.getRawBody()); 

	    } else if (scenarioName.equalsIgnoreCase("Login With Invalid Base URL")) {
	        request = createInvalidBaseRequest();
	  } 
	    
	    else if (testData.getLoginRequest() != null) {
	        request.body(testData.getLoginRequest()); 
	    }
	  
	
	    
	}

	@When("Admin sends request to the valid endpoint for the login API")
	public void admin_sends_request_to_the_valid_endpoint_for_the_login_api() {
		 if (testData.getMethod().equalsIgnoreCase("GET")) {

		        response = request
		                .log().all()
		                .when()
		                .get(testData.getEndpoint());
		     log.info("Login API call with invalid method {} for {}",testData.getMethod(), testData.getTestcaseName());   
		        
		 }else {

		        response = request
		                .log().all()
		                .when()
		                .post(testData.getEndpoint());
		        
		        log.info("Forgot password confirm API call with valid method {} for {} ",testData.getMethod(), testData.getTestcaseName()); 
		 }
		 
	}

	@Then("Admin receives the response for {string} for the login API")
	public void admin_receives_the_response_for_for_the_login_api(String scenarioName) {
		log.info("Response Body:\n{}", response.asPrettyString());
		
		log.info("Validating the Actual Status Code  {} for the login API: {}", scenarioName,response.getStatusCode());
	    
		log.info("Validating the Expected Status Code {} for the login API: {}", scenarioName, testData.getexpectedStatusCode());
		
		 assertEquals(response.getStatusCode(), testData.getexpectedStatusCode());
	}

	@Then("Admin Validates response body matches JSON schema in {string} Login API")
	public void admin_validates_response_body_matches_json_schema_in_login_api(String scenarioName) {
		  log.info("Validating schema for login scenario {}", scenarioName);

		    if (scenarioName.equalsIgnoreCase("Login with invalid content type") ||
		            scenarioName.equalsIgnoreCase("Login With Invalid Base URL") ||
		            scenarioName.equalsIgnoreCase("Login With Invalid Endpoint")) {
		    	
		    	log.info("Login API response boby not found for the validation {} ", scenarioName);
		    	
		    	return;
		    }
		    
		    
		    if (response.getStatusCode() == 200 || response.getStatusCode() == 201) {

		        response.then()
		                .assertThat()
		                .body(matchesJsonSchemaInClasspath("schema/loginSchema.json"));
		}

}

	@Then("Admin validates {string} Login API response headers")
	public void admin_validates_login_api_response_headers(String scenarioName) {
		 log.info("Validating headers for login API {}",scenarioName);

		    String actualContentType = response.getHeader("Content-Type");
		    
		    
		    if (actualContentType == null || !actualContentType.contains("application/json")){
		    	
		    	log.info("Login API response with content rype {} ", actualContentType);
		    	
		    	return;
		    }
		    
		    assertTrue( actualContentType.contains("application/json"), "Expected Content-Type not found" + actualContentType );

 
	}

	@Then("Admin validates {string} Login API response time")
	public void admin_validates_login_api_response_time(String scenarioName) {
		long responseTime = response.getTime();
		
		assertTrue( response.getTime() <= 3000, "API response time is greater than 3000");

		    log.info("Login API response time: {} ms for the scenario {} ", responseTime, scenarioName);


	}

	@Then("Admin validates {string} Login API response all")
	public void admin_validates_login_api_response_all(String scenarioName) {
		log.info("Validating response Token value in the response {}", scenarioName);
		
		  if (scenarioName.equalsIgnoreCase("Login with invalid content type") ||
			        scenarioName.equalsIgnoreCase("Login With Invalid Base URL") ||
			        scenarioName.equalsIgnoreCase("Login With Invalid Endpoint")) {

			        log.info("Token is not generated for the scenario: {}", scenarioName);
			        return;
			    }
	assertTrue( response.jsonPath().getString("token") == null, "Token is populated");

	}

	@Given("Admin creates forgot password POST request with {string}")
	public void admin_creates_forgot_password_post_request_with(String scenarioName) {
		 request = createRequest();
		 TestcaseWrapper wrapper = getTestData();
		 testData = JsonReader.getTestDataByScenarioName(scenarioName, wrapper.getPostRequest());
		  
		 request.contentType(testData.getContentType());
		
		 
		    if ("text/plain".equalsIgnoreCase(testData.getContentType())) {
		        request.body(testData.getRawBody()); 

		    } else if (testData.getForgotPasswordRequest() != null) {
		    	   request.body(testData.getForgotPasswordRequest());
		    }
	  
	     
	     log.info("Admin creates POST request for forgot password API {}", scenarioName);
	}

	@When("Admin sends a HTTPS request to the valid endpoint for forgot password API with {string}")
	public void admin_sends_a_https_request_to_the_valid_endpoint_for_forgot_password_api_with(String scenarioName) {
		if (testData.getMethod().equalsIgnoreCase("GET")) {

	        response = request
	                .log().all()
	                .when()
	                .get(testData.getEndpoint());
	     log.info("Forgot password confirm API call with invalid method {} for {}",testData.getMethod(), scenarioName);   
	        
	 }else {

	        response = request
	                .log().all()
	                .when()
	                .post(testData.getEndpoint());
	        
	        log.info("Forgot password confirm API call with valid method {} for {} ",testData.getMethod(), scenarioName); 
	 }
	 

	}

	@Then("Admin validates forgot password response with {string}")
	public void admin_validates_forgot_password_response_with(String scenarioName) {
		log.info("Response Body:\n{}", response.asPrettyString());
		
		log.info("Validating the Actual Status Code  {} for the forgot password confirmation API: {}", scenarioName,response.getStatusCode());
	    
		log.info("Validating the Expected Status Code {} for the forgot password confirmation API: {}", scenarioName, testData.getexpectedStatusCode());
		
		 assertEquals(response.getStatusCode(), testData.getexpectedStatusCode());
	}

	@Then("Admin Validates response body matches JSON schema in forgot password API")
	public void admin_validates_response_body_matches_json_schema_in_forgot_password_api() {
		if (response.getStatusCode() == 201) {
			  try {      
				response.then().log().all()
			                .assertThat()
			                .body(matchesJsonSchemaInClasspath("schema/loginSchema.json"));
			      	        log.info("Schema validation Passed successfully for the forgot password confirmation API response");
				
			    } catch (AssertionError e) {

			        log.error("Schema validation Failed for the forgot password confirmation API response");
			        throw e; 
			    }

		}
	}

}
