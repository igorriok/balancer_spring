package com.solonari.balancer_spring.controllers;

import com.solonari.balancer_spring.dto.EmailPassword;
import com.solonari.balancer_spring.entities.UserEntity;
import com.solonari.balancer_spring.security.CustomDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class SignupController {
	
	private static final Logger log = LoggerFactory.getLogger(SignupController.class);
	
	private final CustomDetailsService customDetailsService;
	
	public SignupController(CustomDetailsService customDetailsService) {
		this.customDetailsService = customDetailsService;
	}
	
	
	@PostMapping(value = "/signup")
	public ResponseEntity<String> signup(@RequestBody EmailPassword emailPassword) {
		
		log.info("Signup: {}", emailPassword.toString());
		
		return customDetailsService.saveNewUser(emailPassword);
	}
}
