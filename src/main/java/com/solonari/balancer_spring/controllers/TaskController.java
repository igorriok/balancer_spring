package com.solonari.balancer_spring.controllers;

import com.solonari.balancer_spring.dto.TaskDto;
import com.solonari.balancer_spring.services.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;
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
		
		if (taskDto.taskName != null && !taskDto.taskName.equals("")) {
			List<TaskDto> taskDtoList = taskService.addTask(taskDto.taskName, token.getName(), taskDto.groupId).stream()
					.map((TaskDto::new))
					.collect(Collectors.toList());
			
			return ResponseEntity.ok().body(taskDtoList);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	@GetMapping(value = "/tasks")
	public ResponseEntity<List<TaskDto>> tasks(Principal token) {
		
		log.info("Task list for {}", token.getName());
		
		List<TaskDto> taskDtoList = taskService.getTaskListByUsername(token.getName()).stream()
				.map((TaskDto::new))
				.collect(Collectors.toList());
		
		log.info("Got list of tasks: {}", taskDtoList);
		
		return ResponseEntity.ok().body(taskDtoList);
	}
	
	
	@DeleteMapping(value = "deletetask/{id}")
	public ResponseEntity<Set<TaskDto>> deleteTask(@PathVariable Long id, Principal principal) {
		
		log.info("{} delete task: {}", principal.getName(), id);
		
		Set<TaskDto> taskDtoList = taskService.deleteTask(id, principal.getName()).stream()
				.map((TaskDto::new))
				.collect(Collectors.toSet());
		
		return ResponseEntity.ok().body(taskDtoList);
	}
	
}
