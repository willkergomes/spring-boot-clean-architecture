package com.willkergomes.freedom.trial.wishlistapi.infrastructure.persistence.mappers.impl;


import com.google.common.collect.Lists;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.CustomerProduct;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.Wishlist;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.WishlistProduct;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.models.WishlistModel;
import com.willkergomes.freedom.trial.wishlistapi.infrastructure.persistence.entities.WishlistEntity;
import com.willkergomes.freedom.trial.wishlistapi.infrastructure.persistence.mappers.WishlistEntityMapper;
import com.willkergomes.freedom.trial.wishlistapi.infrastructure.persistence.mappers.WishlistProductEntityMapper;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WishlistEntityMapperImpl implements WishlistEntityMapper {

    private final WishlistProductEntityMapper wishlistProductEntityMapper;

    public WishlistEntityMapperImpl(WishlistProductEntityMapper wishlistProductEntityMapper) {
        this.wishlistProductEntityMapper = wishlistProductEntityMapper;
    }

    @Override
    public WishlistEntity mapToCreateNewPersistenceEntity(CustomerProduct customerProduct) {
        List<WishlistProduct> wishlistProductList = Lists.newArrayList(
                this.wishlistProductEntityMapper.mapToNewPersistenceEntity(customerProduct.productId()));
        return new WishlistEntity(null, customerProduct.customerId(), wishlistProductList, new Date(), null);
    }

    @Override
    public WishlistEntity mapToUpdateNewPersistenceEntity(Wishlist wishlist) {
        return new WishlistEntity(
                wishlist.id(), wishlist.customerId(), wishlist.wishlistProducts(), wishlist.createdAt(), new Date());
    }

    @Override
    public WishlistModel mapToDomainModel(Wishlist wishlist) {
        List<WishlistProduct> wishlistProductList = wishlist.wishlistProducts().stream()
                .map(this.wishlistProductEntityMapper::mapToDomainModel)
                .collect(Collectors.toList());
        return new WishlistModel(
                wishlist.id(), wishlist.customerId(), wishlistProductList, wishlist.createdAt(), wishlist.updatedAt());
    }

}
