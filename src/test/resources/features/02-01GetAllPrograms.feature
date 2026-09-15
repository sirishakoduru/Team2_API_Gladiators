@authToken @GetProgram
Feature: GET Program Module- All Programs, ProgramId, Program by All Users 

Background: 
Given Admin sets Authorization to Bearer Token
 
            
 Scenario Outline: Validate Program GET API Scenarios
       Given Admin creates "<scenarioName>" in program API
       When Admin sends a GET request for "<scenarioName>" in program API
       Then Admin validates GET response for"<scenarioName>" in program API

Examples:
		|scenarioName                                 |
		|Get all Programs Valid EndPoint              |
		|Get all Programs Invalid EndPoint            |
		|Get all Programs Invalid Method              |
		|Get Program By ID with Valid ProgramID       |
		|Get Program By ID with Invalid ProgramID     |
		|Get Program By ID Invalid BaseURI            |
		|Get Program By ID Invalid EndPoint           |
		|Get All Programs with Users Valid EndPoint   |
		|Get All Programs with Users Invalid EndPoint |
		|Get All Programs with Users Invalid Method   |