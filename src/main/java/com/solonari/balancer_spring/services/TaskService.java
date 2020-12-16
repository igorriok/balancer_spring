package com.solonari.balancer_spring.services;

import com.solonari.balancer_spring.dao.TaskDao;
import com.solonari.balancer_spring.dao.UserDao;
import com.solonari.balancer_spring.entities.TaskEntity;
import com.solonari.balancer_spring.entities.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
	
	private static final Logger log = LoggerFactory.getLogger(TaskService.class);
	private final TaskDao taskDao;
	private final UserDao userDao;
	
	public TaskService(TaskDao taskDao, UserDao userDao) {
		this.taskDao = taskDao;
		this.userDao = userDao;
	}
	
	
	public List<TaskEntity> addTask(String taskName, String username, String groupName) {
		
		if (taskName != null) {
			
			UserEntity userEntity = userDao.findFirstByUsername(username);
			
			log.info("UserEntity is: {}", userEntity);
			
			taskDao.save(new TaskEntity(taskName, userEntity));
			
			return getTaskListByUsername(username);
			
		} else {
			throw new NullPointerException("Task name is null");
		}
	}
	
	
	public List<TaskEntity> getTaskListByUsername(String username) {
		
		return taskDao.findAllByUser_Username(username);
	}
}
