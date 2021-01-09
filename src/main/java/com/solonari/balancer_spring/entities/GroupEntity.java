package com.solonari.balancer_spring.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
public class GroupEntity {
	
	
	@Id
	@GeneratedValue
	public Long id;
	
	@NotBlank
	@Size(max = 30)
	public String name;
	
	@NotBlank
	public LocalDateTime added = LocalDateTime.now();
	
	
	
	@ManyToMany(mappedBy = "groups")
	public Set<UserEntity> users = new HashSet<>();
	
	
	@OneToMany(mappedBy = "group")
	public Set<TaskEntity> tasks = new HashSet<>();
	
	
	public GroupEntity() {}
	
	public GroupEntity(String name, UserEntity userEntity) {
		this.name = name;
		this.users.add(userEntity);
	}
	
	public GroupEntity addUser(UserEntity userEntity) {
		this.users.add(userEntity);
		return this;
	}
	
	@Override
	public String toString() {
		return "GroupEntity{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}
