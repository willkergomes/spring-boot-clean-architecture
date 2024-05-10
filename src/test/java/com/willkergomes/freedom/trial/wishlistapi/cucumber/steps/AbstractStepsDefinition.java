package com.willkergomes.freedom.trial.wishlistapi.cucumber.steps;

import com.willkergomes.freedom.trial.wishlistapi.cucumber.steps.contexts.CommonWishlistStepsContext;

public abstract class AbstractStepsDefinition {

    protected final CommonWishlistStepsContext context;

    public AbstractStepsDefinition(CommonWishlistStepsContext context) {
        this.context = context;
    }

}