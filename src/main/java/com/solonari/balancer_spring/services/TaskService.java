package com.solonari.balancer_spring.services;

import com.solonari.balancer_spring.entities.TaskEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
	
	private static final Logger log = LoggerFactory.getLogger(TaskService.class);
	
	public ResponseEntity<TaskEntity> addTask() {
	
		return null;
	}
}
