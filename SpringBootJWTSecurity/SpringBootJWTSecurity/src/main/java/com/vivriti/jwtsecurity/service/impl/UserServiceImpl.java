package com.vivriti.jwtsecurity.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.vivriti.jwtsecurity.model.User;
import com.vivriti.jwtsecurity.rpo.UserRepoitory;
import com.vivriti.jwtsecurity.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {
	@Autowired
	private UserRepoitory repo;
	@Autowired
	private BCryptPasswordEncoder encoder1;

	@Override
	public Integer saveUser(User user) {
		String pwd = user.getPwd();
		pwd = encoder1.encode(pwd);
		user.setPwd(pwd);
		return repo.save(user).getId();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Model class based on User Name
		User user = repo.findByUsername(username);
		// roles to set<GA>
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		List<String> roles = user.getRoles();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}

		org.springframework.security.core.userdetails.User urs = new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPwd(), authorities);
		return urs;
	}

}
