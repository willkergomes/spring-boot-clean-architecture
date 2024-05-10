package com.willkergomes.freedom.trial.wishlistapi.application.dto;

import java.util.List;

public record ErrorDetailsResponse(String message, List<String> errors) {

}
