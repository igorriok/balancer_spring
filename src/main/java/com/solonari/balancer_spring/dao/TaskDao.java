package com.solonari.balancer_spring.dao;

import com.solonari.balancer_spring.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskDao extends JpaRepository<TaskEntity, Integer> {
	
	List<TaskEntity> findAllByUser_Username(String username);

}
