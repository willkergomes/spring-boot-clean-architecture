package com.willkergomes.freedom.trial.wishlistapi.cucumber.steps;

import com.willkergomes.freedom.trial.wishlistapi.cucumber.steps.contexts.CommonWishlistStepsContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;

public class CommonWishlistStepsDefinition extends AbstractStepsDefinition {

    public CommonWishlistStepsDefinition(CommonWishlistStepsContext context) {
        super(context);
    }

    @Given("a wishlist who's customer id is left blank")
    public void aCustomerIdIsLeftBlank() {
        this.context.setCustomerId(" ");
    }

    @Given("a wishlist who's customer id is {string}")
    public void aCustomerIdIsString(String customerId) {
        this.context.setCustomerId(customerId);
    }

    @Given("a wishlist with a list product id is {string}")
    public void aWishlistWhoSListOfProductIdIs(String productIds) {
        final var requestList = Arrays.stream(productIds.split(",")).toList();
        this.context.setProductList(requestList);
    }

    @And("a product id is {string}")
    public void aProductIdToAddIntoWishlist(String productId) {
        this.context.setProductId(productId);
    }

    @Then("should fail bad request")
    public void shouldFailBadRequest() {
        this.context.getResponse().then().assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Then("should fail not found")
    public void shouldFailNotFound() {
        this.context.getResponse().then().assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Then("should fail for customer id was null")
    public void shouldFailForCustomerNull() {
        this.context.getResponse().then().assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", equalTo("customerId 'null' is not valid"));
    }

    @Then("should fail for product id was null")
    public void shouldFailForProductNull() {
        this.context.getResponse().then().assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", equalTo("productId 'null' is not valid"));
    }

    @Then("should fail for customer id was left blank")
    public void shouldFailForCustomerBlank() {
        this.context.getResponse().then().assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message",
                        equalTo("customerId ' ' is not valid"));
    }

    @Then("should fail for customer id was {string}")
    public void shouldFailForCustomerId(String customerId) {
        this.context.getResponse().then().assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("message",
                        equalTo(String.format("wishlist not found for customerId '%s'", customerId)));
    }

    @Then("should fail for product id was {string}")
    public void shouldFailForProductId(String productId) {
        this.context.getResponse().then().assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("message",
                        equalTo(String.format("wishlist not found for productId '%s'", productId)));
    }

    @Then("should success status code OK")
    public void shouldSuccess() {
        this.context.getResponse().then().assertThat()
                .statusCode(HttpStatus.OK.value());
    }

}