package com.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TransferFundsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final By FROM_ACCOUNT    = By.id("fromAccountId");
    private static final By TO_ACCOUNT      = By.id("toAccountId");
    private static final By AMOUNT          = By.id("amount");
    private static final By TRANSFER_BUTTON = By.xpath("//input[@value='Transfer']");
    private static final By TRANSFER_RESULT = By.cssSelector("#showResult .title, #showResult h1, #showResult h2");
    private static final By ERROR_MESSAGE   = By.cssSelector(".error");

    public TransferFundsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void selectFromAccount(String accountId) {
        selectAccount(FROM_ACCOUNT, accountId);
    }

    public void selectToAccount(String accountId) {
        selectAccount(TO_ACCOUNT, accountId);
    }

    private void selectAccount(By accountDropdown, String accountId) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountDropdown));
        wait.until(driver -> new Select(driver.findElement(accountDropdown))
                .getOptions()
                .stream()
                .anyMatch(option -> accountId.equals(option.getAttribute("value"))));
        new Select(driver.findElement(accountDropdown)).selectByValue(accountId);
    }

    public void enterAmount(String amount) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(AMOUNT));
        driver.findElement(AMOUNT).clear();
        driver.findElement(AMOUNT).sendKeys(amount);
    }

    public void clearAmount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(AMOUNT));
        driver.findElement(AMOUNT).clear();
    }

    public void clickTransfer() {
        driver.findElement(TRANSFER_BUTTON).click();
    }

    public boolean isTransferSuccessful() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(TRANSFER_RESULT));
            return driver.findElement(TRANSFER_RESULT).getText().contains("Transfer Complete");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isErrorDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE));
            return driver.findElement(ERROR_MESSAGE).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}