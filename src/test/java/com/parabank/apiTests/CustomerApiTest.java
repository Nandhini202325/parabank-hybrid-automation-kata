package com.parabank.apiTests;

import com.parabank.api.ApiClientFactory;
import com.parabank.api.CustomerApiClient;
import com.parabank.utils.TestDataReader;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CustomerApiTest {

    @Test
    public void verifyGetCustomer() {

        CustomerApiClient customerApiClient = ApiClientFactory.customer();

        int customerId = Integer.parseInt(
                TestDataReader.getProperty("customer.id"));

        Response response = customerApiClient.getCustomer(customerId);

        Assert.assertEquals(
                response.getStatusCode(),
                200
        );
    }
}