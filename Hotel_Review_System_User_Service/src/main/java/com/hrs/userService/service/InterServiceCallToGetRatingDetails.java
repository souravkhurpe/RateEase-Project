package com.hrs.userService.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hrs.userService.dto.RatingResponseDTO;

@FeignClient("RatingService")
public interface InterServiceCallToGetRatingDetails {

	@GetMapping("/rating/user/{userId}")
	public abstract List<RatingResponseDTO> getRatingByUserId(@PathVariable ("userId")Integer userId);
}
