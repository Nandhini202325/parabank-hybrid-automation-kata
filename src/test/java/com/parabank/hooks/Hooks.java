package com.parabank.hooks;

import com.parabank.base.DriverFactory;
import com.parabank.utils.BrowserManager;
import com.parabank.utils.FrameworkConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class Hooks {

    @Before("@ui")
    public void setup() {

        WebDriver driver = BrowserManager.createDriver();

        DriverFactory.setDriver(driver);

        driver.manage().window().maximize();

        driver.get(FrameworkConfig.getBaseUrl());
    }

    @After("@ui")
    public void teardown() {

        if (DriverFactory.getDriver() != null) {
            DriverFactory.getDriver().quit();
            DriverFactory.setDriver(null);
        }
    }
}