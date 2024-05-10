package com.willkergomes.freedom.trial.wishlistapi.cucumber.steps;

import com.willkergomes.freedom.trial.wishlistapi.cucumber.steps.contexts.CommonWishlistStepsContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class FindAllProductsWishlistStepsDefinition extends AbstractStepsDefinition {

    public FindAllProductsWishlistStepsDefinition(CommonWishlistStepsContext context) {
        super(context);
    }

    @When("find products from customer wishlist")
    public void findProductsFromCustomerWishlist() {
        final var uri = String.format("/api/v1/wishlists/%s/products?message=true",
                this.context.getCustomerId());
        final var response = this.context.getRequest().get(uri);
        this.context.setResponse(response);
    }

}