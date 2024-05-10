package com.willkergomes.freedom.trial.wishlistapi.infrastructure.config;

import com.mongodb.MongoTimeoutException;
import com.willkergomes.freedom.trial.wishlistapi.application.dto.ErrorDetailsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler({ MongoTimeoutException.class })
	public Object handleMongoTimeoutException(MongoTimeoutException ex) {
		if (LOGGER.isErrorEnabled()) {
			LOGGER.error(String.format("INTERNAL_SERVER_ERROR - %s", ex.getMessage()), ex);
		}
		final var response = new ErrorDetailsResponse("Unavailable Service", null);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<ErrorDetailsResponse> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException ex) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(String.format("One or more fields are invalid - %s", ex.getMessage()), ex);
		}
		final var message = "One or more fields are invalid";
		final var errorList = ex.getBindingResult().getFieldErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
		final var response = new ErrorDetailsResponse(message, errorList);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}