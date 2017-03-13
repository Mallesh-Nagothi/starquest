/**
 * 
 */
package com.starquest.usermgmt.kie.restful.service;

import com.starquest.usermgmt.vo.UserProfile;
import com.starquest.usermgmt.vo.UserVo;

/**
 * @author mallesh
 *
 */
public interface UserRegistrationRules {
	
	
	public UserVo applyNewUserRegistrationPasswordRules(UserVo userVo);
	
	public UserProfile applyNewUserRegistrationSSNRules(UserProfile userProfile);

}
