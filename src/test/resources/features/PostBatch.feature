@authToken @postBatch
Feature: Add New Batch [Post Operation]

  Background:
    Given a new program is created for batch testing

  @positive
  Scenario: Admin creates batch with valid data 
   Given Admin prepares a POST request payload for the current batch scenario
    When Admin sends a POST request to the valid endpoint in Batch API
    Then the API response status matches the expected status code
    
    @negative @fieldValidation
  Scenario Outline: Admin creates batch with invalid field parameters
    Given Admin prepares a POST request payload for batch test case "<TestCaseName>"
    When Admin sends a POST request to the valid endpoint in Batch API
    Then the API response status matches the expected status code

    Examples:
      | TestCaseName                                                                |
      | Admin creates batch with only optional fields                               |
      | Admin creates batch without underscore format in batch name                 |
      | Admin creates batch with hyphen format in batch name                        |
      | Admin creates batch with characters in the suffix of batch name             |
      | Admin creates batch with special characters in the suffix of batch name     |
      | Admin creates batch with batch name length more than 28 characters in total |
      | Admin creates batch with batch name length less than 6 characters in total  |
      | Admin creates batch with batch description less than 4 characters           |
      | Admin creates batch with batch description more than 25 characters          |
      | Admin creates batch with random characters in status field                  |
      | Admin creates batch with random numbers in status field                     |
      | Admin creates batch with special characters in status field                 |
      | Admin creates batch with number of classes length less than 1               |
      | Admin creates batch with number of classes length more than 99              |