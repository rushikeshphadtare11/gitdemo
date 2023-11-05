package com.demo.springboot.config;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.demo.springboot.model.User;
import com.demo.springboot.repository.UserRepository;

@Service
public class CustomUserDetails implements UserDetails {
	
	@Autowired
	UserRepository urp;
	
	public CustomUserDetails(User u) {
		// TODO Auto-generated constructor stub
	}
	
	public CustomUserDetails() {
		// TODO Auto-generated constructor stub
	}
	
	User u=urp.getByUserName();
	
	public UserDetails loadUserByUsernameDetails() {
		
		if(u==null) {
			throw new RuntimeException("User Not Found");
		}
		else {
			return new CustomUserDetails(u);
		}
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return (Collection<? extends GrantedAuthority>) u.getRoles();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return u.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return u.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
