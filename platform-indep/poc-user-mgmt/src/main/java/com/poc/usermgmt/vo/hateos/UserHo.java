/**
 * 
 */
package com.poc.usermgmt.vo.hateos;

import java.util.Date;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author mallesh
 *
 */
public class UserHo extends ResourceSupport{
	
	private final Integer primaryId;
	private final String userId;
	private final String firstName;
	private final String lastName;
	private final String emailAddress;
	private final Date createdOn;
	private final String createdBy;
	
	
	@JsonCreator
	public UserHo(@JsonProperty("primaryId") Integer primaryId,
			@JsonProperty("userId") String userId,
			@JsonProperty("firstName") String firstName,
			@JsonProperty("lastName") String lastName,
			@JsonProperty("emailAddress") String emailAddress,
			@JsonProperty("createdBy") String createdBy,
			@JsonProperty("createdOn") Date   createdOn
			){
		this.primaryId 			= primaryId;
		this.userId 		= userId;
		this.firstName 		= firstName;
		this.lastName 		= lastName;
		this.emailAddress 	= emailAddress;
		this.createdBy 		= createdBy;
		this.createdOn 		= createdOn;
	}


	/**
	 * @return the id
	 */
	public Integer getPrimaryId() {
		return primaryId;
	}


	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}


	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}


	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}


	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}


	/**
	 * @return the createdOn
	 */
	public Date getCreatedOn() {
		return createdOn;
	}


	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	
	
	
	
	
	
}
