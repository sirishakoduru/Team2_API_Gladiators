Feature: Get all Users,Active users

@authToken
Scenario Outline: Validate User GET API scenarios
    Given Admin creates GET request for "<scenarioName>" in program API
    When Admin sends GET request for "<scenarioName>" in program API
    Then Admin validates GET response for "<scenarioName>" in program API
     Examples:
      |scenarioName|
      |Get all users with valid Endpoint|
      |Get all users with invalid Endpoint|
      |Get all users with invalid Method|
      |Get all active users with valid Endpoint|
      |Get all active users with invalid Endpoint|
      