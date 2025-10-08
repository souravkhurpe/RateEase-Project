package com.hrs.userService.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrs.userService.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

}
