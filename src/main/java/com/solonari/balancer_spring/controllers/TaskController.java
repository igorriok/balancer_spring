package com.solonari.balancer_spring.controllers;

import com.solonari.balancer_spring.dto.TaskDto;
import com.solonari.balancer_spring.services.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class TaskController {
	
	private static final Logger log = LoggerFactory.getLogger(TaskController.class);
	private final TaskService taskService;
	
	@Autowired
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	
	@PostMapping(value = "/savetask")
	public ResponseEntity<List<TaskDto>> saveTask(@RequestBody TaskDto taskDto, Principal token) {
		
		log.info("Save task: {} by {}", taskDto, token.getName());
		
		List<TaskDto> taskDtoList = taskService.addTask(taskDto.taskName, token.getName(), taskDto.groupName).stream()
				.map((TaskDto::new))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(taskDtoList);
	}
	
	
	@GetMapping(value = "/tasks")
	public ResponseEntity<List<TaskDto>> tasks(Principal token) {
		
		log.info("Task list for {}", token.getName());
		
		List<TaskDto> taskDtoList = taskService.getTaskListByUsername(token.getName()).stream()
				.map((TaskDto::new))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(taskDtoList);
	}
	
}
