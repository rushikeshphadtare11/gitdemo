package com.demo.springboot.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.demo.springboot.model.User;
import com.demo.springboot.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{

	User save(UserRegistrationDto registrationDto);
}
