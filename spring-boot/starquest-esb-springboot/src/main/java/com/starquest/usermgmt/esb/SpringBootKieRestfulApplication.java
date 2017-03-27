/**
 * 
 */
package com.starquest.usermgmt.esb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author mallesh
 * @since  03/25/2017
 * @version 1.0
 * 
 * 
 * Bootstraping Starquest ESB Restful Services
 * using Springboot
 *
 */

@SpringBootApplication
public class SpringBootEsbRestfulApplication {
	
	public static void main(String[] args){
		SpringApplication.run(SpringBootEsbRestfulApplication.class, args);
	}
}
