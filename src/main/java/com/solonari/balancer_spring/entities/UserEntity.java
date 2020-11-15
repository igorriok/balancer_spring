package com.solonari.balancer_spring.entities;




import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(	name = "users",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "email")
		})
public class UserEntity implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	
	
	public UserEntity() {
	}
	
	public UserEntity(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public UserEntity(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}
}
