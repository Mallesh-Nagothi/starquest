package com.starquest.usermgmt.vo;

import java.util.Date;

public class ElgMember {
	
	private long Id;
	private String firstName;
	private String citizenshipStatus;
	private String gender;
	private boolean stateResident;
	private String incarcerationStatus;
	private boolean pregnant;
	private int pregnantExpectedBabies;
	private long eligibilityId;
	private String relationship;
	private Date dateOfBirth;
	/**
	 * @return the id
	 */
	public long getId() {
		return Id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		Id = id;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the citizenshipStatus
	 */
	public String getCitizenshipStatus() {
		return citizenshipStatus;
	}
	/**
	 * @param citizenshipStatus the citizenshipStatus to set
	 */
	public void setCitizenshipStatus(String citizenshipStatus) {
		this.citizenshipStatus = citizenshipStatus;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the stateResident
	 */
	public boolean isStateResident() {
		return stateResident;
	}
	/**
	 * @param stateResident the stateResident to set
	 */
	public void setStateResident(boolean stateResident) {
		this.stateResident = stateResident;
	}
	/**
	 * @return the incarcerationStatus
	 */
	public String getIncarcerationStatus() {
		return incarcerationStatus;
	}
	/**
	 * @param incarcerationStatus the incarcerationStatus to set
	 */
	public void setIncarcerationStatus(String incarcerationStatus) {
		this.incarcerationStatus = incarcerationStatus;
	}
	/**
	 * @return the pregnant
	 */
	public boolean isPregnant() {
		return pregnant;
	}
	/**
	 * @param pregnant the pregnant to set
	 */
	public void setPregnant(boolean pregnant) {
		this.pregnant = pregnant;
	}
	/**
	 * @return the pregnantExpectedBabies
	 */
	public int getPregnantExpectedBabies() {
		return pregnantExpectedBabies;
	}
	/**
	 * @param pregnantExpectedBabies the pregnantExpectedBabies to set
	 */
	public void setPregnantExpectedBabies(int pregnantExpectedBabies) {
		this.pregnantExpectedBabies = pregnantExpectedBabies;
	}
	/**
	 * @return the eligibilityId
	 */
	public long getEligibilityId() {
		return eligibilityId;
	}
	/**
	 * @param eligibilityId the eligibilityId to set
	 */
	public void setEligibilityId(long eligibilityId) {
		this.eligibilityId = eligibilityId;
	}
	/**
	 * @return the relationship
	 */
	public String getRelationship() {
		return relationship;
	}
	/**
	 * @param relationship the relationship to set
	 */
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	/**
	 * @return the fosterChild
	 */
	public boolean isFosterChild() {
		return fosterChild;
	}
	/**
	 * @param fosterChild the fosterChild to set
	 */
	public void setFosterChild(boolean fosterChild) {
		this.fosterChild = fosterChild;
	}
	/**
	 * @return the ssn
	 */
	public String getSsn() {
		return ssn;
	}
	/**
	 * @param ssn the ssn to set
	 */
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}
	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the idProofType
	 */
	public String getIdProofType() {
		return idProofType;
	}
	/**
	 * @param idProofType the idProofType to set
	 */
	public void setIdProofType(String idProofType) {
		this.idProofType = idProofType;
	}
	/**
	 * @return the pregnantDueDate
	 */
	public Date getPregnantDueDate() {
		return pregnantDueDate;
	}
	/**
	 * @param pregnantDueDate the pregnantDueDate to set
	 */
	public void setPregnantDueDate(Date pregnantDueDate) {
		this.pregnantDueDate = pregnantDueDate;
	}
	/**
	 * @return the nyhxMemberId
	 */
	public String getNyhxMemberId() {
		return nyhxMemberId;
	}
	/**
	 * @param nyhxMemberId the nyhxMemberId to set
	 */
	public void setNyhxMemberId(String nyhxMemberId) {
		this.nyhxMemberId = nyhxMemberId;
	}
	/**
	 * @return the cinNumber
	 */
	public String getCinNumber() {
		return cinNumber;
	}
	/**
	 * @param cinNumber the cinNumber to set
	 */
	public void setCinNumber(String cinNumber) {
		this.cinNumber = cinNumber;
	}
	/**
	 * @return the dateOfDeath
	 */
	public Date getDateOfDeath() {
		return dateOfDeath;
	}
	/**
	 * @param dateOfDeath the dateOfDeath to set
	 */
	public void setDateOfDeath(Date dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}
	boolean fosterChild;
	String ssn;
	String middleName;
	String lastName;
	String idProofType;
	Date pregnantDueDate;
	String nyhxMemberId;
	String cinNumber;
	Date dateOfDeath;


}