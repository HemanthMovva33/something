package com.corporate.classifieds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.corporate.classifieds.model.User;
import com.corporate.classifieds.repository.UserRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class CorporateAuthenticationApplication {

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(CorporateAuthenticationApplication.class, args);
	}

}
