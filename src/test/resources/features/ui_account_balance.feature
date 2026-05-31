Feature: UI Account Balance Verification

  @smoke @ui
  Scenario: Verify newly opened account balance matches bank records
    Given a customer has successfully opened a bank account
    When the customer views their account summary
    Then the displayed account balance should match the bank records