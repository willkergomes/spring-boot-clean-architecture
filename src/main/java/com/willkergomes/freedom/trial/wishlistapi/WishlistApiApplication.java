package com.willkergomes.freedom.trial.wishlistapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class WishlistApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WishlistApiApplication.class, args);
	}

}
