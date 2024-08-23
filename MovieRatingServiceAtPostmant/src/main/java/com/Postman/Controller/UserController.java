package com.Postman.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Postman.Service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestParam("username") String username,@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("role") String role) {
		try {
			userService.registerUser(username, email, password, role);
			return ResponseEntity.ok("User registered Successfully");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
	
	@PostMapping("/login")
	public String loginUser(@RequestParam("username") String username, @RequestParam("password") String password) {
		if(userService.loginUser(username, password) == null) {
			System.out.println("Login UnSuccessFull");
			return "Wrong Credentials";
		}
		else {
			System.out.println("Login SuccessFull");
			return "Login Successfull";
		}
	}

}
