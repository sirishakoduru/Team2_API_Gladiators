Feature: Get all Users,Active users,Emails of All Users with Active Status

Background: Admin sets Authorization 
Given Admin sets Authorization to Bearer Token

@authToken
Scenario Outline: Validate User GET API scenarios
    Given Admin creates GET request for "<scenarioName>" in User
    When Admin sends GET request for "<scenarioName>" in User
    Then Admin validates GET response for "<scenarioName>" in User
     Examples:
      |scenarioName|
      |Get all users with valid Endpoint|
      |Get all users with invalid Endpoint|
      |Get all users with invalid Method|
      |Get all active users with valid Endpoint|
      |Get all active users with invalid Endpoint|
      |Get all active users with invalid Method|
      |Get all active users e-mail id with valid Endpoint|
      |Get all active users e-mail id with invalid Endpoint|
      |Get all active users e-mail id with invalid Method|
      |Get all user role with valid Endpoint|
      |Get all user role with invalid Endpoint|
      |Get all user role with invalid Method|
      
      
      
      