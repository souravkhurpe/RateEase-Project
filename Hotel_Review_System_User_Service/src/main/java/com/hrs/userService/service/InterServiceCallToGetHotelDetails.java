package com.hrs.userService.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hrs.userService.dto.HotelResponseDTO;

@FeignClient("hotel-service")
public interface InterServiceCallToGetHotelDetails {

	@GetMapping("/hotel/{hotelId}")
	public abstract HotelResponseDTO getHotelById(@PathVariable ("hotelId") Integer hotelId);
}
