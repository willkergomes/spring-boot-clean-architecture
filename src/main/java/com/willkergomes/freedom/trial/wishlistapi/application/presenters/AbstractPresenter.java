package com.willkergomes.freedom.trial.wishlistapi.application.presenters;

import org.springframework.http.ResponseEntity;

public abstract class AbstractPresenter {

	protected ResponseEntity<?> viewModel;

	public ResponseEntity<?> getViewModel() {
		return viewModel;
	}
}
