package com.vivritiTechnologies.RegistrationAPI.repository;

import com.vivritiTechnologies.RegistrationAPI.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}
