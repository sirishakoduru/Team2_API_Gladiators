Feature: Get Users with no Auth

Background: Admin sets to no Authorization 
Given Admin sets to no Authorization

Scenario: Admin retrieves all users without Authorization
Given Admin creates GET request without auth
When Admin sends a HTTPS request to the valid endpoint of user
Then Admin receives 401 Unauthorized  

Scenario: Admin retrieves all active users without Authorization
Given Admin creates GET request without auth
When Admin sends a HTTPS request to the valid endpoint of user
Then Admin receives 401 Unauthorized

Scenario: Admin retrieves all active users e-mail id without Authorization
Given Admin creates GET request without auth
When Admin sends a HTTPS request to the valid endpoint of user
Then Admin receives 401 Unauthorized

Scenario: Admin retrieves all user role without Authorization
Given Admin creates GET request without auth 
When Admin sends a HTTPS request to the valid endpoint of user
Then Admin receives 401 Unauthorized

Scenario: Admin retrieves user info without Authorization
Given Admin creates GET request without auth
When Admin sends a HTTPS request to the valid endpoint of user
Then Admin receives 401 Unauthorized

Scenario: Admin retrieves all users info without Authorization
Given Admin creates GET request without auth
When Admin sends a HTTPS request to the valid endpoint of user
Then Admin receives 401 Unauthorized

Scenario: Admin updates a user by user id without Authorization
Given Admin creates PUT request without authorization
When Admin sends HTTPS request to the endpoint 
Then Admin receives 401 Unauthorized