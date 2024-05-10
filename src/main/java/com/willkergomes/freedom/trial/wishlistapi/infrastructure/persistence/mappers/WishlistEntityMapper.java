package com.willkergomes.freedom.trial.wishlistapi.infrastructure.persistence.mappers;

import com.willkergomes.freedom.trial.wishlistapi.domain.entities.CustomerProduct;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.Wishlist;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.models.WishlistModel;
import com.willkergomes.freedom.trial.wishlistapi.infrastructure.persistence.entities.WishlistEntity;
import org.springframework.stereotype.Component;

@Component
public interface WishlistEntityMapper {

    WishlistEntity mapToCreateNewPersistenceEntity(CustomerProduct customerProduct);

    WishlistEntity mapToUpdateNewPersistenceEntity(Wishlist wishlist);

    WishlistModel mapToDomainModel(Wishlist wishlist);

}
