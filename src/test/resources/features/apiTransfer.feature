Feature: Fund Transfer via API

  @smoke @api
  Scenario: Successfully transfer funds between accounts via API
    Given a customer is authenticated via API with two accounts
    When a valid fund transfer is requested via API
    Then the transfer should succeed with status 200
    And the account balance should reflect the transferred amount

  @regression @api
  Scenario: ParaBank API accepts transfer amount exceeding account balance
    Given a customer is authenticated via API with two accounts
    When a fund transfer exceeding the account balance is requested
    Then the transfer should be processed with status 200

  @regression @api
  Scenario: Transfer to non-existent account returns error via API
    Given a customer is authenticated via API with one account
    When a fund transfer to a non-existent account is requested
    Then the transfer should fail with an error response

  @regression @api
  Scenario: ParaBank API accepts negative transfer amount
    Given a customer is authenticated via API with two accounts
    When a fund transfer with a negative amount is requested
    Then the transfer should be processed with status 200