package com.willkergomes.freedom.trial.wishlistapi.domain.repositories;

import com.willkergomes.freedom.trial.wishlistapi.domain.entities.CustomerProduct;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.Wishlist;

public interface WishlistRepository {

    Wishlist createWishlist(CustomerProduct customerProduct);

    Wishlist updateWishlist(Wishlist wishlist);

    void removeProductFromWishlist(CustomerProduct customerProduct);

    Wishlist findByCustomerId(String customerId);

}
