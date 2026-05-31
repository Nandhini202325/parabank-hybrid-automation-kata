package com.parabank.stepdefinitions;

import com.parabank.api.AccountApiClient;
import com.parabank.api.CustomerApiClient;
import com.parabank.api.TransferApiClient;
import com.parabank.base.DriverFactory;
import com.parabank.context.TestContext;
import com.parabank.models.Customer;
import com.parabank.pages.AccountServicesPage;
import com.parabank.pages.RegisterPage;
import com.parabank.pages.TransferFundsPage;
import com.parabank.utils.TestDataFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

public class FundTransferUISteps {

    private final TestContext testContext;
    private final CustomerApiClient customerApiClient = new CustomerApiClient();
    private final AccountApiClient accountApiClient   = new AccountApiClient();
    private final TransferApiClient transferApiClient = new TransferApiClient();

    public FundTransferUISteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @Given("a registered customer is logged in with two accounts")
    public void aRegisteredCustomerIsLoggedInWithTwoAccounts() {
        Customer customer = TestDataFactory.createCustomer();

        RegisterPage registerPage = new RegisterPage(DriverFactory.getDriver());
        registerPage.clickRegisterLink();
        registerPage.registerCustomer(customer);

        testContext.setUsername(customer.getUsername());
        testContext.setPassword(customer.getPassword());

        Response loginResponse = customerApiClient.login(
                testContext.getUsername(), testContext.getPassword());
        int customerId = loginResponse.jsonPath().getInt("id");

        Response accountsResponse = customerApiClient.getAccountsByCustomerId(customerId);
        int firstAccountId = accountsResponse.jsonPath().getInt("[0].id");
        testContext.setFromAccountId(String.valueOf(firstAccountId));

        Response createResponse = transferApiClient.createAccount(customerId, firstAccountId);
        int secondAccountId = createResponse.jsonPath().getInt("id");
        testContext.setToAccountId(String.valueOf(secondAccountId));

        Response updatedAccount = accountApiClient.getAccountDetails(firstAccountId);
        testContext.setInitialBalance(String.format("%.2f",
                updatedAccount.jsonPath().getDouble("balance")));
    }

    @When("the customer transfers a valid amount to another account")
    public void theCustomerTransfersAValidAmountToAnotherAccount() {
        String amount = "100";
        testContext.setTransferAmount(amount);

        AccountServicesPage accountServicesPage = new AccountServicesPage(DriverFactory.getDriver());
        accountServicesPage.navigateToTransferFunds();

        TransferFundsPage transferPage = new TransferFundsPage(DriverFactory.getDriver());
        transferPage.selectFromAccount(testContext.getFromAccountId());
        transferPage.selectToAccount(testContext.getToAccountId());
        transferPage.enterAmount(amount);
        transferPage.clickTransfer();
    }

    @When("the customer attempts to transfer an amount exceeding the balance")
    public void theCustomerAttemptsToTransferAnAmountExceedingTheBalance() {
        String amount = "999999";
        testContext.setTransferAmount(amount);

        AccountServicesPage accountServicesPage = new AccountServicesPage(DriverFactory.getDriver());
        accountServicesPage.navigateToTransferFunds();

        TransferFundsPage transferPage = new TransferFundsPage(DriverFactory.getDriver());
        transferPage.selectFromAccount(testContext.getFromAccountId());
        transferPage.selectToAccount(testContext.getToAccountId());
        transferPage.enterAmount(amount);
        transferPage.clickTransfer();
    }

    @When("the customer submits the transfer form with an empty amount")
    public void theCustomerSubmitsTheTransferFormWithAnEmptyAmount() {
        AccountServicesPage accountServicesPage = new AccountServicesPage(DriverFactory.getDriver());
        accountServicesPage.navigateToTransferFunds();

        TransferFundsPage transferPage = new TransferFundsPage(DriverFactory.getDriver());
        transferPage.selectFromAccount(testContext.getFromAccountId());
        transferPage.selectToAccount(testContext.getToAccountId());
        transferPage.clearAmount();
        transferPage.clickTransfer();
    }

    @When("the customer attempts to transfer funds to the same account")
    public void theCustomerAttemptsToTransferFundsToTheSameAccount() {
        AccountServicesPage accountServicesPage = new AccountServicesPage(DriverFactory.getDriver());
        accountServicesPage.navigateToTransferFunds();

        TransferFundsPage transferPage = new TransferFundsPage(DriverFactory.getDriver());
        transferPage.selectFromAccount(testContext.getFromAccountId());
        transferPage.selectToAccount(testContext.getFromAccountId());
        transferPage.enterAmount("100");
        transferPage.clickTransfer();
    }

    @Then("the transfer should complete successfully")
    public void theTransferShouldCompleteSuccessfully() {
        TransferFundsPage transferPage = new TransferFundsPage(DriverFactory.getDriver());
        Assert.assertTrue(transferPage.isTransferSuccessful(),
                "Expected transfer to complete successfully but it did not");
    }

    @And("the account balances should reflect the transfer")
    public void theAccountBalancesShouldReflectTheTransfer() {
        Response response = accountApiClient.getAccountDetails(
                Integer.parseInt(testContext.getFromAccountId()));
        double currentBalance = response.jsonPath().getDouble("balance");
        double expectedBalance = Double.parseDouble(testContext.getInitialBalance())
                - Double.parseDouble(testContext.getTransferAmount());

        Assert.assertEquals(currentBalance, expectedBalance, 0.01,
                "Account balance did not reflect the transfer amount");
    }

    @Then("the transfer should display an error message")
    public void theTransferShouldDisplayAnErrorMessage() {
        TransferFundsPage transferPage = new TransferFundsPage(DriverFactory.getDriver());
        Assert.assertTrue(
                transferPage.isErrorDisplayed() || !transferPage.isTransferSuccessful(),
                "Expected an error message but the transfer completed successfully");
    }
}