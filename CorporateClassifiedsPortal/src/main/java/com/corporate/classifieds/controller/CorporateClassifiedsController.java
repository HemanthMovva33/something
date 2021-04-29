package com.corporate.classifieds.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.corporate.classifieds.entity.Employee;
import com.corporate.classifieds.entity.LoginRequest;
import com.corporate.classifieds.entity.LoginResponse;
import com.corporate.classifieds.entity.Offer;
import com.corporate.classifieds.entity.User;
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
//import io.github.resilience4j.retry.annotation.Retry;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Controller
public class CorporateClassifiedsController {
	RestTemplate rt = new RestTemplate();

	@ResponseBody
	@GetMapping(value = "/hello")
	public String hello() {
		return "Hi";
	}

	@GetMapping(value = "/login")
	public String login() {
		return "login";
	}

	@PostMapping(value = "/login")
	public String successfulLogin(@RequestParam String username, @RequestParam String password, HttpSession session) {
		LoginRequest request = new LoginRequest();
		request.setUsername(username);
		request.setPassword(password);

		ResponseEntity<LoginResponse> response = rt.postForEntity(
				"http://localhost:9090/api/authentication-service/authenticate", request, LoginResponse.class);
		LoginResponse loginResponse = response.getBody();
		String token = loginResponse.getToken();
		User user = loginResponse.getUser();
		int userId = user.getEmpid();
		String uname=user.getUsername();
		
		session.setAttribute("userId", userId);
		session.setAttribute("token", token);
		session.setAttribute("username", uname);

		return "redirect:/viewAllOffers";

	}

	@RequestMapping("/addOffers")
	public String addOffer() {
		return "addOffer";
	}

	/*-------------------------This calls offer microservice for getting all offers------------------------------------- */
	//@Retry(name = "viewOffers", fallbackMethod = "fallBackMethodForOffer")
	//@CircuitBreaker(name = "default", fallbackMethod = "fallBackMethodForOffer")
	@GetMapping("/viewAllOffers")
	@HystrixCommand(fallbackMethod="fallBackMethodForOffer")
	public String viewOffers(ModelMap model, HttpSession session) {
		RestTemplate rt = new RestTemplate();
		String token = (String) session.getAttribute("token");
		String username=(String) session.getAttribute("username");
		System.out.println(session.getId());
		String jwtToken = "Bearer " + token;
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", jwtToken);
		HttpEntity<String> httpEntity = new HttpEntity<String>(header);
		ResponseEntity<Offer[]> rs = rt.exchange("http://localhost:9090/api/offer-service/ViewAllOffers",
				HttpMethod.GET, httpEntity, Offer[].class);
		Offer offer[] = rs.getBody();
		List<Offer> offers = Arrays.asList(offer);
		model.put("uname", username);
		model.put("Offerlist", offers);
		return "frontEnd";
	}

	/*---------------------- this calls employee service to view points------------------------------------------------- */
	@GetMapping("/viewMyProfile")
//	@Retry(name = "viewMyProfile", fallbackMethod = "fallBackMethodForEmployeeProfile")
//	@CircuitBreaker(name = "default", fallbackMethod = "fallBackMethodForEmployeeProfile")
	@HystrixCommand(fallbackMethod="fallBackMethodForEmployeeProfile")
	public String viewMyProfile(ModelMap model, HttpSession session) {
		String token = (String) session.getAttribute("token");
		String jwtToken = "Bearer " + token;

		RestTemplate rt = new RestTemplate();
		int empid = (int) session.getAttribute("userId");
		System.out.println(empid);
		HashMap<String, Integer> uriVariables = new HashMap<>();
		uriVariables.put("empid", empid);
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", jwtToken);
		HttpEntity<String> httpEntity = new HttpEntity<String>(header);

		ResponseEntity<Employee> rs = rt.exchange("http://localhost:9090/api/employee-service/viewProfile/{empid}",
				HttpMethod.GET, httpEntity, Employee.class, uriVariables);
		Employee employee = rs.getBody();

		model.put("employeeid", employee.getEmployeeid());
		model.put("firstname", employee.getFirstname());
		model.put("lastname", employee.getLastname());
		model.put("email", employee.getEmail());
		model.put("Points", employee.getPoints());
		return "MyProfile";
	}
	
	
	/*------------this displays all offers from offer microservice of the particular employee who is logged in -----------*/
	@GetMapping("/viewMyOffers")
//	@Retry(name = "viewMyOffers", fallbackMethod = "fallBackMethodForOffer")
//	@CircuitBreaker(name = "default", fallbackMethod = "fallBackMethodForOffer")
	@HystrixCommand(fallbackMethod="fallBackMethodForOffer")
	public String viewMyOffers(ModelMap model, HttpSession session) {
		RestTemplate rt = new RestTemplate();
		String token = (String) session.getAttribute("token");
		String jwtToken = "Bearer " + token;
		int empid = (int) session.getAttribute("userId");
		String username=(String) session.getAttribute("username");
		HashMap<String, Integer> uriVariables = new HashMap<>();
		uriVariables.put("empid", empid);
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", jwtToken);
		HttpEntity<String> httpEntity = new HttpEntity<String>(header);

		ResponseEntity<Offer[]> rs = rt.exchange("http://localhost:9090/api/offer-service/allOffersOfEmployee/{empid}",
				HttpMethod.GET, httpEntity, Offer[].class, uriVariables);
		Offer offer[] = rs.getBody();
		List<Offer> offers = Arrays.asList(offer);
		model.put("uname", username);
		model.put("Offerlist", offers);
		return "MyOffer";
	}
	
