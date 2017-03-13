/**
 * 
 */
package com.starquest.usermgmt.vo;

import java.util.Date;


/**
 * @author mhsyed
 *
 */
public class UserProfile {
	
	public enum UserProfileStatus{
		NA, EXISTING, BAD_SSN, BAD_DOB, BAD_LASTNAME,BAD_FIRSTNAME, GOOD_SSN
	}
	
	private long id;
	private String userName;
	private String suffix;
	private String firstName;
	private String middleName;
	private String lastName;
	private Date dateOfBirth;
	private String email; 
	private String mobilePhone;
	private String ssn;
	private String gender;
	private String preferredLangauge;
	private String userType;
	private String preferredSpokenLangauge;
	private String preferredWrittenLangauge;
	private String dontHaveSSNIND;
	private String noSSNReasonCD;
	
	private String identitySuccessInd;
	private String idProofingCompletedInd;
	private String state;
	private String nyhxId;
	private String userModuleRoleId;
	private boolean statusInd;
	private String accountId;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private String cinNumber;
	private Eligibility eligibility;
	private UserProfileStatus userProfileStatus = UserProfileStatus.NA;
	
	
	//elibiglity --> elgmember --> elg_income
	
	/**
	 * @return the eligibility
	 */
	public Eligibility getEligibility() {
		return eligibility;
	}
	/**
	 * @param eligibility the eligibility to set
	 */
	public void setEligibility(Eligibility eligibility) {
		this.eligibility = eligibility;
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the suffix
	 */
	public String getSuffix() {
		return suffix;
	}
	/**
	 * @param suffix the suffix to set
	 */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the mobilePhone
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}
	/**
	 * @param mobilePhone the mobilePhone to set
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
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
	 * @return the preferredLangauge
	 */
	public String getPreferredLangauge() {
		return preferredLangauge;
	}
	/**
	 * @param preferredLangauge the preferredLangauge to set
	 */
	public void setPreferredLangauge(String preferredLangauge) {
		this.preferredLangauge = preferredLangauge;
	}
	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}
	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	/**
	 * @return the preferredSpokenLangauge
	 */
	public String getPreferredSpokenLangauge() {
		return preferredSpokenLangauge;
	}
	/**
	 * @param preferredSpokenLangauge the preferredSpokenLangauge to set
	 */
	public void setPreferredSpokenLangauge(String preferredSpokenLangauge) {
		this.preferredSpokenLangauge = preferredSpokenLangauge;
	}
	/**
	 * @return the preferredWrittenLangauge
	 */
	public String getPreferredWrittenLangauge() {
		return preferredWrittenLangauge;
	}
	/**
	 * @param preferredWrittenLangauge the preferredWrittenLangauge to set
	 */
	public void setPreferredWrittenLangauge(String preferredWrittenLangauge) {
		this.preferredWrittenLangauge = preferredWrittenLangauge;
	}
	/**
	 * @return the dontHaveSSNIND
	 */
	public String getDontHaveSSNIND() {
		return dontHaveSSNIND;
	}
	/**
	 * @param dontHaveSSNIND the dontHaveSSNIND to set
	 */
	public void setDontHaveSSNIND(String dontHaveSSNIND) {
		this.dontHaveSSNIND = dontHaveSSNIND;
	}
	/**
	 * @return the noSSNReasonCD
	 */
	public String getNoSSNReasonCD() {
		return noSSNReasonCD;
	}
	/**
	 * @param noSSNReasonCD the noSSNReasonCD to set
	 */
	public void setNoSSNReasonCD(String noSSNReasonCD) {
		this.noSSNReasonCD = noSSNReasonCD;
	}
	/**
	 * @return the identitySuccessInd
	 */
	public String getIdentitySuccessInd() {
		return identitySuccessInd;
	}
	/**
	 * @param identitySuccessInd the identitySuccessInd to set
	 */
	public void setIdentitySuccessInd(String identitySuccessInd) {
		this.identitySuccessInd = identitySuccessInd;
	}
	/**
	 * @return the idProofingCompletedInd
	 */
	public String getIdProofingCompletedInd() {
		return idProofingCompletedInd;
	}
	/**
	 * @param idProofingCompletedInd the idProofingCompletedInd to set
	 */
	public void setIdProofingCompletedInd(String idProofingCompletedInd) {
		this.idProofingCompletedInd = idProofingCompletedInd;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the nyhxId
	 */
	public String getNyhxId() {
		return nyhxId;
	}
	/**
	 * @param nyhxId the nyhxId to set
	 */
	public void setNyhxId(String nyhxId) {
		this.nyhxId = nyhxId;
	}
	/**
	 * @return the userModuleRoleId
	 */
	public String getUserModuleRoleId() {
		return userModuleRoleId;
	}
	/**
	 * @param userModuleRoleId the userModuleRoleId to set
	 */
	public void setUserModuleRoleId(String userModuleRoleId) {
		this.userModuleRoleId = userModuleRoleId;
	}
	/**
	 * @return the statusInd
	 */
	public boolean isStatusInd() {
		return statusInd;
	}
	/**
	 * @param statusInd the statusInd to set
	 */
	public void setStatusInd(boolean statusInd) {
		this.statusInd = statusInd;
	}
	/**
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
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
	 * @return the userProfileStatus
	 */
	public UserProfileStatus getUserProfileStatus() {
		return userProfileStatus;
	}
	/**
	 * @param userProfileStatus the userProfileStatus to set
	 */
	public void setUserProfileStatus(UserProfileStatus userProfileStatus) {
		this.userProfileStatus = userProfileStatus;
	}
	
	
	
	
	
}