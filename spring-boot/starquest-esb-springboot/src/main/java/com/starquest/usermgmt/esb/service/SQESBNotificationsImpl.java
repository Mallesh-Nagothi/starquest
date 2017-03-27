/**
 * 
 */
package com.starquest.usermgmt.esb.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.starquest.usermgmt.vo.UserVo;

/**
 * @author mallesh
 *
 */
@Service
public class SQESBNotificationsImpl implements SQESBNotificationService {
	
	private static final Logger logger = Logger.getLogger(SQESBNotificationsImpl.class);

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

}
