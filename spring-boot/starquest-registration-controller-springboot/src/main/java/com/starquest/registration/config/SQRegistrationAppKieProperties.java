/**
 * 
 */
package com.starquest.registration.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;



/**
 * @author mallesh
 * @since Mar/18/2017
 * @version 1.0
 * YML KIE Endpoints Properties holder
 * 
 */
@Component
@ConfigurationProperties(prefix = "kie")
public class SQRegistrationAppKieProperties {
	
	
	private String registrationBPMWorkflow; 
	private String validatepassword;
	private String ssnValidation;
	private String invalidEndPointURL;
	private String globalMediaTypeJson;
	private String globalOperationPost;
	private List<SQEndPoint> endPoints = new ArrayList<SQEndPoint>();
	
	private String loginBPMWorkflow;
	private String invalidLoginEndPointURL;
	private String loginMediaTypeJson;
	private String loginOperationPost;
	
	
	
	public SQRegistrationAppKieProperties(String registrationBPMWorkflow, String validatepassword, 
											String ssnValidation, String invalidEndPointURL, List<SQEndPoint> endPoints,
											String globalMediaTypeJson, String globalOperationPost,
											String loginBPMWorkflow, String invalidLoginEndPointURL,
											String loginMediaTypeJson, String loginOperationPost
											) {
		
		this.registrationBPMWorkflow 	= registrationBPMWorkflow;
		this.validatepassword			= validatepassword;
		this.ssnValidation				= ssnValidation;
		this.invalidEndPointURL			= invalidEndPointURL;
		this.endPoints					= endPoints;
		this.globalMediaTypeJson		= globalMediaTypeJson;
		this.globalOperationPost		= globalOperationPost;
		this.loginBPMWorkflow			= loginBPMWorkflow; 
		this.invalidLoginEndPointURL	= invalidLoginEndPointURL;
		this.loginMediaTypeJson			= loginMediaTypeJson;
		this.loginOperationPost			= loginOperationPost;
		
		
	}
	
	public SQRegistrationAppKieProperties(){
		
	}
	
	public SQEndPoint getLoginBPMWorkFlowEndPointDetails(){
		return (SQEndPoint) endPoints.stream().filter(endPoint -> endPoint.getEndPoint().equals(SqEndPoints.LOGIN_BPM_WORKFLOW_ENDPOINT.endPoint())
				).findFirst().get();
	}
	
	/**
	 * @return the endPoints
	 */
	public List<SQEndPoint> getEndPoints() {
		return endPoints;
	}

	/**
	 * @param endPoints the endPoints to set
	 */
	public void setEndPoints(List<SQEndPoint> endPoints) {
		this.endPoints = endPoints;
	}

	/**
	 * @return the registrationBPMWorkflow
	 */
	public String getRegistrationBPMWorkflow() {
		return registrationBPMWorkflow;
	}

	/**
	 * @param registrationBPMWorkflow the registrationBPMWorkflow to set
	 */
	public void setRegistrationBPMWorkflow(String registrationBPMWorkflow) {
		this.registrationBPMWorkflow = registrationBPMWorkflow;
	}

	/**
	 * @return the validatepassword
	 */
	public String getValidatepassword() {
		return validatepassword;
	}

	/**
	 * @param validatepassword the validatepassword to set
	 */
	public void setValidatepassword(String validatepassword) {
		this.validatepassword = validatepassword;
	}

	/**
	 * @return the ssnValidation
	 */
	public String getSsnValidation() {
		return ssnValidation;
	}

	/**
	 * @param ssnValidation the ssnValidation to set
	 */
	public void setSsnValidation(String ssnValidation) {
		this.ssnValidation = ssnValidation;
	}

	/**
	 * @return the invalidEndPointURL
	 */
	public String getInvalidEndPointURL() {
		return invalidEndPointURL;
	}

	/**
	 * @param invalidEndPointURL the invalidEndPointURL to set
	 */
	public void setInvalidEndPointURL(String invalidEndPointURL) {
		this.invalidEndPointURL = invalidEndPointURL;
	}

	/**
	 * @return the globalMediaTypeJson
	 */
	public String getGlobalMediaTypeJson() {
		return globalMediaTypeJson;
	}

	/**
	 * @param globalMediaTypeJson the globalMediaTypeJson to set
	 */
	public void setGlobalMediaTypeJson(String globalMediaTypeJson) {
		this.globalMediaTypeJson = globalMediaTypeJson;
	}

	/**
	 * @return the globalOperationPost
	 */
	public String getGlobalOperationPost() {
		return globalOperationPost;
	}

	/**
	 * @param globalOperationPost the globalOperationPost to set
	 */
	public void setGlobalOperationPost(String globalOperationPost) {
		this.globalOperationPost = globalOperationPost;
	}

	public String getLoginBPMWorkflow() {
		return loginBPMWorkflow;
	}

	public void setLoginBPMWorkflow(String loginBPMWorkflow) {
		this.loginBPMWorkflow = loginBPMWorkflow;
	}

	public String getInvalidLoginEndPointURL() {
		return invalidLoginEndPointURL;
	}

	public void setInvalidLoginEndPointURL(String invalidLoginEndPointURL) {
		this.invalidLoginEndPointURL = invalidLoginEndPointURL;
	}

	public String getLoginMediaTypeJson() {
		return loginMediaTypeJson;
	}

	public void setLoginMediaTypeJson(String loginMediaTypeJson) {
		this.loginMediaTypeJson = loginMediaTypeJson;
	}

	public String getLoginOperationPost() {
		return loginOperationPost;
	}

	public void setLoginOperationPost(String loginOperationPost) {
		this.loginOperationPost = loginOperationPost;
	}
	

}
