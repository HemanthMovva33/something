package com.corporate.classfileds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.corporate.classfileds.MyFeignClient;
import com.corporate.classfileds.VO.Offer;
import com.corporate.classfileds.VO.ResponseTemplate;
import com.corporate.classfileds.entity.Points;
import com.corporate.classfileds.repository.PointsRepository;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class PointsService {

	@Autowired
	private PointsRepository pointsRepository;

	@Autowired
	private MyFeignClient myFeignClient;

	ResponseTemplate vo = new ResponseTemplate();

	public Points getPointsOfEmployee(int empId) {
		return pointsRepository.findAllByEmployeeId(empId);
	}

	//@HystrixCommand(fallbackMethod = "HystrixMethodOffer")
	public Points refreshPointsOfEmployee(int empId) {

		List<Offer> offer = myFeignClient.refreshingEmployeePointsAndSendingData(empId);

		int points = 0;

		for (Offer off : offer) {
			int points1 = 0;
			if (off.getOfferLikesInTwoDays() >= 50 && off.getOfferLikesInTwoDays() < 100) {
				points1 = points1 + 110;
			} else if (off.getOfferLikesInTwoDays() >= 100) {
				points1 = points1 + 120;
			} else if (off.getOfferLikesInTwoDays() < 50 && off.getOfferLikesInTwoDays() > 0) {
				points1 = points1 + 100;
			} else {
				points1 = points1;
			}

			points = points + points1;
		}
		Points point = new Points();
		point.setEmployeeId(empId);
		point.setPointsOfEmployee(points);
		return pointsRepository.save(point);

	}

//	public Points HystrixMethodOffer(int empId) {
//		Points points = new Points();
//		points.setEmployeeId(empId);
//		points.setPointsOfEmployee(0);
//		return points;
//	}

}
