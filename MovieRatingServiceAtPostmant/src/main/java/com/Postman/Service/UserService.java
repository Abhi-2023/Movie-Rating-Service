package com.Postman.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Postman.Model.Users;
import com.Postman.Repository.UserRepository;

@Service
public class UserService {

	
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
    public UserService(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
	@Autowired
	UserRepository repository;
	public void registerUser(String username, String email, String password, String role) {
		String encodePasswordString = passwordEncoder.encode(password);
		try {
			Users user = new Users();
			
			user.setUsername(username);
			user.setEmailId(email);
			user.setPassword(encodePasswordString);
			user.setRole(role);
			repository.save(user);
			
		} catch (Exception e) {
			throw new RuntimeException("Username already exists");
		}

	}
	
	public Users loginUser(String username, String password) {
		Users user = repository.findByUsername(username);
		if(user != null && passwordEncoder.matches(password, user.getPassword())) {
			return user;
		}
		return null;
	}
}
