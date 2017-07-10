package com.starquest.encryption.restservices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.starquest.decryption.services.DecryptionService;
import com.starquest.decryption.services.PasswordDecryption;
import com.starquest.usermgmt.vo.UserVo;

@RestController
@RequestMapping("/sq/decryption")
public class DecryptionRESTfulService {

	
	@RequestMapping(value = "/password", method = { RequestMethod.POST }, 
			consumes = { "application/json"}, produces = { "application/json",})
	public ResponseEntity<UserVo> getDecryptedValue(@RequestBody UserVo rawValue){
		String decryptVal = new String();
		System.out.println(":::Decryption Service receive Request:::"+rawValue.getPassword());
		System.out.println(":::Decryption Service receive Request:::"+rawValue.getFirstName());
		try{
			DecryptionService decryptionService = new PasswordDecryption(); //dude use spring
			decryptVal = decryptionService.decryptPassword(rawValue.getPassword(), "");
			System.out.println("Decryption Service Encrypted::"+decryptVal);
			
		}catch(Exception ex){
			//bad way of programming
			System.out.println(ex.toString());
			decryptVal =  rawValue.getPassword();
		}
		rawValue.setPassword(decryptVal);
		System.out.println("::Decryption Service Processed Complete:::");
		return new ResponseEntity<UserVo>(rawValue, HttpStatus.OK);
	}
	
	/**
	 * Bootstap method for Springboot load end points
	 * @param args
	 * @throws Exception
	 */
	
	/*public static void main(String[] args) throws Exception{
		SpringApplication.run(DecryptionRESTfulService.class, args);
	}*/
	
	
}
