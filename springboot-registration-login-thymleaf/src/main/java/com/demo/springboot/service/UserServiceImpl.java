package com.demo.springboot.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.springboot.model.Role;
import com.demo.springboot.model.User;
import com.demo.springboot.repository.UserRepository;
import com.demo.springboot.web.dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}


	@Override
	public User save(UserRegistrationDto registrationDto) {
		User user =new User(registrationDto.getUsername(),
				registrationDto.getEmail(), 
				passwordEncoder.encode(registrationDto.getPassword()), 
				Arrays.asList(new Role("ROLE_USER")));
		
		return userRepository.save(user);
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if(user ==null) {
		throw new UsernameNotFoundException("Invalid username or password.");
	}

		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),mapRoleToAuthorities(user.getRoles()));
}

	private Collection<? extends GrantedAuthority> mapRoleToAuthorities(Collection<Role>roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getUserName())).collect(Collectors.toList());
		
	}

}




