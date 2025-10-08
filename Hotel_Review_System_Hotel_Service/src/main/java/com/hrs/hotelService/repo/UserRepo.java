package com.hrs.hotelService.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrs.hotelService.entity.User;




@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

}
