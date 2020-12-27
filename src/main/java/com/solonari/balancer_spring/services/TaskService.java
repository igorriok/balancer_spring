package com.solonari.balancer_spring.services;

import com.solonari.balancer_spring.dao.TaskDao;
import com.solonari.balancer_spring.entities.TaskEntity;
import com.solonari.balancer_spring.entities.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TaskService {
	
	private static final Logger log = LoggerFactory.getLogger(TaskService.class);
	private final TaskDao taskDao;
	private final UsersDetailsService usersDetailsService;
	
	public TaskService(TaskDao taskDao, UsersDetailsService usersDetailsService) {
		this.taskDao = taskDao;
		this.usersDetailsService = usersDetailsService;
	}
	
	
	public Set<TaskEntity> addTask(String taskName, String username, Long groupId) {
		
		if (taskName != null) {
			
			UserEntity userEntity = usersDetailsService.getUserByUsername(username);
			
			//log.info("UserEntity is: {}", userEntity);
			
			userEntity = userEntity.addTask(taskName, groupId);
			
			userEntity = usersDetailsService.saveUser(userEntity);
			
			return userEntity.tasks;
			
		} else {
			throw new NullPointerException("Task name is null");
		}
	}
	
	
	public List<TaskEntity> getTaskListByUsername(String username) {
		
		return taskDao.findAllByUser_Username(username);
	}
	
	
	public Set<TaskEntity> deleteTask (Long taskId, String username) {
		
		UserEntity userEntity = usersDetailsService.getUserByUsername(username);
		
		userEntity = userEntity.removeTask(taskId);
		
		userEntity = usersDetailsService.saveUser(userEntity);
		
		return userEntity.tasks;
	}
}
