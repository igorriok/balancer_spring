package com.solonari.balancer_spring.controllers;

import com.solonari.balancer_spring.dto.TaskDto;
import com.solonari.balancer_spring.services.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
public class TaskController {
	
	private static final Logger log = LoggerFactory.getLogger(TaskController.class);
	private final TaskService taskService;
	
	@Autowired
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	
	@PostMapping(value = "/savetask")
	public ResponseEntity<TaskDto> saveTask(@RequestBody TaskDto taskDto, Principal token) {
		
		log.info("Task is: {}", taskDto);
		log.info("Token is: {}", token.getName());
		
		TaskDto savedTask = new TaskDto(taskService.addTask(taskDto.taskName, token.getName(), taskDto.groupName));
		
		return ResponseEntity.ok().body(savedTask);
	}
	
}
