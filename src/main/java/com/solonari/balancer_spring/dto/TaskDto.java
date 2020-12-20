package com.solonari.balancer_spring.dto;

import com.solonari.balancer_spring.entities.TaskEntity;

public class TaskDto {
	
	public Long id;
	public String taskName;
	public Long groupId = 0L;
	
	public TaskDto() {}
	
	public TaskDto(TaskEntity taskEntity) {
		this.id = taskEntity.id;
		this.taskName = taskEntity.name;
		if (taskEntity.group != null) {
			this.groupId = taskEntity.group.id;
		}
	}
	
	
	@Override
	public String toString() {
		return "TaskDto{" +
				"id=" + id +
				", taskName='" + taskName + '\'' +
				", groupId=" + groupId +
				'}';
	}
}
