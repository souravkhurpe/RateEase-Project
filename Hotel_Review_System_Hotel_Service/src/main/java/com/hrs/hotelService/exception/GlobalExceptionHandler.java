package com.hrs.hotelService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(HotelNotFoundException.class)
	public ResponseEntity<?> hotelNotFoundExceptionHandle(HotelNotFoundException ex) {

		return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);

	}
}
