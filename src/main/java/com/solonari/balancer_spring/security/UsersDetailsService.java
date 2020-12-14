package com.solonari.balancer_spring.security;

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

import java.util.ArrayList;
import java.util.List;

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
	public User loadUserByUsername(final String username) throws UsernameNotFoundException {
		
		log.info("Load user by username: {}", username);
		
		UserEntity userEntity;
		
		List<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
		
		grantedAuthoritiesList.add(new SimpleGrantedAuthority("User"));
		
		userEntity = userDao.findFirstByUsername(username);
		
		log.info("User Entity: {}", userEntity);
		
		if (userEntity != null) {
			return new User(
					userEntity.username, userEntity.password, grantedAuthoritiesList);
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
}
