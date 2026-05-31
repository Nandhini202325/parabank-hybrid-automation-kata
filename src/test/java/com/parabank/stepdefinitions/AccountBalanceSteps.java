package com.parabank.stepdefinitions;

import com.parabank.api.AccountApiClient;
import com.parabank.api.CustomerApiClient;
import com.parabank.context.TestContext;
import com.parabank.models.Customer;
import com.parabank.pages.AccountOverviewPage;
import com.parabank.pages.RegisterPage;
import com.parabank.tests.BaseTest;
import com.parabank.utils.TestDataFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

public class AccountBalanceSteps extends BaseTest {

    private final TestContext testContext;
    private final CustomerApiClient customerApiClient = new CustomerApiClient();
    private final AccountApiClient accountApiClient = new AccountApiClient();

    public AccountBalanceSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @Given("a customer has successfully opened a bank account")
    public void aCustomerHasSuccessfullyOpenedABankAccount() {

        // Register a new user via UI using dynamically generated credentials
        setUp();

        Customer customer = TestDataFactory.createCustomer();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickRegisterLink();
        registerPage.registerCustomer(customer);

        // Store the registered credentials in TestContext for use across all steps
        testContext.setUsername(customer.getUsername());
        testContext.setPassword(customer.getPassword());

        // Use the registered credentials to fetch account ID via API
        Response loginResponse = customerApiClient.login(
                testContext.getUsername(), testContext.getPassword());
        int customerId = loginResponse.jsonPath().getInt("id");

        Response accountsResponse = customerApiClient.getAccountsByCustomerId(customerId);
        int accountId = accountsResponse.jsonPath().getInt("[0].id");

        testContext.setAccountId(String.valueOf(accountId));
    }

    @When("the customer views their account summary")
    public void theCustomerViewsTheirAccountSummary() {

        // Browser is already open from the Given step (registration auto-redirects to accounts overview)
        AccountOverviewPage overviewPage = new AccountOverviewPage(driver);
        String uiBalance = overviewPage.getBalanceByAccountId(testContext.getAccountId());

        testContext.setUiBalance(uiBalance);
    }

    @Then("the displayed account balance should match the bank records")
    public void theDisplayedAccountBalanceShouldMatchTheBankRecords() {

        try {
            // Fetch the same account's balance from the API using registered credentials
            Response accountResponse = accountApiClient.getAccountDetails(
                    Integer.parseInt(testContext.getAccountId()));
            String apiBalance = String.format("%.2f",
                    accountResponse.jsonPath().getDouble("balance"));

            testContext.setApiBalance(apiBalance);

            Assert.assertEquals(
                    testContext.getUiBalance(),
                    testContext.getApiBalance(),
                    "UI balance does not match API balance for account " + testContext.getAccountId()
            );
        } finally {
            tearDown();
        }
    }
}