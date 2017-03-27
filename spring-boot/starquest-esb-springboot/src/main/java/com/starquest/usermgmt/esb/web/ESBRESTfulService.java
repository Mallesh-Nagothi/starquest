/**
 * 
 */
package com.starquest.usermgmt.esb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.starquest.usermgmt.esb.service.SQESBNotificationService;
import com.starquest.usermgmt.vo.UserVo;

/**
 * @author mallesh
 * @since March 26 2017
 * @version 1.0
 * 
 */
@RestController
@RequestMapping("/sq/esb")
public class ESBRESTfulService {
	
	private SQESBNotificationService sqESBNotificationService;
	
	
	/**
	 * 
	 */
	@Autowired
	public ESBRESTfulService(SQESBNotificationService sqESBNotificationService) {
		this.sqESBNotificationService = sqESBNotificationService;
	}
	
	
	@RequestMapping(path="/registrationRulesFail", method = RequestMethod.POST,
	consumes = { "application/json"}, produces = { "application/json"})
	public ResponseEntity<UserVo> registrationRulesFail(@RequestBody UserVo userVo ){
		
		sqESBNotificationService.sendMQNotification(userVo);
		
		return new ResponseEntity<UserVo>(userVo, HttpStatus.OK);
	}
	
	
	@RequestMapping(path="/registrationEncryptionFail", method = RequestMethod.POST,
	consumes = { "application/json"}, produces = { "application/json"})
	public ResponseEntity<UserVo> registrationEncryptionFail(@RequestBody UserVo userVo ){
		
		sqESBNotificationService.sendMQNotification(userVo);
		
		return new ResponseEntity<UserVo>(userVo, HttpStatus.OK);
	}
	
	@RequestMapping(path="/registrationPersistenceFail", method = RequestMethod.POST,
	consumes = { "application/json"}, produces = { "application/json"})
	public ResponseEntity<UserVo> registrationPersistenceFail(@RequestBody UserVo userVo ){
		
		sqESBNotificationService.sendMQNotification(userVo);
		
		return new ResponseEntity<UserVo>(userVo, HttpStatus.OK);
	}
	
	

}
