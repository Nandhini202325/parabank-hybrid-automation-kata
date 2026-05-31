package com.parabank.hooks;

import com.parabank.base.DriverFactory;
import com.parabank.utils.BrowserManager;
import com.parabank.utils.FrameworkConfig;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hooks {

    private static final Path SCREENSHOT_DIR = Path.of("target", "screenshots");
    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss-SSS");

    @Before("@ui")
    public void setup() {

        WebDriver driver = BrowserManager.createDriver();

        DriverFactory.setDriver(driver);

        driver.manage().window().maximize();

        driver.get(FrameworkConfig.getBaseUrl());
    }

    @AfterStep("@ui")
    public void captureScreenshotOnFailure(Scenario scenario) throws IOException {

        WebDriver driver = DriverFactory.getDriver();

        if (!scenario.isFailed() || !(driver instanceof TakesScreenshot)) {
            return;
        }

        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Files.createDirectories(SCREENSHOT_DIR);
        Files.write(SCREENSHOT_DIR.resolve(buildScreenshotName(scenario)), screenshot);
        scenario.attach(screenshot, "image/png", "Failure screenshot");
    }

    @After("@ui")
    public void teardown() {

        if (DriverFactory.getDriver() != null) {
            DriverFactory.getDriver().quit();
            DriverFactory.setDriver(null);
        }
    }

    private String buildScreenshotName(Scenario scenario) {

        String safeScenarioName = scenario.getName()
                .replaceAll("[^a-zA-Z0-9]+", "-")
                .replaceAll("(^-|-$)", "")
                .toLowerCase();

        return safeScenarioName + "-" + LocalDateTime.now().format(TIMESTAMP_FORMAT) + ".png";
    }
}