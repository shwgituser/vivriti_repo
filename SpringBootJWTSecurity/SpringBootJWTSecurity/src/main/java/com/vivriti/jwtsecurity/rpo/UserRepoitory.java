package com.vivriti.jwtsecurity.rpo;

import com.vivriti.jwtsecurity.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepoitory extends JpaRepository<User, Integer> {
	User findByUsername(String username);

}
