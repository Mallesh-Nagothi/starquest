/**
 * 
 */
package com.starquest.usermgmt.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.starquest.usermgmt.service.UserBusinessLogic;
import com.starquest.usermgmt.vo.UserVo;



/**
 * @author mallesh
 * Registring a new StarQuest user using SpringMVC REST 
 */

@RestController
@RequestMapping("/startquest")
public class StarQuestUserRestfulService {
	
	Logger logger = Logger.getLogger(StarQuestUserRestfulService.class);
	
	@Autowired
	UserBusinessLogic userBusinessLogic;
	
	
	@RequestMapping(value = "/newStarQuestUser", method = { RequestMethod.POST }, 
			consumes = { "application/json"}, produces = { "application/json",})
	public ResponseEntity<UserVo> createStarQuestUser(@RequestBody UserVo userVo){
		
		logger.debug("Entrypoint :: /newStarQuestUser");
		
		userBusinessLogic.saveUser(userVo);
		
		logger.debug("Exitpoint :: /newStarQuestUser");
		return new ResponseEntity<UserVo>(userVo, HttpStatus.OK);
		
	}
	
	
}
