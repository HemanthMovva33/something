package com.corporate.classifieds.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.corporate.classifieds.entity.Offer;
import com.corporate.classifieds.service.CategoryService;
import com.corporate.classifieds.service.OfferService;

@RestController
public class OfferController {

	@Autowired
	OfferService offerService;

	@Autowired
	CategoryService categoryService;

	@GetMapping(value = "/getOfferDetails/{id}")
	public Offer getOfferDetails(@PathVariable int id) {
		return offerService.getDetails(id);
	}

	@GetMapping(value = "/getOfferByCategory/{categoryId}")
	public List<Offer> getOfferByCategory(@PathVariable("categoryId") int id) {
		return offerService.getDetailsByCategory(id);
	}

	@GetMapping(value = "/getOfferByTopLikes")
	public List<Offer> getOfferByTopLikes() {
		return offerService.getDetailsByTopLikes();
	}

	@GetMapping(value = "/getOfferByPostedDate/{date}")
	public List<Offer> getOfferByDate(@PathVariable("date") String date) throws ParseException {
		System.out.println("before" + date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date OpenDate = sdf.parse(date);
		System.out.println(OpenDate);
		return offerService.getDetailsByDate(OpenDate);
	}

	@PostMapping(value = "/addOffer/")
	public Offer addOffer(@RequestBody Offer offer) {
		return offerService.saveoffer(offer);
	}

	@GetMapping(value = "/editOffer/{offid}")
	public Offer getOfferDetails2(@PathVariable int offid) {
		return offerService.getDetails(offid);
	}

	@PostMapping(value = "/submitAndUpdate/")
	public Offer formResponse(@RequestBody Offer offer) {
		return offerService.formReply(offer);
	}

	@GetMapping(value = "/allOffersOfEmployee/{id}")
	public List<Offer> getOfferOfEmployee(@PathVariable int id) {
		return offerService.getDetailsByemployeeId(id);
	}

	@GetMapping(value = "/ViewAllOffers")
	public List<Offer> viewAllOffers() {
		return offerService.viewAllOffers();

	}

	@GetMapping(value = "/refreshingEmployeePointsAndSendingData/{id}")
	public List<Offer> getOfferOfEmployee2(@PathVariable int id) {
		return offerService.getDetailsByemployeeId(id);
	}

	@PostMapping("/updateLikeResponse")
	public Offer updateLikeResponse(@RequestBody Offer offer) {
		return offerService.upadteLikeResponse(offer);
	}

}
