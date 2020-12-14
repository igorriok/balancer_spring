package com.solonari.balancer_spring.dto;

import com.solonari.balancer_spring.entities.TaskEntity;

public class TaskDto {
	
	public Long id;
	public String taskName;
	public String groupName;
	
	public TaskDto() {}
	
	public TaskDto(TaskEntity taskEntity) {
		this.id = taskEntity.id;
		this.taskName = taskEntity.name;
		if (taskEntity.group != null) {
			this.groupName = taskEntity.group.name;
		}
	}
}
