@authToken
Feature: Gets Count of Active and Inactive Users

Background: Admin sets Authorization 
Given Admin sets Authorization to Bearer Token

Scenario: Admin retrieves count of all active & inactive users with valid Endpoint
Given Admin creates GET request for the LMS API
When Admin sends a HTTPS request to the valid endpoint of user
Then Admin receives 200 OK Status with response body.   

Scenario: Admin retrieves count of active & inactive users for R01 role with valid Endpoint
Given Admin creates GET request for the LMS API
When Admin sends a HTTPS request to the valid endpoint of user
Then Admin receives 200 OK Status with response body.                                                        

Scenario: Admin retrieves count of active & inactive users for R02 role with valid Endpoint
Given Admin creates GET request for the LMS API
When Admin sends a HTTPS request to the valid endpoint of user
Then Admin receives 200 OK Status with response body.                                                        

Scenario: Admin retrieves count of active & inactive users for R03 role with valid Endpoint
Given Admin creates GET request for the LMS API
When Admin sends a HTTPS request to the valid endpoint of user
Then Admin receives 200 OK Status with response body.  

Scenario: Admin retrieves count of active & inactive users with invalid role id 
Given Admin creates GET request for the LMS API
When Admin sends a HTTPS request to the invalid role id
Then Admin receives 404 not found Status with RoleID not found message

Scenario: Admin retrieves count of all active & inactive users with invalid Endpoint
Given Admin creates GET request for the LMS API
When Admin sends a HTTPS request to the invalid endpoint
Then Admin receives 404 Not Found  

Scenario: Admin retrieves count of all active & inactive users with invalid Method
Given Admin creates invalid request for the LMS API
When Admin sends a HTTPS request to the valid endpoint and invalid method of user
Then Admin receives 405 Method Not Allowed 

                                                            
