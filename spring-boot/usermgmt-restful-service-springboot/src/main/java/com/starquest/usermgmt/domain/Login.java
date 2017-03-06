/**
 * 
 */
package com.starquest.usermgmt.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * @author mallesh
 *
 */
@Entity
@Table(name = "LOGIN")
public class Login {

	@Id
	@SequenceGenerator(name="login_id_seq", sequenceName="login_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.IDENTITY,generator="login_id_seq")
	private Integer id;
	
	@Column(name = "userid")
	private Integer userId;
	
	@Column(name = "passwordsalt")
	private String passwordSalt;
	
	@Column(name = "passwordhash")
	private String passwordHash;
	
	@Column(name = "createdby")
	private String createdBy;
	
	@Column(name = "createdon")
	@Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdOn;
	
	 
	
	@OneToOne 
	@JoinColumn(name="id")
	private User user;
	
	
	public Login(String passwordSalt, String passwordHash, String createdBy){
		this.passwordHash 	= passwordHash;
		this.passwordSalt 	= passwordSalt;
		this.createdBy		= createdBy;
	}
	
	public Login(){ 	}
	
	
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
	 * @return the passwordSalt
	 */
	public String getPasswordSalt() {
		return passwordSalt;
	}
	/**
	 * @param passwordSalt the passwordSalt to set
	 */
	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}
	/**
	 * @return the passwordHash
	 */
	public String getPasswordHash() {
		return passwordHash;
	}
	/**
	 * @param passwordHash the passwordHash to set
	 */
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
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
	 * @return the createdOn
	 */
	public java.util.Date getCreatedOn() {
		return createdOn;
	}
	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(java.util.Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	
	
	
}
