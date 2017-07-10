/**
 * 
 */
package com.starquest.usermgmt.esb.service;

import javax.jms.ConnectionFactory;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.starquest.registration.config.NotificationRouter;
import com.starquest.usermgmt.vo.UserVo;

/**
 * @author mallesh
 *
 */
@Service
public class SQESBNotificationsImpl implements SQESBNotificationService {
	
	private static final Logger logger = Logger.getLogger(SQESBNotificationsImpl.class);
	
	@Autowired
	CamelContext camelContext;
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Autowired
	ConnectionFactory sqJmsFactory;

	/* (non-Javadoc)
	 * @see com.starquest.usermgmt.esb.service.SQESBNotificationService#sendEmailNotification(com.starquest.usermgmt.vo.UserVo)
	 */
	@Override
	public boolean sendEmailNotification(UserVo userVo) {
		System.out.println("Camel Snet Notification to MQ...");
		return false;
	}

	/* (non-Javadoc)
	 * @see com.starquest.usermgmt.esb.service.SQESBNotificationService#sendMQNotification(com.starquest.usermgmt.vo.UserVo)
	 */
	@Override
	public boolean sendMQNotification(UserVo userVo) {
		System.out.println("Camel Snet Notification to MQ...");
		return false;
	}

	/* (non-Javadoc)
	 * @see com.starquest.usermgmt.esb.service.SQESBNotificationService#notifyRegistrationSuccess()
	 */
	@Override
	public boolean notifyRegistrationSuccess(NotificationRouter notificationRouter) throws Exception {
		// TODO Auto-generated method stub
		camelContext.addComponent("jms",JmsComponent.jmsComponentAutoAcknowledge(sqJmsFactory));
		
		camelContext.addRoutes(new RouteBuilder(){
			public void configure(){
				from("").to("jms:newRegistrations");
			}
		});
		camelContext.start();
		Thread.sleep(10000);
		camelContext.stop();
		
		return false;
	}
	
	

}
