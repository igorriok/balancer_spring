package com.solonari.balancer_spring.entities;

import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class TaskEntity {
	
	@Id
	@GeneratedValue
	public Long id;
	
	@NotBlank
	@Size(max = 30)
	public String name;
	
	@NotBlank
	public LocalDateTime addedDate = LocalDateTime.now();
	
	public LocalDateTime endDate;
	
	@ManyToOne
	public UserEntity user;
	
	@ManyToOne
	public GroupEntity group;
	
	
	public TaskEntity() {
		this.addedDate = LocalDateTime.now();
	}
	
}
