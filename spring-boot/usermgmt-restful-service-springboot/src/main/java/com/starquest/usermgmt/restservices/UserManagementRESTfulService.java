/**
 * 
 */
package com.starquest.usermgmt.restservices;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.starquest.usermgmt.bootstrap.LoginLoader;
import com.starquest.usermgmt.domain.Login;
import com.starquest.usermgmt.vo.LoginVo;

/**
 * @author mallesh
 *
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/user")
public class UserManagementRESTfulService {

	@Autowired
	private LoginLoader loginLoad;
	
	Logger logger = Logger.getLogger(UserManagementRESTfulService.class);
	
	@RequestMapping(value = "/register", method = { RequestMethod.POST })
	public String registerUser(@RequestParam(value="salt", defaultValue="1") String salt,
			@RequestParam(value="pwd", defaultValue="0") String password){
		
		String newString = new String("Nothing");
		if(null!=salt && null!=password){
			newString =   salt + password; 
			
			Login login = new Login();
			login.setCreatedBy("SpringBootTest");
			login.setPasswordHash(password);
			login.setPasswordSalt(salt);
			login.setCreatedOn(new Date());
			loginLoad.getLoginRepository().save(login);
			
		}
		return newString;
	}
	
	
	@RequestMapping(value = "/encryptAndSave", method = { RequestMethod.POST }, 
			consumes = { "application/json","text/plain", "application/xml", "text/xml" }, 
			produces = { "application/json", "text/plain", "application/xml", "text/xml" })
	public ResponseEntity<LoginVo> encryptAndSave(@RequestBody LoginVo loginVo){
		
		logger.debug("encryptAndSave Started...");
		if(null!=loginVo){
			
			RestTemplate restTeamplate = new RestTemplate();
			
			String consumeJSONString = restTeamplate.getForObject("http://localhost:8080/encryption/"+loginVo.getPasswordHash(), String.class);
	        System.out.println("JSON String: "+consumeJSONString);
	        
/*	        List<String> listStrings = restTemplate.getForObject("http://localhost:8080/SampleRESTFulService/consumeJSONList", List.class);
	        System.out.println("List of String: "+listStrings);
	        
	        HashMap<String, String> hashMap = restTemplate.getForObject("http://localhost:8080/SampleRESTFulService/consumeJSONMap",  HashMap.class);        
	        System.out.println("JSON Map: "+hashMap);*/
	        
	        
			Login login = new Login();
			login.setCreatedBy(loginVo.getCreatedBy());
			login.setPasswordHash(consumeJSONString);
			login.setPasswordSalt(loginVo.getPasswordSalt());
			login.setCreatedOn(new Date());
			loginLoad.getLoginRepository().save(login);
			loginVo.setId(login.getId());
			loginVo.setPasswordHash(consumeJSONString);
			loginVo.setCreatedOn(login.getCreatedOn());
			logger.debug("Login Saved...");
		}
		logger.debug("returing from encryptAndSave...");
		return new ResponseEntity<LoginVo>(loginVo, HttpStatus.OK);
	}
	
	/**
	 * Bootstap method for Springboot load end points
	 * @param args
	 * @throws Exception
	 */
	
	public static void main(String[] args) throws Exception{
		SpringApplication.run(UserManagementRESTfulService.class, args);
	}
}
