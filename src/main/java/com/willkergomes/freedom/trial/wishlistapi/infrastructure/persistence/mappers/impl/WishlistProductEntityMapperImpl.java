package com.willkergomes.freedom.trial.wishlistapi.infrastructure.persistence.mappers.impl;

import com.willkergomes.freedom.trial.wishlistapi.domain.entities.WishlistProduct;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.models.WishlistProductModel;
import com.willkergomes.freedom.trial.wishlistapi.infrastructure.persistence.entities.WishlistProductEntity;
import com.willkergomes.freedom.trial.wishlistapi.infrastructure.persistence.mappers.WishlistProductEntityMapper;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class WishlistProductEntityMapperImpl implements WishlistProductEntityMapper {

    @Override
    public WishlistProductEntity mapToNewPersistenceEntity(String productId) {
        return new WishlistProductEntity(productId, new Date());
    }

    @Override
    public WishlistProductModel mapToDomainModel(WishlistProduct wishlistProduct) {
        return new WishlistProductModel(wishlistProduct.productId(), wishlistProduct.createdAt());
    }

}
