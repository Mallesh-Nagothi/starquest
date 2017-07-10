/**
 * 
 */
package com.starquest.usermgmt.esb.service;

/**
 * @author mallesh
 *
 */
public class NewRegistrationNotification extends CamelNotification {

	public NewRegistrationNotification(){
		camelNotificationBehavior = new MQCamelNotification();
	}
}
