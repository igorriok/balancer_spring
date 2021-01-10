package com.solonari.balancer_spring.controllers;


import com.solonari.balancer_spring.entities.UserEntity;
import com.solonari.balancer_spring.services.UsersDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	private final UsersDetailsService usersDetailsService;
	
	public UserController(UsersDetailsService usersDetailsService) {
		this.usersDetailsService = usersDetailsService;
	}
	
	
	@GetMapping(value = "/nickname")
	public ResponseEntity<String> getNickName(Principal token) {
		
		log.info("Get nickname for {}", token.getName());
		
		return ResponseEntity.ok().body(usersDetailsService.getUserByUsername(token.getName()).nickName);
	}
	
	
	@PostMapping(value = "/nickname")
	public ResponseEntity<String> setNickName(@RequestParam(name = "nickname") String nickName, Principal token) {
		
		log.info("Set nickName {} for: {}", nickName, token.getName());
		
		UserEntity userEntity = usersDetailsService.getUserByUsername(token.getName());
		
		userEntity.nickName = nickName;
		
		userEntity = usersDetailsService.saveUser(userEntity);
		
		return ResponseEntity.ok().body(userEntity.nickName);
	}
	
	
	@PostMapping(value = "/password")
	public ResponseEntity<String> setPassword(@RequestBody String password, Principal token) {
		
		log.info("Set password for: {}", token.getName());
		
		UserEntity userEntity = usersDetailsService.getUserByUsername(token.getName());
		
		userEntity.password = password;
		
		usersDetailsService.saveUser(userEntity);
		
		return ResponseEntity.ok().body("saved");
	}
}
