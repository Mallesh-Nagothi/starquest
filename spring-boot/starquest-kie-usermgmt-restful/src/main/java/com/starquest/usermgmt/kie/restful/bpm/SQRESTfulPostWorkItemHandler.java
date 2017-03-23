/**
 * 
 */
package com.starquest.usermgmt.kie.restful.bpm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.kie.api.cdi.KSession;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;
import org.kie.api.runtime.process.WorkflowProcessInstance;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.starquest.registration.config.SQEndPoint;
import com.starquest.usermgmt.kie.restful.config.SQBPMConfiguration;
import com.starquest.usermgmt.vo.UserVo;

import net.minidev.json.JSONObject;

/**
 * @author mallesh
 *
 */
public class SQRESTfulPostWorkItemHandler implements WorkItemHandler {
	
	
	
	//Password Rules (Registration) End points configuration
	private String registrationPasswordRulesflow;
	private String registrationPasswordRulesflowEndPoint;
	private HttpMethod registrationPasswordRulesflowHttpMethod;
	private MediaType registrationPasswordRulesflowMediaType;
	
	
	//Password Rules (Registration) Fail End points configuration
	private String registrationPasswordRulesFailflow;
	private String registrationPasswordRulesFailflowEndPoint;
	private HttpMethod registrationPasswordRulesFailflowHttpMethod;
	private MediaType registrationPasswordRulesFailflowMediaType;
		
		
	//Password Rules (Registration) Success End points configuration
	private String registrationPasswordRulesSuccessflow;
	private String registrationPasswordRulesSuccessflowEndPoint;
	private HttpMethod registrationPasswordRulesSuccessflowHttpMethod;
	private MediaType registrationPasswordRulesSuccessflowMediaType;
	
	//Work Item for Applying Registration Rules in Registration jBPM Process 
	private String wiApplyRegistrationRules;

	//Work Item for Processing Registration Rules Failed Request in Registration jBPM Process 
	private String wiProcessFailedRegistration;

	//Work Item for Encrypting Key Fields once Registration Rules Success in Registration jBPM Process 
	private String wiEncryptKeyFields;

	/**
	 * Constructor
	 */
	public SQRESTfulPostWorkItemHandler() {
		super();
	}
	
