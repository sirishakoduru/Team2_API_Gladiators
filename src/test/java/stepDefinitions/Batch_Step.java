package stepDefinitions;

import base.BaseClass;
import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.BatchRequest;
import pojo.JsonTestData;
import pojo.ProgramRequest;
import utilities.JsonReader;
import utilities.ScenarioContext;

public class Batch_Step extends BaseClass {

    private Response response;
    private RequestSpecification request;

    @Given("a new program is created for batch testing")
    public void a_new_program_is_created_for_batch_testing() {
        // 1. Specify the test case name defined in BatchTestData.json
        String testCaseName = "Add New Program";

        // 2. Look up the test data dynamically without index position
        JsonTestData postData = JsonReader.getTestDataByScenarioName(
                testCaseName, 
                getTestData().getPostRequest()
        );

        if (postData == null) {
            throw new IllegalStateException("Test case '" + testCaseName + "' not found in BatchTestData.json");
        }

        String endpoint = postData.getEndpoint();
        if (endpoint == null || endpoint.trim().isEmpty()) {
            throw new IllegalStateException("Endpoint missing from BatchTestData.json");
        }

        ProgramRequest programRequest = postData.getProgramRequest();

        // 3. Send API request
        Response programResponse = createRequest()
                .body(programRequest)
                .when()
                .post(endpoint);

        System.out.println("STATUS CODE: " + programResponse.getStatusCode());
        System.out.println("RESPONSE BODY: " + programResponse.getBody().asString());

        programResponse.then().statusCode(201);

        // 4. Store created details in ScenarioContext for batch steps
        int id = programResponse.jsonPath().getInt("programId");
        String name = programResponse.jsonPath().getString("programName");

        ScenarioContext.set("programId", id);
        ScenarioContext.set("programName", name);
    }
    
    @Given("Admin prepares a POST request payload for the current batch scenario")
    public void admin_prepares_post_request_payload_for_test_case() {
        JsonTestData testCase = JsonReader.getTestDataByScenarioName("Admin creates batch with valid data", getTestData().getPostRequest());
        BatchRequest payload = testCase.getBatchRequest();

        String programName = ScenarioContext.get("programName", String.class);
        int programId = ScenarioContext.get("programId", Integer.class);

        if (payload.getProgramId() == 0) payload.setProgramId(programId);
        if (payload.getProgramName() == null) payload.setProgramName(programName);
        if (payload.getBatchName() == null) {
            long batchNumber = System.currentTimeMillis() % 100000;
            payload.setBatchName(programName + "_" + batchNumber);
        }

        request = createRequest().body(payload);
        ScenarioContext.set("expectedStatusCode", testCase.getexpectedStatusCode());
    }
    
    @When("Admin sends a POST request to the valid endpoint in Batch API")
    public void admin_sends_post_request_to_valid_endpoint() {
        response = request.when().post("/batches");
    }

    @Then("the API response status matches the expected status code")
    public void the_api_response_status_matches_expected() {
        int expected = ScenarioContext.get("expectedStatusCode", Integer.class);
        response.then().statusCode(expected);
        if (expected == 201) {
            ScenarioContext.set("batchId", response.jsonPath().getInt("batchId"));
        }
    }
}

    