	/*------------This edits existing offers by getting offer id---------------------------------*/
	@GetMapping("/editOffers/{offerId}")
	public String editMyOffers(ModelMap model, @PathVariable int offerId, HttpSession session) {
		RestTemplate rt = new RestTemplate();
		String token = (String) session.getAttribute("token");
		String jwtToken = "Bearer " + token;
		String username=(String) session.getAttribute("username");
		
		HashMap<String, Integer> uriVariables = new HashMap<>();
		uriVariables.put("offerId", offerId);
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", jwtToken);
		HttpEntity<String> httpEntity = new HttpEntity<String>(header);
		ResponseEntity<Offer> rs = rt.exchange("http://localhost:9090/api/offer-service/editOffer/{offerId}",
				HttpMethod.GET, httpEntity, Offer.class, uriVariables);
		Offer offer = rs.getBody();
		model.put("uname", username);
		model.put("offerId", offer.getOfferId());
		model.put("offerTitle", offer.getOfferTitle());
		model.put("offerDescription", offer.getOfferDescription());
		return "editOffer";
	}
	
	/*----on click submit after editing the existing offer this is called to update and save the offer----*/
	@PostMapping("/editSuccess/")
	public String editOfferSuccess(@RequestParam("OfferId") int offerId, @RequestParam("offerTitle") String offerTitle,
			@RequestParam("Category") int offerCateId, @RequestParam("Description") String offerDescription,
			HttpSession session) {
		String token = (String) session.getAttribute("token");
		String jwtToken = "Bearer " + token;
		HashMap<String, Integer> uriVariables = new HashMap<>();
		uriVariables.put("offerId", offerId);
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", jwtToken);
		HttpEntity<String> httpEnt = new HttpEntity<String>(header);
		ResponseEntity<Offer> rs = rt.exchange("http://localhost:9090/api/offer-service/editOffer/{offerId}",
				HttpMethod.GET, httpEnt, Offer.class, uriVariables);
		Offer offer = rs.getBody();
		Offer offer1 = new Offer();
		offer1.setOfferId(offerId);
		offer1.setOfferTitle(offerTitle);
		offer1.setOfferOpenDate(offer.getOfferOpenDate());
		offer1.setOfferClosedDate(offer.getOfferClosedDate());
		offer1.setCategoryId(offerCateId);
		offer1.setOfferDescription(offerDescription);
		offer1.setOfferLikes(offer.getOfferLikes());
		offer1.setOfferLikesInTwoDays(offer.getOfferLikesInTwoDays());
		offer1.setEmployeeID(offer.getEmployeeID());
		HttpEntity<Offer> httpEntity = new HttpEntity<Offer>(offer1, header);
		ResponseEntity<Offer> rs1 = rt.exchange("http://localhost:9090/api/offer-service/submitAndUpdate/",
				HttpMethod.POST, httpEntity, Offer.class);
		return "redirect:/viewMyOffers";

	}


