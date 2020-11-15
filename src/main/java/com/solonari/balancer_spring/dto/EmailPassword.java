package com.solonari.balancer_spring.dto;

public class EmailPassword {
	
	public String email;
	public String password;
	
	@Override
	public String toString() {
		return "EmailPassword{" +
				"email='" + email + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
