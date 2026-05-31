package com.parabank.stepdefinitions;

import com.parabank.api.AccountApiClient;
import com.parabank.api.CustomerApiClient;
import com.parabank.api.TransferApiClient;
import com.parabank.context.TestContext;
import com.parabank.utils.FrameworkConfig;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

public class FundTransferAPISteps {

    private final TestContext testContext;
    private final CustomerApiClient customerApiClient = new CustomerApiClient();
    private final AccountApiClient accountApiClient   = new AccountApiClient();
    private final TransferApiClient transferApiClient = new TransferApiClient();

    public FundTransferAPISteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @Given("a customer is authenticated via API with two accounts")
    public void aCustomerIsAuthenticatedViaAPIWithTwoAccounts() {
        Response loginResponse = customerApiClient.login(
                FrameworkConfig.getDefaultUsername(),
                FrameworkConfig.getDefaultPassword());
        int customerId = loginResponse.jsonPath().getInt("id");

        Response accountsResponse = customerApiClient.getAccountsByCustomerId(customerId);
        testContext.setFromAccountId(String.valueOf(accountsResponse.jsonPath().getInt("[0].id")));
        testContext.setToAccountId(String.valueOf(accountsResponse.jsonPath().getInt("[1].id")));

        Response accountResponse = accountApiClient.getAccountDetails(
                Integer.parseInt(testContext.getFromAccountId()));
        testContext.setInitialBalance(String.format("%.2f",
                accountResponse.jsonPath().getDouble("balance")));
    }

    @Given("a customer is authenticated via API with one account")
    public void aCustomerIsAuthenticatedViaAPIWithOneAccount() {
        Response loginResponse = customerApiClient.login(
                FrameworkConfig.getDefaultUsername(),
                FrameworkConfig.getDefaultPassword());
        int customerId = loginResponse.jsonPath().getInt("id");

        Response accountsResponse = customerApiClient.getAccountsByCustomerId(customerId);
        testContext.setFromAccountId(String.valueOf(accountsResponse.jsonPath().getInt("[0].id")));
    }

    @When("a valid fund transfer is requested via API")
    public void aValidFundTransferIsRequestedViaAPI() {
        String amount = "100.00";
        testContext.setTransferAmount(amount);

        Response response = transferApiClient.transfer(
                Integer.parseInt(testContext.getFromAccountId()),
                Integer.parseInt(testContext.getToAccountId()),
                Double.parseDouble(amount));

        testContext.setApiStatusCode(response.getStatusCode());
        testContext.setApiResponseBody(response.getBody().asString());
    }

    @When("a fund transfer exceeding the account balance is requested")
    public void aFundTransferExceedingTheAccountBalanceIsRequested() {
        String amount = "999999.99";
        testContext.setTransferAmount(amount);

        Response response = transferApiClient.transfer(
                Integer.parseInt(testContext.getFromAccountId()),
                Integer.parseInt(testContext.getToAccountId()),
                Double.parseDouble(amount));

        testContext.setApiStatusCode(response.getStatusCode());
        testContext.setApiResponseBody(response.getBody().asString());
    }

    @When("a fund transfer to a non-existent account is requested")
    public void aFundTransferToNonExistentAccountIsRequested() {
        Response response = transferApiClient.transfer(
                Integer.parseInt(testContext.getFromAccountId()),
                99999999,
                100.00);

        testContext.setApiStatusCode(response.getStatusCode());
        testContext.setApiResponseBody(response.getBody().asString());
    }

    @When("a fund transfer with a negative amount is requested")
    public void aFundTransferWithNegativeAmountIsRequested() {
        Response response = transferApiClient.transfer(
                Integer.parseInt(testContext.getFromAccountId()),
                Integer.parseInt(testContext.getToAccountId()),
                -50.00);

        testContext.setApiStatusCode(response.getStatusCode());
        testContext.setApiResponseBody(response.getBody().asString());
    }

    @Then("the transfer should succeed with status 200")
    public void theTransferShouldSucceedWithStatus200() {
        Assert.assertEquals(testContext.getApiStatusCode(), 200,
                "Expected transfer to succeed with 200 but got: " + testContext.getApiStatusCode());
    }

    @Then("the transfer should be processed with status 200")
    public void theTransferShouldBeProcessedWithStatus200() {
        Assert.assertEquals(testContext.getApiStatusCode(), 200,
                "Expected transfer API to return 200 but got: " + testContext.getApiStatusCode());
    }

    @And("the account balance should reflect the transferred amount")
    public void theAccountBalanceShouldReflectTheTransferredAmount() {
        Response response = accountApiClient.getAccountDetails(
                Integer.parseInt(testContext.getFromAccountId()));
        double currentBalance = response.jsonPath().getDouble("balance");
        double expectedBalance = Double.parseDouble(testContext.getInitialBalance())
                - Double.parseDouble(testContext.getTransferAmount());

        Assert.assertEquals(currentBalance, expectedBalance, 0.01,
                "Account balance did not reflect the API transfer");
    }

    @Then("the transfer should fail with an error response")
    public void theTransferShouldFailWithAnErrorResponse() {
        Assert.assertNotEquals(testContext.getApiStatusCode(), 200,
                "Expected an error response but the transfer succeeded with 200");
    }
}