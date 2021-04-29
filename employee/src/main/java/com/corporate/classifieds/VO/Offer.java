package com.corporate.classifieds.VO;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Offer {
private int offerId;

	@Temporal(TemporalType.DATE)
	private Date offerOpenDate;
	@Temporal(TemporalType.DATE)
	private Date offerClosedDate;
	@Temporal(TemporalType.DATE)
	private Date offerEngagedDate;
	private String offerTitle;
	private String offerDescription;
	private int offerLikes;
	private int employeeID;
	private int categoryId;
	private int offerLikesInTwoDays;
	
	public int getOfferId() {
		return offerId;
	}
	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}
	public Date getOfferOpenDate() {
		return offerOpenDate;
	}
	public void setOfferOpenDate(Date offerOpenDate) {
		this.offerOpenDate = offerOpenDate;
	}
	public Date getOfferClosedDate() {
		return offerClosedDate;
	}
	public void setOfferClosedDate(Date offerClosedDate) {
		this.offerClosedDate = offerClosedDate;
	}
	public Date getOfferEngagedDate() {
		return offerEngagedDate;
	}
	public void setOfferEngagedDate(Date offerEngagedDate) {
		this.offerEngagedDate = offerEngagedDate;
	}
	public String getOfferTitle() {
		return offerTitle;
	}
	public void setOfferTitle(String offerTitle) {
		this.offerTitle = offerTitle;
	}
	public String getOfferDescription() {
		return offerDescription;
	}
	public void setOfferDescription(String offerDescription) {
		this.offerDescription = offerDescription;
	}
	public int getOfferLikes() {
		return offerLikes;
	}
	public void setOfferLikes(int offerLikes) {
		this.offerLikes = offerLikes;
	}
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getOfferLikesInTwoDays() {
		return offerLikesInTwoDays;
	}
	public void setOfferLikesInTwoDays(int offerLikesInTwoDays) {
		this.offerLikesInTwoDays = offerLikesInTwoDays;
	}

	

}
