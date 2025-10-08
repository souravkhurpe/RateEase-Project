package com.hrs.hotelService.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrs.hotelService.dto.HotelRequestDTO;
import com.hrs.hotelService.dto.HotelResponseDTO;
import com.hrs.hotelService.entity.Hotel;
import com.hrs.hotelService.exception.HotelNotFoundException;
import com.hrs.hotelService.repo.HotelRepo;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepo repo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public HotelResponseDTO saveHotel(HotelRequestDTO requestDTO) {

		Hotel hotel = mapper.map(requestDTO, Hotel.class);

		Hotel saveHotel = repo.save(hotel);

		HotelResponseDTO responseDTO = mapper.map(saveHotel, HotelResponseDTO.class);

		return responseDTO;

	}

	@Override
	public List<HotelResponseDTO> getAllHotel() {
		List<Hotel> list = repo.findAll();

		List<HotelResponseDTO> list2 = list.stream().map(hotel -> mapper.map(hotel, HotelResponseDTO.class))
				.collect(Collectors.toList());

		return list2;
	}

	@Override
	public HotelResponseDTO getHotelById(Integer hotelId) {

		Hotel hotel = repo.findById(hotelId)
				.orElseThrow(() -> new HotelNotFoundException("Hotel not found against provided ID..!!"));

		HotelResponseDTO responseDTO = mapper.map(hotel, HotelResponseDTO.class);
		return responseDTO;
	}

	@Override
	public void deleteHotelById(Integer hotelId) {
		Hotel hotel = repo.findById(hotelId)
				.orElseThrow(() -> new HotelNotFoundException("Hotel not found against provided ID..!!"));

		repo.delete(hotel);

	}

}
