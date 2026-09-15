@authToken
Feature: Update User Login Status [Put Operation]   

Background: Background: Admin sets Authorization 
Given Admin sets Authorization to Bearer Token

Scenario: Admin updates user login status with valid Endpoint
Given Admin creates PUT request for the LMS API for user login
When Admin sends a HTTPS request to the valid endpoint for update
Then Admin receives 200 OK Status with response body for user login    

Scenario: Admin updates user login status with invalid user id
Given Admin creates PUT request for the LMS API with invalid user id
When Admin sends a HTTPS request to the valid endpoint for update
Then Admin receives 404 not found Status with batch id not found message  

Scenario: Admin updates user with email field empty
Given Admin creates PUT request with email field empty
When Admin sends a HTTPS request to the valid endpoint for update
Then Admin receives 400 Bad Request Status with valid error message

Scenario: Admin updates user with login Status field empty
Given Admin creates PUT request with login status field empty
When Admin sends a HTTPS request to the valid endpoint for update
Then Admin receives 400 Bad Request Status with valid error message  

Scenario: Admin updates user with Status field empty
Given Admin creates PUT request with status field empty
When Admin sends a HTTPS request to the valid endpoint for update
Then Admin receives 400 Bad Request Status with valid error message  

Scenario: Admin updates user with invalid email 
Given Admin creates PUT request with invalid email
When Admin sends a HTTPS request to the valid endpoint for update
Then Admin receives 400 Bad Request Status with valid error message  

Scenario: Admin updates user with invalid login Status
Given Admin creates PUT request with invalid login status
When Admin sends a HTTPS request to the valid endpoint for update
Then Admin receives 400 Bad Request Status with valid error message   

Scenario: Admin updates user with invalid Status
Given Admin creates PUT request with invalid status 
When Admin sends a HTTPS request to the valid endpoint for update
Then Admin receives 400 Bad Request Status with valid error message  

Scenario: Admin updates user with duplicate email 
Given Admin creates PUT request with email id that is already associated with another user
When Admin sends a HTTPS request to the valid endpoint for update
Then Admin receives 400 Bad Request Status with valid error message

Scenario: Admin updates user login status with invalid Endpoint
Given Admin creates PUT request with valid request body
When Admin sends a HTTPS request to the valid endpoint for update
Then Admin receives 404 Not Found 

Scenario: Admin updates user login status with invalid Method
Given Admin creates invalid request for the LMS API for user
When Admin sends POST HTTPS request to the endpoint 
Then Admin receives 405 Method Not Allowed 