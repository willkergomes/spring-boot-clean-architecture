package com.willkergomes.freedom.trial.wishlistapi.cucumber.steps;

import com.willkergomes.freedom.trial.wishlistapi.application.dto.requests.AddProductWishlistRequest;
import com.willkergomes.freedom.trial.wishlistapi.cucumber.steps.contexts.CommonWishlistStepsContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class AddProductWishlistStepsDefinition extends AbstractStepsDefinition {

    private AddProductWishlistRequest request;

    public AddProductWishlistStepsDefinition(CommonWishlistStepsContext context) {
        super(context);
    }

    @And("a product id is {string} in add product request")
    public void aProductIdToAddIntoWishlist(String productId) {
        this.request = new AddProductWishlistRequest(productId);
    }

    @When("add product to customer wishlist")
    public void addProductToWishlist() {
        final var uri = String.format("/api/v1/wishlists/%s/products?message=true",
                this.context.getCustomerId());
        final var response = this.context.getRequest()
                .body(request)
                .post(uri);
        this.context.setResponse(response);
    }

    @When("add product list to customer wishlist")
    public void addProductListToWishlist() {
        this.context.getProductList().forEach(productId -> {
            this.request = new AddProductWishlistRequest(productId);
            addProductToWishlist();
        });
    }

    @Then("should success for add product into customer wishlist")
    public void shouldAddProductToWishlistSuccess() {
        this.context.getResponse().then().assertThat().statusCode(HttpStatus.CREATED.value());
    }

    @Then("should fail for existing product id {string} in wishlist")
    public void shouldFailForExistingProductIdInWishlist(String productId) {
        this.context.getResponse().then().assertThat()
                .statusCode(HttpStatus.CONFLICT.value())
                .body("message",
                        equalTo(String.format("Product '%s' already exists in wishlist", productId)));
    }

    @Then("should fail add product {string} when limit of {int} products into wishlist")
    public void shouldFailForFullWishlist(String productId, Integer maxLimit) {
        this.context.getResponse().then().assertThat()
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .body("message",
                        equalTo(String.format("Product '%s' has exceeded number of %d products in wishlist",
                                productId, maxLimit)));
    }

    @Then("should return product list {string} in customer {string} wishlist")
    public void shouldAddProductsToWishlistSuccess(String productIds, String customerId) {
        final var productList = Arrays.stream(productIds.split(",")).toList();
        final var response = this.context.getResponse().then().assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("customerId", equalTo(customerId));

        productList.forEach(productId -> response.body("productIds", hasItem(productId)));
    }

}