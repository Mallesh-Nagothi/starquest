/**
 * 
 */
package com.starquest.usermgmt.web;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.starquest.usermgmt.vo.LoginVo;

/**
 * @author mallesh
 *
 */
@RestController
@RequestMapping("/user")
public class UserManagementRESTfulService {

	
	
	Logger logger = Logger.getLogger(UserManagementRESTfulService.class);
	
	@RequestMapping(value = "/encryptAndSave", method = { RequestMethod.POST }, 
			consumes = { "application/json"}, produces = { "application/json"})
	public ResponseEntity<LoginVo> encryptAndSave(@RequestBody LoginVo loginVo){
		
		/*logger.debug("encryptAndSave Started...");
		if(null!=loginVo){
			
			RestTemplate restTeamplate = new RestTemplate();
			
			String consumeJSONString = restTeamplate.getForObject("http://localhost:8080/encryption/"+loginVo.getPasswordHash(), String.class);
	        System.out.println("JSON String: "+consumeJSONString);
	        
	        List<String> listStrings = restTemplate.getForObject("http://localhost:8080/SampleRESTFulService/consumeJSONList", List.class);
	        System.out.println("List of String: "+listStrings);
	        
	        HashMap<String, String> hashMap = restTemplate.getForObject("http://localhost:8080/SampleRESTFulService/consumeJSONMap",  HashMap.class);        
	        System.out.println("JSON Map: "+hashMap);
	        
	        
			Login login = new Login();
			login.setCreatedBy(loginVo.getCreatedBy());
			login.setPasswordHash(consumeJSONString);
			login.setPasswordSalt(loginVo.getPasswordSalt());
			login.setCreatedOn(new Date());
			//loginLoad.getLoginRepository().save(login);
			loginVo.setId(login.getId());
			loginVo.setPasswordHash(consumeJSONString);
			loginVo.setCreatedOn(login.getCreatedOn());
			logger.debug("Login Saved...");
		}
		logger.debug("returing from encryptAndSave...");*/
		return new ResponseEntity<LoginVo>(loginVo, HttpStatus.OK);
	}
	
}
