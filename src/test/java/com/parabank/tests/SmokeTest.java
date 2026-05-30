package com.parabank.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest extends BaseTest {

    @Test
    public void verifyFrameworkSetup() {

        String actualTitle = driver.getTitle();

        Assert.assertTrue(
                actualTitle.contains("ParaBank"),
                "Page title does not contain ParaBank"
        );
    }
}