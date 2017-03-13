package com.starquest.usermgmt.vo;

import java.util.Date;

public class Eligibility {
	
	private long id;
	private long householdCount;
	private long zip;
	private String county;
	private boolean publicProgram;
	private long userProfileId;
	private long clientId;
	private String forLsc;
	private String eligibilityStatus;
	private Date submissionDate;
	private long prevEligibilityId;
	private String createdBy;;
	private Date createdDate;;
	private String updatedBy;;
	private Date updatedDate;;
	private String eligibilityId;
	private Date runDate;
	
	private ElgMember elgMember;
	/**
	 * @return the elgMember
	 */
	public ElgMember getElgMember() {
		return elgMember;
	}
	/**
	 * @param elgMember the elgMember to set
	 */
	public void setElgMember(ElgMember elgMember) {
		this.elgMember = elgMember;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the householdCount
	 */
	public long getHouseholdCount() {
		return householdCount;
	}
	/**
	 * @param householdCount the householdCount to set
	 */
	public void setHouseholdCount(long householdCount) {
		this.householdCount = householdCount;
	}
	/**
	 * @return the zip
	 */
	public long getZip() {
		return zip;
	}
	/**
	 * @param zip the zip to set
	 */
	public void setZip(long zip) {
		this.zip = zip;
	}
	/**
	 * @return the county
	 */
	public String getCounty() {
		return county;
	}
	/**
	 * @param county the county to set
	 */
	public void setCounty(String county) {
		this.county = county;
	}
	/**
	 * @return the publicProgram
	 */
	public boolean isPublicProgram() {
		return publicProgram;
	}
	/**
	 * @param publicProgram the publicProgram to set
	 */
	public void setPublicProgram(boolean publicProgram) {
		this.publicProgram = publicProgram;
	}
	/**
	 * @return the userProfileId
	 */
	public long getUserProfileId() {
		return userProfileId;
	}
	/**
	 * @param userProfileId the userProfileId to set
	 */
	public void setUserProfileId(long userProfileId) {
		this.userProfileId = userProfileId;
	}
	/**
	 * @return the clientId
	 */
	public long getClientId() {
		return clientId;
	}
	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
	/**
	 * @return the forLsc
	 */
	public String getForLsc() {
		return forLsc;
	}
	/**
	 * @param forLsc the forLsc to set
	 */
	public void setForLsc(String forLsc) {
		this.forLsc = forLsc;
	}
	/**
	 * @return the eligibilityStatus
	 */
	public String getEligibilityStatus() {
		return eligibilityStatus;
	}
	/**
	 * @param eligibilityStatus the eligibilityStatus to set
	 */
	public void setEligibilityStatus(String eligibilityStatus) {
		this.eligibilityStatus = eligibilityStatus;
	}
	/**
	 * @return the submissionDate
	 */
	public Date getSubmissionDate() {
		return submissionDate;
	}
	/**
	 * @param submissionDate the submissionDate to set
	 */
	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}
	/**
	 * @return the prevEligibilityId
	 */
	public long getPrevEligibilityId() {
		return prevEligibilityId;
	}
	/**
	 * @param prevEligibilityId the prevEligibilityId to set
	 */
	public void setPrevEligibilityId(long prevEligibilityId) {
		this.prevEligibilityId = prevEligibilityId;
	}
	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}
	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	/**
	 * @return the eligibilityId
	 */
	public String getEligibilityId() {
		return eligibilityId;
	}
	/**
	 * @param eligibilityId the eligibilityId to set
	 */
	public void setEligibilityId(String eligibilityId) {
		this.eligibilityId = eligibilityId;
	}
	/**
	 * @return the runDate
	 */
	public Date getRunDate() {
		return runDate;
	}
	/**
	 * @param runDate the runDate to set
	 */
	public void setRunDate(Date runDate) {
		this.runDate = runDate;
	}


}