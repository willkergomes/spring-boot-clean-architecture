package com.willkergomes.freedom.trial.wishlistapi.infrastructure.persistence.entities;

import com.willkergomes.freedom.trial.wishlistapi.domain.entities.WishlistProduct;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record WishlistProductEntity(
        @NotBlank(message = "is mandatory and cannot be empty") String productId,
        @NotNull(message = "is mandatory") Date createdAt
) implements WishlistProduct {

}
