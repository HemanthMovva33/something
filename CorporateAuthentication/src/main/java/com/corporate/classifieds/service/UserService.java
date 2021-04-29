package com.corporate.classifieds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corporate.classifieds.model.User;
import com.corporate.classifieds.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired UserRepository userRepository;
	
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
}