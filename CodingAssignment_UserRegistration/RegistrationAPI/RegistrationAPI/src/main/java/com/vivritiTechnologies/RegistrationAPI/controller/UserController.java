package com.vivritiTechnologies.RegistrationAPI.controller;

//import java.util.ArrayList;
import java.util.List;

import com.vivritiTechnologies.RegistrationAPI.entity.UserEntity;
import com.vivritiTechnologies.RegistrationAPI.service.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class UserController {
	@Autowired
	UserServiceImpl userService;
	
	@ApiOperation(value = "Create a new User", response = UserEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "User created succesfully")
	})
	@PostMapping("/addUser")
	public ResponseEntity<Integer> addUser(@RequestBody UserEntity user)
	{
		userService.addUser(user);
		return ResponseEntity.status(HttpStatus.OK).body(user.getUserId());
		
	}
	
	@ApiOperation(value = "Update user details based on user ID", response = UserEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "User updated succesfully"),
			@ApiResponse(code = 204, message = "User entry empty"),
	})
	@PatchMapping("/patchUser")
	public ResponseEntity<List<String>> patchUser(
			@RequestParam("userid") int userId,
			@RequestBody UserEntity user)
	{
		List<String> response = userService.patchUser(userId, user);
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
	}
	
	
	/*
	 * @PutMapping("/updateUser") public ResponseEntity<List<String>> updateUser(
	 * 
	 * @RequestParam("userid") int userId,
	 * 
	 * @RequestBody UserEntity user) { List<String> response =
	 * userService.updateUser(userId, user); return
	 * ResponseEntity.status(HttpStatus.OK).body(response);
	 * 
	 * }
	 */
	
	@ApiOperation(value = "Delete single user or multiple users based on ID", response = UserEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "User deleted succesfully"),
			@ApiResponse(code = 204, message = "User entry empty"),
	})
	@DeleteMapping("/deleteUser")
	public ResponseEntity<List<String>> deleteUser(@RequestParam List<Integer> userIds)
	{
		List<String> response = userService.deleteUser(userIds);
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
	}
	
	@ApiOperation(value = "Get User Details", response = UserEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Get User Details"),
			@ApiResponse(code = 204, message = "User entry empty"),
	})
	
	@GetMapping("/getUser")
	public ResponseEntity<List<String>> getUser(@RequestParam("userid") int userId)
	{
		List<String> response = userService.getUser(userId);
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
	}
	
}
