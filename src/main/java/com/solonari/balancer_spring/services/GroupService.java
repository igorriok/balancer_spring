package com.solonari.balancer_spring.services;

import com.solonari.balancer_spring.dao.GroupDao;
import com.solonari.balancer_spring.entities.GroupEntity;
import com.solonari.balancer_spring.entities.UserEntity;
import com.solonari.balancer_spring.security.UsersDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class GroupService {
	
	private static final Logger log = LoggerFactory.getLogger(GroupService.class);
	private final GroupDao groupDao;
	private final UsersDetailsService usersDetailsService;
	
	public GroupService(GroupDao groupDao, UsersDetailsService usersDetailsService) {
		this.groupDao = groupDao;
		this.usersDetailsService = usersDetailsService;
	}
	
	
	public Set<GroupEntity> saveGroup(String groupName, String username) {
		
		UserEntity userEntity = usersDetailsService.getUserByUsername(username);
		
		userEntity = userEntity.addGroup(groupName);
		
		userEntity = usersDetailsService.saveUser(userEntity);
		
		return userEntity.groups;
	}
	
	
	public Set<GroupEntity> getGroupListByUsername(String username) {
		
		UserEntity userEntity = usersDetailsService.getUserByUsername(username);
		
		return userEntity.groups;
	}
}
