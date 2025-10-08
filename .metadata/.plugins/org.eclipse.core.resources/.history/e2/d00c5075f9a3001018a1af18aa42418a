package com.hrs.userService.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hrs.userService.security.CustomeUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtServiceIMPL implements JwtService {

	@Value("${jwt.secret}")
	private String secretKey;

	@Value("${jwt.expiration-time}")
	private long jwtExpiration;

	@Override
	public String extractUsername(String token) {
		log.debug("extractUsername({})", token);
		return extractClaim(token, Claims::getSubject);
	}

	@Override
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		if (claimsResolver == null) {
			log.warn("Claims resolver is null");
			return null;
		}
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	@Override
	public String generateToken(CustomeUserDetails userDetails) {
		log.debug("generateToken({})", userDetails.getUserEmail());
		return generateToken(new HashMap<>(), userDetails);
	}

	@Override
	public String generateToken(Map<String, Object> extraClaims, CustomeUserDetails userDetails) {
		log.debug("generateToken({}, {})", extraClaims.size(), userDetails.getUsername());
		return buildToken(extraClaims, userDetails, jwtExpiration);
	}

	@Override
	public long getExpirationTime() {
		log.debug("getExpirationTime()");
		return jwtExpiration;
	}

	private String buildToken(Map<String, Object> extraClaims, CustomeUserDetails userDetails, long expiration) {
		log.debug("buildToken({}, {}, {})", extraClaims.size(), userDetails.getUserEmail(), expiration);
		return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUserEmail())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
	}

	@Override
	public boolean isTokenValid(String token, CustomeUserDetails userDetails) {
		final String username = extractUsername(token);
		log.info("Username: {}", username);
		return (username.equals(userDetails.getUserEmail())) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
	}

	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	@Override
	public String extractTokenFromRequest(HttpServletRequest request) {
		String authorizationHeader = request.getHeader("Authorization");
		if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
			return authorizationHeader.substring(7);
		}
		return null;
	}
}
