package com.parabank.stepdefinitions;

import com.parabank.models.Customer;
import com.parabank.pages.RegisterPage;
import com.parabank.pages.RegistrationSuccessPage;
import com.parabank.tests.BaseTest;
import com.parabank.utils.TestDataFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class RegistrationSteps extends BaseTest {

    private Customer customer;
    private RegistrationSuccessPage successPage;

    @Given("user is on registration page")
    public void userIsOnRegistrationPage() {

        setUp();

        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.clickRegisterLink();
    }

    @When("user registers with valid customer details")
    public void userRegistersWithValidCustomerDetails() {

        customer = TestDataFactory.createCustomer();

        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.registerCustomer(customer);
    }

    @Then("account should be created successfully")
    public void accountShouldBeCreatedSuccessfully() {

        successPage = new RegistrationSuccessPage(driver);

        Assert.assertTrue(successPage.getSuccessMessage().contains("Your account was created successfully"));

        tearDown();
    }
}