	/** Initialize Configuration **/
	private void initializeWorkItem(SQBPMConfiguration sqBpmConfig){
		System.out.println("=====================START SQRESTfulPostWorkItemHandler Post Constructor======================================");
		//Getting End Point Constants for each flow jBPM Process for Registration
		if(null!=sqBpmConfig.getRegistrationPasswordRulesflow()){
			this.registrationPasswordRulesflow = sqBpmConfig.getRegistrationPasswordRulesflow();
		}
		if(null!=sqBpmConfig.getRegistrationPasswordRulesFailflow()){
			this.registrationPasswordRulesFailflow = sqBpmConfig.getRegistrationPasswordRulesFailflow();
		}
		if(null!=sqBpmConfig.getRegistrationPasswordRulesSuccessflow()){
			this.registrationPasswordRulesSuccessflow = sqBpmConfig.getRegistrationPasswordRulesSuccessflow();
		}
		
		//Getting Work Item Names from Configuration
		if(null!=sqBpmConfig.getWiApplyRegistrationRules()){
			this.wiApplyRegistrationRules = sqBpmConfig.getWiApplyRegistrationRules();
		}
		if(null!=sqBpmConfig.getWiEncryptKeyFields()){
			this.wiEncryptKeyFields = sqBpmConfig.getWiEncryptKeyFields();
		}
		if(null!=sqBpmConfig.getWiProcessFailedRegistration()){
			this.wiProcessFailedRegistration = sqBpmConfig.getWiProcessFailedRegistration();
		}
		
		
		//Getting Configuration for each End Point for Each Work Item in the jBPM flow for Registration
		List<SQEndPoint> sqEndPoints = sqBpmConfig.getEndPoints();
		if(sqEndPoints.size()>0){
			for(SQEndPoint sqEndPoint: sqEndPoints){
				//Assigning Registration Rules End Points
				if(this.registrationPasswordRulesflow.equalsIgnoreCase(sqEndPoint.getEndPoint())){
					this.registrationPasswordRulesflowEndPoint	= sqEndPoint.getUrl();
					
					if(null!=sqEndPoint.getOperation() 
							&& sqEndPoint.getOperation().equalsIgnoreCase(sqBpmConfig.getGlobalOperationPost())){
						this.registrationPasswordRulesflowHttpMethod	= HttpMethod.POST;
					}
					if(null!=sqEndPoint.getMediaType() && 
							sqEndPoint.getMediaType().equalsIgnoreCase(sqBpmConfig.getGlobalMediaTypeJson())){
						this.registrationPasswordRulesflowMediaType	= MediaType.APPLICATION_JSON;
					}
					
				}
				
				//Assigning Registration Rules Fail End Points
				if(this.registrationPasswordRulesFailflow.equalsIgnoreCase(sqEndPoint.getEndPoint())){
					this.registrationPasswordRulesFailflowEndPoint	= sqEndPoint.getUrl();
					
					if(null!=sqEndPoint.getOperation() 
							&& sqEndPoint.getOperation().equalsIgnoreCase(sqBpmConfig.getGlobalOperationPost())){
						this.registrationPasswordRulesFailflowHttpMethod	= HttpMethod.POST;
					}
					if(null!=sqEndPoint.getMediaType() && 
							sqEndPoint.getMediaType().equalsIgnoreCase(sqBpmConfig.getGlobalMediaTypeJson())){
						this.registrationPasswordRulesFailflowMediaType	= MediaType.APPLICATION_JSON;
					}
					
				}
				
				//Assigning Registration Rules Success End Points
				if(this.registrationPasswordRulesSuccessflow.equalsIgnoreCase(sqEndPoint.getEndPoint())){
					this.registrationPasswordRulesSuccessflowEndPoint	= sqEndPoint.getUrl();
					
					if(null!=sqEndPoint.getOperation() 
							&& sqEndPoint.getOperation().equalsIgnoreCase(sqBpmConfig.getGlobalOperationPost())){
						this.registrationPasswordRulesSuccessflowHttpMethod	= HttpMethod.POST;
					}
					if(null!=sqEndPoint.getMediaType() && 
							sqEndPoint.getMediaType().equalsIgnoreCase(sqBpmConfig.getGlobalMediaTypeJson())){
						this.registrationPasswordRulesSuccessflowMediaType	= MediaType.APPLICATION_JSON;
					}
				}
			}
		}
		
		System.out.println("registrationPasswordRulesflow -->"+registrationPasswordRulesflow);
		System.out.println("registrationPasswordRulesflowEndPoint -->"+registrationPasswordRulesflowEndPoint);
		System.out.println("registrationPasswordRulesflowHttpMethod -->"+registrationPasswordRulesflowHttpMethod);
		System.out.println("registrationPasswordRulesflowMediaType -->"+registrationPasswordRulesflowMediaType);
		
		System.out.println("registrationPasswordRulesFailflow -->"+registrationPasswordRulesFailflow);
		System.out.println("registrationPasswordRulesFailflowEndPoint -->"+registrationPasswordRulesFailflowEndPoint);
		System.out.println("registrationPasswordRulesFailflowHttpMethod -->"+registrationPasswordRulesFailflowHttpMethod);
		System.out.println("registrationPasswordRulesFailflowMediaType -->"+registrationPasswordRulesFailflowMediaType);
		
		System.out.println("registrationPasswordRulesSuccessflow -->"+registrationPasswordRulesSuccessflow);
		System.out.println("registrationPasswordRulesSuccessflowEndPoint -->"+registrationPasswordRulesSuccessflow);
		System.out.println("registrationPasswordRulesSuccessflowHttpMethod -->"+registrationPasswordRulesSuccessflow);
		System.out.println("registrationPasswordRulesSuccessflowMediaType -->"+registrationPasswordRulesSuccessflowMediaType);
		
		System.out.println("wiApplyRegistrationRules -->"+wiApplyRegistrationRules);
		System.out.println("wiProcessFailedRegistration -->"+wiProcessFailedRegistration);
		System.out.println("wiEncryptKeyFields -->"+wiEncryptKeyFields);
		System.out.println("=====================END SQRESTfulPostWorkItemHandler Post Constructor======================================");
	}
	
