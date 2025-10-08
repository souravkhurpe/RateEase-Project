package com.hrs.ratingService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RatingNotFoundException.class)
	public ResponseEntity<?> ratingNotFoundExceptionHandle(RatingNotFoundException ex) {

		return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
}
