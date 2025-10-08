package com.hrs.userService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HotelResponseDTO {

	private String hotelName;

	private String hotelLocation;

	private String hotelAbout;
}
