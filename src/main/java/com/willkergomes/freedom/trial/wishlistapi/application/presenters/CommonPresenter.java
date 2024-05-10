package com.willkergomes.freedom.trial.wishlistapi.application.presenters;

import com.willkergomes.freedom.trial.wishlistapi.domain.entities.CustomerProduct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

public class CommonPresenter extends AbstractPresenter {

    public ResponseEntity<?> handleSuccess() {
        return ResponseEntity.ok().build();
    }

    public void handleCustomerIdNotValid(String customerId) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                String.format("customerId '%s' is not valid", customerId));
    }

    public void handleProductIdNotValid(String productId) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                String.format("productId '%s' is not valid", productId));
    }

    public void handleNotFoundCustomerWishlist(String customerId) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("wishlist not found for customerId '%s'", customerId));
    }

    public void handleNotExistsWishlistProduct(CustomerProduct customerProduct) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("product '%s' not found in customer '%s' wishlist",
                        customerProduct.productId(), customerProduct.customerId()));
    }

}
