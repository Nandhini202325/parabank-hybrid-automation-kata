package com.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationSuccessPage {

    private final WebDriver driver;

    private static final By SUCCESS_MESSAGE = By.cssSelector("#rightPanel p");

    public RegistrationSuccessPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getSuccessMessage() {

        return driver.findElement(SUCCESS_MESSAGE).getText();
    }
}