package com.parabank.utils;

import com.parabank.models.Customer;


public final class TestDataFactory {

    private TestDataFactory() {
    }

    public static Customer createCustomer() {

        String uniqueValue = String.valueOf(System.currentTimeMillis());

        Customer customer = new Customer();

        customer.setFirstName(TestDataReader.getProperty("firstname"));

        customer.setLastName(TestDataReader.getProperty("lastname"));

        customer.setAddress(TestDataReader.getProperty("address"));

        customer.setCity(TestDataReader.getProperty("city"));

        customer.setState(TestDataReader.getProperty("state"));

        customer.setZipCode(TestDataReader.getProperty("zipcode"));

        customer.setPhoneNumber(TestDataReader.getProperty("phone"));

        customer.setPassword(TestDataReader.getProperty("password"));

        customer.setUsername("user" + uniqueValue);

        customer.setSsn(uniqueValue);

        return customer;
    }
}