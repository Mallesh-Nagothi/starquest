/**	
 * 
 */
package com.starquest.usermgmt.kie.restful.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.starquest.usermgmt.kie.restful.service.UserRegistrationKieService;
import com.starquest.usermgmt.vo.UserVo;

/**
 * @author mallesh
 *
 */
@Controller
@RequestMapping("/sq/registrationkie")
public class UserRegistrationKieController {
	
	private static final Logger logger = Logger.getLogger(UserRegistrationKieController.class);
	
	UserRegistrationKieService userRegistrationKieService;
	
	/**
	 * Constructor 
	 */
	@Autowired
	public UserRegistrationKieController(UserRegistrationKieService userRegistrationKieService) {
		this.userRegistrationKieService = userRegistrationKieService;
	}
	
	@RequestMapping(path="/startRegistrationKieProcess", method = RequestMethod.POST,
			consumes = { "application/json"}, produces = { "application/json"})
	public ResponseEntity<UserVo> startRegistrationBPMProcess(@RequestBody UserVo userVo){
		HttpStatus httpStatus = HttpStatus.FORBIDDEN;
		logger.debug("BPMS-BRMS KIE Controller Received Request-->End Point-->startRegistrationBPMProcess()....");
		
		try{
			UserVo processedUserVo = userRegistrationKieService.startRegistrationBPMWorkflow(userVo);
			if(null!=processedUserVo){
				userVo.setFailCategory(processedUserVo.getFailCategory());
				userVo.setCategory(processedUserVo.getCategory());
				System.out.println("End Point-->startRegistrationBPMProcess() UserVo.FAILCATEGORY="+userVo.getFailCategory());;
				System.out.println("End Point-->startRegistrationBPMProcess() UserVo.CATEGORY=..."+userVo.getCategory());
				
			}
			httpStatus = HttpStatus.OK;
		}catch(Exception ex){
			System.out.println("Damallll::"+ex);
		}
		
		logger.debug("END BPMS-BRMS KIE Controller Request-->End Point-->startRegistrationBPMProcess()....");
		
		return new ResponseEntity<UserVo>(userVo, httpStatus);
		
	}
	
	@RequestMapping(path="/applyPasswordRules", method = RequestMethod.POST,
	consumes = { "application/json"}, produces = { "application/json"})
	public ResponseEntity<UserVo> validatePasswordNewwUserRegistration(@RequestBody UserVo userVo){
		logger.debug("BPMS-BRMS KIE Controller Received Request-->End Point-->validatePasswordNewwUserRegistration()....");
		try{
			userVo = userRegistrationKieService.applySQPasswordRules(userVo);
		}catch(Exception ex){
			System.out.println("Damallll::"+ex);
		}
		logger.debug("END BPMS-BRMS KIE Controller Request-->End Point-->validatePasswordNewwUserRegistration()....");
		return new ResponseEntity<UserVo>(userVo, HttpStatus.OK);
	}

}
