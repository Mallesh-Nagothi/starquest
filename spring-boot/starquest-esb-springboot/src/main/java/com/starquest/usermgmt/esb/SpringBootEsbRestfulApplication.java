/**
 * 
 */
package com.starquest.usermgmt.esb;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

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
	
	@Bean
	public ConnectionFactory connectionFactory(){
		ConnectionFactory connFactory = new ActiveMQConnectionFactory("vm://127.0.0.1");
		return connFactory;
	}
	
	@Bean
	public MessageConverter jacksonJmsMessageConverter(){
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}
	
	
	public static void main(String[] args){
		//SpringApplication.run(SpringBootEsbRestfulApplication.class, args);
		
		ConfigurableApplicationContext context = SpringApplication.run(SpringBootEsbRestfulApplication.class, args);
		
		//JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
		//System.out.println("Sending an email......");
		//jmsTemplate.convertAndSend("mailbox", new Email);
		
		
		
	}
}
