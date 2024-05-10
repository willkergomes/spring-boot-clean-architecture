package com.willkergomes.freedom.trial.wishlistapi.application.controllers;

import com.willkergomes.freedom.trial.wishlistapi.application.dto.requests.AddProductWishlistRequest;
import com.willkergomes.freedom.trial.wishlistapi.application.presenters.WishlistPresenter;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.Wishlist;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.models.CustomerProductModel;
import com.willkergomes.freedom.trial.wishlistapi.domain.usecases.WishlistUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class WishlistControllerTest {

    private WishlistController wishlistController;

    @Mock
    private WishlistUseCase wishlistUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        wishlistController = new WishlistController(wishlistUseCase);
    }

    @Test
    void shouldSuccessWhenAddProductWishlist() {
        // given
        String customerId = "123";
        String productId = "1";
        AddProductWishlistRequest createCategoryRequest = new AddProductWishlistRequest(productId);
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();

        // when

        Wishlist wishlist = mock(Wishlist.class);
        when(wishlist.customerId()).thenReturn(customerId);

        CustomerProductModel model = new CustomerProductModel(customerId, productId);

        when(wishlistUseCase.addProduct(eq(model), any(WishlistPresenter.class))).thenReturn(wishlist);

        ResponseEntity<?> result = wishlistController.addProductWishlist(
                customerId, createCategoryRequest, uriComponentsBuilder);

        // then
        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        uriComponentsBuilder = UriComponentsBuilder.newInstance();
        UriComponents uriComponents = uriComponentsBuilder.path("/api/v1/wishlists/{customerId}/products/{productId}")
                .buildAndExpand(customerId, createCategoryRequest.productId());

        assertEquals(uriComponents.toUriString(), Objects.requireNonNull(result.getHeaders().get("Location")).getFirst());

    }

    @Test
    void shouldFailWhenAddProductWishlist() {
        // given
        String customerId = "123";
        String productId = null;
        AddProductWishlistRequest createCategoryRequest = new AddProductWishlistRequest(productId);
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();

        // when
        Wishlist wishlist = mock(Wishlist.class);
        when(wishlist.customerId()).thenReturn(customerId);

        CustomerProductModel model = new CustomerProductModel(customerId, productId);

        when(wishlistUseCase.addProduct(eq(model), any(WishlistPresenter.class))).thenReturn(wishlist);

        ResponseEntity<?> result = wishlistController.addProductWishlist(
                customerId, createCategoryRequest, uriComponentsBuilder);

        // then
        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        uriComponentsBuilder = UriComponentsBuilder.newInstance();
        UriComponents uriComponents = uriComponentsBuilder.path("/api/v1/wishlists/{customerId}/products/{productId}")
                .buildAndExpand(customerId, createCategoryRequest.productId());

        assertEquals(uriComponents.toUriString(), Objects.requireNonNull(result.getHeaders().get("Location")).getFirst());

    }

    @Test
    void testFindAllProductsWishlist() {
        String customerId = "123";

        // Mock the behavior of wishlistUseCase.findAllProducts
//        CustomerModel model = new CustomerModel(customerId);
//        WishlistPresenter presenter = new WishlistPresenter();
//        when(wishlistUseCase.findAllProducts(eq(model), eq(presenter))).thenReturn(/* expected response */);

        ResponseEntity<?> result = wishlistController.findAllProductsWishlist(customerId);

        assertNotNull(result);
        // Add more assertions as needed
    }

}