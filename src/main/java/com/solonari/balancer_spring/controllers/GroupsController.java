package com.solonari.balancer_spring.controllers;

import com.solonari.balancer_spring.dto.GroupDto;
import com.solonari.balancer_spring.entities.GroupEntity;
import com.solonari.balancer_spring.services.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
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
	public ResponseEntity<Set<GroupDto>> saveGroup(@RequestBody GroupDto groupDto, Principal token) {
		
		log.info("Save group: {} by {}", groupDto.toString(), token.getName());
		
		Set<GroupDto> groupDtoSet = new HashSet<>();
		
		if (groupDto.id != null) {
			
			Set<GroupEntity> groupEntitySet = groupService.updateGroup(groupDto, token.getName());
			
			if (groupEntitySet != null) {
				
				groupDtoSet = groupEntitySet.stream()
						.map((GroupDto::new))
						.collect(Collectors.toSet());
			}
			
			return ResponseEntity.ok(groupDtoSet);
		}
		
		groupDtoSet = groupService.addGroup(groupDto.groupName, token.getName())
				.stream()
				.map((GroupDto::new))
				.collect(Collectors.toSet());
		
		return ResponseEntity.ok(groupDtoSet);
	}
	
	
	@GetMapping(value = "/groups")
	public ResponseEntity<Set<GroupDto>> groups(Principal token) {
		
		log.info("Groups for {}", token.getName());
		
		Set<GroupDto> taskDtoList = groupService.getGroupListByUsername(token.getName()).stream()
				.map((GroupDto::new))
				.collect(Collectors.toSet());
		
		return ResponseEntity.ok().body(taskDtoList);
	}
	
	
	@DeleteMapping(value = "deletegroup/{id}")
	public ResponseEntity<Set<GroupDto>> deleteGroup(@PathVariable Long id, Principal principal) {
		
		log.info("{} delete group: {}", principal.getName(), id);
		
		Set<GroupDto> taskDtoList = groupService.deleteGroup(id, principal.getName()).stream()
				.map((GroupDto::new))
				.collect(Collectors.toSet());
		
		return ResponseEntity.ok().body(taskDtoList);
	}
}