	@Override
	public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
		
		
		System.out.println("Work Item: "+ workItem.getName() +", Work Item Id: "+ workItem.getId() + " Started::");
		Boolean isRegRulesPassed = false;
		 workItem.getResults().put("isRegRulesPassed", isRegRulesPassed);
	     manager.completeWorkItem(workItem.getId(), null);
		
		//Commented for DivergeTesting
		/*UserVo userVo =  new UserVo();
		
		//Retrieve json representation of UserVo and UserVo POJO
		JSONObject jsonRequest 					= (JSONObject) workItem.getParameter("PAYLOADJSON");
		UserVo bpmUserVo						= (UserVo) workItem.getParameter("USERVO");
		SQBPMConfiguration sqBpmConfig			= (SQBPMConfiguration) workItem.getParameter("SQBPMConfiguration");
		
		initializeWorkItem(sqBpmConfig);
		
		if(null!=jsonRequest.entrySet().iterator()){
			Iterator<Entry<String, Object>> itr = jsonRequest.entrySet().iterator();
			while(itr.hasNext()){
				Map.Entry<String,Object> entry = (Map.Entry<String, Object>) itr.next();
				System.out.println("Key  = " + entry.getKey() + " Value=" + entry.getValue());
			}
			
			// @TODO remove hard coding here use mediaType Parameter here -Mallesh
	        HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);  
			
			HttpEntity<String> entity = new HttpEntity<String>(jsonRequest.toString() ,httpHeaders);
			RestTemplate restTeamplate = new RestTemplate();
			
			//Start of Work Items
			//Process Registration Rules Work Item
			//if(workItem.getName().equalsIgnoreCase(getWiApplyRegistrationRules())){
			if(1==workItem.getId()){
				
				//@TODO remove HttpMethod.POST hard coding in the following line, use parameter - Mallesh 
				ResponseEntity<UserVo> respEntity = 
						restTeamplate.exchange(getRegistrationPasswordRulesflowEndPoint(),HttpMethod.POST, entity, UserVo.class);
				Boolean nextStep = new Boolean(false);
				//TODO Handle all other HTTP Responses
				if(respEntity.getStatusCode() == HttpStatus.OK){
					UserVo resultedUserVo = respEntity.getBody();
					userVo.setFailCategory(resultedUserVo.getFailCategory());
					userVo.setCategory(resultedUserVo.getCategory());
					if(resultedUserVo.getCategory()==UserVo.Category.REGISTRATION_SUCCESS){
						nextStep = true;
					}
				}
				
				Map<String, Object> results = new HashMap<String, Object>();
		        Map<String,Object> processedResults = new HashMap<String, Object>();
		        processedResults.put("USERVO", userVo);
		        processedResults.put("isRegRulesPassed", nextStep);
		        results.put("Result", processedResults);
		        results.put("isRegRulesPassed", nextStep);
		        System.out.println("Results Size="+results.size());
		        
		        workItem.getResults().put("isRegRulesPassed", new Boolean(false));
		        
				//manager.completeWorkItem(workItem.getId(), results);
		        manager.completeWorkItem(workItem.getId(), null);
				
				
			}//else if(workItem.getName().equalsIgnoreCase(getWiEncryptKeyFields())){
			else if(2==workItem.getId()){
				//@TODO remove HttpMethod.POST hard coding in the following line, use parameter - Mallesh 
				ResponseEntity<UserVo> respEntity = 
						restTeamplate.exchange(getRegistrationPasswordRulesFailflowEndPoint(),HttpMethod.POST, entity, UserVo.class);
				
				//TODO Handle all other HTTP Responses
				if(respEntity.getStatusCode() == HttpStatus.OK){
					UserVo resultedUserVo = respEntity.getBody();
					userVo.setFailCategory(resultedUserVo.getFailCategory());
					userVo.setCategory(resultedUserVo.getCategory());
				}
				
				Map<String, Object> results = new HashMap<String, Object>();
		        Map<String,Object> processedResults = new HashMap<String, Object>();
		        processedResults.put("USERVO", userVo);
		        results.put("Result", processedResults);
		        System.out.println("Results Size="+results.size());
		        
		        workItem.getResults().put("isRegRulesPassed", new Boolean(false));
		        manager.completeWorkItem(workItem.getId(), null);
				//manager.completeWorkItem(workItem.getId(), results);
				
				
			}//else if(workItem.getName().equalsIgnoreCase(getWiProcessFailedRegistration())){
			else if(3==workItem.getId()){
				
				//@TODO remove HttpMethod.POST hard coding in the following line, use parameter - Mallesh 
				ResponseEntity<UserVo> respEntity = 
						restTeamplate.exchange(getRegistrationPasswordRulesSuccessflowEndPoint(),HttpMethod.POST, entity, UserVo.class);
				
				//TODO Handle all other HTTP Responses
				if(respEntity.getStatusCode() == HttpStatus.OK){
					UserVo resultedUserVo = respEntity.getBody();
					userVo.setFailCategory(resultedUserVo.getFailCategory());
					userVo.setCategory(resultedUserVo.getCategory());
				}
				
				Map<String, Object> results = new HashMap<String, Object>();
		        Map<String,Object> processedResults = new HashMap<String, Object>();
		        processedResults.put("USERVO", userVo);
		        results.put("Result", processedResults);
		        System.out.println("Results Size="+results.size());
		        workItem.getResults().put("isRegRulesPassed", new Boolean(true));
		        manager.completeWorkItem(workItem.getId(), null);
		        
		        //manager.completeWorkItem(workItem.getId(), results);
				
			}else{
				System.out.println("Work Item : "+workItem.getId()+ " Not configured Yet!!");
			}
			
			
			
			  
			//End of Work Items
			
			
		}else{
			System.out.println("Invalid JSON Payload Received.......");
		}
		*/
		//Enmd of comments for testing Diverge
		
