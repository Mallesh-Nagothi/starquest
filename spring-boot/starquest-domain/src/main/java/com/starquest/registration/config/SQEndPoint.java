/**
 * 
 */
package com.starquest.registration.config;

/**
 * @author mallesh
 * @version 1.0
 * @since Mar/18/2017
 * YML properties holder class
 */
public class SQEndPoint {
	
	private static final long serialVersionUID = 1L;

	/**  To Store what kind of end point **/
	private String endPoint; 
	
	/**  To Store Actual URL **/
	private String url;       
	
	/**  To Store Context for End Point **/
	private String context;
	
	/**  To Store Operation type POST/GET for End Point **/
	private String operation;
	
	/**  To Store media type JSON/XML etc for End Point **/
	private String mediaType;
	
	public SQEndPoint() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the endPoint
	 */
	public String getEndPoint() {
		return endPoint;
	}
	/**
	 * @param endPoint the endPoint to set
	 */
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the context
	 */
	public String getContext() {
		return context;
	}
	/**
	 * @param context the context to set
	 */
	public void setContext(String context) {
		this.context = context;
	}
	
	@Override
    public String toString() {
        return "EndPoint{" +
                "endPointType='" + endPoint + '\'' +
                ", url='" + url + '\'' +
                ", context='" + context + '\'' +
                ", operation='" + operation + '\'' +
                ", mediaType='" + mediaType + '\'' +
                '}';
    }
	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}
	/**
	 * @param operation the operation to set
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}
	/**
	 * @return the mediaType
	 */
	public String getMediaType() {
		return mediaType;
	}
	/**
	 * @param mediaType the mediaType to set
	 */
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	
}
