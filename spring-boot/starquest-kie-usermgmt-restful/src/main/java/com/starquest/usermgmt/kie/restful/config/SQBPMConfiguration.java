/**
 * 
 */
package com.starquest.usermgmt.kie.restful.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.starquest.registration.config.SQEndPoint;

/**
 * @author mallesh
 *
 */
@Component
@ConfigurationProperties(prefix = "kie")
public class SQBPMConfiguration {
	
	private String registrationKIESession;
	private String registrationBPMProcessflowName;
	private String registrationBPMProcessflowFulllName;
	private String registrationPasswordRulesKSession;
	private List<SQEndPoint> endPoints = new ArrayList<SQEndPoint>();
	
	//Password Rules End Point Properties
	private String registrationBPMWorkflow;
	private String registrationPasswordRulesflow;
	private String globalMediaTypeJson;
	private String globalOperationPost;
	
	
	public SQBPMConfiguration(String registrationKIESession,
								String registrationBPMProcessflowName, 
								String registrationBPMProcessflowFulllName, 
								String registrationPasswordRulesKSession, 
								List<SQEndPoint> endPoints,
								String globalMediaTypeJson,
								String globalOperationPost,
								String registrationBPMWorkflow,
								String registrationPasswordRulesflow) {
		
		this.registrationBPMProcessflowFulllName 	= registrationBPMProcessflowFulllName;
		this.registrationBPMProcessflowName			= registrationBPMProcessflowName;
		this.registrationKIESession					= registrationKIESession;
		this.registrationPasswordRulesKSession 		= registrationPasswordRulesKSession; 
		this.endPoints								= endPoints;
		this.globalMediaTypeJson					= globalMediaTypeJson;
		this.globalOperationPost					= globalOperationPost;
		this.registrationBPMWorkflow				= registrationBPMWorkflow;
		this.registrationPasswordRulesflow			= registrationPasswordRulesflow;
	}
	
	public SQBPMConfiguration() {
		
	}

	/**
	 * @return the registrationKIESession
	 */
	public String getRegistrationKIESession() {
		return registrationKIESession;
	}

	/**
	 * @param registrationKIESession the registrationKIESession to set
	 */
	public void setRegistrationKIESession(String registrationKIESession) {
		this.registrationKIESession = registrationKIESession;
	}

	/**
	 * @return the registrationBPMProcessflowName
	 */
	public String getRegistrationBPMProcessflowName() {
		return registrationBPMProcessflowName;
	}

	/**
	 * @param registrationBPMProcessflowName the registrationBPMProcessflowName to set
	 */
	public void setRegistrationBPMProcessflowName(String registrationBPMProcessflowName) {
		this.registrationBPMProcessflowName = registrationBPMProcessflowName;
	}

	/**
	 * @return the registrationBPMProcessflowFulllName
	 */
	public String getRegistrationBPMProcessflowFulllName() {
		return registrationBPMProcessflowFulllName;
	}

	/**
	 * @param registrationBPMProcessflowFulllName the registrationBPMProcessflowFulllName to set
	 */
	public void setRegistrationBPMProcessflowFulllName(String registrationBPMProcessflowFulllName) {
		this.registrationBPMProcessflowFulllName = registrationBPMProcessflowFulllName;
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
	 * @return the registrationPasswordRulesKSession
	 */
	public String getRegistrationPasswordRulesKSession() {
		return registrationPasswordRulesKSession;
	}

	/**
	 * @param registrationPasswordRulesKSession the registrationPasswordRulesKSession to set
	 */
	public void setRegistrationPasswordRulesKSession(String registrationPasswordRulesKSession) {
		this.registrationPasswordRulesKSession = registrationPasswordRulesKSession;
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
	 * @return the registrationPasswordRulesflow
	 */
	public String getRegistrationPasswordRulesflow() {
		return registrationPasswordRulesflow;
	}

	/**
	 * @param registrationPasswordRulesflow the registrationPasswordRulesflow to set
	 */
	public void setRegistrationPasswordRulesflow(String registrationPasswordRulesflow) {
		this.registrationPasswordRulesflow = registrationPasswordRulesflow;
	}
	
	
	
	
	
	
}
