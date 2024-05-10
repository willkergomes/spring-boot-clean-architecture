package com.willkergomes.freedom.trial.wishlistapi.infrastructure.persistence.repositories;

import com.mongodb.lang.NonNull;
import com.willkergomes.freedom.trial.wishlistapi.infrastructure.persistence.entities.WishlistEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistMongoRepository extends MongoRepository<WishlistEntity, ObjectId> {

    Optional<WishlistEntity> findByCustomerId(@NonNull String customerId);

}
