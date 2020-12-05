package com.solonari.balancer_spring.entities;

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
	
	@ManyToOne
	public UserEntity userEntity;
	
	@NotBlank
	@Size(max = 30)
	public String name;
	
	@NotBlank
	public LocalDateTime addedDate;
	
	public LocalDateTime endDate;
	
	
	public TaskEntity() {
		this.addedDate = LocalDateTime.now();
	}
	
}
