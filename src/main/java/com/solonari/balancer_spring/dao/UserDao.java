package com.solonari.balancer_spring.dao;

import com.solonari.balancer_spring.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserEntity, Integer> {

	UserEntity findFirstByUsername(String email);
	
	boolean existsByEmail(String email);
}
