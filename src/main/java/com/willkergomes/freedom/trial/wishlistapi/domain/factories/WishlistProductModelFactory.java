package com.willkergomes.freedom.trial.wishlistapi.domain.factories;

import com.willkergomes.freedom.trial.wishlistapi.domain.entities.Wishlist;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.WishlistProduct;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.models.WishlistModel;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.models.WishlistProductModel;

import java.util.Date;
import java.util.List;

public class WishlistProductModelFactory {

    private WishlistProductModelFactory() {
    }

    public static WishlistProductModel createNew(String productId) {
        return new WishlistProductModel(productId, new Date());
    }

    public static WishlistModel copyWithWishlistProducts(
            Wishlist wishlist, List<WishlistProduct> wishlistProducts) {
        return new WishlistModel(
                wishlist.id(),
                wishlist.customerId(),
                wishlistProducts,
                wishlist.createdAt(),
                wishlist.updatedAt()
        );
    }


}
