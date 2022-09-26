package com.vivritiTechnologies.RegistrationAPI.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_master", catalog = "vivriti")
public class UserEntity implements Serializable{
	
	/**
	 *
	 */
	private static final long serialVersionUID = -7481482419574000597L;

	@Id
	@Column(name = "userid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;

	@ApiModelProperty(notes = "First name of the user")
	@Column(name = "firstname")
	private String firstName;

	@ApiModelProperty(notes = "Last name of the user")
	@Column(name = "lastname")
	private String lastName;
	
	@ApiModelProperty(notes = "Date of Birth of the user, format should dd/MM/yyyy")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	@Column(name = "dob")
	private Date dateOfBirth;
	
	@ApiModelProperty(notes = "User name of the user")
	@Column(name = "username")
	private String userName;

	@ApiModelProperty(notes = "Password of the user")
	@Column(name = "password")
	private String password;
	

}
