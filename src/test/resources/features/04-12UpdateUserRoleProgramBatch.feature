@authToken
Feature: Update User Role Program Batch Status [Put Operation] 

Background: Background: Admin sets Authorization 
Given Admin sets Authorization to Bearer Token

Scenario: Admin updates user role program batch status 
Given Admin creates PUT request for the LMS API
When Admin sends HTTPS request to the valid endpoint
Then Admin receives 200 OK Status with updated value in response body.  

Scenario: Admin updates user role program batch status with invalid program id
Given Admin creates PUT request with invalid program id
When Admin sends HTTPS request to the valid endpoint
Then Admin receives 400 Bad Request Status with valid error message

Scenario: Admin updates user role program batch status with program id field empty
Given Admin creates PUT request with program id field empty
When Admin sends HTTPS request to the valid endpoint
Then Admin receives 400 Bad Request Status with valid error message  

Scenario: Admin updates user role program batch status with invalid batch id
Given Admin creates PUT request with invalid batch id
When Admin sends HTTPS request to the valid endpoint
Then Admin receives 400 Bad Request Status with valid error message                                     