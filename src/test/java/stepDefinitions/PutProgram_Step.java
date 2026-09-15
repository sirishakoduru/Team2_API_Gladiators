package stepDefinitions;

import org.testng.Assert;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import base.BaseClass;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.JsonTestData;
import pojo.TestcaseWrapper;
import utilities.JsonReader;
import io.cucumber.java.en.*;


public class PutProgram_Step extends BaseClass {
	
	Response response;
    RequestSpecification request;
	String endpoint;
    JsonTestData testData;
	private static final Logger log = LoggerFactory.getLogger(PutProgram_Step.class);
	
	
@Given("Admin creates {string} PUT Request in program API")
public void admin_creates_put_request_in_program_api(String scenarioName) {
	
	
	 TestcaseWrapper wrapper = getTestData();
	 Assert.assertNotNull( wrapper, "TestcaseWrapper is null" ); 
	 List<JsonTestData> testDataList = wrapper.getPutRequest();
	 Assert.assertNotNull( testDataList, "PutRequest test data is null" );
	 testData = JsonReader.getTestDataByScenarioName( scenarioName, testDataList ); 
	 Assert.assertNotNull( testData, "Test data not found for scenario: " + scenarioName );
	 endpoint = testData.getEndpoint();
	 
	 if (endpoint != null && endpoint.contains("{programId}"))
	 { 
		 if (scenarioName.equalsIgnoreCase( "Admin updates a program with invalid program Id")) 
	 { 
			 endpoint = endpoint.replace( "{programId}", "999999" ); }
	 else 
	 { endpoint = endpoint.replace( "{programId}", "112" ); 
	 } 
	 }
	 
if (
	scenarioName.equalsIgnoreCase( "Admin updates a program without Authorization"))
{ 
	request = requestWithoutAuth(); 
	} else 
	{ request = BaseClass.createRequest(); 
	}
if(testData.getContentType()!= null) {
	request.header("Content-Type",testData.getContentType());
}
if(testData.getProgramRequest()!= null) {
	request.body(testData.getProgramRequest());
}
log.info("Test Case : {}", testData.getTestcaseName());
log.info("Method : {}", testData.getMethod());
log.info("Endpoint : {}", endpoint); 
log.info("Expected Status : {}", testData.getexpectedStatusCode());
}
	
	
@When("Admin sends a PUT request to the valid endpoint in program API")
public void admin_sends_a_put_request_to_the_valid_endpoint_in_program_api() {
	
    Assert.assertNotNull(request,"RequestSpecification is null");
    Assert.assertNotNull(endpoint,"Endpoint is null");

    Assert.assertNotNull(
            testData,
            "Test data is null");

	   if (testData.getMethod() != null
               && testData.getMethod().equalsIgnoreCase("POST")) {

           response = request.post(endpoint);

       } else {

           response = request.put(endpoint);
       }
	   log.info("Status Code : {}", response.getStatusCode()); 
	   log.info("Response : {}", response.asPrettyString());  
	
}

@Then("Admin validates PUT response for {string} in program API")
public void admin_validates_put_response_for_in_program_api(
        String scenarioName) {

    Assert.assertNotNull(
            response,
            "PUT response is null");

   	 TestcaseWrapper wrapper = getTestData();
     List<JsonTestData> testDataList =
             wrapper.getPutRequest();

	    testData = JsonReader.getTestDataByScenarioName(
                scenarioName,
                testDataList
        );       
	            
    Assert.assertNotNull(
            testData,
            "Test data not found for: " + scenarioName);


    int expectedStatus =
            testData.getexpectedStatusCode();

    int actualStatus =
            response.getStatusCode();
    
    log.info("Expected Status : {}", expectedStatus);
    log.info("Actual Status   : {}", actualStatus);

    Assert.assertEquals(
            actualStatus,
            expectedStatus,
            "PUT response status code does not match"
    );
}

@Given("Admin creates PUT Request without auth and with request body")
public void admin_creates_put_request_without_auth_and_with_request_body() {
	 TestcaseWrapper wrapper = getTestData();
	 Assert.assertNotNull( wrapper, "TestcaseWrapper is null" ); 
	 List<JsonTestData> testDataList = wrapper.getPutRequest();
	 Assert.assertNotNull( testDataList, "PutRequest test data is null" );
	 
	 testData = JsonReader.getTestDataByScenarioName( "Admin updates a program without Authorization", testDataList ); 
	 Assert.assertNotNull( testData, "Test data not found for scenario: " + testDataList );
	 endpoint = testData.getEndpoint();
	 request = requestWithoutAuth();
	 if(testData.getContentType()!= null) {
		 request.body(testData.getProgramRequest());
		}
		if(testData.getProgramRequest()!= null) {
			request.header("Content-Type",testData.getProgramRequest());
		}
		
		log.info("PUT WITHOUT AUTHORIZATION"); 
		log.info("Test Case : {}", testData.getTestcaseName()); 
		log.info("Method : {}", testData.getMethod());
		log.info("Endpoint : {}", endpoint);
}

@When("Admin sends the PUT request without Authorization")
public void admin_sends_the_put_request_without_authorization() {
	Assert.assertNotNull(request, "RequestSpecification is null");
    Assert.assertNotNull(endpoint, "Endpoint is null");
	response = request.put(endpoint);

    log.info("Status Code : {}", response.getStatusCode());
    log.info("Response : {}", response.asPrettyString());
}

@Then("Admin receives {int} Unauthorized for PUT")
public void admin_receives_unauthorized_for_put(Integer int1) {
    Assert.assertNotNull(response, "Response is null");
    Assert.assertEquals(response.getStatusCode(),401,"Expected 401 Unauthorized for PUT");
}


}