package com.solonari.balancer_spring.dto;

import com.solonari.balancer_spring.entities.TaskEntity;

import java.time.LocalDateTime;

public class TaskDto {
	
	public Long id;
	public String taskName;
	public LocalDateTime addedDate;
	public Long groupId = 0L;
	
	public TaskDto() {}
	
	public TaskDto(TaskEntity taskEntity) {
		this.id = taskEntity.id;
		this.addedDate = taskEntity.addedDate;
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
				", addedDate=" + addedDate +
				", groupId=" + groupId +
				'}';
	}
}
