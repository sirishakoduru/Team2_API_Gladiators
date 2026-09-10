Feature: Get Users with no Auth

Scenario: Admin retrieves all users without Authorization
Given Admin creates GET request without auth
When Admin sends a HTTPS request to the valid endpoint
Then Admin receives 401 Unauthorized   