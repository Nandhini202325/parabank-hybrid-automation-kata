//package com.parabank.tests;
//
//import com.parabank.models.Customer;
//import com.parabank.pages.RegisterPage;
//import com.parabank.pages.RegistrationSuccessPage;
//import com.parabank.utils.TestDataFactory;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//public class RegisterTest extends BaseTest {
//
//    @Test
//    public void verifyUserRegistration() {
//
//        Customer customer = TestDataFactory.createCustomer();
//
//        RegisterPage registerPage = new RegisterPage(driver);
//
//        registerPage.clickRegisterLink();
//
//        registerPage.registerCustomer(customer);
//
//        RegistrationSuccessPage successPage = new RegistrationSuccessPage(driver);
//
//        Assert.assertTrue(successPage.getSuccessMessage().
//                contains("Your account was created successfully"), "Registration failed");
//    }
//}