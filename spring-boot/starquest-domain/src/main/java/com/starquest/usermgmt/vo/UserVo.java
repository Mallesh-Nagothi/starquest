/**
 * 
 */
package com.starquest.usermgmt.vo;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author mallesh
 *
 */
public class UserVo implements Serializable{

	private static final long serialVersionUID = 1L;

	public enum Category {
		NA, NEW_USER, EXISTING_USER, INACTIVE_USER, REGISTRATION_SUCCESS, REGISTRATION_FAILED
    };
    
    public enum FailCategory {
        TOO_MANY_TIMES_USED, 
        TOO_WEAK,
        TOO_LESS_CHARS,
        TOO_MANY_CHARS,
        FAILED_MIN_REQUIREMENT, 
        STRONG_ENOUGH, 
        VERY_STRONG,
        VERY_VERY_STRONG,
        VERY_VERY_VERY_STRONG,
        NA
    };
    
   
    
	private Integer id;
	private String userId;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String password;
	private String salt;
	private Date createdOn;
	private String createdBy;
	
	private FailCategory failCategory = FailCategory.NA;
	private Category category = Category.NA;
	
	
	public UserVo() {
		
	}
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
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
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	/**
	 * @return the createdOn
	 */
	public Date getCreatedOn() {
		return createdOn;
	}
	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the salt
	 */
	public String getSalt() {
		return salt;
	}
	/**
	 * @param salt the salt to set
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}
	/**
	 * @return the failCategory
	 */
	public FailCategory getFailCategory() {
		return failCategory;
	}
	/**
	 * @param failCategory the failCategory to set
	 */
	public void setFailCategory(FailCategory failCategory) {
		this.failCategory = failCategory;
	}
	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}


}
