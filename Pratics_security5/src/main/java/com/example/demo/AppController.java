package com.example.demo;

import java.security.Principal;

import javax.servlet.http.Cookie;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
	
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/mylogin")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/admin")
	public String admin(Principal pal) {
		Authentication myauthentication= SecurityContextHolder.getContext().getAuthentication();
        System.out.println(myauthentication.getName());
        System.out.println(pal.getName());
        
        
		return "admin";
	}

}
