/**
 * 
 */
package com.starquest.usermgmt.bootstrap;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.starquest.usermgmt.domain.Login;
import com.starquest.usermgmt.repositories.LoginRepository;

/**
 * @author mallesh
 *
 */
@Component
public class LoginLoader implements ApplicationListener<ContextRefreshedEvent> {
	
	private LoginRepository loginRepository;
	private Logger log = Logger.getLogger(LoginLoader.class);
	
	/**
	 * @param loginRepository the loginRepository to set
	 */
	@Autowired
	public void setLoginRepository(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}

	public LoginRepository getLoginRepository(){
		return this.loginRepository;
	}


	@Override
	public void onApplicationEvent(ContextRefreshedEvent event){
		
		Login login = new Login();
		login.setCreatedBy("SpringBootTest");
		login.setPasswordHash("HashedPassword");
		login.setPasswordSalt("salt");
		login.setCreatedOn(new Date());
		loginRepository.save(login);
		
		log.info("Saving Login Info::"+login.getId());
		
		
		Login login1 = new Login();
		login.setCreatedBy("SpringBootTest");
		login.setPasswordHash("HashedPassword1");
		login.setPasswordSalt("salt1");
		loginRepository.save(login);
		
		log.info("Saving Login Info::"+login1.getId());
		
	}
	
	

}
