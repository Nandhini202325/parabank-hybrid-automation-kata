package com.parabank.utils;

import com.parabank.constants.FrameworkConstants;

public final class FrameworkConfig {

    private FrameworkConfig() {
    }

    public static String getBrowser() {

        return ConfigReader.getProperty(FrameworkConstants.BROWSER);
    }

    public static String getBaseUrl() {

        return ConfigReader.getProperty(FrameworkConstants.UI_BASE_URL);
    }

    public static String getApiBaseUrl() {

        return ConfigReader.getProperty(FrameworkConstants.API_BASE_URL);
    }
}