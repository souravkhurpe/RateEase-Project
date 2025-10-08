package com.hrs.userService.service;

import com.hrs.userService.dto.SignInRequestDTO;
import com.hrs.userService.dto.SignInResponseDTO;
import com.hrs.userService.dto.SignUpRequestDTO;

public interface AuthService {

	String signUp(SignUpRequestDTO requestDTO);

	SignInResponseDTO signIn(SignInRequestDTO requestDTO);

}
