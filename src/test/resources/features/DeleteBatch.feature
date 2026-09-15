@authToken @deleteBatch
Feature: Delete Batch by BatchId [Delete Operation]

Background:
Given a batch is created for delete testing

  @positive
  Scenario: Admin deletes a batch with valid batch id
    When Admin sends a DELETE request to the valid batch ID endpoint in Batch API
    Then the API response status matches the expected status code

  @negative
  Scenario: Admin deletes a batch with invalid batch id
    When Admin sends a DELETE request to an invalid batch ID endpoint in Batch API
    Then the API response status matches the expected status codes