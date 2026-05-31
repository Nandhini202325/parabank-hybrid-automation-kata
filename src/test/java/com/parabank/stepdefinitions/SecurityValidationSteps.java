package com.parabank.stepdefinitions;

import com.parabank.api.CustomerApiClient;
import com.parabank.context.TestContext;
import com.parabank.utils.FrameworkConfig;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

public class SecurityValidationSteps {

    private final TestContext testContext;
    private final CustomerApiClient customerApiClient = new CustomerApiClient();

    public SecurityValidationSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @When("a login request is made with valid credentials")
    public void aLoginRequestIsMadeWithValidCredentials() {
        Response response = customerApiClient.login(
                FrameworkConfig.getDefaultUsername(),
                FrameworkConfig.getDefaultPassword());

        testContext.setApiStatusCode(response.getStatusCode());
        testContext.setApiResponseBody(response.getBody().asString());
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int expectedStatus) {
        Assert.assertEquals(testContext.getApiStatusCode(), expectedStatus,
                "Expected status " + expectedStatus + " but got: " + testContext.getApiStatusCode());
    }

    @And("the response should contain a valid customer id")
    public void theResponseShouldContainAValidCustomerId() {
        Response response = customerApiClient.login(
                FrameworkConfig.getDefaultUsername(),
                FrameworkConfig.getDefaultPassword());

        int customerId = response.jsonPath().getInt("id");
        Assert.assertTrue(customerId > 0,
                "Expected a valid customer id greater than 0 but got: " + customerId);
    }

    @When("a login request is made with an incorrect password")
    public void aLoginRequestIsMadeWithIncorrectPassword() {
        Response response = customerApiClient.login(
                FrameworkConfig.getDefaultUsername(), "incorrectPassword");
        testContext.setApiStatusCode(response.getStatusCode());
    }

    @When("a login request is made with a non-existent username")
    public void aLoginRequestIsMadeWithNonExistentUsername() {
        Response response = customerApiClient.login("nonExistentUser", "anyPassword");
        testContext.setApiStatusCode(response.getStatusCode());
    }

    @When("a login request is made with a malicious SQL injection payload")
    public void aLoginRequestIsMadeWithMaliciousSQLInjectionPayload() {
        Response response = customerApiClient.login("' OR 1=1--", "anything");
        testContext.setApiStatusCode(response.getStatusCode());
    }
}