package com.parabank.base;

import org.openqa.selenium.WebDriver;

public final class DriverFactory {

    private static WebDriver driver;

    private DriverFactory() {
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driverInstance) {
        driver = driverInstance;
    }
}