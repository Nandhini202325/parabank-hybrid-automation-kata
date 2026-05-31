package com.parabank.api;

import com.parabank.utils.FrameworkConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AccountApiClient {

    public Response getAccountDetails(int accountId) {

        return RestAssured
                .given()
                .baseUri(FrameworkConfig.getApiBaseUrl())
                .log().all()
                .when()
                .get("/accounts/" + accountId)
                .then()
                .log().all()
                .extract()
                .response();
    }
}