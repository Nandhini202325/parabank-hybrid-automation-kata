Feature: Hybrid Account Balance Verification

  @smoke @ui
  Scenario: Verify newly opened account balance matches bank records
    Given a customer has successfully opened a bank account
    When the customer views their account summary
    Then the displayed account balance should match the bank records

  @regression @api
  Scenario: Verify balance API returns error for a non-existent account
    When the customer queries the balance for a non-existent account
    Then the balance API should return a not found error

  @regression @api
  Scenario: Verify invalid login credentials are rejected by the API
    When a login request is made with invalid credentials
    Then the login should be rejected with a bad request error