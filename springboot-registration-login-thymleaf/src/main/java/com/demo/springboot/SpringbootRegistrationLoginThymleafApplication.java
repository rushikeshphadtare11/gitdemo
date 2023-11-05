package com.demo.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.demo.springboot.config.CustomUserDetails;
import com.demo.springboot.model.User;

@SpringBootApplication
public class SpringbootRegistrationLoginThymleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRegistrationLoginThymleafApplication.class, args);
	}
	
	@Bean
	public User u() {
		return new User();
	}
	
	@Bean
	public CustomUserDetails cud() {
		return new CustomUserDetails();
	}

}
