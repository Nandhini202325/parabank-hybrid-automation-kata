package com.parabank.api;

import com.parabank.utils.FrameworkConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CustomerApiClient {

    public Response getCustomer(int customerId) {

        return RestAssured.
                given().
                    baseUri(FrameworkConfig.getApiBaseUrl()).
                    log().all().
                when().
                    get("/customers/" + customerId).
                then().
                    log().all().extract().response();
    }
}