package com.willkergomes.freedom.trial.wishlistapi.domain.usecases.handlers;

import com.willkergomes.freedom.trial.wishlistapi.domain.entities.CustomerProduct;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.Wishlist;
import org.springframework.http.ResponseEntity;

public interface WishlistOutputHandler {

    ResponseEntity<?> handleSuccessAddProduct(Wishlist wishlist);

    ResponseEntity<?> handleSuccessFindAllProducts(Wishlist wishlist);

    void handleCustomerIdNotValid(String customerId);

    void handleProductIdNotValid(String productId);

    void handleExistsWishlistProduct(CustomerProduct customerProduct);

    void handleHasExceededWishlistProducts(CustomerProduct customerProduct, int maxLimit);

    void handleNotFoundCustomerWishlist(String customerId);

    void handleNotExistsWishlistProduct(CustomerProduct customerProduct);

}