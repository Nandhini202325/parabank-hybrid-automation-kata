package com.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver driver;

    private static final By USERNAME = By.name("username");

    private static final By PASSWORD = By.name("password");

    private static final By LOGIN_BUTTON = By.cssSelector("input[value='Log In']");

    private static final By REGISTER_LINK = By.linkText("Register");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String password) {

        driver.findElement(USERNAME).sendKeys(username);

        driver.findElement(PASSWORD).sendKeys(password);

        driver.findElement(LOGIN_BUTTON).click();
    }

    public void clickRegisterLink() {
        driver.findElement(REGISTER_LINK).click();
    }
}
