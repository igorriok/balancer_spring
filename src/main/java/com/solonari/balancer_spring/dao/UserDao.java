package com.solonari.balancer_spring.dao;

import com.solonari.balancer_spring.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserEntity, Integer> {

	UserEntity findFirstByEmail(String email);
	
	UserEntity findFirstByUsername(String username);
	
	boolean existsByEmail(String email);
}
