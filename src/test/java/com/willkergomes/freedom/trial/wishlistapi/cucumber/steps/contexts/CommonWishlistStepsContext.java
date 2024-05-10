package com.willkergomes.freedom.trial.wishlistapi.cucumber.steps.contexts;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

public class CommonWishlistStepsContext {

    private String customerId;

    private String productId;

    private List<String> productList;

    private RequestSpecification request;

    private Response response;

    public CommonWishlistStepsContext() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;

        final var encoderconfig = EncoderConfig.encoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false);
        this.request = RestAssured.given()
                .config(RestAssured.config().encoderConfig(encoderconfig))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public List<String> getProductList() {
        return productList;
    }

    public void setProductList(List<String> productList) {
        this.productList = productList;
    }

    public RequestSpecification getRequest() {
        return request;
    }

    public void setRequest(RequestSpecification request) {
        this.request = request;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
