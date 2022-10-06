package com.product.tracking.controllers.advice;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.product.tracking.exceptions.AccountAlreadyExistsException;
import com.product.tracking.exceptions.OldPasswordIsIncorectException;
import com.product.tracking.exceptions.PznAlreadyExistsException;
import com.product.tracking.exceptions.UsernameAlreadyExistsException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(AccountAlreadyExistsException.class)
	public ResponseEntity<String> dataIntegrityException(AccountAlreadyExistsException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PznAlreadyExistsException.class)
	public ResponseEntity<String> dataIntegrityException(PznAlreadyExistsException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UsernameAlreadyExistsException.class)
	public ResponseEntity<String> dataIntegrityException(UsernameAlreadyExistsException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<String> dataIntegrityException(DataIntegrityViolationException e) {
		return new ResponseEntity<>("Data integrity violation! Some required property may be missing", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(OldPasswordIsIncorectException.class)
	public ResponseEntity<String> dataIntegrityException(OldPasswordIsIncorectException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
