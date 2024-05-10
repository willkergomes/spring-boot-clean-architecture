package com.willkergomes.freedom.trial.wishlistapi.application.controllers;

import com.willkergomes.freedom.trial.wishlistapi.application.dto.requests.AddProductWishlistRequest;
import com.willkergomes.freedom.trial.wishlistapi.application.presenters.WishlistPresenter;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.models.CustomerModel;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.models.CustomerProductModel;
import com.willkergomes.freedom.trial.wishlistapi.domain.usecases.WishlistUseCase;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@Validated
@ApiV1Controller
public class WishlistController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WishlistController.class);

    private final WishlistUseCase wishlistUseCase;

    public WishlistController(WishlistUseCase wishlistUseCase) {
        this.wishlistUseCase = wishlistUseCase;
    }

    @PostMapping("wishlists/{customerId}/products")
    public ResponseEntity<?> addProductWishlist(
            @PathVariable String customerId,
            @Valid @RequestBody AddProductWishlistRequest createCategoryRequest,
            UriComponentsBuilder uriComponentsBuilder) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("addProductWishlist::init(%s, %s)",
                    customerId, createCategoryRequest.toString()));
        }

        final var model = new CustomerProductModel(customerId, createCategoryRequest.productId());
        final var presenter = new WishlistPresenter(createCategoryRequest, uriComponentsBuilder);

        final var wishlist = this.wishlistUseCase.addProduct(model, presenter);

        return presenter.handleSuccessAddProduct(wishlist);
    }

    @GetMapping("wishlists/{customerId}/products")
    public ResponseEntity<?> findAllProductsWishlist(@PathVariable String customerId) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("findAllProductsWishlist::init(%s)", customerId));
        }

        final var model = new CustomerModel(customerId);
        final var presenter = new WishlistPresenter();

        final var wishlist = this.wishlistUseCase.findAllProducts(model, presenter);

        return presenter.handleSuccessFindAllProducts(wishlist);
    }

    @RequestMapping(
            value = "wishlists/{customerId}/products/{productId}",
            method = RequestMethod.HEAD
    )
    public ResponseEntity<?> checkProductWishlist(
            @PathVariable String customerId, @PathVariable String productId) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("checkProductWishlist::init(%s, %s)", customerId, productId));
        }

        final var model = new CustomerProductModel(customerId, productId);
        final var presenter = new WishlistPresenter();

        this.wishlistUseCase.checkProduct(model, presenter);

        return presenter.handleSuccess();
    }

    @DeleteMapping("wishlists/{customerId}/products/{productId}")
    public ResponseEntity<?> removeProductWishlist(
            @PathVariable String customerId, @PathVariable String productId) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("removeProductWishlist::init(%s, %s)", customerId, productId));
        }


        final var model = new CustomerProductModel(customerId, productId);
        final var presenter = new WishlistPresenter();

        this.wishlistUseCase.removeProduct(model, presenter);

        return presenter.handleSuccess();
    }

}
