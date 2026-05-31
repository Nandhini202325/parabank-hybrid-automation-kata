package com.parabank.stepdefinitions;

import com.parabank.api.AccountApiClient;
import com.parabank.api.ApiClientFactory;
import com.parabank.api.CustomerApiClient;
import com.parabank.base.DriverFactory;
import com.parabank.context.TestContext;
import com.parabank.models.Customer;
import com.parabank.pages.AccountOverviewPage;
import com.parabank.pages.AccountServicesPage;
import com.parabank.pages.RegisterPage;
import com.parabank.utils.TestDataFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

public class AccountBalanceSteps {

    private final TestContext testContext;
    private final CustomerApiClient customerApiClient = ApiClientFactory.customer();
    private final AccountApiClient accountApiClient   = ApiClientFactory.account();

    public AccountBalanceSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @Given("a customer has successfully opened a bank account")
    public void aCustomerHasSuccessfullyOpenedABankAccount() {

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
        int accountId = accountsResponse.jsonPath().getInt("[0].id");
        testContext.setAccountId(accountId);
    }

    @When("the customer views their account summary")
    public void theCustomerViewsTheirAccountSummary() {

        AccountServicesPage accountServicesPage = new AccountServicesPage(DriverFactory.getDriver());
        accountServicesPage.navigateToAccountsOverview();

        AccountOverviewPage overviewPage = new AccountOverviewPage(DriverFactory.getDriver());
        double uiBalance = Double.parseDouble(
                overviewPage.getBalanceByAccountId(String.valueOf(testContext.getAccountId())));
        testContext.setUiBalance(uiBalance);
    }

    @Then("the displayed account balance should match the bank records")
    public void theDisplayedAccountBalanceShouldMatchTheBankRecords() {

        Response accountResponse = accountApiClient.getAccountDetails(testContext.getAccountId());
        double apiBalance = accountResponse.jsonPath().getDouble("balance");

        testContext.setApiBalance(apiBalance);

        Assert.assertEquals(testContext.getUiBalance(), testContext.getApiBalance(), 0.01,
                "UI balance does not match API balance for account " + testContext.getAccountId());
    }

    @When("the customer queries the balance for a non-existent account")
    public void theCustomerQueriesBalanceForNonExistentAccount() {

        Response response = accountApiClient.getAccountDetails(99999999);
        testContext.setApiStatusCode(response.getStatusCode());
    }

    @Then("the balance API should return a not found error")
    public void theBalanceAPIShouldReturnNotFoundError() {

        Assert.assertEquals(testContext.getApiStatusCode(), 400,
                "Expected 400 for non-existent account but got: " + testContext.getApiStatusCode());
    }

    @When("a login request is made with invalid credentials")
    public void aLoginRequestIsMadeWithInvalidCredentials() {

        Response response = customerApiClient.login("invaliduser", "invalidpassword");
        testContext.setApiStatusCode(response.getStatusCode());
    }

    @Then("the login should be rejected with a bad request error")
    public void theLoginShouldBeRejectedWithBadRequestError() {

        Assert.assertEquals(testContext.getApiStatusCode(), 400,
                "Expected 400 for invalid credentials but got: " + testContext.getApiStatusCode());
    }
}