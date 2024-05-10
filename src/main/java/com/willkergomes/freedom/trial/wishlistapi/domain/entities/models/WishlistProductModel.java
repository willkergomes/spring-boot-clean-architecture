package com.willkergomes.freedom.trial.wishlistapi.domain.entities.models;

import com.willkergomes.freedom.trial.wishlistapi.domain.entities.WishlistProduct;

import java.util.Date;

public record WishlistProductModel(String productId, Date createdAt) implements WishlistProduct {

}
