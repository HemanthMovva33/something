package com.corporate.classifieds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
public class CorporateClassifiedsPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CorporateClassifiedsPortalApplication.class, args);
	}

}
