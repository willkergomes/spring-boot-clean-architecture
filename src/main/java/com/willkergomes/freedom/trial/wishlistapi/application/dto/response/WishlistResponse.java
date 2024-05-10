package com.willkergomes.freedom.trial.wishlistapi.application.dto.response;

import java.util.List;

public record WishlistResponse(String customerId, List<String> productIds) {

}
