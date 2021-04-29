package com.corporate.classifieds.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.corporate.classifieds.entity.User;
import com.corporate.classifieds.repository.UserRepository;

public class UserService {

@Autowired UserRepository userRepository;
	
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
}
