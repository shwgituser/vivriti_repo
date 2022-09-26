package com.vivritiTechnologies.RegistrationAPI.service;

import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.vivritiTechnologies.RegistrationAPI.entity.UserEntity;
import com.vivritiTechnologies.RegistrationAPI.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void addUser(UserEntity user) {
		
		userRepository.save(user);
		
	}

	@Override
	public List<String> updateUser(int userId, UserEntity newUser) {
		
		Optional<UserEntity> user = userRepository.findById(userId);
		
					
		//user.get().setUserId(userId);
		
		if(newUser.getDateOfBirth() != null)
		{
			user.get().setDateOfBirth(newUser.getDateOfBirth());
		}
		
		if(newUser.getFirstName() != null)
		{
			user.get().setFirstName(newUser.getFirstName());
		}
		
		if(newUser.getLastName() != null)
		{
			user.get().setLastName(newUser.getLastName());
		}
		
		if(newUser.getUserName() != null)
		{
			user.get().setUserName(newUser.getUserName());
		}
		
		if(newUser.getPassword() != null)
		{
			user.get().setPassword(newUser.getPassword());
		}
		
		
		userRepository.save(user.get());
		
		List<String> userData = new ArrayList<String>();
		userData.add(user.get().getFirstName());
		userData.add(user.get().getLastName());
		userData.add(user.get().getUserName());
		userData.add(user.get().getPassword());
		userData.add(user.get().getDateOfBirth().toString());
		return userData;
	}

	@Override
	public List<String> deleteUser(List<Integer> userIds) {
		
		List<String> userData = new ArrayList<String>();
		List<UserEntity> users = userRepository.findAllById(userIds);
		
		for (UserEntity user : users) {
			userData.add(user.getFirstName());
			userData.add(user.getLastName());
			userData.add(user.getUserName());
			userData.add(user.getPassword());
			userData.add(user.getDateOfBirth().toString());
		}
		userRepository.deleteAllById(userIds);
		return userData;
		
	}

	@Override
	public List<String> getUser(int userId) {
		
		Optional<UserEntity> user = userRepository.findById(userId);
		List<String> userData = new ArrayList<String>();
		userData.add(user.get().getFirstName());
		userData.add(user.get().getLastName());
		userData.add(user.get().getUserName());
		userData.add(user.get().getPassword());
		userData.add(user.get().getDateOfBirth().toString());
		return userData;
	}

	public List<String> patchUser(int userId, UserEntity newUser) {
		
		Optional<UserEntity> user = userRepository.findById(userId);
		
		
		user.get().setUserId(userId);
		
		if(newUser.getDateOfBirth() != null)
		{
			user.get().setDateOfBirth(newUser.getDateOfBirth());
		}
		
		if(newUser.getFirstName() != null)
		{
			user.get().setFirstName(newUser.getFirstName());
		}
		
		if(newUser.getLastName() != null)
		{
			user.get().setLastName(newUser.getLastName());
		}
		
		if(newUser.getUserName() != null)
		{
			user.get().setUserName(newUser.getUserName());
		}
		
		if(newUser.getPassword() != null)
		{
			user.get().setPassword(newUser.getPassword());
		}
		
		
		userRepository.save(user.get());
		
		List<String> userData = new ArrayList<String>();
		userData.add(user.get().getFirstName());
		userData.add(user.get().getLastName());
		userData.add(user.get().getUserName());
		userData.add(user.get().getPassword());
		userData.add(user.get().getDateOfBirth().toString());
		return userData;
	}

}
