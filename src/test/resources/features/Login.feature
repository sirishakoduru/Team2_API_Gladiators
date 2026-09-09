Feature: User Sign In [Post Operation]

Scenario: Admin generates token with valid credential
Given Admin creates POST request with valid credentials
When Admin sends a HTTPS request to the valid endpoint
Then Admin receives 200 ok with auto generated token