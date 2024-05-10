package com.willkergomes.freedom.trial.wishlistapi.application.services;

import com.google.common.collect.Lists;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.Customer;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.CustomerProduct;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.Wishlist;
import com.willkergomes.freedom.trial.wishlistapi.domain.factories.WishlistProductModelFactory;
import com.willkergomes.freedom.trial.wishlistapi.domain.repositories.WishlistRepository;
import com.willkergomes.freedom.trial.wishlistapi.domain.rules.CustomerRule;
import com.willkergomes.freedom.trial.wishlistapi.domain.rules.WishlistProductsRule;
import com.willkergomes.freedom.trial.wishlistapi.domain.rules.WishlistRule;
import com.willkergomes.freedom.trial.wishlistapi.domain.usecases.WishlistUseCase;
import com.willkergomes.freedom.trial.wishlistapi.domain.usecases.handlers.WishlistOutputHandler;
import com.willkergomes.freedom.trial.wishlistapi.infrastructure.persistence.exceptions.NotFoundCustomerWishlistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WishlistService implements WishlistUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(WishlistService.class);

    private final WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public Wishlist addProduct(CustomerProduct customerProduct, WishlistOutputHandler outputHandler) {
        try {
            checkWishlistInputRules(customerProduct, outputHandler);

            Wishlist wishlist = this.wishlistRepository.findByCustomerId(customerProduct.customerId());

            wishlist = checkInputWishlistProduct(customerProduct, wishlist, outputHandler);

            final var newWishlistProduct = WishlistProductModelFactory.createNew(customerProduct.productId());
            wishlist.wishlistProducts().add(newWishlistProduct);
            return this.wishlistRepository.updateWishlist(wishlist);
        } catch (NotFoundCustomerWishlistException e) {
            return this.wishlistRepository.createWishlist(customerProduct);
        }
    }

    private Wishlist checkInputWishlistProduct(
            CustomerProduct customerProduct, Wishlist wishlist, WishlistOutputHandler outputHandler) {
        final var wishlistProductsRules = (WishlistProductsRule) wishlist;

        if (wishlistProductsRules.isWishlistProductsNull()) {
            return WishlistProductModelFactory.copyWithWishlistProducts(wishlist, Lists.newArrayList());
        }

        if (wishlistProductsRules.existsWishlistProductById(customerProduct.productId())) {
            outputHandler.handleExistsWishlistProduct(customerProduct);
        }

        if (wishlistProductsRules.hasWishlistProductsExceededMaxLimit(
                WishlistProductsRule.WISHLIST_PRODUCTS_MAX_LIMIT)) {
            outputHandler.handleHasExceededWishlistProducts(customerProduct,
                    WishlistProductsRule.WISHLIST_PRODUCTS_MAX_LIMIT);
        }

        return wishlist;
    }

    @Override
    public Wishlist findAllProducts(Customer customer, WishlistOutputHandler outputHandler) {
        final var customerRule = (CustomerRule) customer;
        Wishlist wishlist = null;
        try {
            if (customerRule.isCustomerIdNotValid()) {
                outputHandler.handleCustomerIdNotValid(customer.customerId());
            }

            wishlist = this.wishlistRepository.findByCustomerId(customer.customerId());
        } catch (NotFoundCustomerWishlistException e) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("FindAllProductsFromWishlistUseCase::notFound", e);
            }
            outputHandler.handleNotFoundCustomerWishlist(customer.customerId());
        }
        return wishlist;
    }

    @Override
    public void checkProduct(CustomerProduct customerProduct, WishlistOutputHandler outputHandler) {
        try {
            checkWishlistInputRules(customerProduct, outputHandler);

            WishlistProductsRule wishlist = (WishlistProductsRule) this.wishlistRepository
                    .findByCustomerId(customerProduct.customerId());

            if (!wishlist.existsWishlistProductById(customerProduct.productId())) {
                outputHandler.handleNotExistsWishlistProduct(customerProduct);
            }
        } catch (NotFoundCustomerWishlistException e) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("FindAllProductsFromWishlistUseCase::notFound", e);
            }
            outputHandler.handleNotFoundCustomerWishlist(customerProduct.customerId());
        }
    }

    @Override
    public void removeProduct(CustomerProduct customerProduct, WishlistOutputHandler outputHandler) {
        try {
            checkWishlistInputRules(customerProduct, outputHandler);

            WishlistProductsRule wishlistProductsRule = (WishlistProductsRule) this.wishlistRepository
                    .findByCustomerId(customerProduct.customerId());

            if (!wishlistProductsRule.existsWishlistProductById(customerProduct.productId())) {
                outputHandler.handleNotExistsWishlistProduct(customerProduct);
            }

            this.wishlistRepository.removeProductFromWishlist(customerProduct);
        } catch (NotFoundCustomerWishlistException e) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("FindAllProductsFromWishlistUseCase::notFound", e);
            }
            outputHandler.handleNotFoundCustomerWishlist(customerProduct.customerId());
        }
    }

    private void checkWishlistInputRules(CustomerProduct customerProduct, WishlistOutputHandler outputHandler) {
        final var wishlistRule = (WishlistRule) customerProduct;

        if (wishlistRule.isCustomerIdNotValid()) {
            outputHandler.handleCustomerIdNotValid(customerProduct.customerId());
        }

        if (wishlistRule.isProductIdNotValid()) {
            outputHandler.handleProductIdNotValid(customerProduct.productId());
        }
    }

}
