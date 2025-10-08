package com.hrs.ratingService.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface CustomeUserDetails extends UserDetails {

	String getUserEmail();
}
