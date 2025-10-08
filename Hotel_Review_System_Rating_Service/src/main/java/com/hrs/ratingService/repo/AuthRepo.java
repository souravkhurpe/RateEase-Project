package com.hrs.ratingService.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrs.ratingService.entity.User;



@Repository
public interface AuthRepo extends JpaRepository<User, Integer> {

	User findByUserEmail(String userEmail);

}
