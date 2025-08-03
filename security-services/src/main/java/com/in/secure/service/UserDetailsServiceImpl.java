package com.in.secure.service;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.in.secure.entity.User;
import com.in.secure.repo.UserRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;
	
	public UserDetailsServiceImpl(UserRepository userRepository) {
		
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("=================================username=>"+username);
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: "+username));
		
		System.out.println("======================================user "+user.toString());
		if (!user.isEnabled()) {
	        throw new DisabledException("User account is disabled");
	    }
	    if (!user.isAccountNonLocked()) {
	        throw new LockedException("User account is locked");
	    }
	    
		return user;
	}

}
