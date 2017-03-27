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
	
	//jBPM Process Configuration
	private String registrationKIESession;
	private String registrationBPMProcessflowName;
	private String registrationBPMProcessflowFulllName;
	private String registrationPasswordRulesKSession;
	private List<SQEndPoint> endPoints = new ArrayList<SQEndPoint>();
	
	//RESTful End point configuration for GLOBAL SETUP. 
	//Fall back, in case if the properties are not available
	private String globalMediaTypeJson;
	private String globalOperationPost;
	
	//RESTful End point configuration for jBPM Workflow for New Registration
	private String registrationBPMWorkflow;
	
	//RESTful End point configuration for Password Rules (Registration Rules) with in New Registration jBPM workflow 
	//for New Registration
	private String registrationPasswordRulesflow;
	
	//RESTful End point configuration for Password Rules (Registration Rules) with in New Registration jBPM workflow 
	//for New Registration
	private String registrationPasswordRulesFailflow;
	
	//RESTful End point configuration for Password Rules (Registration Rules) with in New Registration jBPM workflow 
	//for New Registration
	private String registrationPasswordRulesSuccessflow;
	
	
	//RESTful End point configuration for Encryption (Encryption Fail) with in New Registration jBPM workflow 
	//for New Registration
	private String registrationEncryptionFailflow;
	
	
	//RESTful End point configuration to Persist User with in New Registration jBPM Workflow
	private String registrationPersistBPMWorkflow;
	
	//RESTful End point configuration for Persistence (Persistence Fail) with in New Registration jBPM workflow 
	//for New Registration
	private String registrationPersistenceFailflow;	
	
	
	//Work Item for Applying Registration Rules in Registration jBPM Process 
	private String wiApplyRegistrationRules;

	//Work Item for Processing Registration Rules Failed Request in Registration jBPM Process 
	private String wiProcessFailedRegistration;

	//Work Item for Encrypting Key Fields once Registration Rules Success in Registration jBPM Process 
	private String wiEncryptKeyFields;
	

	
	
	
	
	/** Registration Process Workflow NEXT STEPS configuration **/
	private String registrationRulesFailed;
	private String registrationRulesSuccess;
	private String registrationEncryptionFailed;
	private String registrationEncryptionSuccess;
	private String registrationPersistenceFailed;
	private String registrationPersistenceSuccess;
	private String registrationProcessFlowEnd;
	
	
	/** ESB End points configuration**/
	private String registrationRulesFailedNotification; 
	private String registrationEncryptionFailedNotification;
	private String registrationPersistenceFailedNotification;
	

	
	public SQBPMConfiguration(String registrationKIESession,
								String registrationBPMProcessflowName, 
								String registrationBPMProcessflowFulllName, 
								String registrationPasswordRulesKSession, 
								List<SQEndPoint> endPoints,
								String globalMediaTypeJson,
								String globalOperationPost,
								String registrationBPMWorkflow,
								String registrationPasswordRulesflow,
								String registrationPasswordRulesFailflow,
								String registrationPasswordRulesSuccessflow,
								String wiApplyRegistrationRules,
								String wiProcessFailedRegistration,
								String wiEncryptKeyFields,
								String registrationPersistBPMWorkflow,
								String registrationRulesFailed,
								String registrationRulesSuccess,
								String registrationEncryptionFailed,
								String registrationEncryptionSuccess,
								String registrationPersistenceFailed,
								String registrationPersistenceSuccess,
								String registrationProcessFlowEnd,
								String registrationRulesFailedNotification,
								String registrationEncryptionFailedNotification,
								String registrationPersistenceFailedNotification,
								String registrationEncryptionFailflow,
								String registrationPersistenceFailflow) {
		
		this.registrationBPMProcessflowFulllName 	= registrationBPMProcessflowFulllName;
		this.registrationBPMProcessflowName			= registrationBPMProcessflowName;
		this.registrationKIESession					= registrationKIESession;
		this.registrationPasswordRulesKSession 		= registrationPasswordRulesKSession; 
		this.endPoints								= endPoints;
		this.globalMediaTypeJson					= globalMediaTypeJson;
		this.globalOperationPost					= globalOperationPost;
		this.registrationBPMWorkflow				= registrationBPMWorkflow;
		this.registrationPasswordRulesflow			= registrationPasswordRulesflow;
		this.registrationPasswordRulesFailflow		= registrationPasswordRulesFailflow;
		this.registrationPasswordRulesSuccessflow	= registrationPasswordRulesSuccessflow;
		this.wiApplyRegistrationRules				= wiApplyRegistrationRules;
		this.wiEncryptKeyFields						= wiEncryptKeyFields;
		this.wiProcessFailedRegistration			= wiProcessFailedRegistration;
		this.registrationPersistBPMWorkflow			= registrationPersistBPMWorkflow;
		this.registrationRulesFailed				= registrationRulesFailed;
		this.registrationRulesSuccess				= registrationRulesSuccess;
		this.registrationEncryptionFailed			= registrationEncryptionFailed ;
		this.registrationEncryptionSuccess			= registrationEncryptionSuccess;
		this.registrationPersistenceFailed			= registrationPersistenceFailed;
		this.registrationPersistenceSuccess			= registrationPersistenceSuccess;
		this.registrationRulesFailedNotification	= registrationRulesFailedNotification; 
		this.registrationEncryptionFailedNotification	= registrationEncryptionFailedNotification;
		this.registrationPersistenceFailedNotification	= registrationPersistenceFailedNotification;
		this.registrationEncryptionFailflow			= registrationEncryptionFailflow;
		this.registrationPersistenceFailflow  		= registrationPersistenceFailflow;
		
		
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

	/**
	 * @return the registrationPasswordRulesFailflow
	 */
	public String getRegistrationPasswordRulesFailflow() {
		return registrationPasswordRulesFailflow;
	}

	/**
	 * @param registrationPasswordRulesFailflow the registrationPasswordRulesFailflow to set
	 */
	public void setRegistrationPasswordRulesFailflow(String registrationPasswordRulesFailflow) {
		this.registrationPasswordRulesFailflow = registrationPasswordRulesFailflow;
	}

	/**
	 * @return the registrationPasswordRulesSuccessflow
	 */
	public String getRegistrationPasswordRulesSuccessflow() {
		return registrationPasswordRulesSuccessflow;
	}

	/**
	 * @param registrationPasswordRulesSuccessflow the registrationPasswordRulesSuccessflow to set
	 */
	public void setRegistrationPasswordRulesSuccessflow(String registrationPasswordRulesSuccessflow) {
		this.registrationPasswordRulesSuccessflow = registrationPasswordRulesSuccessflow;
	}

	/**
	 * @return the wiApplyRegistrationRules
	 */
	public String getWiApplyRegistrationRules() {
		return wiApplyRegistrationRules;
	}

	/**
	 * @param wiApplyRegistrationRules the wiApplyRegistrationRules to set
	 */
	public void setWiApplyRegistrationRules(String wiApplyRegistrationRules) {
		this.wiApplyRegistrationRules = wiApplyRegistrationRules;
	}

	/**
	 * @return the wiProcessFailedRegistration
	 */
	public String getWiProcessFailedRegistration() {
		return wiProcessFailedRegistration;
	}

	/**
	 * @param wiProcessFailedRegistration the wiProcessFailedRegistration to set
	 */
	public void setWiProcessFailedRegistration(String wiProcessFailedRegistration) {
		this.wiProcessFailedRegistration = wiProcessFailedRegistration;
	}

	/**
	 * @return the wiEncryptKeyFields
	 */
	public String getWiEncryptKeyFields() {
		return wiEncryptKeyFields;
	}

	/**
	 * @param wiEncryptKeyFields the wiEncryptKeyFields to set
	 */
	public void setWiEncryptKeyFields(String wiEncryptKeyFields) {
		this.wiEncryptKeyFields = wiEncryptKeyFields;
	}

	/**
	 * @return the registrationPersistBPMWorkflow
	 */
	public String getRegistrationPersistBPMWorkflow() {
		return registrationPersistBPMWorkflow;
	}

	/**
	 * @param registrationPersistBPMWorkflow the registrationPersistBPMWorkflow to set
	 */
	public void setRegistrationPersistBPMWorkflow(String registrationPersistBPMWorkflow) {
		this.registrationPersistBPMWorkflow = registrationPersistBPMWorkflow;
	}

	/**
	 * @return the registrationRulesFailed
	 */
	public String getRegistrationRulesFailed() {
		return registrationRulesFailed;
	}

	/**
	 * @param registrationRulesFailed the registrationRulesFailed to set
	 */
	public void setRegistrationRulesFailed(String registrationRulesFailed) {
		this.registrationRulesFailed = registrationRulesFailed;
	}

	/**
	 * @return the registrationRulesSuccess
	 */
	public String getRegistrationRulesSuccess() {
		return registrationRulesSuccess;
	}

	/**
	 * @param registrationRulesSuccess the registrationRulesSuccess to set
	 */
	public void setRegistrationRulesSuccess(String registrationRulesSuccess) {
		this.registrationRulesSuccess = registrationRulesSuccess;
	}

	/**
	 * @return the registrationEncryptionFailed
	 */
	public String getRegistrationEncryptionFailed() {
		return registrationEncryptionFailed;
	}

	/**
	 * @param registrationEncryptionFailed the registrationEncryptionFailed to set
	 */
	public void setRegistrationEncryptionFailed(String registrationEncryptionFailed) {
		this.registrationEncryptionFailed = registrationEncryptionFailed;
	}

	/**
	 * @return the registrationEncryptionSuccess
	 */
	public String getRegistrationEncryptionSuccess() {
		return registrationEncryptionSuccess;
	}

	/**
	 * @param registrationEncryptionSuccess the registrationEncryptionSuccess to set
	 */
	public void setRegistrationEncryptionSuccess(String registrationEncryptionSuccess) {
		this.registrationEncryptionSuccess = registrationEncryptionSuccess;
	}

	/**
	 * @return the registrationPersistenceFailed
	 */
	public String getRegistrationPersistenceFailed() {
		return registrationPersistenceFailed;
	}

	/**
	 * @param registrationPersistenceFailed the registrationPersistenceFailed to set
	 */
	public void setRegistrationPersistenceFailed(String registrationPersistenceFailed) {
		this.registrationPersistenceFailed = registrationPersistenceFailed;
	}

	/**
	 * @return the registrationPersistenceSuccess
	 */
	public String getRegistrationPersistenceSuccess() {
		return registrationPersistenceSuccess;
	}

	/**
	 * @param registrationPersistenceSuccess the registrationPersistenceSuccess to set
	 */
	public void setRegistrationPersistenceSuccess(String registrationPersistenceSuccess) {
		this.registrationPersistenceSuccess = registrationPersistenceSuccess;
	}

	/**
	 * @return the registrationProcessFlowEnd
	 */
	public String getRegistrationProcessFlowEnd() {
		return registrationProcessFlowEnd;
	}

	/**
	 * @param registrationProcessFlowEnd the registrationProcessFlowEnd to set
	 */
	public void setRegistrationProcessFlowEnd(String registrationProcessFlowEnd) {
		this.registrationProcessFlowEnd = registrationProcessFlowEnd;
	}

	/**
	 * @return the registrationRulesFailedNotification
	 */
	public String getRegistrationRulesFailedNotification() {
		return registrationRulesFailedNotification;
	}

	/**
	 * @param registrationRulesFailedNotification the registrationRulesFailedNotification to set
	 */
	public void setRegistrationRulesFailedNotification(String registrationRulesFailedNotification) {
		this.registrationRulesFailedNotification = registrationRulesFailedNotification;
	}

	/**
	 * @return the registrationEncryptionFailedNotification
	 */
	public String getRegistrationEncryptionFailedNotification() {
		return registrationEncryptionFailedNotification;
	}

	/**
	 * @param registrationEncryptionFailedNotification the registrationEncryptionFailedNotification to set
	 */
	public void setRegistrationEncryptionFailedNotification(String registrationEncryptionFailedNotification) {
		this.registrationEncryptionFailedNotification = registrationEncryptionFailedNotification;
	}

	/**
	 * @return the registrationPersistenceFailedNotification
	 */
	public String getRegistrationPersistenceFailedNotification() {
		return registrationPersistenceFailedNotification;
	}

	/**
	 * @param registrationPersistenceFailedNotification the registrationPersistenceFailedNotification to set
	 */
	public void setRegistrationPersistenceFailedNotification(String registrationPersistenceFailedNotification) {
		this.registrationPersistenceFailedNotification = registrationPersistenceFailedNotification;
	}

	/**
	 * @return the registrationEncryptionFailflow
	 */
	public String getRegistrationEncryptionFailflow() {
		return registrationEncryptionFailflow;
	}

	/**
	 * @param registrationEncryptionFailflow the registrationEncryptionFailflow to set
	 */
	public void setRegistrationEncryptionFailflow(String registrationEncryptionFailflow) {
		this.registrationEncryptionFailflow = registrationEncryptionFailflow;
	}

	/**
	 * @return the registrationPersistenceFailflow
	 */
	public String getRegistrationPersistenceFailflow() {
		return registrationPersistenceFailflow;
	}

	/**
	 * @param registrationPersistenceFailflow the registrationPersistenceFailflow to set
	 */
	public void setRegistrationPersistenceFailflow(String registrationPersistenceFailflow) {
		this.registrationPersistenceFailflow = registrationPersistenceFailflow;
	}
	
	
	
	
	
	
}
