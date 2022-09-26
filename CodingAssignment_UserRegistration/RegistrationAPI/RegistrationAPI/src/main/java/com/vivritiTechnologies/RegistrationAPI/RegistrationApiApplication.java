package com.vivritiTechnologies.RegistrationAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class RegistrationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistrationApiApplication.class, args);
	}
	
}

