package com.willkergomes.freedom.trial.wishlistapi.infrastructure.persistence.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.willkergomes.freedom.trial.wishlistapi.common.WishlistConstants;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.Wishlist;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.WishlistProduct;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Document(collection = WishlistConstants.WISHLIST_MONGO_COLLECTION)
@CompoundIndexes({
        @CompoundIndex(name = "userId_idx", def = "{ 'customerId': 1 }", unique = true),
        @CompoundIndex(name = "userProduct_idx", def = "{ 'customerId': 1, 'wishlistProducts.productId': 1 }", unique = true)
})
public record WishlistEntity(
        @Id @JsonProperty(value = "_id") String id,
        @NotBlank(message = "is mandatory and cannot be empty") String customerId,
        @Valid @Field(value = "wishlistProducts") List<WishlistProduct> wishlistProducts,
        @NotNull(message = "is mandatory") Date createdAt,
        Date updatedAt
) implements Wishlist {

}
