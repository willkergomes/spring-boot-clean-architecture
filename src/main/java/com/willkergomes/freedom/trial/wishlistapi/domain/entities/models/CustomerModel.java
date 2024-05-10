package com.willkergomes.freedom.trial.wishlistapi.domain.entities.models;

import com.willkergomes.freedom.trial.wishlistapi.common.utils.Assertions;
import com.willkergomes.freedom.trial.wishlistapi.domain.entities.Customer;
import com.willkergomes.freedom.trial.wishlistapi.domain.rules.CustomerRule;

public record CustomerModel(String customerId) implements Customer, CustomerRule {

    @Override
    public boolean isCustomerIdNotValid() {
        return Assertions.assertNullOrEmpty(customerId) || Assertions.assertEqualsNullString(customerId);
    }

}
