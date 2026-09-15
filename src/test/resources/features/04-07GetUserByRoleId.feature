@authToken
Feature: Gets Users by RoleId

Background: Admin sets Authorization 
Given Admin sets Authorization to Bearer Token

Scenario: Admin retrieves all users with admin role valid Endpoint
Given Admin creates GET request for the LMS API
When Admin sends a HTTPS request to the valid endpoint with R01 role id
Then Admin receives 200 OK Status with response body.                                                        

Scenario:Admin retrieves all users with staff role valid Endpoint
Given Admin creates GET request for the LMS API
When Admin sends a HTTPS request to the valid endpoint with R02 role id
Then Admin receives 200 OK Status with response body.                                                        

Scenario: Admin retrieves all users with student role valid Endpoint
Given Admin creates GET request for the LMS API
When Admin sends a HTTPS request to the valid endpoint with R03 role id
Then Admin receives 200 OK Status with response body.                                                        

Scenario: Admin retrieves all users info with invalid Endpoint
Given Admin creates GET request for the LMS API
When Admin sends a HTTPS request to the invalid endpoint
Then Admin receives 404 Not Found 

Scenario: Admin retrieves all users info with invalid Method
Given Admin creates invalid request for the LMS API
When Admin sends a HTTPS request to the valid endpoint and invalid method of user
Then Admin receives 405 Method Not Allowed 
