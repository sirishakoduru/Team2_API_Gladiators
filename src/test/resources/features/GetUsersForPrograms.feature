@authToken
Feature: Gets Users for Program

Background: Admin sets Authorization 
Given Admin sets Authorization to Bearer Token

Scenario: Admin retrieves all users linked to program id with valid Endpoint
Given Admin creates GET request for the LMS API with valid program id
When Admin sends a HTTPS request to the valid endpoint of user
Then Admin receives 200 OK Status with response body.                                                        

Scenario: Admin retrieves all users linked to program with invalid program id
Given Admin creates GET request for the LMS API with invalid program id
When Admin sends a HTTPS request to the valid endpoint of user
Then Admin receives 404 not found Status with program id not found message

Scenario: Admin retrieves all users linked to program id with invalid Endpoint
Given Admin creates GET request for the LMS API
When Admin sends a HTTPS request to the invalid endpoint
Then Admin receives 404 Not Found  

Scenario: Admin retrieves all users linked to program id with invalid Method
Given Admin creates invalid request for the LMS API
When Admin sends a HTTPS request to the valid endpoint and invalid method of user
Then Admin receives 405 Method Not Allowed 
