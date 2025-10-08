package com.hrs.userService.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface CustomeUserDetails extends UserDetails {

	String getUserEmail();
}
