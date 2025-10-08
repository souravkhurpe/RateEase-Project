package com.hrs.hotelService.exception;

public class HotelNotFoundException extends RuntimeException {

	public HotelNotFoundException(String msg) {
		super(msg);
	}
}
