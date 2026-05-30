package com.parabank.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserManager {

    public static WebDriver launchBrowser() {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        return driver;
    }
}
