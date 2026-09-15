@noauthuser
Feature: Creating User with Role [Post Operation and No Authorization]
  Background: Admin sets No Auth
    Given Admin sets Authorization to No Auth.
    
  Scenario: Admin creates POST request without auth and with valid request body
    Given Admin creates POST request without auth and with valid request body
    When Admin sends a HTTPS request to the valid user endpoint without auth
    Then Admin receives 401 unauthorized for request without authorization