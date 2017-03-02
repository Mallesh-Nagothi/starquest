/**
 * 
 */
package com.starquest.usermgmt.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.jooq.util.GenerationTool;
import org.jooq.util.jaxb.Strategy;


/**
 * @author mallesh
 *
 */
@Entity
public class Login {

	@Id
	@SequenceGenerator(name="login_id_seq", sequenceName="login_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.IDENTITY,generator="login_id_seq")
	private Integer id;
	
	@Column(name = "passwordsalt")
	private String passwordSalt;
	
	@Column(name = "passwordhash")
	private String passwordHash;
	
	@Column(name = "createdby")
	private String createdBy;
	
	@Column(name = "createdon")
	@Temporal(TemporalType.TIMESTAMP)
    public java.util.Date createdOn;
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
	
	
	
	
	
}
