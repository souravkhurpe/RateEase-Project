package com.hrs.userService.service;

import java.util.Map;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;

import com.hrs.userService.security.CustomeUserDetails;

import io.jsonwebtoken.Claims;

public interface JwtService {
	
	String extractUsername(String token);

	<T> T extractClaim(String token, Function<Claims, T> claimsResolver);

	String generateToken(CustomeUserDetails userDetails);

	String generateToken(Map<String, Object> extraClaims, CustomeUserDetails userDetails);

	long getExpirationTime();

	boolean isTokenValid(String token, CustomeUserDetails userDetails);

	String extractTokenFromRequest(HttpServletRequest request);

}
