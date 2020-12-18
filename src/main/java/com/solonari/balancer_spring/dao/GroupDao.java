package com.solonari.balancer_spring.dao;

import com.solonari.balancer_spring.entities.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupDao extends JpaRepository<GroupEntity, Long> {
}
