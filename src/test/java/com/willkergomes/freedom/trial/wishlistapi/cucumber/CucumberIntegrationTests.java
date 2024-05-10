package com.willkergomes.freedom.trial.wishlistapi.cucumber;

import com.willkergomes.freedom.trial.wishlistapi.common.WishlistConstants;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.MongoTemplate;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "com.willkergomes.freedom.trial.wishlistapi.cucumber.steps",
        features = {
                "classpath:features/add_product_wishlist.feature",
                "classpath:features/check_product_wishlist.feature",
                "classpath:features/find_all_products_wishlist.feature",
                "classpath:features/remove_product_wishlist.feature",
        },
        objectFactory = io.cucumber.picocontainer.PicoFactory.class
)
public class CucumberIntegrationTests {

    private final MongoTemplate mongoTemplate;


    public CucumberIntegrationTests(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @BeforeAll
    public void dropMongoCollections() {
        this.mongoTemplate.dropCollection(WishlistConstants.WISHLIST_MONGO_COLLECTION);
    }
}
