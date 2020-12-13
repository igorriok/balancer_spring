package com.solonari.balancer_spring.dao;

import com.solonari.balancer_spring.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskDao extends JpaRepository<TaskEntity, Integer> {

}
