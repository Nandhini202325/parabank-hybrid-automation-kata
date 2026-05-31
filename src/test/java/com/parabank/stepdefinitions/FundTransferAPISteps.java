package com.parabank.stepdefinitions;

import com.parabank.api.AccountApiClient;
import com.parabank.api.ApiClientFactory;
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

import java.util.List;
import java.util.Map;

public class FundTransferAPISteps {

    private final TestContext testContext;
    private final CustomerApiClient customerApiClient = ApiClientFactory.customer();
    private final AccountApiClient accountApiClient   = ApiClientFactory.account();
    private final TransferApiClient transferApiClient = ApiClientFactory.transfer();

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
        List<Map<String, Object>> accounts = accountsResponse.jsonPath().getList("$");
        Assert.assertFalse(accounts.isEmpty(), "Expected customer to have at least one source account");

        int firstAccountId = getAccountId(accounts, 0);
        int secondAccountId = getOrCreateSecondAccount(customerId, accounts, firstAccountId);

        testContext.setFromAccountId(firstAccountId);
        testContext.setToAccountId(secondAccountId);

        Response accountResponse = accountApiClient.getAccountDetails(testContext.getFromAccountId());
        testContext.setInitialBalance(accountResponse.jsonPath().getDouble("balance"));
    }

    @Given("a customer is authenticated via API with one account")
    public void aCustomerIsAuthenticatedViaAPIWithOneAccount() {

        Response loginResponse = customerApiClient.login(
                FrameworkConfig.getDefaultUsername(),
                FrameworkConfig.getDefaultPassword());
        int customerId = loginResponse.jsonPath().getInt("id");

        Response accountsResponse = customerApiClient.getAccountsByCustomerId(customerId);
        List<Map<String, Object>> accounts = accountsResponse.jsonPath().getList("$");
        Assert.assertFalse(accounts.isEmpty(), "Expected customer to have at least one source account");
        testContext.setFromAccountId(getAccountId(accounts, 0));
    }

    @When("a valid fund transfer is requested via API")
    public void aValidFundTransferIsRequestedViaAPI() {

        double amount = 100.00;
        testContext.setTransferAmount(amount);

        Response response = transferApiClient.transfer(
                testContext.getFromAccountId(),
                testContext.getToAccountId(),
                amount);

        testContext.setApiStatusCode(response.getStatusCode());
        testContext.setApiResponseBody(response.getBody().asString());
    }

    @When("a fund transfer exceeding the account balance is requested")
    public void aFundTransferExceedingTheAccountBalanceIsRequested() {

        double amount = 999999.99;
        testContext.setTransferAmount(amount);

        Response response = transferApiClient.transfer(
                testContext.getFromAccountId(),
                testContext.getToAccountId(),
                amount);

        testContext.setApiStatusCode(response.getStatusCode());
        testContext.setApiResponseBody(response.getBody().asString());
    }

    @When("a fund transfer to a non-existent account is requested")
    public void aFundTransferToNonExistentAccountIsRequested() {

        Response response = transferApiClient.transfer(
                testContext.getFromAccountId(),
                99999999,
                100.00);

        testContext.setApiStatusCode(response.getStatusCode());
        testContext.setApiResponseBody(response.getBody().asString());
    }

    @When("a fund transfer with a negative amount is requested")
    public void aFundTransferWithNegativeAmountIsRequested() {

        Response response = transferApiClient.transfer(
                testContext.getFromAccountId(),
                testContext.getToAccountId(),
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

        Response response = accountApiClient.getAccountDetails(testContext.getFromAccountId());
        double currentBalance = response.jsonPath().getDouble("balance");
        double expectedBalance = testContext.getInitialBalance() - testContext.getTransferAmount();

        Assert.assertEquals(currentBalance, expectedBalance, 0.01,
                "Account balance did not reflect the API transfer");
    }

    @Then("the transfer should fail with an error response")
    public void theTransferShouldFailWithAnErrorResponse() {

        Assert.assertNotEquals(testContext.getApiStatusCode(), 200,
                "Expected an error response but the transfer succeeded with 200");
    }

    private int getOrCreateSecondAccount(int customerId, List<Map<String, Object>> accounts, int firstAccountId) {

        if (accounts.size() > 1) {
            return getAccountId(accounts, 1);
        }

        Response createResponse = transferApiClient.createAccount(customerId, firstAccountId);
        Assert.assertEquals(createResponse.getStatusCode(), 200,
                "Expected second account creation to return 200 but got: " + createResponse.getStatusCode());
        return createResponse.jsonPath().getInt("id");
    }

    private int getAccountId(List<Map<String, Object>> accounts, int index) {

        Object accountId = accounts.get(index).get("id");
        Assert.assertTrue(accountId instanceof Number,
                "Expected account id at index " + index + " but got: " + accountId);
        return ((Number) accountId).intValue();
    }
}