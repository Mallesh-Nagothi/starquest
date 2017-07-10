/**
 * 
 */
package com.starquest.usermgmt.esb.service;

import com.starquest.registration.config.NotificationRouter;
import com.starquest.usermgmt.vo.UserVo;

/**
 * @author mallesh
 *
 */
public interface SQESBNotificationService {
	
	public boolean sendEmailNotification(UserVo userVo);
	
	public boolean sendMQNotification(UserVo userVo);
	
	public boolean notifyRegistrationSuccess(NotificationRouter notificationRouter) throws Exception;

}
