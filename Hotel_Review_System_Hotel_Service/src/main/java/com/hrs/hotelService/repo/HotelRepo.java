package com.hrs.hotelService.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrs.hotelService.entity.Hotel;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Integer> {

}
