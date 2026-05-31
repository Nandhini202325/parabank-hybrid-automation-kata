package com.parabank.pages;

import com.parabank.models.Customer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    private final WebDriver driver;

    // Locators
    private static final By FIRST_NAME = By.id("customer.firstName");

    private static final By LAST_NAME = By.id("customer.lastName");

    private static final By ADDRESS = By.id("customer.address.street");

    private static final By CITY = By.id("customer.address.city");

    private static final By STATE = By.id("customer.address.state");

    private static final By ZIP_CODE = By.id("customer.address.zipCode");

    private static final By PHONE_NUMBER = By.id("customer.phoneNumber");

    private static final By SSN = By.id("customer.ssn");

    private static final By USERNAME = By.id("customer.username");

    private static final By PASSWORD = By.id("customer.password");

    private static final By CONFIRM_PASSWORD = By.id("repeatedPassword");

    private static final By REGISTER_BUTTON = By.xpath("//input[@value='Register']");

    private static final By REGISTER_LINK = By.linkText("Register");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickRegisterLink() {
        driver.findElement(REGISTER_LINK).click();
    }

    public void enterFirstName(String firstName) {
        driver.findElement(FIRST_NAME).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(LAST_NAME).sendKeys(lastName);
    }

    public void enterAddress(String address) {
        driver.findElement(ADDRESS).sendKeys(address);
    }

    public void enterCity(String city) {
        driver.findElement(CITY).sendKeys(city);
    }

    public void enterState(String state) {
        driver.findElement(STATE).sendKeys(state);
    }

    public void enterZipCode(String zipCode) {
        driver.findElement(ZIP_CODE).sendKeys(zipCode);
    }

    public void enterPhoneNumber(String phoneNumber) {
        driver.findElement(PHONE_NUMBER).sendKeys(phoneNumber);
    }

    public void enterSsn(String ssn) {
        driver.findElement(SSN).sendKeys(ssn);
    }

    public void enterUsername(String username) {
        driver.findElement(USERNAME).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(PASSWORD).sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        driver.findElement(CONFIRM_PASSWORD).sendKeys(confirmPassword);
    }

    public void clickRegisterButton() {
        driver.findElement(REGISTER_BUTTON).click();
    }

    public void registerCustomer(Customer customer) {

        enterFirstName(customer.getFirstName());

        enterLastName(customer.getLastName());

        enterAddress(customer.getAddress());

        enterCity(customer.getCity());

        enterState(customer.getState());

        enterZipCode(customer.getZipCode());

        enterPhoneNumber(customer.getPhoneNumber());

        enterSsn(customer.getSsn());

        enterUsername(customer.getUsername());

        enterPassword(customer.getPassword());

        enterConfirmPassword(customer.getPassword());

        clickRegisterButton();
    }
}