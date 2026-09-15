@authToken @PUTProgram

Feature: Update Program by ProgramId [Put Operation]
Background:
    Given Admin sets Authorization to Bearer Token

Scenario Outline: Admin validates PUT Program API
Given Admin creates "<scenarioName>" PUT Request in program API
When Admin sends a PUT request to the valid endpoint in program API
Then Admin validates PUT response for "<scenarioName>" in program API

Examples:
|scenarioName       |
|Admin updates a program with valid program id|
|Admin updates a program with invalid program id|
|Admin updates a program with already existing program name in request body|
|Admin updates a program without request body|
|Admin updates a program with invalid baseURI|
|Admin updates a program with invalid method|
|Admin updates a program with invalid endpoint|


   
   Scenario: Admin updates a program without Authorization 
   Given Admin creates PUT Request without auth and with request body
    When Admin sends the PUT request without Authorization
    Then Admin receives 401 Unauthorized for PUT
