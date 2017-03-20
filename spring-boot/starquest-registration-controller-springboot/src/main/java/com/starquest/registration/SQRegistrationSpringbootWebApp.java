package com.starquest.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author mallesh
 * @version 1.0
 * @since Mar/18/2017
 * SQ Registration Springboot Web Application 
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
public class SQRegistrationSpringbootWebApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SQRegistrationSpringbootWebApp.class, args);

	}

}

