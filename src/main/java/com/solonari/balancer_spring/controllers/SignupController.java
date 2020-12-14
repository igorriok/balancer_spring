package com.solonari.balancer_spring.controllers;

import com.solonari.balancer_spring.dto.EmailPassword;
import com.solonari.balancer_spring.security.UsersDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class SignupController {
	
	private static final Logger log = LoggerFactory.getLogger(SignupController.class);
	
	private final UsersDetailsService usersDetailsService;
	
	public SignupController(UsersDetailsService usersDetailsService) {
		this.usersDetailsService = usersDetailsService;
	}
	
	
	@PostMapping(value = "/signup")
	public ResponseEntity<String> signup(@RequestBody EmailPassword emailPassword) {
		
		log.info("Signup: {}", emailPassword.toString());
		
		return usersDetailsService.saveNewUser(emailPassword);
	}
}