		//Not requried
		/*SQRESTfulEndPoints sqRESTfulEndPoints 	= (SQRESTfulEndPoints) workItem.getParameter("SQRESTfulEndPoints");
		List<SQEndPoint> sqEndPoints;
		if(null!=sqRESTfulEndPoints.getSqEndPoints() && 
				!sqRESTfulEndPoints.getSqEndPoints().isEmpty()){
			
			sqEndPoints = sqRESTfulEndPoints.getSqEndPoints();
			for(SQEndPoint sqEndPoint: sqEndPoints){
				
			}
		
		
		}else{
			System.out.println("Invalid Configuration!! Unable to start jBPM Work Item:: WorkItem Name: " + workItem.getName() +
					"WorkItem Id: " + workItem.getId() + " in Process Instance Id: "+ workItem.getProcessInstanceId());
		}*/
		
		
		
		
		System.out.println("Work Item: "+ workItem.getName() +", Work Item Id: "+ workItem.getId() + " End::");
		
	}
	
	/* (non-Javadoc)
	 * @see org.kie.api.runtime.process.WorkItemHandler#executeWorkItem(org.kie.api.runtime.process.WorkItem, org.kie.api.runtime.process.WorkItemManager)
	 */
	/*@Override
	public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {

		String uri 				= (String) workItem.getParameter("URL");
		HttpMethod operation	= (HttpMethod) workItem.getParameter("OPERATION");
		MediaType mediaType 	= (MediaType) workItem.getParameter("MEDIATYPE");
		JSONObject jsonRequest 	= (JSONObject) workItem.getParameter("PAYLOADJSON");
		UserVo bpmUserVo			= (UserVo) workItem.getParameter("USERVO");
		
		
		System.out.println("WorkItem.getId() -->"+workItem.getId());
		System.out.println("WorkItem.getProcessInstanceId-->"+workItem.getProcessInstanceId());
		
		//System.out.println("URL="+uri+" operation="+operation+" mediaType="+mediaType);
		Iterator<Entry<String, Object>> itr = jsonRequest.entrySet().iterator();
		while(itr.hasNext()){
			Map.Entry<String,Object> entry = (Map.Entry<String, Object>) itr.next();
			System.out.println("Key  = " + entry.getKey() + " Value=" + entry.getValue());
		}
		
		// Notify manager that work item has been completed and return results
		UserVo userVo =  new UserVo();
        Map<String, Object> results = new HashMap<String, Object>();
        Map<String,Object> processedResults = new HashMap<String, Object>();
		
		// @TODO remove hard coding here use mediaType Parameter here -Mallesh
        HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);  
		
		HttpEntity<String> entity = new HttpEntity<String>(jsonRequest.toString() ,httpHeaders);
		
		RestTemplate restTeamplate = new RestTemplate();
		
		//@TODO remove HttpMethod.POST hardcoding in the following line, use parameter - Mallesh 
		ResponseEntity<UserVo> respEntity = 
				restTeamplate.exchange(uri,HttpMethod.POST, entity, UserVo.class);
		
		if(respEntity.getStatusCode() == HttpStatus.OK){
			UserVo filteredNewUserProfile = respEntity.getBody();
			userVo.setFailCategory(filteredNewUserProfile.getFailCategory());
			userVo.setCategory(filteredNewUserProfile.getCategory());
		}
		
		
		processedResults.put("USERVO", userVo);
        results.put("Result", processedResults);
        System.out.println("Results Size="+results.size());
		manager.completeWorkItem(workItem.getId(), results);  
		System.out.println("Handler Completed..........................");
		
	}*/

	/* (non-Javadoc)
	 * @see org.kie.api.runtime.process.WorkItemHandler#abortWorkItem(org.kie.api.runtime.process.WorkItem, org.kie.api.runtime.process.WorkItemManager)
	 */
	@Override
	public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
		// TODO Auto-generated method stub

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
	 * @return the registrationPasswordRulesflowEndPoint
	 */
	public String getRegistrationPasswordRulesflowEndPoint() {
		return registrationPasswordRulesflowEndPoint;
	}

	/**
	 * @param registrationPasswordRulesflowEndPoint the registrationPasswordRulesflowEndPoint to set
	 */
	public void setRegistrationPasswordRulesflowEndPoint(String registrationPasswordRulesflowEndPoint) {
		this.registrationPasswordRulesflowEndPoint = registrationPasswordRulesflowEndPoint;
	}

	/**
	 * @return the registrationPasswordRulesflowHttpMethod
	 */
	public HttpMethod getRegistrationPasswordRulesflowHttpMethod() {
		return registrationPasswordRulesflowHttpMethod;
	}

	/**
	 * @param registrationPasswordRulesflowHttpMethod the registrationPasswordRulesflowHttpMethod to set
	 */
	public void setRegistrationPasswordRulesflowHttpMethod(HttpMethod registrationPasswordRulesflowHttpMethod) {
		this.registrationPasswordRulesflowHttpMethod = registrationPasswordRulesflowHttpMethod;
	}

	/**
	 * @return the registrationPasswordRulesflowMediaType
	 */
	public MediaType getRegistrationPasswordRulesflowMediaType() {
		return registrationPasswordRulesflowMediaType;
	}

	/**
	 * @param registrationPasswordRulesflowMediaType the registrationPasswordRulesflowMediaType to set
	 */
	public void setRegistrationPasswordRulesflowMediaType(MediaType registrationPasswordRulesflowMediaType) {
		this.registrationPasswordRulesflowMediaType = registrationPasswordRulesflowMediaType;
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
	 * @return the registrationPasswordRulesFailflowEndPoint
	 */
	public String getRegistrationPasswordRulesFailflowEndPoint() {
		return registrationPasswordRulesFailflowEndPoint;
	}

	/**
	 * @param registrationPasswordRulesFailflowEndPoint the registrationPasswordRulesFailflowEndPoint to set
	 */
	public void setRegistrationPasswordRulesFailflowEndPoint(String registrationPasswordRulesFailflowEndPoint) {
		this.registrationPasswordRulesFailflowEndPoint = registrationPasswordRulesFailflowEndPoint;
	}

	/**
	 * @return the registrationPasswordRulesFailflowHttpMethod
	 */
	public HttpMethod getRegistrationPasswordRulesFailflowHttpMethod() {
		return registrationPasswordRulesFailflowHttpMethod;
	}

	/**
	 * @param registrationPasswordRulesFailflowHttpMethod the registrationPasswordRulesFailflowHttpMethod to set
	 */
	public void setRegistrationPasswordRulesFailflowHttpMethod(HttpMethod registrationPasswordRulesFailflowHttpMethod) {
		this.registrationPasswordRulesFailflowHttpMethod = registrationPasswordRulesFailflowHttpMethod;
	}

	/**
	 * @return the registrationPasswordRulesFailflowMediaType
	 */
	public MediaType getRegistrationPasswordRulesFailflowMediaType() {
		return registrationPasswordRulesFailflowMediaType;
	}

	/**
	 * @param registrationPasswordRulesFailflowMediaType the registrationPasswordRulesFailflowMediaType to set
	 */
	public void setRegistrationPasswordRulesFailflowMediaType(MediaType registrationPasswordRulesFailflowMediaType) {
		this.registrationPasswordRulesFailflowMediaType = registrationPasswordRulesFailflowMediaType;
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
	 * @return the registrationPasswordRulesSuccessflowEndPoint
	 */
	public String getRegistrationPasswordRulesSuccessflowEndPoint() {
		return registrationPasswordRulesSuccessflowEndPoint;
	}

	/**
	 * @param registrationPasswordRulesSuccessflowEndPoint the registrationPasswordRulesSuccessflowEndPoint to set
	 */
	public void setRegistrationPasswordRulesSuccessflowEndPoint(String registrationPasswordRulesSuccessflowEndPoint) {
		this.registrationPasswordRulesSuccessflowEndPoint = registrationPasswordRulesSuccessflowEndPoint;
	}

	/**
	 * @return the registrationPasswordRulesSuccessflowHttpMethod
	 */
	public HttpMethod getRegistrationPasswordRulesSuccessflowHttpMethod() {
		return registrationPasswordRulesSuccessflowHttpMethod;
	}

	/**
	 * @param registrationPasswordRulesSuccessflowHttpMethod the registrationPasswordRulesSuccessflowHttpMethod to set
	 */
	public void setRegistrationPasswordRulesSuccessflowHttpMethod(
			HttpMethod registrationPasswordRulesSuccessflowHttpMethod) {
		this.registrationPasswordRulesSuccessflowHttpMethod = registrationPasswordRulesSuccessflowHttpMethod;
	}

	/**
	 * @return the registrationPasswordRulesSuccessflowMediaType
	 */
	public MediaType getRegistrationPasswordRulesSuccessflowMediaType() {
		return registrationPasswordRulesSuccessflowMediaType;
	}

	/**
	 * @param registrationPasswordRulesSuccessflowMediaType the registrationPasswordRulesSuccessflowMediaType to set
	 */
	public void setRegistrationPasswordRulesSuccessflowMediaType(MediaType registrationPasswordRulesSuccessflowMediaType) {
		this.registrationPasswordRulesSuccessflowMediaType = registrationPasswordRulesSuccessflowMediaType;
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

}
