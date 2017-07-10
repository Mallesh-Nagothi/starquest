/**
 * 
 */
package com.starquest.usermgmt.esb.service;

import com.starquest.registration.config.NotificationRouter;

/**
 * @author mallesh
 * @since Mar-27-2017
 * @version 1.0
 * 
 * Used Strategy Pattern for all Camel Notifications.
 * Behavior is abstracted in a composit object
 * @see com.starquest.usermgmt.esb.service.CamelNotificationBehavior
 * 
 */
public abstract class CamelNotification {
	
	CamelNotificationBehavior camelNotificationBehavior;
	
	public CamelNotification (){
		
	}
	
	
	public boolean processCamelNotification(NotificationRouter notificationRouter) throws Exception{
		return camelNotificationBehavior.processCamelNotification(notificationRouter);
	}


	/**
	 * @return the camelNotificationBehavior
	 */
	public CamelNotificationBehavior getCamelNotificationBehavior() {
		return camelNotificationBehavior;
	}


	/**
	 * @param camelNotificationBehavior the camelNotificationBehavior to set
	 */
	public void setCamelNotificationBehavior(CamelNotificationBehavior camelNotificationBehavior) {
		this.camelNotificationBehavior = camelNotificationBehavior;
	}
	
	
	
	
	
}
