/**
 * 
 */
package com.starquest.usermgmt.esb.service;

import com.starquest.registration.config.NotificationRouter;

/**
 * @author mallesh
 *
 */
public interface CamelNotificationBehavior {

	
	public boolean processCamelNotification(NotificationRouter notificationRouter) throws Exception;
	
}
