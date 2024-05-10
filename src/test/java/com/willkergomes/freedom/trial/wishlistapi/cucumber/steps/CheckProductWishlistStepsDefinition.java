package com.willkergomes.freedom.trial.wishlistapi.cucumber.steps;

import com.willkergomes.freedom.trial.wishlistapi.cucumber.steps.contexts.CommonWishlistStepsContext;
import io.cucumber.java.en.When;

public class CheckProductWishlistStepsDefinition extends AbstractStepsDefinition {

    public CheckProductWishlistStepsDefinition(CommonWishlistStepsContext context) {
        super(context);
    }

    @When("check product from customer wishlist")
    public void checkProductFromCustomerWishlist() {
        final var uri = String.format("/api/v1/wishlists/%s/products/%s?message=true",
                this.context.getCustomerId(),
                this.context.getProductId());
        final var response = this.context.getRequest().head(uri);
        this.context.setResponse(response);
    }

}