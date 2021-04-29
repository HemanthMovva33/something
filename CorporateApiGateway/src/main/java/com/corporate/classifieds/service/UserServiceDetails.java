package com.corporate.classifieds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.corporate.classifieds.entity.User;
import com.corporate.classifieds.model.UserPrincipal;
import com.corporate.classifieds.repository.UserRepository;

@Service
public class UserServiceDetails implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("No such user exists");
		}
		
		UserPrincipal userPrincipal = new UserPrincipal(user);
		return userPrincipal;
	}

}
