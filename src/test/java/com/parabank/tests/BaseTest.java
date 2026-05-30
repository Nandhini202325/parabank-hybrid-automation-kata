package com.parabank.tests;

import com.parabank.base.DriverFactory;
import com.parabank.constants.FrameworkConstants;
import com.parabank.utils.BrowserManager;
import com.parabank.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {

        driver = BrowserManager.createDriver();

        DriverFactory.setDriver(driver);

        driver.get(
                ConfigReader.getProperty(FrameworkConstants.BASE_URL)
        );
    }

    @AfterMethod
    public void tearDown() {

        if (DriverFactory.getDriver() != null) {
            DriverFactory.getDriver().quit();
        }
    }
}