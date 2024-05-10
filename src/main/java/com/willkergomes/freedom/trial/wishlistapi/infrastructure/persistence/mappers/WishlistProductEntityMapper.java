package com.willkergomes.freedom.trial.wishlistapi.infrastructure.persistence.mappers;

import com.willkergomes.freedom.trial.wishlistapi.domain.entities.WishlistProduct;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.models.WishlistProductModel;
import com.willkergomes.freedom.trial.wishlistapi.infrastructure.persistence.entities.WishlistProductEntity;
import org.springframework.stereotype.Component;

@Component
public interface WishlistProductEntityMapper {

    WishlistProductEntity mapToNewPersistenceEntity(String productId);

    WishlistProductModel mapToDomainModel(WishlistProduct wishlistProduct);

}
