@authToken @deleteUser
Feature: Delete User [Delete Operation]

  Background: Admin sets Authorization
    Given Admin sets Authorization to Bearer Token for delete user

  @validcase
  Scenario: Admin deletes a user with valid user ID
    Given Admin creates DELETE request with valid user ID in endpoints
    When Admin sends a HTTPS request to the valid delete user endpoint
    Then Admin receives 200 OK Status with response body for delete user

  @invalidcase
  Scenario: Admin deletes a user with invalid user ID
    Given Admin creates DELETE request invalid user ID in endpoints
    When Admin sends a HTTPS request to the valid delete user endpoint
    Then Admin receives 404 Not Found Status for delete user

  @invalidcase
  Scenario: Admin deletes a user with invalid endpoint
    Given Admin creates DELETE request for invalid endpoint
    When Admin sends a HTTPS request to the invalid delete user endpoint
    Then Admin receives 404 Not Found Status for delete user

  @invalidcase
  Scenario: Admin deletes a user with invalid method
    Given Admin creates invalid request for delete user
    When Admin sends a HTTPS request to the valid delete user endpoint
    Then Admin receives 405 Method Not Allowed for delete user
