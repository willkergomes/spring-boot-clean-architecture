package com.willkergomes.freedom.trial.wishlistapi.domain.rules;

public interface WishlistProductsRule {

    int WISHLIST_PRODUCTS_MAX_LIMIT = 20;

    boolean isWishlistProductsNull();

    boolean existsWishlistProductById(String productId);

    boolean hasWishlistProductsExceededMaxLimit(int maxLimit);

}
