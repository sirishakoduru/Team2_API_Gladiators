@authToken @AddNewProgram
Feature: Add New Program[Post Operation]

Background:
Given Admin sets Authorization to Bearer Token

 @PostProgram_01
 Scenario: Admin creates a program with valid request body and authorization
 Given Admin creates POST request with valid credentials in program API
 When Admin sends a HTTPS request to the valid endpoint in program API
 Then Admin receives 201 Created Status with response body in program API
 
 @PostProgram_02
 Scenario: Admin creates a program with only mandatory field
 Given Admin creates POST Request with only mandatory field in program API
 When Admin sends a HTTPS request to the valid endpoint in program API
 Then Admin receives 201 Created Status with response body in program API
 
 @PostProgram_03
 Scenario: Admin creates a program with program description length between 4 and 25 characters
 Given Admin creates POST Request with program description length between 4 and 25 characters in program API
 When Admin sends a HTTPS request to the valid endpoint in program API
 Then Admin receives 201 Created Status with response body in program API
 
 @PostProgram_04
 Scenario: Admin creates a program with program name length between 4 and 25 characters
 Given Admin creates POST Request with program name length between 4 and 25 characters in program API
 When Admin sends a HTTPS request to the valid endpoint in program API
 Then Admin receives 201 Created Status with response body in program API
 
  @PostProgram_05
 Scenario: Admin creates a program with invalid token
 Given Admin creates POST Request with invalid token in program API
 Then Admin receives 401 Unauthorized in program API
  
 @PostProgram_06
 Scenario: Admin creates a program with invalid endpoint
 Given Admin creates POST Request with valid request body in program API
 When Admin sends a HTTPS request to the invalid endpoint in program API
 Then Admin receives 404 not found  Status with message and boolean success details in program API
   
 @PostProgram_07
 Scenario: Admin creates a program with invalid content type
 Given Admin creates POST Request with valid request body in program API
 When Admin sends a HTTPS request to the valid endpoint in program API
 Then Admin receives 415 unsupported media type in program API
  
 @PostProgram_08
 Scenario: Admin creates a program with invalid method
 Given Admin creates invalid Request with valid request body in program API
 When Admin sends a HTTPS request to the valid endpoint in program API
 Then Admin receives 405 Method Not Allowed in program API
 
@PostProgram_09
Scenario Outline: Validate POST Program API Negative scenarios
Given Admin creates "<scenarioName>" POST Request in program API
When Admin sends a POST request to the valid endpoint in program API
Then Admin validates POST response for "<scenarioName>" in program API

Examples:
|scenarioName           					                                     |
|Admin creates a program with already existing program name                      |
|Admin creates a program with trailing space in program name                     |
|Admin creates a program with only numbers in program name                       |
|Admin creates a program with invalid status keyword                             |
|Admin creates a program without Program name                                    |
|Admin creates a program with Program name length less than desired length       |
|Admin creates a program with empty payload                                      |
|Admin creates a program with alpha & special char in program description        |
|Admin creates a program with program description length more than desired length|

