package com.willkergomes.freedom.trial.wishlistapi.domain.usecases;

import com.willkergomes.freedom.trial.wishlistapi.domain.entities.Customer;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.CustomerProduct;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.Wishlist;
import com.willkergomes.freedom.trial.wishlistapi.domain.usecases.handlers.WishlistOutputHandler;

public interface WishlistUseCase {

    Wishlist addProduct(CustomerProduct customerProduct, WishlistOutputHandler outputHandler);

    Wishlist findAllProducts(Customer customer, WishlistOutputHandler outputHandler);

    void checkProduct(CustomerProduct customerProduct, WishlistOutputHandler outputHandler);

    void removeProduct(CustomerProduct customerProduct, WishlistOutputHandler outputHandler);
}
