package com.solonari.balancer_spring.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(	name = "users",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "email")
		})
public class UserEntity implements Serializable {
	
	@Id
	@GeneratedValue
	public Long id;
	
	@NotBlank
	@Size(max = 20)
	public String username;
	
	@NotBlank
	@Size(max = 50)
	@Email
	public String email;
	
	@NotBlank
	@Size(max = 120)
	public String password;
	
	
	@OneToMany(mappedBy = "userEntity")
	public Set<TaskEntity> taskEntityList = new HashSet<>();
	
	
	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
	})
	@JoinTable(
			name = "users_groups",
			joinColumns = @JoinColumn(name = "users_id"),
			inverseJoinColumns = @JoinColumn(name = "groups_id"))
	public Set<GroupEntity> groupEntityList = new HashSet<>();
	
	
	
	public UserEntity() {
	}
	
	public UserEntity(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	
	@Override
	public String toString() {
		return "UserEntity{" +
				"id=" + id +
				", username='" + username + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
