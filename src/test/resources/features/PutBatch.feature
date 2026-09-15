@authToken @putBatch
Feature: Update Batch by BatchId [Put Operation]

Background:
Given a new program is created for batch testing
Given a batch is created for update testing

  @positive
  Scenario: Admin updates a batch with valid batch ID
    Given Admin prepares a valid PUT request payload for the existing batch
    When Admin sends a PUT request to the valid endpoint in Batch API
    Then the API response status matches the expected status code

  @negative
  Scenario: Admin updates a batch with invalid batch ID
    Given Admin prepares a valid PUT request payload for the existing batch
    When Admin sends a PUT request to an invalid batch ID in Batch API
    Then the API response status matches the expected status code