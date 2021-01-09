package com.solonari.balancer_spring.services;

import com.solonari.balancer_spring.dao.UserDao;
import com.solonari.balancer_spring.dto.EmailPassword;
import com.solonari.balancer_spring.entities.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UsersDetailsService implements UserDetailsService {
	
	
	private static final Logger log = LoggerFactory.getLogger(UsersDetailsService.class);
	
	//@Autowired
	private final UserDao userDao;
	private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	public UsersDetailsService(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public User loadUserByUsername(final String email) throws UsernameNotFoundException {
		
		log.info("Load user by email: {}", email);
		
		UserEntity userEntity;
		
		List<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
		
		grantedAuthoritiesList.add(new SimpleGrantedAuthority("User"));
		
		userEntity = userDao.findFirstByUsername(email);
		
		log.info("User Entity: {}", userEntity);
		
		if (userEntity != null) {
			return new User(userEntity.username, userEntity.password, grantedAuthoritiesList);
		} else {
			return null;
		}
		
	}
	
	
	public UserEntity getUserEntity(String email) {
		
		log.info("Get user entity by email: {}", email);
		
		if (email != null) {
			return userDao.findFirstByUsername(email);
		} else {
			return null;
		}
	}
	
	
	public ResponseEntity<String> saveNewUser(EmailPassword emailPassword) {
		
		log.info("Save new user {}", emailPassword.toString());
		
		if (!userDao.existsByUsername(emailPassword.email)) {
			
			userDao.save(
					new UserEntity(emailPassword.email,
							bCryptPasswordEncoder.encode(emailPassword.password),
					emailPassword.email.substring(0, emailPassword.email.indexOf("@")))
			);
			
			return ResponseEntity.ok("Saved");
		} else {
			return ResponseEntity.badRequest().body("User already exists");
		}
	}
	
	
	public UserEntity addInvitedUser(String email) {
		
		log.info("Add invited user {}", email);
		
		if (!userDao.existsByUsername(email)) {
			
			byte[] array = new byte[7];
			new Random().nextBytes(array);
			String generatedString = new String(array, StandardCharsets.UTF_8);
			
			return userDao.save(
					new UserEntity(email,
							bCryptPasswordEncoder.encode(generatedString),
							email.substring(0, email.indexOf("@")), "invited")
			);
		} else {
			return userDao.findFirstByUsername(email);
		}
	}
	
	
	public UserEntity getUserByUsername (String email) {
		return userDao.findFirstByUsername(email);
	}
	
	
	public UserEntity saveUser (UserEntity user) {
		log.info("Save user entity: {}", user);
		return userDao.save(user);
	}
	
	public boolean userExists (String email) {
		return userDao.existsByUsername(email);
	}
}
