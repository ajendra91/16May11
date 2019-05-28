package com.example.demo;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

	public String generateToken(Authentication authentication) {
		
		return "success";
	}

	public String get() {
		return "success";
	}

}
