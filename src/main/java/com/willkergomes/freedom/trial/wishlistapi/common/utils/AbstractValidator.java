package com.willkergomes.freedom.trial.wishlistapi.common.utils;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

public abstract class AbstractValidator {

    private final Validator validator;

    public AbstractValidator() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public Validator getValidator() {
        return validator;
    }
}
