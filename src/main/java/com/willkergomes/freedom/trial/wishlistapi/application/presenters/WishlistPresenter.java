package com.willkergomes.freedom.trial.wishlistapi.application.presenters;

import com.willkergomes.freedom.trial.wishlistapi.application.dto.requests.AddProductWishlistRequest;
import com.willkergomes.freedom.trial.wishlistapi.application.dto.response.WishlistResponse;
import com.willkergomes.freedom.trial.wishlistapi.common.utils.UriHelper;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.CustomerProduct;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.Wishlist;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.WishlistProduct;
import com.willkergomes.freedom.trial.wishlistapi.domain.usecases.handlers.WishlistOutputHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;
import java.util.stream.Collectors;

public class WishlistPresenter extends CommonPresenter implements WishlistOutputHandler {

    private AddProductWishlistRequest createCategoryRequest;

    private UriComponentsBuilder uriComponentsBuilder;

    public WishlistPresenter() {
    }

    public WishlistPresenter(
            AddProductWishlistRequest createCategoryRequest, UriComponentsBuilder uriComponentsBuilder) {
        this.createCategoryRequest = createCategoryRequest;
        this.uriComponentsBuilder = uriComponentsBuilder;
    }

    @Override
    public ResponseEntity<?> handleSuccessAddProduct(Wishlist wishlist) {
        Objects.requireNonNull(this.createCategoryRequest, "createCategoryRequest should be not null");
        Objects.requireNonNull(this.uriComponentsBuilder, "uriComponentsBuilder should be not null");
        final var path = "/api/v1/wishlists/{customerId}/products/{productId}";
        final var uri = UriHelper.buildURI(
                this.uriComponentsBuilder, path, wishlist.customerId(), this.createCategoryRequest.productId());

        return ResponseEntity.created(uri).build();

    }

    @Override
    public ResponseEntity<?> handleSuccessFindAllProducts(Wishlist wishlist) {
        final var customerid = wishlist.customerId();
        final var productIds = wishlist.wishlistProducts().stream()
                .map(WishlistProduct::productId).collect(Collectors.toList());
        return ResponseEntity.ok(new WishlistResponse(customerid, productIds));
    }

    @Override
    public void handleExistsWishlistProduct(CustomerProduct customerProduct) {
        throw new ResponseStatusException(HttpStatus.CONFLICT,
                String.format("Product '%s' already exists in wishlist", customerProduct.productId()));
    }

    @Override
    public void handleHasExceededWishlistProducts(CustomerProduct customerProduct, int maxLimit) {
        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                String.format("Product '%s' has exceeded number of %d products in wishlist",
                        customerProduct.productId(), maxLimit));
    }
}
