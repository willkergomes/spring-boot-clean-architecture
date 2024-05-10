package com.willkergomes.freedom.trial.wishlistapi.domain.entities.models;

import com.willkergomes.freedom.trial.wishlistapi.common.utils.Assertions;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.CustomerProduct;
import com.willkergomes.freedom.trial.wishlistapi.domain.rules.WishlistRule;

public record CustomerProductModel(String customerId, String productId) implements CustomerProduct, WishlistRule {

    @Override
    public boolean isCustomerIdNotValid() {
        return Assertions.assertNullOrEmpty(customerId) || Assertions.assertEqualsNullString(customerId);
    }

    @Override
    public boolean isProductIdNotValid() {
        return Assertions.assertNullOrEmpty(productId) || Assertions.assertEqualsNullString(productId);
    }

}
