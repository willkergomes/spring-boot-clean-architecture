package com.willkergomes.freedom.trial.wishlistapi.infrastructure.persistence.exceptions;

import com.willkergomes.freedom.trial.wishlistapi.domain.exceptions.AbstractRuleDomainException;

public class NotFoundCustomerWishlistException extends AbstractRuleDomainException {

    public NotFoundCustomerWishlistException(String message) {
        super(message);
    }
}
