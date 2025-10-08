package com.hrs.userService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrs.userService.dto.UserRequestDTO;
import com.hrs.userService.dto.UserResponseDTO;
import com.hrs.userService.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping
	public ResponseEntity<?> saveUser(@RequestBody UserRequestDTO requestDTO) {

		System.out.println(requestDTO);

		UserResponseDTO responseDTO = service.saveUser(requestDTO);

		return new ResponseEntity(responseDTO, HttpStatus.OK);

	}

	@GetMapping
	@CircuitBreaker(fallbackMethod = "fallBackLogic", name = "circuitBreaker")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> getAllUser() {

		List<UserResponseDTO> list = service.getAllUser();

		return new ResponseEntity(list, HttpStatus.OK);
	}

	// fall back method
	public ResponseEntity<?> fallBackLogic(Throwable t) {

		System.out.println("Fall back logic executed..!!");

		return new ResponseEntity("Please try again later..!!", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	int retryCount = 1;

	@GetMapping("/{userId}")
//	@Retry(name = "retryLogic", fallbackMethod = "fallBackLogic") // this is for retry and then go to fall back logic.
//	@CircuitBreaker(fallbackMethod = "fallBackLogic", name = "circuitBreaker")
	@PreAuthorize("hasAnyRole('USER')")
	@RateLimiter(name = "RateLimiter", fallbackMethod = "fallBackLogic") // this is for rate limiting on this API.
	public ResponseEntity<?> getUserById(@PathVariable("userId") Integer userId) {

		this.retryCount++;

		System.out.println("Retry Count: " + this.retryCount);

		UserResponseDTO responseDTO = service.getUserById(userId);

		return new ResponseEntity(responseDTO, HttpStatus.OK);
	}

	@DeleteMapping("/{userId}")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<?> deleteUserById(@PathVariable("userId") Integer userId) {

		service.deleteUserById(userId);

		return new ResponseEntity("User deleted successfully by ID: " + userId, HttpStatus.OK);

	}

}
