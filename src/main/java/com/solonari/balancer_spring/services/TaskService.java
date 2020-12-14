package com.solonari.balancer_spring.services;

import com.solonari.balancer_spring.dao.TaskDao;
import com.solonari.balancer_spring.dao.UserDao;
import com.solonari.balancer_spring.entities.TaskEntity;
import com.solonari.balancer_spring.entities.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
	
	private static final Logger log = LoggerFactory.getLogger(TaskService.class);
	private final TaskDao taskDao;
	private final UserDao userDao;
	
	public TaskService(TaskDao taskDao, UserDao userDao) {
		this.taskDao = taskDao;
		this.userDao = userDao;
	}
	
	public TaskEntity addTask(String taskName, String username, String groupName) {
		
		if (taskName != null) {
			
			UserEntity userEntity = userDao.findFirstByUsername(username);
			
			log.info("UserEntity is: {}", userEntity);
			
			return taskDao.save(new TaskEntity(taskName, userEntity));
			
		} else {
			throw new NullPointerException("Task name is null");
		}
	}
}
