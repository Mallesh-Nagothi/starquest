/**
 * 
 */
package com.starquest.registration.service;

import com.starquest.usermgmt.vo.UserVo;

/**
 * @author mallesh
 * @version 1.0
 * @since Mar/18/2017
 */
public interface RegistrationService {
	
	/**
	 * 
	 * @param newUser
	 * @return Newly Registered SQ User
	 * Call New User Registration BPM Workflow and Returns UserVo
	 * 
	 */
	public UserVo callNewRegistrationService(UserVo newUser) throws Exception;
	
	
	

}
