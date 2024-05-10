package com.willkergomes.freedom.trial.wishlistapi.infrastructure.persistence.repositories.gateways;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.willkergomes.freedom.trial.wishlistapi.common.WishlistConstants;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.CustomerProduct;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.Wishlist;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.models.WishlistModel;
import com.willkergomes.freedom.trial.wishlistapi.domain.repositories.WishlistRepository;
import com.willkergomes.freedom.trial.wishlistapi.infrastructure.persistence.exceptions.NotFoundCustomerWishlistException;
import com.willkergomes.freedom.trial.wishlistapi.infrastructure.persistence.mappers.WishlistEntityMapper;
import com.willkergomes.freedom.trial.wishlistapi.infrastructure.persistence.repositories.WishlistMongoRepository;
import com.willkergomes.freedom.trial.wishlistapi.infrastructure.persistence.validators.WishlistEntityValidator;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class WishlistRepositoryGateway implements WishlistRepository {

    private final WishlistMongoRepository wishlistMongoRepository;

    private final WishlistEntityMapper wishlistEntityMapper;

    private final WishlistEntityValidator wishlistEntityValidator;

    private final MongoTemplate mongoTemplate;

    public WishlistRepositoryGateway(WishlistMongoRepository wishlistMongoRepository,
                                     WishlistEntityMapper wishlistEntityMapper,
                                     WishlistEntityValidator wishlistEntityValidator,
                                     MongoTemplate mongoTemplate) {
        this.wishlistMongoRepository = wishlistMongoRepository;
        this.wishlistEntityMapper = wishlistEntityMapper;
        this.wishlistEntityValidator = wishlistEntityValidator;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public WishlistModel findByCustomerId(String customerId) {
        final var wishlistEntity = this.wishlistMongoRepository.findByCustomerId(customerId).orElseThrow(
                () -> new NotFoundCustomerWishlistException(
                        String.format("User '%s' wishlist not found!", customerId)));
        return this.wishlistEntityMapper.mapToDomainModel(wishlistEntity);
    }

    @Override
    public WishlistModel createWishlist(CustomerProduct customerProduct) {
        var entityToCreate = this.wishlistEntityMapper.mapToCreateNewPersistenceEntity(customerProduct);
        this.wishlistEntityValidator.validate(entityToCreate);
        var entityCreated = this.wishlistMongoRepository.save(entityToCreate);
        return this.wishlistEntityMapper.mapToDomainModel(entityCreated);
    }

    @Override
    public WishlistModel updateWishlist(Wishlist wishlist) {
        var entityToUpdate = this.wishlistEntityMapper.mapToUpdateNewPersistenceEntity(wishlist);
        this.wishlistEntityValidator.validate(entityToUpdate);
        var entityUpdated = this.wishlistMongoRepository.save(entityToUpdate);
        return this.wishlistEntityMapper.mapToDomainModel(entityUpdated);
    }

    @Override
    public void removeProductFromWishlist(CustomerProduct customerProduct) {
        final var wishlistsCollection = this.mongoTemplate.getCollection(WishlistConstants.WISHLIST_MONGO_COLLECTION);

        Bson wishlistFilter = Filters.eq("customerId", customerProduct.customerId());
        Bson delete = Updates.pull("wishlistProducts",
                new Document("productId", customerProduct.productId()));

        wishlistsCollection.updateOne(wishlistFilter, delete);
    }
}
