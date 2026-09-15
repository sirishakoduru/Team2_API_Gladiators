@authToken
Feature: Creating User with Role [Post Operation]

   Background: Admin Authorization
    Given Admin sets Authorization to Bearer Token
@authToken @validcase1
  Scenario Outline: Admin creates a specific user role with valid request body and authorization
    Given Admin creates POST request with valid request body for Role id "<role_id>", Role name "<role_name>", and Role desc "<role_desc>"
    When Admin sends a HTTPS request to the valid user endpoint
    Then Admin receives 201 Created Status with response body.
    Examples:
      | role_id | role_name     | role_desc  |
      | R01     | ROLE_ADMIN    | LMS_Admin  |
      | R02     | ROLE_STAFF    | LMS_Staff  |
      | R03     | ROLE_STUDENT  | LMS_USER   |


@authToken @validcase2
  Scenario: Admin creates a user with only mandatory field
    Given Admin creates POST request with only mandatory field
    When Admin sends a HTTPS request to the valid user endpoint
    Then Admin receives 201 Created Status with response body.

@authToken @emptyfields
 Scenario Outline: Admin attempts to create a user with a missing required field
  Given Admin creates POST request with "<field>" field empty
  When Admin sends a HTTPS request to the valid user endpoint
  Then Admin receives 400 Bad Request Status with valid error message

  Examples:
    | field        |
    | FirstName    |
    | LastName     |
    | Location     |
    | Time Zone    |
    | Visa Status  |
    | Role Id      |
    | Role Status  |
    | Login Status |
    | email        |
    | Phone Number |

@authToken @duplicatefields
  Scenario Outline: Admin attempts to create a user with duplicate or unique constraint violations
    Given Admin creates POST request with <duplicate_field> that is already associated with another user
    When Admin sends a HTTPS request to the valid user endpoint
    Then Admin receives 400 Bad Request Status with valid error message

    Examples:
| duplicate_field |
| email id        |
| phone number    |
@authToken @invalidfields
  Scenario Outline: Admin attempts to create a user with invalid field values or formats
    Given Admin creates POST request with invalid <field_type>
    When Admin sends a HTTPS request to the valid user endpoint
    Then Admin receives 400 Bad Request Status with valid error message

    Examples:
| field_type            |
| userEduPg             |
| userEduUg             |
| first name            |
| last name             |
| middle name           |
| userLinkedinUrl       |
| userLocation          |
| email format          |
| phone number format   |
| user Role Id          |
| user Role Status      |
| user time zone        |
| user visa status      |

@edgecases @invalidrequest @authToken
  Scenario Outline: Admin attempts user creation handling specific protocol and structural edge cases
    Given Admin creates <request_type>
    When Admin sends a HTTPS request to the <endpoint_type>
    Then Admin receives a <status_code>                                     
Examples:
      | condition            | request_type                            | endpoint_type         | status_code                                                    |
      | empty payload        | POST request with empty payload         | valid user endpoint   | 400 Bad Request                                                |
      | invalid token        | POST request with invalid token         | valid user endpoint   | 401 Unauthorized                                               |
      | invalid endpoint     | POST request with valid request body    | invalid user endpoint | 404 not found Status with message and boolean success details |
      | invalid content type | POST request with invalid content type  | valid user endpoint   | 415 unsupported media type                                     |
      | invalid HTTP method  | invalid request with valid request body | valid user endpoint   | 405 Method Not Allowed                                         |


