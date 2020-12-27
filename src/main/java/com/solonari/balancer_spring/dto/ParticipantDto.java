package com.solonari.balancer_spring.dto;

import com.solonari.balancer_spring.entities.UserEntity;

public class ParticipantDto {
	
	public String nickName;
	public String email;
	
	
	public ParticipantDto() {}
	
	public ParticipantDto(String nickName, String email) {
		this.nickName = nickName;
		this.email = email;
	}
	
	public ParticipantDto(UserEntity userEntity) {
		this.nickName = userEntity.nickName;
		this.email = userEntity.username;
	}
	
	
	@Override
	public String toString() {
		return "ParticipantDto{" +
				"nickName='" + nickName + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}
