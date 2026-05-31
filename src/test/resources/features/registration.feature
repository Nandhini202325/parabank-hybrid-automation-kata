Feature: Customer Registration

  Scenario: Register a new customer successfully

    Given user is on registration page
    When user registers with valid customer details
    Then account should be created successfully