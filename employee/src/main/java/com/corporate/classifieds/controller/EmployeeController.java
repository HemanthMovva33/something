package com.corporate.classifieds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.corporate.classifieds.VO.Offer;
import com.corporate.classifieds.entity.Employee;
import com.corporate.classifieds.service.EmployeeService;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService empService;
	
	@GetMapping(value="/viewEmployeeOffers/{id}")
	public List<Offer> getEmployeeOffers(@PathVariable int id) {
		return empService.getEmployeeOffers(id);
	}
	
	@GetMapping(value="/viewMostLikedOffers/{id}")
	public List<Offer> getMostLikedOffers(@PathVariable int id) {
		return empService.getMostLikedOffers(id);
	}
	
	@GetMapping(value="/viewProfile/{empid}")
//	@HystrixCommand(fallbackMethod="HystrixMethodPoints")
	public Employee getProfile(@PathVariable int empid) {
		return empService.getProfile(empid);
	}
	
//	public Employee HystrixMethodPoints(int id) {
//		Employee emp = new Employee();
//		emp.setEmployeeid(id);
//		emp.setFirstname("Service Not Available please try again later");
//		emp.setLastname("Service Not Available please try again later");
//		emp.setEmail("Service Not Available please try again later");
//		emp.setPoints(0);
//		return emp;
//	}
	
}
