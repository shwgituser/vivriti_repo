package com.vivriti.jwtsecurity.controller;

import java.security.Principal;

import com.vivriti.jwtsecurity.model.User;
import com.vivriti.jwtsecurity.model.UserRequest;
import com.vivriti.jwtsecurity.model.UserResponse;
import com.vivriti.jwtsecurity.service.IUserService;
import com.vivriti.jwtsecurity.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestController {
	@Autowired
	private IUserService service;
	@Autowired
	private AuthenticationManager manager;
	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/save")
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		Integer id = service.saveUser(user);
		return ResponseEntity.ok("User Saved with id : " + id);
	}

	// 2 validate login user
	@PostMapping("/login")
	public ResponseEntity<UserResponse> loginCheck(@RequestBody UserRequest userReq) {
		System.out.println("Login Method in Controller");

		// it will cross check user data with DB using UserDetatilsService
		manager.authenticate(new UsernamePasswordAuthenticationToken(userReq.getUsername(), userReq.getPassword()));
		// generate token

		String token = jwtUtil.generateToken(userReq.getUsername());
		// give response
		return ResponseEntity.ok(new UserResponse("SUCCESS", token));
	}

	// 3 Show Hello msg
	@GetMapping("/inbox")
	public String showInbox(Principal p) {
		return "Welcome to User " + p.getName();
	}
}
