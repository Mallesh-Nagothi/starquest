/**
 * 
 */
package com.starquest.usermgmt.esb.service;

import com.starquest.registration.config.NotificationRouter;

/**
 * @author mallesh
 *
 */
public class MQCamelNotification implements CamelNotificationBehavior {

	/* (non-Javadoc)
	 * @see com.starquest.usermgmt.esb.service.CamelNotificationBehavior#processCamelNotification(com.starquest.registration.config.NotificationRouter)
	 */
	@Override
	public boolean processCamelNotification(NotificationRouter notificationRouter) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
