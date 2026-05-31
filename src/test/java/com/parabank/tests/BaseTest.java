package com.parabank.tests;

import com.parabank.base.DriverFactory;
import com.parabank.utils.BrowserManager;
import com.parabank.utils.FrameworkConfig;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {

        driver = BrowserManager.createDriver();

        DriverFactory.setDriver(driver);

        driver.manage().window().maximize();

        driver.get(FrameworkConfig.getBaseUrl());
    }

    @AfterMethod
    public void tearDown() {

        if (DriverFactory.getDriver() != null) {

            DriverFactory.getDriver().quit();
        }
    }
}