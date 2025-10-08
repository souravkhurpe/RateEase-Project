package com.hrs.ratingService.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrs.ratingService.dto.RatingRequestDTO;
import com.hrs.ratingService.dto.RatingResponseDTO;
import com.hrs.ratingService.entity.Rating;
import com.hrs.ratingService.exception.RatingNotFoundException;
import com.hrs.ratingService.repo.RatingRepo;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepo repo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public RatingResponseDTO saveRating(RatingRequestDTO requestDTO) {

		Rating rating = mapper.map(requestDTO, Rating.class);

		Rating saveRating = repo.save(rating);
		RatingResponseDTO responseDTO = mapper.map(saveRating, RatingResponseDTO.class);

		return responseDTO;

	}

	@Override
	public List<RatingResponseDTO> getAllRating() {
		List<RatingResponseDTO> list = repo.findAll().stream()
				.map(rating -> mapper.map(rating, RatingResponseDTO.class)).collect(Collectors.toList());
		return list;
	}

	@Override
	public List<RatingResponseDTO> getRatingByUserId(Integer userId) {

		List<Rating> list = Optional.ofNullable(repo.findAllByUserId(userId))
				.orElseThrow(() -> new RatingNotFoundException("Rating not found against userId: " + userId));

		List<RatingResponseDTO> list2 = list.stream().map(rating -> mapper.map(rating, RatingResponseDTO.class)).collect(Collectors.toList());
		
//		RatingResponseDTO responseDTO = mapper.map(rating, RatingResponseDTO.class);

		return list2;
	}

	@Override
	public List<RatingResponseDTO> getRatingByHotelId(Integer hotelId) {
		List<Rating> list = Optional.ofNullable(repo.findAllByHotelId(hotelId))
				.orElseThrow(() -> new RatingNotFoundException("Rating not found against hotelId: " + hotelId));

//		RatingResponseDTO responseDTO = mapper.map(rating, RatingResponseDTO.class);

		List<RatingResponseDTO> list2 = list.stream().map(rating -> mapper.map(rating, RatingResponseDTO.class)).collect(Collectors.toList());
		
		return list2;
	}

	@Override
	public void deleteRatingByRatingId(Integer ratingId) {

		Rating rating = repo.findById(ratingId)
				.orElseThrow(() -> new RatingNotFoundException("Rating not found against ratingId: " + ratingId));

		repo.delete(rating);

	}
}
