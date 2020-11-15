package com.solonari.balancer_spring.security;

import com.solonari.balancer_spring.dao.UserDao;
import com.solonari.balancer_spring.dto.EmailPassword;
import com.solonari.balancer_spring.entities.UserEntity;
import net.bytebuddy.matcher.ElementMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomDetailsService implements UserDetailsService {
	
	
	private static final Logger log = LoggerFactory.getLogger(CustomDetailsService.class);
	
	//@Autowired
	private final UserDao userDao;
	
	public CustomDetailsService(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public User loadUserByUsername(final String username) throws UsernameNotFoundException {
		
		UserEntity userEntity;
		List<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
		grantedAuthoritiesList.add(new SimpleGrantedAuthority("User"));
		
		try {
			userEntity = userDao.findFirstByUsername(username);
			return new User(userEntity.username, userEntity.password, grantedAuthoritiesList);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}
	}
	
	
	public ResponseEntity<String> saveNewUser(EmailPassword emailPassword) {
		
		log.info("Save new user {}", emailPassword.toString());
		
		if (!userDao.existsByEmail(emailPassword.email)) {
			
			userDao.save(new UserEntity(emailPassword.email, emailPassword.password));
			
			return ResponseEntity.ok("Saved");
		} else {
			return ResponseEntity.badRequest().body("User already exists");
		}
	}
}
