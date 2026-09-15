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

    private static Integer cachedProgramId;
    private static String cachedProgramName;
    private static Integer cachedBatchIdForUpdate;

    @Given("a new program is created for batch testing")
    public void a_new_program_is_created_for_batch_testing() {
        if (cachedProgramId != null) {
            ScenarioContext.set("programId", cachedProgramId);
            ScenarioContext.set("programName", cachedProgramName);
            return;
        }

        ProgramRequest programRequest = new ProgramRequest();
        programRequest.setProgramName("SDETBatchProgramef");
        programRequest.setProgramDescription("Auto batch test program");
        programRequest.setProgramStatus("ACTIVE");

        Response programResponse = createRequest().body(programRequest).when().post("/saveprogram");
        System.out.println("PROGRAM CREATE BODY: " + programResponse.getBody().asString());
        programResponse.then().statusCode(201);

        cachedProgramId = programResponse.jsonPath().getInt("programId");
        cachedProgramName = programResponse.jsonPath().getString("programName");

        ScenarioContext.set("programId", cachedProgramId);
        ScenarioContext.set("programName", cachedProgramName);
    }
    
    @Given("Admin prepares a POST request payload for the current batch scenario")
    public void admin_prepares_post_request_payload_for_test_case() {
        JsonTestData testCase = JsonReader.getTestDataByScenarioName(Hooks.scenario.getName(), getTestData().getPostRequest());
        
        if (testCase == null) {
            throw new IllegalStateException("Test case '" + Hooks.scenario.getName() + "' not found in PostRequest JSON");
        }

        BatchRequest payload = testCase.getBatchRequest();

        String programName = ScenarioContext.get("programName", String.class);
        Integer programId = ScenarioContext.get("programId", Integer.class);

        if (payload != null && programId != null && payload.getProgramId() == 0) {
            payload.setProgramId(programId);
        }
        if (payload != null && programName != null && payload.getProgramName() == null) {
            payload.setProgramName(programName);
        }
        if (payload != null && payload.getBatchName() == null && programName != null) {
            long batchNumber = System.currentTimeMillis() % 100000;
            payload.setBatchName(programName + "_" + batchNumber);
        }

        request = createRequest().body(payload);
        ScenarioContext.set("expectedStatusCode", testCase.getexpectedStatusCode());
    }
    
    @Given("Admin prepares a POST request payload for batch test case {string}")
    public void admin_prepares_a_post_request_payload_for_batch_test_case(String testCaseName) {
        JsonTestData testCase = JsonReader.getTestDataByScenarioName(testCaseName, getTestData().getPostRequest());

        if (testCase == null) {
            throw new IllegalStateException("Test case '" + testCaseName + "' not found in JSON test data");
        }

        BatchRequest payload = testCase.getBatchRequest();

        if (ScenarioContext.get("programId", Integer.class) != null) {
            int programId = ScenarioContext.get("programId", Integer.class);
            if (payload != null && payload.getProgramId() == 0) {
                payload.setProgramId(programId);
            }
        }

        if (ScenarioContext.get("programName", String.class) != null) {
            String programName = ScenarioContext.get("programName", String.class);
            if (payload != null && payload.getProgramName() == null) {
                payload.setProgramName(programName);
            }
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
    
    @Given("Admin prepares a GET request for test case {string}")
    public void admin_prepares_a_get_request_for_test_case(String testCaseName) {
        JsonTestData testCase = JsonReader.getTestDataByScenarioName(testCaseName, getTestData().getGetRequest());

        if (testCase == null) {
            throw new IllegalStateException("Test case '" + testCaseName + "' not found in GetRequest JSON");
        }

        if (testCaseName.toLowerCase().contains("without authorization")) {
            request = io.restassured.RestAssured.given()
                    .contentType(io.restassured.http.ContentType.JSON);
        } else {
             request = createRequest().log().all();
        }

        ScenarioContext.set("endpoint", testCase.getEndpoint());
        ScenarioContext.set("httpMethod", testCase.getMethod());
        ScenarioContext.set("expectedStatusCode", testCase.getexpectedStatusCode());
    }

    @When("Admin sends a GET request to the Batch API")
    public void admin_sends_a_get_request_to_the_batch_api() {
        String endpoint = ScenarioContext.get("endpoint", String.class);
        String method = ScenarioContext.get("httpMethod", String.class);

        response = request.when().request(method, endpoint);

        System.out.println("GET RESPONSE CODE: " + response.getStatusCode());
        System.out.println("GET RESPONSE BODY: " + response.getBody().asString());
    }
    
    @Given("a batch is created for update testing")
    public void a_batch_is_created_for_update_testing() {
        if (cachedBatchIdForUpdate != null) return;

        if (cachedProgramId == null) {
            ProgramRequest programRequest = new ProgramRequest();
            programRequest.setProgramName("SDETGetBatchProgram");
            programRequest.setProgramDescription("Program for GET batch");
            programRequest.setProgramStatus("ACTIVE");

            Response programResponse = createRequest().body(programRequest).when().post("/saveprogram");
            System.out.println("PROGRAM CREATE BODY: " + programResponse.getBody().asString());
            programResponse.then().statusCode(201);

            cachedProgramId = programResponse.jsonPath().getInt("programId");
            cachedProgramName = programResponse.jsonPath().getString("programName");
        }

        BatchRequest payload = new BatchRequest();
        payload.setBatchName(cachedProgramName + "_" + (System.currentTimeMillis() % 100000));
        payload.setBatchDescription("Batch for GET test");
        payload.setBatchNoOfClasses(10);
        payload.setBatchStatus("ACTIVE");
        payload.setProgramId(cachedProgramId);
        payload.setProgramName(cachedProgramName);

        Response batchResponse = createRequest().body(payload).when().post("/batches");
        System.out.println("BATCH CREATE BODY: " + batchResponse.getBody().asString());
        batchResponse.then().statusCode(201);
        cachedBatchIdForUpdate = batchResponse.jsonPath().getInt("batchId");
    }
    
    @When("Admin sends a GET request to the batch by ID endpoint in Batch API")
    public void admin_sends_get_request_to_batch_by_id() {
        response = createRequest().when().get("/batches/batchId/" + cachedBatchIdForUpdate);
        ScenarioContext.set("expectedStatusCode", 200);
    }

    @When("Admin sends a GET request to an invalid batch ID endpoint in Batch API")
    public void admin_sends_get_request_to_invalid_batch_id() {
        response = createRequest().when().get("/batches/batchId/999999");
        ScenarioContext.set("expectedStatusCode", 404);
    }
    
    
}

    