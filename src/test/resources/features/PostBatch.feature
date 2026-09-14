@authToken @postBatch
Feature: Add New Batch [Post Operation]

  Background:
    Given a new program is created for batch testing

  @positive
  Scenario Outline: Admin creates batch with valid data 
   Given Admin prepares a POST request payload for the current batch scenario
    When Admin sends a POST request to the valid endpoint in Batch API
    Then the API response status matches the expected status code