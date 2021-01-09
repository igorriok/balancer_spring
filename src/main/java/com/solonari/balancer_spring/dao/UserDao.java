package com.solonari.balancer_spring.dao;

import com.solonari.balancer_spring.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserEntity, Integer> {

	UserEntity findFirstByNickName(String email);
	
	UserEntity findFirstByUsername(String email);
	
	boolean existsByUsername(String email);
}
