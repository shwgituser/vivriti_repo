package com.vivriti.jwtsecurity.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data

public class User {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String username;
	private String pwd;
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;

}
