package com.parabank.utils;

import com.parabank.constants.FrameworkConstants;
import org.openqa.selenium.WebDriver;

public final class BrowserManager {

    private BrowserManager() {
    }

    public static WebDriver createDriver() {

        String browser =
                ConfigReader.getProperty(
                        FrameworkConstants.BROWSER
                );

        validateBrowser(browser);

        return BrowserFactory.getBrowser(browser);
    }

    private static void validateBrowser(String browser) {

        if (browser == null || browser.isBlank()) {
            throw new IllegalArgumentException(
                    "Browser configuration is missing."
            );
        }
    }
}