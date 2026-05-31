package com.parabank.api;

import com.parabank.utils.FrameworkConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TransferApiClient {

    public Response createAccount(int customerId, int fromAccountId) {

        return RestAssured
                .given()
                .baseUri(FrameworkConfig.getApiBaseUrl())
                .accept(ContentType.JSON)
                .log().all()
                .when()
                .post("/createAccount?customerId=" + customerId
                        + "&newAccountType=0&fromAccountId=" + fromAccountId)
                .then()
                .log().all()
                .extract().response();
    }

    public Response transfer(int fromAccountId, int toAccountId, double amount) {

        return RestAssured
                .given()
                .baseUri(FrameworkConfig.getApiBaseUrl())
                .accept(ContentType.JSON)
                .log().all()
                .when()
                .post("/transfer?fromAccountId=" + fromAccountId
                        + "&toAccountId=" + toAccountId
                        + "&amount=" + amount)
                .then()
                .log().all()
                .extract().response();
    }
}