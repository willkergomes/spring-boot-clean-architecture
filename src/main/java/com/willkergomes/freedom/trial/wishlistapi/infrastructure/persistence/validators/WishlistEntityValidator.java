package com.willkergomes.freedom.trial.wishlistapi.infrastructure.persistence.validators;

import com.willkergomes.freedom.trial.wishlistapi.common.utils.AbstractValidator;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.Wishlist;
import com.willkergomes.freedom.trial.wishlistapi.domain.validators.WishlistValidator;
import com.willkergomes.freedom.trial.wishlistapi.infrastructure.persistence.entities.WishlistEntity;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
public class WishlistEntityValidator extends AbstractValidator implements WishlistValidator {

    @Override
    public void validate(Wishlist wishlist) {
        Set<ConstraintViolation<WishlistEntity>> violations = this.getValidator().validate((WishlistEntity)wishlist);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

}