	@PostMapping("/addOffer/")
	public String addOffer(@RequestParam("offerTitle") String offerTitle,
			@RequestParam("Closeday") @DateTimeFormat(pattern = "yyyy-MM-dd") Date offerClosedDate,
			@RequestParam("Category") int offerCateId, @RequestParam("Description") String offerDescription,
			HttpSession session) {
		String token = (String) session.getAttribute("token");
		String jwtToken = "Bearer " + token;
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", jwtToken);
		int empid = (int) session.getAttribute("userId");
		Offer offer = new Offer();
		offer.setCategoryId(offerCateId);
		offer.setOfferClosedDate(offerClosedDate);
		offer.setOfferDescription(offerDescription);
		offer.setOfferOpenDate(new Date());
		offer.setOfferTitle(offerTitle);
		offer.setEmployeeID(empid);
		HttpEntity<Offer> httpEntity = new HttpEntity<Offer>(offer, header);
		ResponseEntity<Offer> rs1 = rt.exchange("http://localhost:9090/api/offer-service/addOffer/", HttpMethod.POST,
				httpEntity, Offer.class);
		return "redirect:/viewMyOffers";
	}

	@GetMapping("/incrimentLikes/{offerId}")
	public String incrimentLikes(@PathVariable("offerId") int offerId, HttpSession session) throws ParseException {
		String token = (String) session.getAttribute("token");
		String jwtToken = "Bearer " + token;
		HashMap<String, Integer> uriVariables = new HashMap<>();
		uriVariables.put("offerId", offerId);
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", jwtToken);
		HttpEntity<String> httpEnt = new HttpEntity<String>(header);
		ResponseEntity<Offer> rs = rt.exchange("http://localhost:9090/api/offer-service/getOfferDetails/" + offerId,
				HttpMethod.GET, httpEnt, Offer.class, uriVariables);

		Offer offer = rs.getBody();
		Offer offer2 = new Offer();

		offer2.setOfferId(offerId);
		offer2.setOfferLikes(offer.getOfferLikes());
		offer2.setOfferLikesInTwoDays(offer.getOfferLikesInTwoDays());
		offer2.setOfferOpenDate(offer.getOfferOpenDate());
		offer2.setOfferClosedDate(offer.getOfferClosedDate());
		offer2.setOfferEngagedDate(new Date());
		offer2.setEmployeeID(offer.getEmployeeID());
		offer2.setOfferDescription(offer.getOfferDescription());
		offer2.setCategoryId(offer.getCategoryId());
		offer2.setOfferTitle(offer.getOfferTitle());
		HttpEntity<Offer> httpEntity = new HttpEntity<Offer>(offer2, header);
		ResponseEntity<Offer> rs1 = rt.exchange("http://localhost:9090/api/offer-service/updateLikeResponse",
				HttpMethod.POST, httpEntity, Offer.class);
		return "redirect:/viewAllOffers";
	}

	@GetMapping("/viewOffersByCategory")
	@HystrixCommand(fallbackMethod="fallBackMethodForOfferCategory")
//	@Retry(name = "viewOffersByCategory", fallbackMethod = "fallBackMethodForOffer")
//	@CircuitBreaker(name = "default", fallbackMethod = "fallBackMethodForOffer")
	public String viewOffersByCategory(ModelMap model, @RequestParam int Category, HttpSession session) {
		RestTemplate rt = new RestTemplate();
		String token = (String) session.getAttribute("token");
		String jwtToken = "Bearer " + token;
		HashMap<String, Integer> uriVariables = new HashMap<>();
		uriVariables.put("Category", Category);
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", jwtToken);
		HttpEntity<String> httpEntity = new HttpEntity<String>(header);
		String username=(String) session.getAttribute("username");
		ResponseEntity<Offer[]> rs = rt.exchange(
				"http://localhost:9090/api/offer-service/getOfferByCategory/{Category}", HttpMethod.GET, httpEntity,
				Offer[].class, uriVariables);
		Offer offer[] = rs.getBody();
		List<Offer> offers = Arrays.asList(offer);
		model.put("uname", username);
		model.put("Offerlist", offers);
		return "FilterByCategory";
	}

	@GetMapping("/viewOffersByTopLikes")
//	@Retry(name = "viewOffersByTopLikes", fallbackMethod = "fallBackMethodForOffer")
//	@CircuitBreaker(name = "default", fallbackMethod = "fallBackMethodForOffer")
	@HystrixCommand(fallbackMethod="fallBackMethodForOffer")
	public String viewOffersByTopLikes(ModelMap model, HttpSession session) {
		RestTemplate rt = new RestTemplate();
		String token = (String) session.getAttribute("token");
		String jwtToken = "Bearer " + token;
		String username=(String) session.getAttribute("username");
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", jwtToken);
		HttpEntity<String> httpEntity = new HttpEntity<String>(header);
		ResponseEntity<Offer[]> rs = rt.exchange("http://localhost:9090/api/offer-service/getOfferByTopLikes",
				HttpMethod.GET, httpEntity, Offer[].class);
		Offer offer[] = rs.getBody();
		List<Offer> offers = Arrays.asList(offer);
		model.put("uname", username);
		model.put("Offerlist", offers);
		return "FilterByTopLikes";
	}

