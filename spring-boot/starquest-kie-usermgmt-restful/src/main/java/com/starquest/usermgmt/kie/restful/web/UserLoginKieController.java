package com.starquest.usermgmt.kie.restful.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.starquest.usermgmt.kie.restful.service.UserLoginKieService;
import com.starquest.usermgmt.vo.UserVo;

@Controller
@RequestMapping("/sq/userloginkie")
public class UserLoginKieController {

	@Autowired
	UserLoginKieService userLoginKieService;
	
	@RequestMapping(path="/startLoginProcess", method = RequestMethod.POST,
			consumes = { "application/json"}, produces = { "application/json"})
	public ResponseEntity<UserVo> startLoginBPMProcess(@RequestBody UserVo userVo){
		
		try{
			userLoginKieService.startUserLoginBPMWorkFlow(userVo);
			if(userVo.isBadPassword()){
				System.out.println("BPM Process completed..and Password/UserName = "+userVo.isBadPassword());
				System.out.println("Cannot be logged in");
			}else{
				System.out.println("BPM Process completed..and Password/UserName = "+userVo.isBadPassword());
				System.out.println("Proceed to login...");
			}
			
		}catch(Exception ex){
			System.out.println("something wrong"+ex);
		}
		
		
		return new ResponseEntity<UserVo>(userVo, HttpStatus.OK);
		
	}
	
	
	@RequestMapping(path="/passwordDecryptFailFlow", method = RequestMethod.POST,
			consumes = { "application/json"}, produces = { "application/json"})
	public ResponseEntity<UserVo> passwordDecryptFailFlow(@RequestBody UserVo userVo){
		
		try{
			
			System.out.println("/passwordDecryptFailFlow");
			userVo.setBadPassword(true);
			
		}catch(Exception ex){
			System.out.println("something wrong");
		}
		
		
		return new ResponseEntity<UserVo>(userVo, HttpStatus.OK);
		
	}
	
	//move this to database
	@RequestMapping(path="/validateCredentialsFlow", method = RequestMethod.POST,
			consumes = { "application/json"}, produces = { "application/json"})
	public ResponseEntity<UserVo> validateCredentialsFlow(@RequestBody UserVo userVo){
		
		try{
			System.out.println("/validateCredentialsFlow");
			
		}catch(Exception ex){
			System.out.println("something wrong");
		}
		
		
		return new ResponseEntity<UserVo>(userVo, HttpStatus.OK);
		
	}
	
	//move this also to db 
	@RequestMapping(path="/loginCredentialsFailFlow", method = RequestMethod.POST,
			consumes = { "application/json"}, produces = { "application/json"})
	public ResponseEntity<UserVo> loginCredentialsFailFlow(@RequestBody UserVo userVo){
		
		try{
			System.out.println("/loginCredentialsFailFlow");
			userVo.setBadPassword(true);
			
		}catch(Exception ex){
			System.out.println("something wrong");
		}
		
		
		return new ResponseEntity<UserVo>(userVo, HttpStatus.OK);
		
	}
	
	@RequestMapping(path="/loginCredentialsSucessFlow", method = RequestMethod.POST,
			consumes = { "application/json"}, produces = { "application/json"})
	public ResponseEntity<UserVo> loginCredentialsSucessFlow(@RequestBody UserVo userVo){
		
		try{
			System.out.println("/loginCredentialsSucessFlow");
			userVo.setBadPassword(false);
			
		}catch(Exception ex){
			System.out.println("something wrong");
		}
		
		
		return new ResponseEntity<UserVo>(userVo, HttpStatus.OK);
		
	}
	
}