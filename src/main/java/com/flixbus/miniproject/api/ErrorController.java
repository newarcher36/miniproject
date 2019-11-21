package com.flixbus.miniproject.api;

import com.flixbus.miniproject.domain.exception.BusNotFoundException;
import com.flixbus.miniproject.domain.exception.DepotNotEmptyException;
import com.flixbus.miniproject.domain.exception.DepotNotFoundException;
import com.flixbus.miniproject.domain.exception.DuplicatePlateNumberException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ErrorController {
	
	private final Logger logger = LoggerFactory.getLogger(ErrorController.class);
	private static final String EXCEPTION_HANDLED = "Exception handled!\r";
	
	@ResponseBody
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
		logException(ex);
		return buildResponseEntity(HttpStatus.BAD_REQUEST,ex.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(BusNotFoundException.class)
	public ResponseEntity<?> handleBusNotFoundException(BusNotFoundException ex) {
		logException(ex);
		return buildResponseEntity(HttpStatus.NOT_FOUND,ex.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(DuplicatePlateNumberException.class)
	public ResponseEntity<?> handleDuplicatePlateNumberException(DuplicatePlateNumberException ex) {
		logException(ex);
		return buildResponseEntity(HttpStatus.CONFLICT,ex.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(DepotNotFoundException.class)
	public ResponseEntity<?> handleDepotNotFoundException(DepotNotFoundException ex) {
		logException(ex);
		return buildResponseEntity(HttpStatus.NOT_FOUND,ex.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(DepotNotEmptyException.class)
	public ResponseEntity<?> handleDepotNotEmptyException(DepotNotEmptyException ex) {
		logException(ex);
		return buildResponseEntity(HttpStatus.CONFLICT,ex.getMessage());
	}

	private void logException(final Exception ex) {
		logger.error(EXCEPTION_HANDLED,ex);
	}

	private ResponseEntity<?> buildResponseEntity(final HttpStatus httpStatus,final String message) {
		return new ResponseEntity<>(message, httpStatus);
	}
}