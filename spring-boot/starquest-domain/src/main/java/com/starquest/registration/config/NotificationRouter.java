/**
 * 
 */
package com.starquest.registration.config;

import com.starquest.usermgmt.vo.UserVo;

/**
 * @author mallesh
 *
 */
public class NotificationRouter {
	
	private static final long serialVersionUID = 1L;
	
	public enum RouteDestination {
		NA, EMAIL, MQ, FILE
    };
    
    public enum RouteSource {
        NA, ENDPOINT, EMAIL, MQ, FILE
    };
    
    public enum NotificationType {
    	NEW_REGISTRATION, NA
    }
    
	private UserVo userVo;
	
	private String sourceScheme;
	private String destinationScheme;
	private String sourceContextPath;
	private String destinationContextPath;
	private String sourceOptions;
	private String destinationOptions;
	private RouteDestination routeDestination;
	private RouteSource routeSource;
	private NotificationType notificationType;
	
	
	/**
	 * @return the userVo
	 */
	public UserVo getUserVo() {
		return userVo;
	}
	/**
	 * @param userVo the userVo to set
	 */
	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}
	/**
	 * @return the sourceScheme
	 */
	public String getSourceScheme() {
		return sourceScheme;
	}
	/**
	 * @param sourceScheme the sourceScheme to set
	 */
	public void setSourceScheme(String sourceScheme) {
		this.sourceScheme = sourceScheme;
	}
	/**
	 * @return the destinationScheme
	 */
	public String getDestinationScheme() {
		return destinationScheme;
	}
	/**
	 * @param destinationScheme the destinationScheme to set
	 */
	public void setDestinationScheme(String destinationScheme) {
		this.destinationScheme = destinationScheme;
	}
	/**
	 * @return the sourceContextPath
	 */
	public String getSourceContextPath() {
		return sourceContextPath;
	}
	/**
	 * @param sourceContextPath the sourceContextPath to set
	 */
	public void setSourceContextPath(String sourceContextPath) {
		this.sourceContextPath = sourceContextPath;
	}
	/**
	 * @return the destinationContextPath
	 */
	public String getDestinationContextPath() {
		return destinationContextPath;
	}
	/**
	 * @param destinationContextPath the destinationContextPath to set
	 */
	public void setDestinationContextPath(String destinationContextPath) {
		this.destinationContextPath = destinationContextPath;
	}
	/**
	 * @return the sourceOptions
	 */
	public String getSourceOptions() {
		return sourceOptions;
	}
	/**
	 * @param sourceOptions the sourceOptions to set
	 */
	public void setSourceOptions(String sourceOptions) {
		this.sourceOptions = sourceOptions;
	}
	/**
	 * @return the destinationOptions
	 */
	public String getDestinationOptions() {
		return destinationOptions;
	}
	/**
	 * @param destinationOptions the destinationOptions to set
	 */
	public void setDestinationOptions(String destinationOptions) {
		this.destinationOptions = destinationOptions;
	}
	/**
	 * @return the routeDestination
	 */
	public RouteDestination getRouteDestination() {
		return routeDestination;
	}
	/**
	 * @param routeDestination the routeDestination to set
	 */
	public void setRouteDestination(RouteDestination routeDestination) {
		this.routeDestination = routeDestination;
	}
	/**
	 * @return the routeSource
	 */
	public RouteSource getRouteSource() {
		return routeSource;
	}
	/**
	 * @param routeSource the routeSource to set
	 */
	public void setRouteSource(RouteSource routeSource) {
		this.routeSource = routeSource;
	}
	/**
	 * @return the notificationType
	 */
	public NotificationType getNotificationType() {
		return notificationType;
	}
	/**
	 * @param notificationType the notificationType to set
	 */
	public void setNotificationType(NotificationType notificationType) {
		this.notificationType = notificationType;
	}
	
	
	
	
	
	

}
