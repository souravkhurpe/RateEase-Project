package com.hrs.userService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> userNotFoundExceptionHandle(UserNotFoundException ex) {

		return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<?> userAlreadyExistExceptionHandle(UserAlreadyExistException ex) {

		return new ResponseEntity(ex.getMessage(), HttpStatus.CONFLICT);
	}

}
