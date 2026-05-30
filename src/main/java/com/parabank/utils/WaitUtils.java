package com.parabank.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {
    private WaitUtils() {}

    public static WebDriverWait getWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(
                Integer.parseInt(
                        ConfigReader.getProperty("explicit.wait")
                )));
    }
}
