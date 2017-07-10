package com.starquest.registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.starquest.registration.service.LoginService;
import com.starquest.usermgmt.vo.UserVo;

/**
 * @author mallesh
 * @version 1.0
 * @since Jun/01/2017
 * 
 * Entry Point @Server End for User Login Workflow
 *
 */
@Controller
@RequestMapping("/sq/userlogin")
public class UserLoginController {
	
	private LoginService loginService;
	
	/**
	 * Constructor
	 * @param loginService
	 */
	@Autowired
	public UserLoginController(LoginService loginService){
		this.loginService = loginService;
	}
	

	@RequestMapping(value = "/submitLogin", method = { RequestMethod.POST }, 
			consumes = { "application/json"}, produces = { "application/json",})
	public ResponseEntity<UserVo> userSignIn(@RequestBody UserVo userVo){
		
		try{
			userVo = loginService.callLogMeInBPMService(userVo);
		}catch(Exception ex){
			System.out.println("Error ::"+ex);
		
		}
		
		return new ResponseEntity<UserVo>(userVo, HttpStatus.OK);
	}
	
	

}
