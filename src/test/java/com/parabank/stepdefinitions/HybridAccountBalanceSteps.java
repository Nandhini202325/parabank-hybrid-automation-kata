//package com.parabank.stepdefinitions;
//
//import com.parabank.api.AccountApiClient;
//import com.parabank.api.CustomerApiClient;
//import com.parabank.context.TestContext;
//import com.parabank.models.Customer;
//import com.parabank.pages.AccountServicesPage;
//import com.parabank.pages.AccountsOverviewPage;
//import com.parabank.pages.RegisterPage;
//import com.parabank.tests.BaseTest;
//import com.parabank.utils.FrameworkConfig;
//import com.parabank.utils.TestDataFactory;
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import io.restassured.response.Response;
//
//import org.testng.Assert;
//
//public class HybridAccountBalanceSteps extends BaseTest {
//
//    private final TestContext context = new TestContext();
//
//    private Customer customer;
//    private CustomerApiClient customerApiClient;
//
//    @Given("a customer has successfully opened a bank account")
//    public void customerHasSuccessfullyOpenedABankAccount() {
//
//        setUp();
//
//        customer = TestDataFactory.createCustomer();
//
//        driver.get(FrameworkConfig.getBaseUrl() + "/register.htm");
//
//        RegisterPage registerPage = new RegisterPage(driver);
//        registerPage.registerCustomer(customer);
//
//        AccountServicesPage accountServicesPage = new AccountServicesPage(driver);
//
//        accountServicesPage.navigateToAccountsOverview();
//
//        AccountsOverviewPage accountsOverviewPage = new AccountsOverviewPage(driver);
//
//        String accountId = accountsOverviewPage.getFirstAccountNumber();
//
//        context.setAccountId(accountId);
//    }
//
//    @When("the customer views their account summary")
//    public void customerViewsTheirAccountSummary() {
//
//        AccountsOverviewPage accountsOverviewPage = new AccountsOverviewPage(driver);
//
//        String uiBalance = accountsOverviewPage.getFirstAccountBalance();
//
//        context.setUiBalance(uiBalance);
//    }
//
//    @Then("the displayed account balance should match the bank records")
//    public void displayedAccountBalanceShouldMatchBankRecords() {
//
//        String accountId = context.getAccountId();
//
//        AccountApiClient accountApiClient = new AccountApiClient();
//
//        Response response = Integer.parseInt(accountApiClient.getAccountDetails(accountId));
//        System.out.println("Account Id = " + accountId);
//
//        response.prettyPrint();
//
//        String apiBalance = response.xmlPath().getString("account.balance");
//
//        String uiBalance = context.getUiBalance();
//
//        Assert.assertEquals(Double.parseDouble(uiBalance), Double.parseDouble(apiBalance),
//                "UI balance does not match API balance");
//    }
//
//    @Then("the displayed account balance should not match an incorrect bank record")
//    public void displayedAccountBalanceShouldNotMatchAnIncorrectBankRecord() {
//
//        String uiBalance = context.getUiBalance().replace("$", "").replace(",", "");
//
//        Assert.assertNotEquals(Double.parseDouble(uiBalance), 999999.99,
//                "Balance unexpectedly matched");
//
//        tearDown();
//    }
//}