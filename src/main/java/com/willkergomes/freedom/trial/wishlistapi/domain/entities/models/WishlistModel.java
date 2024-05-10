package com.willkergomes.freedom.trial.wishlistapi.domain.entities.models;

import com.willkergomes.freedom.trial.wishlistapi.common.utils.Assertions;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.Wishlist;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.WishlistProduct;
import com.willkergomes.freedom.trial.wishlistapi.domain.rules.WishlistProductsRule;

import java.util.Date;
import java.util.List;

public record WishlistModel(
        String id, String customerId, List<WishlistProduct> wishlistProducts, Date createdAt, Date updatedAt)
        implements Wishlist, WishlistProductsRule {

    @Override
    public boolean isWishlistProductsNull() {
        return Assertions.assertNullOrEmpty(this.wishlistProducts);
    }

    @Override
    public boolean hasWishlistProductsExceededMaxLimit(int maxLimit) {
        return this.wishlistProducts.size() + 1 > maxLimit;
    }

    @Override
    public boolean existsWishlistProductById(String productId) {
        return !isWishlistProductsNull() && this.wishlistProducts.stream()
                .anyMatch(wishlistProduct -> wishlistProduct.productId().equals(productId));
    }
}
