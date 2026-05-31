package com.parabank.apiTests;

import com.parabank.api.CustomerApiClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CustomerApiTest {

    @Test
    public void verifyGetCustomer() {

        CustomerApiClient customerApiClient = new CustomerApiClient();

        Response loginResponse = customerApiClient.login("john", "demo");
        Assert.assertEquals(loginResponse.getStatusCode(), 200,
                "Login failed — cannot proceed with getCustomer test");

        int customerId = loginResponse.jsonPath().getInt("id");

        Response getCustomerResponse = customerApiClient.getCustomer(customerId);

        Assert.assertEquals(getCustomerResponse.getStatusCode(), 200,
                "Expected 200 for customer ID: " + customerId);
    }
}