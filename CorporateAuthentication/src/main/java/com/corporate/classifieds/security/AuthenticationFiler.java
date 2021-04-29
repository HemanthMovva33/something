package com.corporate.classifieds.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.AuthenticationException;

import com.corporate.classifieds.model.LoginRequest;
import com.corporate.classifieds.service.UserService;
import com.corporate.classifieds.service.UserServiceDetails;
import com.corporate.classifieds.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthenticationFiler extends UsernamePasswordAuthenticationFilter {
	
	@Autowired JwtUtil jwtUtil;
	
	@Autowired UserService userService;
	
	@Autowired UserServiceDetails userDetails;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {

			LoginRequest creds = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
			

			return getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword(), new ArrayList<>()));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

//	@Override
//	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
//			Authentication authResult) throws IOException, ServletException {
//		// TODO Auto-generated method stub
//		String username = authResult.getName();
//		
//		
//		
//		User user = userService.findUserByUsername(username);
//		
//		UserPrincipal userPrincipal = new UserPrincipal(user);
//		 
//		 final String jwt = jwtUtil.generateToken(userPrincipal);
//		 
//		 LoginResponse responseToken = new LoginResponse(jwt);
//		 
//		 response.addHeader("token", jwt);
//		
//		
//	}
	
	

}
