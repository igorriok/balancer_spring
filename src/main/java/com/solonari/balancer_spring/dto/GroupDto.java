package com.solonari.balancer_spring.dto;

import com.solonari.balancer_spring.entities.GroupEntity;

public class GroupDto {
	
	public Long id;
	public String groupName;
	
	
	public GroupDto () {}
	
	public GroupDto (GroupEntity groupEntity) {
		this.id = groupEntity.id;
		this.groupName = groupEntity.name;
	}
}
