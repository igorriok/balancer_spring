package com.solonari.balancer_spring.controllers;

import com.solonari.balancer_spring.dto.TaskDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
public class TaskController {
	
	private static final Logger log = LoggerFactory.getLogger(TaskController.class);
	
	
	@PostMapping(value = "/savetask")
	public ResponseEntity<String> saveTask(@RequestBody TaskDto taskDto, Principal token) {
		
		log.info("Token is: {}", token.toString());
		log.info("Task is: {}", taskDto);
		
		
		return ResponseEntity.ok().body("Ok");
	}
	
}
