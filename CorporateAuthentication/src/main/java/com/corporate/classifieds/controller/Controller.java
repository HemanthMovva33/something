package com.corporate.classifieds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import com.corporate.classifieds.model.LoginRequest;
import com.corporate.classifieds.model.LoginResponse;
import com.corporate.classifieds.model.User;
import com.corporate.classifieds.service.UserService;
import com.corporate.classifieds.service.UserServiceDetails;
import com.corporate.classifieds.util.JwtUtil;

@RestController
public class Controller {
	
	@Autowired 
	AuthenticationManager authenticationManager;
	
	@Autowired 
	UserServiceDetails userDetailsService;
	
	@Autowired 
	UserService userService;
	
	@Autowired JwtUtil jwtUtil;
	
	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody LoginRequest loginRequest) throws Exception{
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		}
		catch(Exception e) {
			throw new Exception("Bad Credentials");
		}
		
		 final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
		 
		 final String jwt = jwtUtil.generateToken(userDetails);
		 
		 User user = userService.findUserByUsername(loginRequest.getUsername());
		 
		 LoginResponse response = new LoginResponse(jwt, user);

		 return new ResponseEntity<LoginResponse>(response, HttpStatus.CREATED);
		 
	}
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}
	
	
}
