/**
 * 
 */
package com.starquest.registration.config;

import java.util.List;

/**
 * @author mallesh
 * @version 1.0
 * @since 03/22/2017
 * 
 * Bulk Endpoints to pass it to jBPM custom Workitem handler RESTful
 *
 */
public class SQRESTfulEndPoints {

	private static final long serialVersionUID = 1L;
	
	/** Hold multiple end Points to call from jBPM custom workitemhandler **/
	private List<SQEndPoint> sqEndPoints;

	/**
	 * @return the sqEndPoints
	 */
	public List<SQEndPoint> getSqEndPoints() {
		return sqEndPoints;
	}

	/**
	 * @param sqEndPoints the sqEndPoints to set
	 */
	public void setSqEndPoints(List<SQEndPoint> sqEndPoints) {
		this.sqEndPoints = sqEndPoints;
	}
	
	
	
	
}
