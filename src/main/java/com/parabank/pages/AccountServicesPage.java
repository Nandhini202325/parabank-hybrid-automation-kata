package com.parabank.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountServicesPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By openNewAccountLink = By.linkText("Open New Account");

    private final By accountsOverviewLink = By.linkText("Accounts Overview");

    private final By transferFundsLink = By.linkText("Transfer Funds");

    private final By billPayLink = By.linkText("Bill Pay");

    private final By findTransactionsLink = By.linkText("Find Transactions");

    private final By updateContactInfoLink = By.linkText("Update Contact Info");

    private final By requestLoanLink = By.linkText("Request Loan");

    private final By logoutLink = By.linkText("Log Out");

    public AccountServicesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToOpenNewAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(openNewAccountLink)).click();
    }

    public void navigateToAccountsOverview() {
        wait.until(ExpectedConditions.elementToBeClickable(accountsOverviewLink)).click();
    }

    public void navigateToTransferFunds() {
        wait.until(ExpectedConditions.elementToBeClickable(transferFundsLink)).click();
    }

    public void navigateToBillPay() {
        wait.until(ExpectedConditions.elementToBeClickable(billPayLink)).click();
    }

    public void navigateToFindTransactions() {
        wait.until(ExpectedConditions.elementToBeClickable(findTransactionsLink)).click();
    }

    public void navigateToUpdateContactInfo() {
        wait.until(ExpectedConditions.elementToBeClickable(updateContactInfoLink)).click();
    }

    public void navigateToRequestLoan() {
        wait.until(ExpectedConditions.elementToBeClickable(requestLoanLink)).click();
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }
}