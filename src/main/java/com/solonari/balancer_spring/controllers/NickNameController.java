package com.solonari.balancer_spring.controllers;


import com.solonari.balancer_spring.entities.UserEntity;
import com.solonari.balancer_spring.security.UsersDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class NickNameController {
	
	private static final Logger log = LoggerFactory.getLogger(NickNameController.class);
	private final UsersDetailsService usersDetailsService;
	
	public NickNameController(UsersDetailsService usersDetailsService) {
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
}
