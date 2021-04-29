package com.corporate.classifieds.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.corporate.classifieds.VO.Offer;
import com.corporate.classifieds.VO.Points;
import com.corporate.classifieds.VO.ResponseTemplateVo;
import com.corporate.classifieds.entity.Employee;
import com.corporate.classifieds.feign.FeignOffer;
import com.corporate.classifieds.feign.FeignPoints;
import com.corporate.classifieds.repository.EmployeeRepository;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepo;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private FeignOffer feignOffer;
	
	@Autowired
	private FeignPoints feignPoints;

	ResponseTemplateVo vo = new ResponseTemplateVo();
	
	//@HystrixCommand(fallbackMethod="HystrixMethodOffer")
	public List<Offer> getEmployeeOffers(int id) {

//		ResponseEntity<Offer[]> rs = restTemplate.getForEntity("http://localhost:8080/allOffersOfEmployee/" + id,
//				Offer[].class);
//		Offer offer[] = rs.getBody();
		
		List<Offer> offers=feignOffer.allOffersOfEmployee(id);
//		List<Offer> offers = Arrays.asList(offer);
		return offers;
	}
	
	//@HystrixCommand(fallbackMethod="HystrixMethodOffer")
	public List<Offer> getMostLikedOffers(int id) {
//		ResponseEntity<Offer[]> rs = restTemplate.getForEntity("http://localhost:8080/allOffersOfEmployee/" + id,
//				Offer[].class);

//		Offer offer[] = rs.getBody();
//		List<Offer> offers = Arrays.asList(offer);
		
		List<Offer> offers=feignOffer.allOffersOfEmployee(id);

		Collections.sort(offers, new Comparator<Offer>() {

			@Override
			public int compare(Offer o1, Offer o2) {
				return Integer.valueOf(o2.getOfferLikes()).compareTo(Integer.valueOf(o1.getOfferLikes()));
			}
		});
		return offers;

	}

	//@HystrixCommand(fallbackMethod="HystrixMethodPoints")
	public Employee getProfile(int id) {
		//Points points = restTemplate.getForObject("http://localhost:9003/refreshPointsOfEmployee/" + id, Points.class);
		Points points=feignPoints.refreshPointsOfEmployee(id);
		int pts = points.getPointsOfEmployee();
		Employee employee= employeeRepo.findByemployeeid(id);
		Employee emp = new Employee();
		emp.setEmployeeid(id);
		emp.setFirstname(employee.getFirstname());
		emp.setLastname(employee.getLastname());
		emp.setEmail(employee.getEmail());
		emp.setPoints(pts);
		return employeeRepo.save(emp);

	}
	
//	public List<Offer> HystrixMethodOffer(){
//		Offer off1 = new Offer();
//        
//        off1.setOfferOpenDate(null);
//        off1.setOfferClosedDate(null);
//        off1.setOfferEngagedDate(null);
//        off1.setOfferTitle(null);
//        off1.setOfferDescription("You are seeing this because of problem in offer service plz try again later");
//        off1.setOfferLikes(0);
//        off1.setEmployeeID(0);
//        off1.setCategoryId(0);
//        off1.setOfferLikesInTwoDays(0);
//       List<Offer>offer=new ArrayList<Offer>();
//       offer.add(off1);
//       return offer;
//	}
	
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
