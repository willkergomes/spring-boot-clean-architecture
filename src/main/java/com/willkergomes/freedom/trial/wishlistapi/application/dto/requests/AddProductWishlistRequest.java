package com.willkergomes.freedom.trial.wishlistapi.application.dto.requests;

import jakarta.validation.constraints.NotBlank;

public record AddProductWishlistRequest(
		@NotBlank(message = "productId is mandatory and cannot be empty") String productId) {

}
