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
			}
			httpStatus = HttpStatus.OK;
		}catch(Exception ex){
			System.out.println("Damallll::"+ex);
		}
		
		logger.debug("END BPMS-BRMS KIE Controller Request-->End Point-->startRegistrationBPMProcess()....");
		
		return new ResponseEntity<UserVo>(userVo, httpStatus);
		
	}
	
	
	public ResponseEntity<UserVo> startLoginBPMProcess(@RequestBody UserVo userVo){
		HttpStatus httpStatus = HttpStatus.FORBIDDEN;
		System.out.println("Starting Login BPM Flow");
		
		try{
			
			
		}catch(Exception ex){
			System.out.println("Damallll::"+ex);
		}
		
		
		System.out.println("Ending Login BPM Flow");
		return new ResponseEntity<UserVo>(userVo,httpStatus);
		
	}

	
	@RequestMapping(path="/registrationPasswordRulesSuccessflow", method = RequestMethod.POST,
	consumes = { "application/json"}, produces = { "application/json"})
	public ResponseEntity<UserVo> registrationPasswordRulesSuccessflow(@RequestBody UserVo userVo){
		logger.debug("BPMS-BRMS KIE Controller Received Request-->End Point-->registrationPasswordRulesSuccessflow()....");
		try{
			//userVo = userRegistrationKieService.applySQPasswordRules(userVo);
			System.out.println("Processing Registration Success Reqest.....");
			
		}catch(Exception ex){
			System.out.println("Damallll::"+ex);
		}
		logger.debug("END BPMS-BRMS KIE Controller Request-->End Point-->registrationPasswordRulesSuccessflow()....");
		return new ResponseEntity<UserVo>(userVo, HttpStatus.OK);
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
	
	
	
	@RequestMapping(path="/processPasswordRulesFail", method = RequestMethod.POST,
	consumes = { "application/json"}, produces = { "application/json"})
	public ResponseEntity<UserVo> processPasswordRulesFail(@RequestBody UserVo userVo){
		logger.debug("BPMS-BRMS KIE Controller Received Request-->End Point-->processPasswordRulesFail()....");
		try{
			userRegistrationKieService.processRegistrationRulesFail(userVo);
		}catch(Exception ex){
			System.out.println("Damallll::"+ex);
		}
		logger.debug("END BPMS-BRMS KIE Controller Request-->End Point-->processPasswordRulesFail()....");
		return new ResponseEntity<UserVo>(userVo, HttpStatus.OK);
	}
	
	@RequestMapping(path="/processEncryptionFail", method = RequestMethod.POST,
	consumes = { "application/json"}, produces = { "application/json"})
	public ResponseEntity<UserVo> processEncryptionFail(@RequestBody UserVo userVo){
		logger.debug("BPMS-BRMS KIE Controller Received Request-->End Point-->processPasswordRulesFail()....");
		try{
			userRegistrationKieService.processRegistrationEncryptionFail(userVo);
		}catch(Exception ex){
			System.out.println("Damallll::"+ex);
		}
		logger.debug("END BPMS-BRMS KIE Controller Request-->End Point-->processPasswordRulesFail()....");
		return new ResponseEntity<UserVo>(userVo, HttpStatus.OK);
	}
	
	
	@RequestMapping(path="/processPersistenceFail", method = RequestMethod.POST,
	consumes = { "application/json"}, produces = { "application/json"})
	public ResponseEntity<UserVo> processPersistenceFail(@RequestBody UserVo userVo){
		logger.debug("BPMS-BRMS KIE Controller Received Request-->End Point-->processPersistenceFail()....");
		try{
			userRegistrationKieService.processRegistrationPersistFail(userVo);
		}catch(Exception ex){
			System.out.println("Damallll::"+ex);
		}
		logger.debug("END BPMS-BRMS KIE Controller Request-->End Point-->processPersistenceFail()....");
		return new ResponseEntity<UserVo>(userVo, HttpStatus.OK);
	}
	


}
