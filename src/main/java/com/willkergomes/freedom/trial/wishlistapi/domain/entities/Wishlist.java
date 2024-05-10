package com.willkergomes.freedom.trial.wishlistapi.domain.entities;

import java.util.Date;
import java.util.List;

public interface Wishlist {

    String id();

    String customerId();

    List<WishlistProduct> wishlistProducts();

    Date createdAt();

    Date updatedAt();

}
