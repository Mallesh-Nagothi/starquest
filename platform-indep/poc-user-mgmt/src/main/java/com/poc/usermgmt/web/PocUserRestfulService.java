/**
 * 
 */
package com.poc.usermgmt.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poc.usermgmt.service.UserBusinessLogic;
import com.poc.usermgmt.vo.UserVo;



/**
 * @author mallesh
 * Registring a new poc user using SpringMVC REST 
 */

@RestController
@RequestMapping("/poc")
public class PocUserRestfulService {
	
	Logger logger = Logger.getLogger(PocUserRestfulService.class);
	
	@Autowired
	UserBusinessLogic userBusinessLogic;
	
	
	@RequestMapping(value = "/newpocUser", method = { RequestMethod.POST }, 
			consumes = { "application/json"}, produces = { "application/json",})
	public ResponseEntity<UserVo> createpocUser(@RequestBody UserVo userVo){
		
		logger.debug("Entrypoint :: /newpocUser");
		
		userBusinessLogic.saveUser(userVo);
		
		logger.debug("Exitpoint :: /newpocUser");
		return new ResponseEntity<UserVo>(userVo, HttpStatus.OK);
		
	}
	
	
}
