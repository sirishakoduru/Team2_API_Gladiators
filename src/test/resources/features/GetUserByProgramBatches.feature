@authToken
Feature: Gets User by Program Batches

Background: Admin sets Authorization 
Given Admin sets Authorization to Bearer Token

Scenario: Admin retrieves all users linked to batch id with valid Endpoint
Given Admin creates GET request for the LMS API with valid batch id
When Admin sends a HTTPS request to the valid endpoint of user
Then Admin receives 200 OK Status with response body.                                                        

Scenario: Admin retrieves all users linked to batch with invalid batch id
Given Admin creates GET request for the LMS API with invalid batch id
When Admin sends a HTTPS request to the valid endpoint of user
Then Admin receives 404 not found Status with batch id not found message

Scenario: Admin retrieves all users linked to batch id with invalid Endpoint
Given Admin creates GET request for the LMS API
When Admin sends a HTTPS request to the invalid endpoint
Then Admin receives 404 Not Found

Scenario: Admin retrieves all users linked to batch id with invalid Method
Given Admin creates invalid request for the LMS API
When Admin sends a HTTPS request to the valid endpoint and invalid method of user
Then Admin receives 405 Method Not Allowed 
