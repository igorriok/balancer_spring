package com.solonari.balancer_spring.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
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
	
	@NotBlank
	@Size(max = 30)
	public String status;
	
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	public Set<TaskEntity> tasks = new HashSet<>();
	
	
	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE })
	@JoinTable(
			name = "users_groups",
			joinColumns = @JoinColumn(name = "users_id"),
			inverseJoinColumns = @JoinColumn(name = "groups_id"))
	public Set<GroupEntity> groups = new HashSet<>();
	
	
	
	public UserEntity() {}
	
	public UserEntity(String username, String password, String nickName) {
		this.username = username;
		this.password = password;
		this.nickName = nickName;
		this.status = "active";
	}
	
	public UserEntity(String username, String password, String nickName, String status) {
		this.username = username;
		this.password = password;
		this.nickName = nickName;
		this.status = status;
	}
	
	public UserEntity addTask (String taskName, Long groupId) {
		
		TaskEntity task = new TaskEntity(taskName, this);
		
		Optional<GroupEntity> groupEntityOptional = this.groups.stream()
				.filter(group -> group.id.equals(groupId))
				.findFirst();
		
		groupEntityOptional.ifPresent(groupEntity -> task.group = groupEntity);
		
		tasks.add(task);
		
		return this;
	}
	
	public UserEntity removeTask (Long taskId) {
		
		Optional<TaskEntity> taskEntityOptional = this.tasks.stream()
				.filter(taskEntity -> taskEntity.id.equals(taskId))
				.findFirst();
		
		taskEntityOptional.ifPresent(taskEntity -> {
			taskEntity.endDate = LocalDateTime.now();
			taskEntity.user = null;
			this.tasks.remove(taskEntity);
		});
		
		return this;
	}
	
	
	public UserEntity createGroup(String groupName) {
		groups.add(new GroupEntity(groupName, this));
		return this;
	}
	
	
	public UserEntity removeGroup (Long groupId) {
		
		Optional<GroupEntity> groupEntityOptional = this.groups.stream()
				.filter(groupEntity -> groupEntity.id.equals(groupId))
				.findFirst();
		
		groupEntityOptional.ifPresent(groupEntity -> {
			this.groups.remove(groupEntity);
			groupEntity.users.remove(this);
		});
		
		return this;
	}
	
	
	@Override
	public String toString() {
		return "UserEntity{" +
				"id=" + id +
				", username='" + username + '\'' +
				", nickName='" + nickName + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
