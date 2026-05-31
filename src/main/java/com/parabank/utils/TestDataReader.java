package com.parabank.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class TestDataReader {

    private static final Properties PROPERTIES = new Properties();

    static {

        try (InputStream inputStream = TestDataReader.class.getClassLoader().getResourceAsStream("testdata/customerData.properties")) {

            if (inputStream == null) {

                throw new RuntimeException("customerData.properties not found");
            }

            PROPERTIES.load(inputStream);

        } catch (IOException exception) {

            throw new RuntimeException("Unable to load test data", exception);
        }
    }

    private TestDataReader() {
    }

    public static String getProperty(String key) {

        return PROPERTIES.getProperty(key);
    }
}