package com.vivritiTechnologies.RegistrationAPI.service;

import java.util.List;

import com.vivritiTechnologies.RegistrationAPI.entity.UserEntity;

public interface IUserService {
	public void addUser(UserEntity user);
	public List<String> updateUser(int userId, UserEntity newUser);
	public List<String> deleteUser(List<Integer> userIds);
	public List<String> getUser(int userId);
	public List<String> patchUser(int userId, UserEntity user);
}
