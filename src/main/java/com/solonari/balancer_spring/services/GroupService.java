package com.solonari.balancer_spring.services;

import com.solonari.balancer_spring.dto.GroupDto;
import com.solonari.balancer_spring.dto.ParticipantDto;
import com.solonari.balancer_spring.entities.GroupEntity;
import com.solonari.balancer_spring.entities.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GroupService {
	
	private static final Logger log = LoggerFactory.getLogger(GroupService.class);
	private final UsersDetailsService usersDetailsService;
	
	public GroupService(UsersDetailsService usersDetailsService) {
		this.usersDetailsService = usersDetailsService;
	}
	
	
	public Set<GroupEntity> addGroup(String groupName, String username) {
		
		UserEntity userEntity = usersDetailsService.getUserByUsername(username);
		
		userEntity = userEntity.createGroup(groupName);
		
		userEntity = usersDetailsService.saveUser(userEntity);
		
		return userEntity.groups;
	}
	
	
	public Set<GroupEntity> updateGroup(GroupDto groupDto, String username) {
		
		UserEntity userEntity = usersDetailsService.getUserByUsername(username);
		
		Optional<GroupEntity> groupEntityOptional = userEntity.groups.stream()
				.filter((groupEntity -> groupEntity.id.equals(groupDto.id)))
				.findFirst();
		
		if (groupEntityOptional.isPresent()) {
			
			GroupEntity groupEntity = groupEntityOptional.get();
			
			if (!groupEntity.name.equals(groupDto.groupName)) {
				groupEntity.name = groupDto.groupName;
			}
			
			// find new participants
			Set<ParticipantDto> newParticipants = groupDto.participants.stream()
					.filter(participantDto -> groupEntity.users.stream()
						.anyMatch(user -> user.username.equals(participantDto.email)))
					.collect(Collectors.toSet());
			
			log.info("new participants: {}", newParticipants.toString());
			
			//save invited users and add to group
			newParticipants.forEach(participantDto -> {
				
				UserEntity participantUser = usersDetailsService.addInvitedUser(participantDto.email);
				
				groupEntity.addUser(participantUser);
			});
			
			userEntity = usersDetailsService.saveUser(userEntity);
			
			return userEntity.groups;
		} else {
			return null;
		}
	}
	
	
	public Set<GroupEntity> getGroupListByUsername(String username) {
		
		UserEntity userEntity = usersDetailsService.getUserByUsername(username);
		
		return userEntity.groups;
	}
	
	
	public Set<GroupEntity> deleteGroup(Long groupId, String username) {
		
		UserEntity userEntity = usersDetailsService.getUserByUsername(username);
		
		userEntity = userEntity.removeGroup(groupId);
		
		userEntity = usersDetailsService.saveUser(userEntity);
		
		return userEntity.groups;
	}
	
	
	public GroupEntity getGroupById(Long id, String username) {
		
		UserEntity userEntity = usersDetailsService.getUserByUsername(username);
		
		Optional<GroupEntity> groupEntityOptional = userEntity.groups.stream()
				.filter((groupEntity -> groupEntity.id.equals(id)))
				.findFirst();
		
		return groupEntityOptional.orElse(null);
	}
	
}
