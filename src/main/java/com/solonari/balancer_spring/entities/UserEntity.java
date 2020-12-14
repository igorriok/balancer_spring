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
				@UniqueConstraint(columnNames = "username")
		})
public class UserEntity implements Serializable {
	
	@Id
	@GeneratedValue
	public Long id;
	
	@NotBlank
	@Size(max = 20)
	@Email
	public String username;
	
	@NotBlank
	@Size(max = 50)
	public String nickName;
	
	@NotBlank
	@Size(max = 120)
	public String password;
	
	
	@OneToMany(mappedBy = "user")
	public Set<TaskEntity> tasks = new HashSet<>();
	
	
	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
	})
	@JoinTable(
			name = "users_groups",
			joinColumns = @JoinColumn(name = "users_id"),
			inverseJoinColumns = @JoinColumn(name = "groups_id"))
	public Set<GroupEntity> groups = new HashSet<>();
	
	
	
	public UserEntity() {
	}
	
	public UserEntity(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public UserEntity(String username, String password, String nickName) {
		this.username = username;
		this.password = password;
		this.nickName = nickName;
	}
	
}