	@GetMapping("/viewOffersByDate")
//	@Retry(name = "viewOffersByDate", fallbackMethod = "fallBackMethodForOffer")
//	@CircuitBreaker(name = "default", fallbackMethod = "fallBackMethodForOffer")
	@HystrixCommand(fallbackMethod="fallBackMethodForOfferDate")
	public String viewOffersByDate(ModelMap model, @RequestParam String date, HttpSession session)
			throws ParseException {
		RestTemplate rt = new RestTemplate();
		System.out.println("controller" + date);
		String token = (String) session.getAttribute("token");
		String jwtToken = "Bearer " + token;
		String username=(String) session.getAttribute("username");
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", jwtToken);
		HttpEntity<String> httpEntity = new HttpEntity<String>(header);
		HashMap<String, String> uriVariables = new HashMap<>();
		uriVariables.put("date", date);
		ResponseEntity<Offer[]> rs = rt.exchange("http://localhost:9090/api/offer-service/getOfferByPostedDate/{date}",
				HttpMethod.GET, httpEntity, Offer[].class, uriVariables);

		Offer offer[] = rs.getBody();
		List<Offer> offers = Arrays.asList(offer);
		model.put("uname", username);
		model.put("Offerlist", offers);
		return "FilterByDate";
	}

	@GetMapping(value = "/logout")
	public String test(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	
	
	/*--------fallback Method------------*/
	public String fallBackMethodForOffer(ModelMap model, HttpSession session) {
		Offer off1 = new Offer();
		off1.setOfferOpenDate(null);
		off1.setOfferClosedDate(null);
		off1.setOfferEngagedDate(null);
		off1.setOfferTitle(null);
		off1.setOfferDescription("You are seeing this because of problem in offer service plz try again later");
		off1.setOfferLikes(0);
		off1.setEmployeeID(0);
		off1.setCategoryId(0);
		off1.setOfferLikesInTwoDays(0);
		List<Offer> offer = new ArrayList<Offer>();
		offer.add(off1);
		
		model.put("Offerlist", offer);
		return "frontEnd";
		
	}
	
	public String fallBackMethodForOfferCategory(ModelMap model, @RequestParam int Category, HttpSession session) {
		Offer off1 = new Offer();
		off1.setOfferOpenDate(null);
		off1.setOfferClosedDate(null);
		off1.setOfferEngagedDate(null);
		off1.setOfferTitle(null);
		off1.setOfferDescription("You are seeing this because of problem in offer service plz try again later");
		off1.setOfferLikes(0);
		off1.setEmployeeID(0);
		off1.setCategoryId(0);
		off1.setOfferLikesInTwoDays(0);
		List<Offer> offer = new ArrayList<Offer>();
		offer.add(off1);
		
		model.put("Offerlist", offer);
		return "frontEnd";
		
	}
	
	public String fallBackMethodForOfferDate(ModelMap model, @RequestParam String date, HttpSession session) {
		Offer off1 = new Offer();
		off1.setOfferOpenDate(null);
		off1.setOfferClosedDate(null);
		off1.setOfferEngagedDate(null);
		off1.setOfferTitle(null);
		off1.setOfferDescription("You are seeing this because of problem in offer service plz try again later");
		off1.setOfferLikes(0);
		off1.setEmployeeID(0);
		off1.setCategoryId(0);
		off1.setOfferLikesInTwoDays(0);
		List<Offer> offer = new ArrayList<Offer>();
		offer.add(off1);
		
		model.put("Offerlist", offer);
		return "frontEnd";
		
	}

	public String fallBackMethodForEmployeeProfile(ModelMap model, HttpSession session) {
		
		int empid = (int) session.getAttribute("userId");
		model.put("employeeid",empid);
		model.put("firstname","Error please try again later");
		model.put("lastname","Error please try again later");
		model.put("email","Error please try again later");
		model.put("Points",0);
		return "MyProfile";
	}
}
