Feature: API Account Balance Validation

  @regression @api
  Scenario: Verify balance API returns error for a non-existent account
    When the customer queries the balance for a non-existent account
    Then the balance API should return a not found error

  @regression @api
  Scenario: Verify invalid login credentials are rejected by the API
    When a login request is made with invalid credentials
    Then the login should be rejected with a bad request error