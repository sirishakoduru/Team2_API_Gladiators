@authToken @getBatch
Feature: Get Batch by BatchId [Get Operation]

Background:
Given a batch is created for update testing

  @positive
  Scenario: Admin retrieves a batch with valid batch ID
    When Admin sends a GET request to the batch by ID endpoint in Batch API
    Then the API response status matches the expected status code

  @negative
  Scenario: Admin retrieves a batch with invalid batch ID
    When Admin sends a GET request to an invalid batch ID endpoint in Batch API
    Then the API response status matches the expected status code