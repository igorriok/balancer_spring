package com.solonari.balancer_spring.dto;

import com.solonari.balancer_spring.entities.GroupEntity;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class GroupDto {
	
	public Long id;
	public String groupName;
	public Set<ParticipantDto> participants = new HashSet<>();
	
	
	public GroupDto () {}
	
	public GroupDto (GroupEntity groupEntity) {
		this.id = groupEntity.id;
		this.groupName = groupEntity.name;
		this.participants = groupEntity.users.stream()
				.map(ParticipantDto::new)
				.collect(Collectors.toSet());
	}
}
