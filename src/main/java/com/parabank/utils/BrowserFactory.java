package com.parabank.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public final class BrowserFactory {

    private BrowserFactory() {
    }

    public static WebDriver getBrowser(String browser, boolean headless) {

        return switch (browser.toLowerCase()) {

            case "chrome" -> new ChromeDriver(getChromeOptions(headless));

            case "edge" -> new EdgeDriver(getEdgeOptions(headless));

            default ->
                    throw new IllegalArgumentException(
                            "Unsupported browser: " + browser
                    );
        };
    }

    private static ChromeOptions getChromeOptions(boolean headless) {
        ChromeOptions options = new ChromeOptions();
        addCommonOptions(options, headless);
        return options;
    }

    private static EdgeOptions getEdgeOptions(boolean headless) {
        EdgeOptions options = new EdgeOptions();
        addCommonOptions(options, headless);
        return options;
    }

    private static void addCommonOptions(org.openqa.selenium.chromium.ChromiumOptions<?> options, boolean headless) {
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");

        if (headless) {
            options.addArguments("--headless=new");
        }
    }
}