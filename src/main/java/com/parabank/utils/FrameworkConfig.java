package com.parabank.utils;
import com.parabank.constants.FrameworkConstants;


public final class FrameworkConfig {

    private FrameworkConfig() {
    }

    public static String getBaseUrl() {
        return ConfigReader.getProperty(FrameworkConstants.BASE_URL);
    }

    public static String getBrowser() {
        return ConfigReader.getProperty(FrameworkConstants.BROWSER);
    }
}