/**
 * 
 */
package com.starquest.usermgmt.kie.restful;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author mallesh
 * @since  03/11/2017
 * @version 1.0
 * 
 * 
 * Bootstraping Starquest KIE Restful Services
 * using Springboot
 *
 */

@SpringBootApplication
public class SpringBootKieRestfulApplication {
	
	public static void main(String[] args){
		SpringApplication.run(SpringBootKieRestfulApplication.class, args);
	}

	
	@Bean
	public KieContainer kieContainer(){
		
		KieServices kieServices = KieServices.Factory.get();
		return kieServices.newKieContainer(kieServices.newReleaseId(
									"com.starquest", "starquest-kie-kjar", "1.0.0"));
	}
}
