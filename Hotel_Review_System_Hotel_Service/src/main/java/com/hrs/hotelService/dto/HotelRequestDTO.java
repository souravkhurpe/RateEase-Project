package com.hrs.hotelService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HotelRequestDTO {

	private String hotelName;

	private String hotelLocation;

	private String hotelAbout;
}
