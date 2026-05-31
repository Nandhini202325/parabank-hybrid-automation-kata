Feature: Transfer Funds Between Accounts

  @smoke @ui
  Scenario: Successfully transfer funds between two valid accounts
    Given a registered customer is logged in with two accounts
    When the customer transfers a valid amount to another account
    Then the transfer should complete successfully
    And the account balances should reflect the transfer

  @regression @ui
  Scenario: ParaBank UI accepts transfer amount exceeding account balance
    Given a registered customer is logged in with two accounts
    When the customer attempts to transfer an amount exceeding the balance
    Then the transfer should complete successfully

  @regression @ui
  Scenario: Transfer with empty amount shows validation error
    Given a registered customer is logged in with two accounts
    When the customer submits the transfer form with an empty amount
    Then the transfer should display an error message

  @regression @ui
  Scenario: ParaBank UI accepts transfer from an account to itself
    Given a registered customer is logged in with two accounts
    When the customer attempts to transfer funds to the same account
    Then the transfer should complete successfully