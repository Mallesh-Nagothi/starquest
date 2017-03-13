/**
 * 
 */
package com.starquest.usermgmt.vo;

import java.util.Date;

/**
 * @author mhsyed
 *
 */
public class MemberIncome {

	private long id;
	private Double ncomeFromEmployment;
	private String frequencyOfPayment;
	private int hoursPerWeek;
	private String wage;
	private Double otherIncome;
	private boolean hasOtherIncome;
	private Double mntlyHouseholdIncome;
	private Double mntlyHouseholdIncomeCorrect;
	private Double irsIncome;
	private boolean irsIncomeCorrect;
	private String newIrsIncome;
	private Double expectedHouseholdIncome;
	private Double jobIncome;
	private Double adjustment;
	private Double totalHouseholdIncome;
	private Double selfEmploymentIncome;
	private long elgMemberId;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private String taxFilingStatusCode;
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
	 * @return the ncomeFromEmployment
	 */
	public Double getNcomeFromEmployment() {
		return ncomeFromEmployment;
	}
	/**
	 * @param ncomeFromEmployment the ncomeFromEmployment to set
	 */
	public void setNcomeFromEmployment(Double ncomeFromEmployment) {
		this.ncomeFromEmployment = ncomeFromEmployment;
	}
	/**
	 * @return the frequencyOfPayment
	 */
	public String getFrequencyOfPayment() {
		return frequencyOfPayment;
	}
	/**
	 * @param frequencyOfPayment the frequencyOfPayment to set
	 */
	public void setFrequencyOfPayment(String frequencyOfPayment) {
		this.frequencyOfPayment = frequencyOfPayment;
	}
	/**
	 * @return the hoursPerWeek
	 */
	public int getHoursPerWeek() {
		return hoursPerWeek;
	}
	/**
	 * @param hoursPerWeek the hoursPerWeek to set
	 */
	public void setHoursPerWeek(int hoursPerWeek) {
		this.hoursPerWeek = hoursPerWeek;
	}
	/**
	 * @return the wage
	 */
	public String getWage() {
		return wage;
	}
	/**
	 * @param wage the wage to set
	 */
	public void setWage(String wage) {
		this.wage = wage;
	}
	/**
	 * @return the otherIncome
	 */
	public Double getOtherIncome() {
		return otherIncome;
	}
	/**
	 * @param otherIncome the otherIncome to set
	 */
	public void setOtherIncome(Double otherIncome) {
		this.otherIncome = otherIncome;
	}
	/**
	 * @return the hasOtherIncome
	 */
	public boolean isHasOtherIncome() {
		return hasOtherIncome;
	}
	/**
	 * @param hasOtherIncome the hasOtherIncome to set
	 */
	public void setHasOtherIncome(boolean hasOtherIncome) {
		this.hasOtherIncome = hasOtherIncome;
	}
	/**
	 * @return the mntlyHouseholdIncome
	 */
	public Double getMntlyHouseholdIncome() {
		return mntlyHouseholdIncome;
	}
	/**
	 * @param mntlyHouseholdIncome the mntlyHouseholdIncome to set
	 */
	public void setMntlyHouseholdIncome(Double mntlyHouseholdIncome) {
		this.mntlyHouseholdIncome = mntlyHouseholdIncome;
	}
	/**
	 * @return the mntlyHouseholdIncomeCorrect
	 */
	public Double getMntlyHouseholdIncomeCorrect() {
		return mntlyHouseholdIncomeCorrect;
	}
	/**
	 * @param mntlyHouseholdIncomeCorrect the mntlyHouseholdIncomeCorrect to set
	 */
	public void setMntlyHouseholdIncomeCorrect(Double mntlyHouseholdIncomeCorrect) {
		this.mntlyHouseholdIncomeCorrect = mntlyHouseholdIncomeCorrect;
	}
	/**
	 * @return the irsIncome
	 */
	public Double getIrsIncome() {
		return irsIncome;
	}
	/**
	 * @param irsIncome the irsIncome to set
	 */
	public void setIrsIncome(Double irsIncome) {
		this.irsIncome = irsIncome;
	}
	/**
	 * @return the irsIncomeCorrect
	 */
	public boolean isIrsIncomeCorrect() {
		return irsIncomeCorrect;
	}
	/**
	 * @param irsIncomeCorrect the irsIncomeCorrect to set
	 */
	public void setIrsIncomeCorrect(boolean irsIncomeCorrect) {
		this.irsIncomeCorrect = irsIncomeCorrect;
	}
	/**
	 * @return the newIrsIncome
	 */
	public String getNewIrsIncome() {
		return newIrsIncome;
	}
	/**
	 * @param newIrsIncome the newIrsIncome to set
	 */
	public void setNewIrsIncome(String newIrsIncome) {
		this.newIrsIncome = newIrsIncome;
	}
	/**
	 * @return the expectedHouseholdIncome
	 */
	public Double getExpectedHouseholdIncome() {
		return expectedHouseholdIncome;
	}
	/**
	 * @param expectedHouseholdIncome the expectedHouseholdIncome to set
	 */
	public void setExpectedHouseholdIncome(Double expectedHouseholdIncome) {
		this.expectedHouseholdIncome = expectedHouseholdIncome;
	}
	/**
	 * @return the jobIncome
	 */
	public Double getJobIncome() {
		return jobIncome;
	}
	/**
	 * @param jobIncome the jobIncome to set
	 */
	public void setJobIncome(Double jobIncome) {
		this.jobIncome = jobIncome;
	}
	/**
	 * @return the adjustment
	 */
	public Double getAdjustment() {
		return adjustment;
	}
	/**
	 * @param adjustment the adjustment to set
	 */
	public void setAdjustment(Double adjustment) {
		this.adjustment = adjustment;
	}
	/**
	 * @return the totalHouseholdIncome
	 */
	public Double getTotalHouseholdIncome() {
		return totalHouseholdIncome;
	}
	/**
	 * @param totalHouseholdIncome the totalHouseholdIncome to set
	 */
	public void setTotalHouseholdIncome(Double totalHouseholdIncome) {
		this.totalHouseholdIncome = totalHouseholdIncome;
	}
	/**
	 * @return the selfEmploymentIncome
	 */
	public Double getSelfEmploymentIncome() {
		return selfEmploymentIncome;
	}
	/**
	 * @param selfEmploymentIncome the selfEmploymentIncome to set
	 */
	public void setSelfEmploymentIncome(Double selfEmploymentIncome) {
		this.selfEmploymentIncome = selfEmploymentIncome;
	}
	/**
	 * @return the elgMemberId
	 */
	public long getElgMemberId() {
		return elgMemberId;
	}
	/**
	 * @param elgMemberId the elgMemberId to set
	 */
	public void setElgMemberId(long elgMemberId) {
		this.elgMemberId = elgMemberId;
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
	 * @return the taxFilingStatusCode
	 */
	public String getTaxFilingStatusCode() {
		return taxFilingStatusCode;
	}
	/**
	 * @param taxFilingStatusCode the taxFilingStatusCode to set
	 */
	public void setTaxFilingStatusCode(String taxFilingStatusCode) {
		this.taxFilingStatusCode = taxFilingStatusCode;
	}

}