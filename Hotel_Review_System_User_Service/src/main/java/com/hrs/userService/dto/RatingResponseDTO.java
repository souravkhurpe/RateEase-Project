package com.hrs.userService.dto;

import lombok.ToString;

import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RatingResponseDTO {

	private Integer userId;

	private Integer hotelId;

	private int rating;

	private String feedback;
}
