@authToken @getBatch
Feature: Get All Batches API Validation

  @positive
  Scenario: Get all batches with valid Endpoint
    Given Admin prepares a GET request for test case "Get all batches with valid Endpoint"
    When Admin sends a GET request to the Batch API
    Then the API response status matches the expected status code

  @negative
  Scenario Outline: Admin receives error status code for invalid GET request parameters
    Given Admin prepares a GET request for test case "<TestCaseName>"
    When Admin sends a GET request to the Batch API
    Then the API response status matches the expected status code

    Examples:
      | TestCaseName                          |
      | Get all batches with invalid Endpoint |
      | Get all batches with invalid Method   |