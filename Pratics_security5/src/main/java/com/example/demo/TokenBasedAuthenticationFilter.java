package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenBasedAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	 @Autowired
	    JwtTokenProvider tokenProvider;
	
	
    TokenBasedAuthenticationFilter(AuthenticationManager authenticationManager) {
    	System.out.println("TokenBasedAuthenticationFilter1");
        setAuthenticationManager(authenticationManager);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

    	System.out.println("TokenBasedAuthenticationFilter2");
    	
    	String token = tokenProvider.get();
        
    	
    	response.addHeader(HttpHeaders.AUTHORIZATION, token);
    }
}
