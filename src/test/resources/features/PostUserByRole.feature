Feature: Creating User with Role [Post Operation]

   Background: Admin Authorization
    Given Admin sets Authorization to Bearer Token

  Scenario Outline: Admin creates a specific user role with valid request body and authorization
    Given Admin creates POST request with valid request body for Role id <role_id>
    When Admin sends a HTTPS request to the valid endpoint
    Then Admin receives 201 Created Status with response body.

    Examples:
| user_type | role_id |
| admin     | 01      |
| staff     | 02      |
| student   | 03      |

  Scenario: Admin creates a user with only mandatory field
    Given Admin creates POST request with only mandatory field
    When Admin sends a HTTPS request to the valid endpoint
    Then Admin receives 201 Created Status with response body.

  Scenario Outline: Admin attempts to create a user with a missing required field
    Given Admin creates POST request with <field> field empty
    When Admin sends a HTTPS request to the valid endpoint
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

  Scenario Outline: Admin attempts to create a user with duplicate or unique constraint violations
    Given Admin creates POST request with <duplicate_field> that is already associated with another user
    When Admin sends a HTTPS request to the valid endpoint
    Then Admin receives 400 Bad Request Status with valid error message

    Examples:
| duplicate_field |
| email id        |
| phone number    |

  Scenario Outline: Admin attempts to create a user with invalid field values or formats
    Given Admin creates POST request with invalid <field_type>
    When Admin sends a HTTPS request to the valid endpoint
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

  Scenario Outline: Admin attempts user creation handling specific protocol and structural edge cases
    Given Admin creates <request_type>
    When Admin sends a HTTPS request to the <endpoint_type>
    Then Admin receives <status_code>

    Examples:
| condition            | request_type                               | endpoint_type    | status_code                                                    |
| empty payload        | POST request with empty payload            | valid endpoint   | 400 Bad Request                                                |
| invalid token        | POST request with invalid token            | valid endpoint   | 401 Unauthorized                                               |
| invalid endpoint     | POST request with valid request body       | invalid endpoint | 404 not found Status with message and boolean success details |
| invalid content type | POST request with invalid content type     | valid endpoint   | 415 unsupported media type                                     |
| invalid HTTP method  | invalid request with valid request body    | valid endpoint   | 405 Method Not Allowed                                         |


