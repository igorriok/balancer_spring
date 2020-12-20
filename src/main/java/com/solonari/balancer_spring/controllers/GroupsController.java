package com.solonari.balancer_spring.controllers;

import com.solonari.balancer_spring.dto.GroupDto;
import com.solonari.balancer_spring.services.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class GroupsController {
	
	private static final Logger log = LoggerFactory.getLogger(GroupsController.class);
	private final GroupService groupService;
	
	public GroupsController(GroupService groupService) {
		this.groupService = groupService;
	}
	
	
	@PostMapping(value = "/savegroup")
	public ResponseEntity<Set<GroupDto>> addGroup(@RequestBody GroupDto groupDto, Principal token) {
		
		log.info("Save group: {} by {}", groupDto, token.getName());
		
		Set<GroupDto> groupDtoSet = groupService.saveGroup(groupDto.groupName, token.getName())
				.stream()
				.map((GroupDto::new))
				.collect(Collectors.toSet());
		
		return ResponseEntity.ok(groupDtoSet);
	}
	
	
	@GetMapping(value = "/groups")
	public ResponseEntity<Set<GroupDto>> tasks(Principal token) {
		
		log.info("Groups for {}", token.getName());
		
		Set<GroupDto> taskDtoList = groupService.getGroupListByUsername(token.getName()).stream()
				.map((GroupDto::new))
				.collect(Collectors.toSet());
		
		return ResponseEntity.ok().body(taskDtoList);
	}
}
