package com.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AccountOverviewPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final By ACCOUNT_ROWS = By.xpath("//table[contains(@id,'accountTable')]/tbody/tr");

    public AccountOverviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public int getTotalAccounts() {
        return driver.findElements(ACCOUNT_ROWS).size();
    }

    public String getAccountNumber(int row) {
        return driver.findElement(By.xpath("//table[contains(@id,'accountTable')]/tbody/tr[" + row + "]/td[1]")).getText().trim();
    }

    public String getBalance(int row) {
        return driver.findElement(By.xpath("//table[contains(@id,'accountTable')]/tbody/tr[" + row + "]/td[2]")).getText().replace("$", "").replace(",", "").trim();
    }

    public String getAvailableAmount(int row) {
        return driver.findElement(By.xpath("//table[contains(@id,'accountTable')]/tbody/tr[" + row + "]/td[3]")).getText().replace("$", "").replace(",", "").trim();
    }

    public String getBalanceByAccountId(String accountId) {
        wait.until(driver -> driver.findElements(ACCOUNT_ROWS)
                .stream()
                .anyMatch(row -> row.getText().contains(accountId)));

        List<org.openqa.selenium.WebElement> rows = driver.findElements(ACCOUNT_ROWS);
        for (int i = 1; i <= rows.size(); i++) {
            String cellAccountId = driver.findElement(
                    By.xpath("//table[contains(@id,'accountTable')]/tbody/tr[" + i + "]/td[1]")
            ).getText().trim();
            if (cellAccountId.equals(accountId)) {
                return getBalance(i);
            }
        }
        throw new RuntimeException("Account ID " + accountId + " not found in accounts overview table");
    }
}