package com.willkergomes.freedom.trial.wishlistapi.cucumber.steps;

import com.willkergomes.freedom.trial.wishlistapi.cucumber.steps.contexts.CommonWishlistStepsContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpStatus;

import static org.hamcrest.Matchers.equalTo;

public class RemoveProductWishlistStepsDefinition extends AbstractStepsDefinition {

    public RemoveProductWishlistStepsDefinition(CommonWishlistStepsContext context) {
        super(context);
    }

    @When("remove product from customer wishlist")
    public void removeProductFromCustomerWishlist() {
        final var uri = String.format("/api/v1/wishlists/%s/products/%s?message=true",
                this.context.getCustomerId(),
                this.context.getProductId());
        final var response = this.context.getRequest().delete(uri);
        this.context.setResponse(response);
    }

    @Then("should fail for product id was {string} from customer {string} wishlist")
    public void shouldFailForProductId(String productId, String customerId) {
        this.context.getResponse().then().assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("message",
                        equalTo(String.format("product '%s' not found in customer '%s' wishlist",
                                productId, customerId)));
    }

}