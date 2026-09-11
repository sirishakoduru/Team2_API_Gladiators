package stepDefinitions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import base.BaseClass;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.cucumber.java.en.*;
public class GETPrograms_Step extends BaseClass {
	
	Response response;
	RequestSpecification request;
	private static final Logger log = LoggerFactory.getLogger(GETPrograms_Step.class);
	private String scenarioName;

@Given("Admin creates {string} in program API")
public void admin_creates_in_program_api(String scenarioName) {
	this.scenarioName = scenarioName;
	log.info("Creating request for:{ }",scenarioName);
	request = BaseClass.createRequest();
}

@When("Admin sends a GET request for {string} in program API")
public void admin_sends_a_get_request_for_in_program_api(String scenarioName) {
	this.scenarioName = scenarioName;
	log.info("Sending GET request for :{}" , scenarioName);
	switch(scenarioName.trim()) {
	case "Get all Programs Valid EndPoint":
		response = request.when().get("/allPrograms"); 
		break;
	case "Get all Programs Invalid EndPoint":
		response = request.when().get("/allPrograms"); 
		break;
	case "Get all Programs Invalid Method":
		response = request.when().get("/allprograms");
		break;
		
	case "Get Program By ID with Valid ProgramID":
		response= request.when().get("/");
		break;
	default:	
	throw new IllegalArgumentException( "Unknown scenario: " + scenarioName);		

	}
	
	log.info("Response Status Code: {}", response.getStatusCode());
	log.info("Response Body: {}", response.asPrettyString());
}

@Then("Admin validates GET response for{string} in program API")
public void admin_validates_get_response_for_get_all_programs_valid_end_point_in_program_api(String scenarioName) {
	this.scenarioName = scenarioName;
    log.info("Validating GET response for Valid EndPoint" ,scenarioName);
    switch(scenarioName.trim()) {
    case "Get all Programs Valid EndPoint":
    	response.then().statusCode(200);
    	log.info("Expected Status: 200");
    	log.info("Actual Status:{}", response.getStatusCode());
    	break;
    case "Get all Programs Invalid EndPoint":
    	response.then().statusCode(404);
    	log.info("Expected Status: 404");
       	log.info("Actual Status:{}", response.getStatusCode());
    	break;
    case "Get all Programs Invalid Method":
    	response.then().statusCode(405);
    	log.info("Expected Status: 405");
    	log.info("Actual Status:{}", response.getStatusCode());
    	break;	
    case "Get Program By ID with Valid ProgramID":
    	response.then().statusCode(200);
    	log.info("Expected Status: 200");
    	log.info("Actual Status:{}", response.getStatusCode());
    	break;
    case "Get Program By ID with Invalid ProgramID":
    	response.then().statusCode(404);
    	log.info("Expected Status: 404");
    	log.info("Actual Status:{}", response.getStatusCode());
    	break;
    case "Get Program By ID Invalid BaseURI":
    	response.then().statusCode(404);
    	log.info("Expected Status: 404");
    	log.info("Actual Status:{}", response.getStatusCode());
    	break;
    case "Get Program By ID Invalid EndPoint":
    	response.then().statusCode(404);
    	log.info("Expected Status: 404");
    	log.info("Actual Status:{}", response.getStatusCode());
    	break;
    	
    case "Get All Programs with Users Valid EndPoint":
    	response.then().statusCode(200);
    	log.info("Expected Status: 200");
    	log.info("Actual Status:{}", response.getStatusCode());
    	break;
    case "Get All Programs with Users Invalid EndPoint":
    	response.then().statusCode(404);
    	log.info("Expected Status: 404");
    	log.info("Actual Status:{}", response.getStatusCode());
    	break;
    case "Get All Programs with Users Invalid Method":
    	response.then().statusCode(405);
    	log.info("Expected Status: 405");
    	log.info("Actual Status:{}", response.getStatusCode());
    	break;
    default:	
    	throw new IllegalArgumentException( "Unknown scenario: " + scenarioName);
    
    }
    
    log.info("GET response validation completed for: {}", scenarioName);
}



	
}

