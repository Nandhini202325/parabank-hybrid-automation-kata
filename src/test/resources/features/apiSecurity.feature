Feature: API Security Validation

  @smoke @api
  Scenario: Valid credentials return successful login response
    When a login request is made with valid credentials
    Then the response status should be 200
    And the response should contain a valid customer id

  @regression @api
  Scenario: Incorrect password returns bad request response
    When a login request is made with an incorrect password
    Then the response status should be 400

  @regression @api
  Scenario: Non-existent username returns bad request response
    When a login request is made with a non-existent username
    Then the response status should be 400

  @regression @api
  Scenario: SQL injection attempt in login field is rejected
    When a login request is made with a malicious SQL injection payload
    Then the response status should be 403