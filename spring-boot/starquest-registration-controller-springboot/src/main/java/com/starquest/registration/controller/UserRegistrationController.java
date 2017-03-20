/**
 * 
 */
package com.starquest.registration.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.starquest.registration.service.RegistrationService;
import com.starquest.usermgmt.vo.UserVo;

/**
 * @author mallesh
 * @version 1.0
 * @since Mar/19/2017
 * 
 * Entry Point @Server End for User Registration Workflow
 *
 */
@Controller
@RequestMapping("/sq/userreg")
public class UserRegistrationController {
	
	private static final Logger logger = Logger.getLogger(UserRegistrationController.class);
	
	private RegistrationService registrationService;
	
	
	/**
	 * Constructor
	 */
	@Autowired
	public UserRegistrationController(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}
	
	@RequestMapping(value = "/registerNewUser", method = { RequestMethod.POST }, 
			consumes = { "application/json"}, produces = { "application/json",})
	public ResponseEntity<UserVo> registerNewStarQuestUser(@RequestBody UserVo userVo){
		
		logger.debug("START Swarm Controller Received Request-->End Point-->registerNewStarQuestUser()....");
		try{
			UserVo kieProcessedUserVo = registrationService.callNewRegistrationService(userVo);
			if(null!=kieProcessedUserVo){
				userVo.setFailCategory(kieProcessedUserVo.getFailCategory());
				userVo.setCategory(kieProcessedUserVo.getCategory());
				System.out.println("End Point-->registerNewStarQuestUser() UserVo.FAILCATEGORY="+userVo.getFailCategory());;
				System.out.println("End Point-->registerNewStarQuestUser() UserVo.CATEGORY=..."+userVo.getCategory());
				
			}
		}catch(Exception ex){
			System.out.println("Error ::"+ex);
			logger.error("Exception::"+ex);
		}
		logger.debug("END Swarm Controller Request-->End Point-->registerNewStarQuestUser()....");
		return new ResponseEntity<UserVo>(userVo, HttpStatus.OK);
	}
	
	

}
