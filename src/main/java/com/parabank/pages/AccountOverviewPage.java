package com.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class AccountOverviewPage {

    private final WebDriver driver;

    private static final By ACCOUNT_ROWS = By.xpath("//table[contains(@id,'accountTable')]/tbody/tr");

    public AccountOverviewPage(WebDriver driver) {
        this.driver = driver;
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
}