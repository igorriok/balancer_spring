package com.solonari.balancer_spring.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
public class GroupEntity {
	
	
	@Id
	@GeneratedValue
	public String id;
	
	@NotBlank
	@Size(max = 30)
	public String name;
	
	
	@ManyToMany(mappedBy = "groupEntityList")
	public Set<UserEntity> userEntityList = new HashSet<>();
	
	
}
