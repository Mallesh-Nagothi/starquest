/**
 * 
 */
package com.starquest.usermgmt.kie.restful.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.starquest.usermgmt.kie.restful.service.UserRegistrationRules;
import com.starquest.usermgmt.vo.UserProfile;
import com.starquest.usermgmt.vo.UserVo;

/**
 * @author mallesh
 *
 */
@RestController
@RequestMapping("/starquest/userregrules")
public class NewUserRegistrationRules {
	
	
	UserRegistrationRules userRegistrationRules;
	
	
	@Autowired
	public NewUserRegistrationRules(UserRegistrationRules userRegistrationRules) {
		this.userRegistrationRules = userRegistrationRules;
	}
	
	@RequestMapping(path="/validatepassword", method = RequestMethod.POST,
	consumes = { "application/json"}, produces = { "application/json"})
	public ResponseEntity<UserVo> categorizeNewUserRegistration(@RequestBody UserVo userVo ){
		
		userRegistrationRules.applyNewUserRegistrationPasswordRules(userVo);
		
		return new ResponseEntity<UserVo>(userVo, HttpStatus.OK);
	}
	
	
	@RequestMapping(path="/validateSSN", method = RequestMethod.POST,
	consumes = { "application/json"}, produces = { "application/json"})
	public ResponseEntity<UserProfile> validateSSNForNewUserRegistration(@RequestBody UserProfile userProfile){
		
		userRegistrationRules.applyNewUserRegistrationSSNRules(userProfile);
		
		return new ResponseEntity<UserProfile>(userProfile, HttpStatus.OK);
	}
	
	

}
