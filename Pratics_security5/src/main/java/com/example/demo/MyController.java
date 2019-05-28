package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class MyController {
	
	  @Autowired
	    AuthenticationManager authenticationManager;
	  
	  @Autowired
	    JwtTokenProvider tokenProvider;

	@RequestMapping("/getData1")
	public String getData1() {
		return "getData1";
	}
	
	@PostMapping("/getData2")
	public String getData2() {
		return "getData2";
	}
	
	 @GetMapping("/signin")
	    public ResponseEntity<?> authenticateUser() {

	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        "user",
	                        "user"
	                )
	        );

	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        String jwt = tokenProvider.generateToken(authentication);
	        return ResponseEntity.ok(jwt);
	    }
	    
	
}
