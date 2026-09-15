@authToken
Feature: Update User RoleId [Put Operation]

Background: Background: Admin sets Authorization 
Given Admin sets Authorization to Bearer Token

@validRole
Scenario: Admin updates user role id with valid role id
Given Admin creates PUT request with valid role id
When Admin sends HTTPS request to the valid endpoint for updating user
Then Admin receives 200 OK Status with updated value in response body for update   

Scenario: Admin updates user role status with valid role status
Given Admin creates PUT request with valid role status
When Admin sends HTTPS request to the valid endpoint for updating user
Then Admin receives 200 OK Status with updated value in response body.                                         

Scenario: Admin updates user role id with invalid role id
Given Admin creates PUT request with invalid role id
When Admin sends a HTTPS request to the valid endpoint for update
Then Admin receives 400 Bad Request Status with valid error message for roleId

Scenario: Admin updates user role status with invalid role status
Given Admin creates PUT request with invalid role status
When Admin sends a HTTPS request to the valid endpoint for update
Then Admin receives 400 Bad Request Status with valid error message for update

Scenario: Admin update same Role id for the user
Given Admin creates PUT request with already existing role id
When Admin sends a HTTPS request to the valid endpoint for update
Then Admin receives 404 Not Found  

Scenario: Admin updates user role id with invalid user id
Given Admin creates PUT request with invalid user id for roleId
When Admin sends a HTTPS request to the invalid endpoint for roleId
Then Admin receives 404 Not Found 

Scenario: Admin updates user role id with invalid Endpoint
Given Admin creates PUT request for the LMS API
When Admin sends a HTTPS request to the invalid endpoint for roleId
Then Admin receives 404 Not Found    

Scenario: Admin updates user role id with invalid Method
Given Admin creates invalid request for the LMS API for roleId
When Admin sends a HTTPS request to the valid endpoint for update
Then Admin receives 405 Method Not Allowed 

Scenario: Admin updates user role id with invalid Content Type
Given Admin creates PUT request with invalid content type
When Admin sends HTTPS request to the endpoint 
Then Admin receives 415 unsupported media type                                 