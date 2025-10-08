package com.hrs.userService.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hrs.userService.Enum.Role;
import com.hrs.userService.dto.SignInRequestDTO;
import com.hrs.userService.dto.SignInResponseDTO;
import com.hrs.userService.dto.SignUpRequestDTO;
import com.hrs.userService.entity.User;
import com.hrs.userService.exception.UserAlreadyExistException;
import com.hrs.userService.exception.UserNotFoundException;
import com.hrs.userService.repo.AuthRepo;

@Service
public class AuthServiceIMPL implements AuthService {

	@Autowired
	private AuthRepo repo;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	@Override
	public String signUp(SignUpRequestDTO requestDTO) {

		Optional<User> optionalUser = Optional.ofNullable(repo.findByUserEmail(requestDTO.getUserEmail()));

		String response = "";
		if (!optionalUser.isPresent()) {

			User user = mapper.map(requestDTO, User.class);

			user.setUserPassword(encoder.encode(requestDTO.getUserPassword()).toString());

			user.setRole(Role.USER);

			User saveUser = repo.save(user);

			response = response + "User register successfully...!!";

		} else {

			throw new UserAlreadyExistException("User already present with provided email, use other email to signUP");
		}

		return response;

	}

	@Override
	public SignInResponseDTO signIn(SignInRequestDTO requestDTO) {

		User user = Optional.ofNullable(repo.findByUserEmail(requestDTO.getUserEmail()))
				.orElseThrow(() -> new UserNotFoundException("User not found against provided username ..!!"));

		SignInResponseDTO responseDTO = new SignInResponseDTO();
		if (encoder.matches(requestDTO.getUserPassword(), user.getPassword())) {

			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(requestDTO.getUserEmail(), requestDTO.getUserPassword())); // giving

			String token = jwtService.generateToken(user);

			responseDTO.setToken(token);

		} else {

			throw new UserNotFoundException("Incorrect password..!!");
		}

		
		return responseDTO;
	}

}
