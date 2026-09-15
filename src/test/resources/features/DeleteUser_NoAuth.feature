@noauth @deleteUser
Feature: Delete User without Authorization [Delete Operation and No Authorization]

  Background: Admin sets No Auth
    Given Admin sets Authorization to No Auth for delete

  Scenario: Admin able to delete a user without Authorization
    Given Admin creates DELETE request without auth
    When Admin sends a HTTPS request to the valid user endpoint without auth for delete
    Then Admin receives 401 Unauthorized for delete user without authorization
