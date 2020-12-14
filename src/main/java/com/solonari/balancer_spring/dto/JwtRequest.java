package com.solonari.balancer_spring.dto;

import java.io.Serial;
import java.io.Serializable;

public class JwtRequest implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 5926468583005150707L;
	public String username;
	public String password;
	
	
	//need default constructor for JSON Parsing
	public JwtRequest() { }
	
	
	public JwtRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	
	@Override
	public String toString() {
		return "JwtRequest{" +
				"email='" + username + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
