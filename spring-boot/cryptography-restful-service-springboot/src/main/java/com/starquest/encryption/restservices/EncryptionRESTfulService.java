/**
 * 
 */
package com.starquest.encryption.restservices;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.starquest.encryption.PasswordEncryption;
import com.starquest.encryption.services.EncryptionService;
import com.starquest.encryption.services.SaltGeneratorService;
import com.starquest.encryption.services.SaltType;

/**
 * @author mallesh
 * @since  Feb 27 2017
 * @version 1.0
 * 
 * 
 */

@RestController
@EnableAutoConfiguration
@RequestMapping("/encryption")
public class EncryptionRESTfulService {

	@RequestMapping(method = RequestMethod.GET, value = "/{rawValue}")
	public String getEncryptedValue(@PathVariable String rawValue){
		
		/*String rawValue = "Password";*/
		String encryptedRawValue = new String();
		/* inject validator here and do all validations for password,
		 * possibly add one more parameter that tells type of rawValue whether its password or not
	    */
		/*if(rawValue == null){
			return encryptedRawValue;
		}*/
		
		try{
			EncryptionService encryptionService = new PasswordEncryption();
			encryptedRawValue = encryptionService.encryptPassword(rawValue, SaltType.NON_NATIVE, SaltGeneratorService.SHA1, 32);
		}catch(Exception ex){
			//bad way of programming
			rawValue = ex.toString();
			System.out.println(ex.toString());
			return encryptedRawValue;
		}
		return encryptedRawValue;
	}
	
	/**
	 * Bootstap method for Springboot load end points
	 * @param args
	 * @throws Exception
	 */
	
	public static void main(String[] args) throws Exception{
		SpringApplication.run(EncryptionRESTfulService.class, args);
	}
	
}
