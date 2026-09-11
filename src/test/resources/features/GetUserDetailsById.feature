@authToken
Feature:  Get User Details by Id

Background: Admin sets Authorization 
Given Admin sets Authorization to Bearer Token

Scenario: Admin retrieves user details with valid user ID
Given Admin creates GET request with valid user id in endpoint for user details
When Admin sends a HTTPS request to the valid endpoint of user
Then Admin receives 200 OK Status with response body.                                                          

Scenario: Admin retrieves user details with invalid user ID 
Given Admin creates GET request with invalid user id in endpoint
When Admin sends a HTTPS request to the valid endpoint of user
Then Admin receives 404 Not Found Status with message and boolean success details

Scenario: Admin retrieves user details with invalid baseURI
Given Admin creates GET request with invalid baseURI
When Admin sends a HTTPS request to the valid endpoint of user
Then Admin receives 404 Not Found 

Scenario: Admin retrieves user details with invalid Endpoint
Given Admin creates GET request for the LMS API
When Admin sends a HTTPS request to the invalid endpoint
Then Admin receives 404 Not Found 

Scenario: Admin retrieves user details with invalid Method
Given Admin creates invalid request for the LMS API
When Admin sends a HTTPS request to the valid endpoint and invalid method of user
Then Admin receives 405 Method Not Allowed 