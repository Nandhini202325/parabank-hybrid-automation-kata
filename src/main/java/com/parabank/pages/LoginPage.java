package com.parabank.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final By USERNAME = By.name("username");

    private static final By PASSWORD = By.name("password");

    private static final By LOGIN_BUTTON = By.cssSelector("input[value='Log In']");

    private static final By REGISTER_LINK = By.linkText("Register");

    public LoginPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void login(String username, String password) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME)).sendKeys(username);

        driver.findElement(PASSWORD).sendKeys(password);

        driver.findElement(LOGIN_BUTTON).click();
    }

    public void clickRegisterLink() {

        wait.until(ExpectedConditions.elementToBeClickable(REGISTER_LINK)).click();
    }
}