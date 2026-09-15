@authToken
Feature: Update User [Put Operation]

Background: Background: Admin sets Authorization 
Given Admin sets Authorization to Bearer Token

@validPut
Scenario: Admin updates user with valid user id 
Given Admin creates PUT request with valid user id
When Admin sends a HTTPS request to the valid endpoint for updating user
Then Admin receives 200 OK Status with updated value in response body.  
     
Scenario: Admin updates user with invalid user id
Given Admin creates PUT request with invalid user id
When Admin sends a HTTPS request to the valid endpoint for update
Then Admin receives 404 Not Found Status with error message

Scenario: Admin updates user by user id with missing mandatory fields
Given Admin creates PUT request with missing mandatory fields in the request body
When Admin sends a HTTPS request to the valid endpoint for update
Then Admin receives 400 Bad Request Status with valid error message for update

Scenario: Admin updates user by user id with duplicate email 
Given Admin creates PUT request with email id that is already associated with another user
When Admin sends a HTTPS request to the valid endpoint for update
Then Admin receives 400 Bad Request Status with valid error message and boolean success details

Scenario: Admin updates user by user id with duplicate phone number
Given Admin creates PUT request with phone number that is already associated  with another user
When Admin sends a HTTPS request to the valid endpoint for update
Then Admin receives 400 Bad Request Status with valid error message for update

Scenario: Admin updates user by user id with invalid userEduPg
Given Admin creates PUT request with invalid userEduPg
When Admin sends a HTTPS request to the valid endpoint for update
Then Admin receives 400 Bad Request Status with valid error message for update

Scenario: Admin updates user by user id with invalid userEduUg
Given Admin creates PUT request with invalid userEduUg
When Admin sends a HTTPS request to the valid endpoint for update
Then Admin receives 400 Bad Request Status with valid error message for update

Scenario: Admin updates user by user id with invalid first name
Given Admin creates PUT request with invalid first name
When Admin sends a HTTPS request to the valid endpoint for update
Then Admin receives 400 Bad Request Status with valid error message for update

Scenario: Admin updates user by user id with invalid last name
Given Admin creates PUT request with invalid last name
When Admin sends a HTTPS request to the valid endpoint for update
Then Admin receives 400 Bad Request Status with valid error message for update

Scenario: Admin updates user by user id with invalid middle name
Given Admin creates PUT request with invalid middle name
When Admin sends a HTTPS request to the valid endpoint for update
Then Admin receives 400 Bad Request Status with valid error message for update

Scenario: Admin updates user by user id with invalid userLinkedinUrl
Given Admin creates PUT request with invalid userLinkedinUrl
When Admin sends a HTTPS request to the valid endpoint for update
Then Admin receives 400 Bad Request Status with valid error message

Scenario: Admin updates user by user id with invalid userLocation
Given Admin creates PUT request with invalid userLocation
When Admin sends a HTTPS request to the valid endpoint for update
Then Admin receives 400 Bad Request Status with valid error message

Scenario: Admin updates user by user id with invalid email format
Given Admin creates PUT request with invalid email format
When Admin sends a HTTPS request to the valid endpoint for update
Then Admin receives 400 Bad Request Status with valid error message

Scenario: Admin updates user by user id with invalid phone number format
Given Admin creates PUT request with invalid phone number format
When Admin sends a HTTPS request to the valid endpoint for update
Then Admin receives 400 Bad Request Status with valid error message

Scenario: Admin updates user by user id with invalid user time zone
Given Admin creates PUT request with invalid user time zone
When Admin sends a HTTPS request to the valid endpoint for update
Then Admin receives 400 Bad Request Status with valid error message

Scenario: Admin updates user by user id with invalid user visa status
Given Admin creates PUT request with invalid user visa status
When Admin sends a HTTPS request to the valid endpoint for update
Then Admin receives 400 Bad Request Status with valid error message

Scenario: Admin updates a user by user id with invalid endpoint
Given Admin creates PUT request with valid request body
When Admin sends a HTTPS request to the invalid endpoint of user
Then Admin receives 404 Not Found  

Scenario: Admin updates a user by user id with invalid method
Given Admin creates POST request with valid request body of update user
When Admin sends POST HTTPS request to the endpoint 
Then Admin receives 405 method not allowed

Scenario: Admin updates a user by user id with invalid content type
Given Admin creates PUT request with invalid content type
When Admin sends HTTPS request to the endpoint 
Then Admin receives 415 unsupported media type
 
                                     