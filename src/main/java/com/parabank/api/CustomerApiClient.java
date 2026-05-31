package com.parabank.api;

import com.parabank.utils.FrameworkConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CustomerApiClient {

    public Response getCustomer(int customerId) {

        return RestAssured
                .given()
                .baseUri(FrameworkConfig.getApiBaseUrl())
                .accept(ContentType.JSON)
                .log().all()
                .when()
                .get("/customers/" + customerId)
                .then()
                .log().all()
                .extract().response();
    }

    public Response login(String username, String password) {

        return RestAssured
                .given()
                .baseUri(FrameworkConfig.getApiBaseUrl())
                .accept(ContentType.JSON)
                .log().all()
                .when()
                .get("/login/" + username + "/" + password)
                .then()
                .log().all()
                .extract().response();
    }

    public Response getAccountsByCustomerId(int customerId) {

        return RestAssured
                .given()
                .baseUri(FrameworkConfig.getApiBaseUrl())
                .accept(ContentType.JSON)
                .log().all()
                .when()
                .get("/customers/" + customerId + "/accounts")
                .then()
                .log().all()
                .extract().response();
    }
}