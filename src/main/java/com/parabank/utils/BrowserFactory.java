package com.parabank.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public final class BrowserFactory {

    private BrowserFactory() {
    }

    public static WebDriver getBrowser(String browser) {

        return switch (browser.toLowerCase()) {

            case "chrome" -> new ChromeDriver();

            case "edge" -> new EdgeDriver();

            default ->
                    throw new IllegalArgumentException(
                            "Unsupported browser: " + browser
                    );
        };
    }
}