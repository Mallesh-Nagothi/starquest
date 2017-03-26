/**
 * 
 */
package com.starquest.usermgmt.kie.restful.bpm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import com.starquest.usermgmt.kie.restful.utils.Utilities;
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
	
	
	//User Persit (Registration) End Points Configuration
	private String registrationPersistBPMWorkflow;
	private String registrationPersistBPMWorkflowEndPoint;
	private HttpMethod registrationPersistBPMWorkflowHttpMethod;
	private MediaType registrationPersistBPMWorkflowMediaType;
	
	
	//Work Item for Applying Registration Rules in Registration jBPM Process 
	private String wiApplyRegistrationRules;

	//Work Item for Processing Registration Rules Failed Request in Registration jBPM Process 
	private String wiProcessFailedRegistration;

	//Work Item for Encrypting Key Fields once Registration Rules Success in Registration jBPM Process 
	private String wiEncryptKeyFields;

	/** Registration Process Workflow Steps configuration **/
	private String registrationRulesFailed;
	private String registrationRulesSuccess;
	private String registrationEncryptionFailed;
	private String registrationEncryptionSuccess;
	private String registrationPersistenceFailed;
	private String registrationPersistenceSuccess;
	private String registrationProcessFlowEnd;
	
	/**
	 * Constructor
	 */
	public SQRESTfulPostWorkItemHandler() {
		super();
	}
	
	/** Initialize Configuration **/
	private void initializeWorkItem(SQBPMConfiguration sqBpmConfig){
		/*System.out.println("=====================START SQRESTfulPostWorkItemHandler Post Constructor======================================");*/
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
		if(null!=sqBpmConfig.getRegistrationPersistBPMWorkflow()){
			this.registrationPersistBPMWorkflow = sqBpmConfig.getRegistrationPersistBPMWorkflow();
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
		
		/** Registration Process Workflow Steps configuration **/
		//Rules Gateway
		if(null!=sqBpmConfig.getRegistrationRulesFailed()){
			this.registrationRulesFailed = sqBpmConfig.getRegistrationRulesFailed();
		}
		if(null!=sqBpmConfig.getRegistrationRulesSuccess()){
			this.registrationRulesSuccess = sqBpmConfig.getRegistrationRulesSuccess();
		}
		//Encryption Gateway		
		if(null!=sqBpmConfig.getRegistrationEncryptionFailed()){
			this.registrationEncryptionFailed = sqBpmConfig.getRegistrationEncryptionFailed();
		}
		if(null!=sqBpmConfig.getRegistrationEncryptionSuccess()){
			this.registrationEncryptionSuccess = sqBpmConfig.getRegistrationEncryptionSuccess();
		}
		//Persistence Gateway		
		if(null!=sqBpmConfig.getRegistrationPersistenceFailed()){
			this.registrationPersistenceFailed = sqBpmConfig.getRegistrationPersistenceFailed();
		}
		if(null!=sqBpmConfig.getRegistrationPersistenceSuccess()){
			this.registrationPersistenceSuccess = sqBpmConfig.getRegistrationPersistenceSuccess();
		}
		//End of Workflow Process		
		if(null!=sqBpmConfig.getRegistrationProcessFlowEnd()){
			this.registrationProcessFlowEnd = sqBpmConfig.getRegistrationProcessFlowEnd();
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
				
				//Assigning Registration Persistence End Points
				if(this.registrationPersistBPMWorkflow.equalsIgnoreCase(sqEndPoint.getEndPoint())){
					this.registrationPersistBPMWorkflowEndPoint	= sqEndPoint.getUrl();
					
					if(null!=sqEndPoint.getOperation() 
							&& sqEndPoint.getOperation().equalsIgnoreCase(sqBpmConfig.getGlobalOperationPost())){
						this.registrationPersistBPMWorkflowHttpMethod	= HttpMethod.POST;
					}
					if(null!=sqEndPoint.getMediaType() && 
							sqEndPoint.getMediaType().equalsIgnoreCase(sqBpmConfig.getGlobalMediaTypeJson())){
						this.registrationPersistBPMWorkflowMediaType	= MediaType.APPLICATION_JSON;
					}
				}
				
				
				
				
				
			}
		}
		
		/*
		System.out.println("registrationPasswordRulesflow -->"+registrationPasswordRulesflow);
		System.out.println("registrationPasswordRulesflowEndPoint -->"+registrationPasswordRulesflowEndPoint);
		System.out.println("registrationPasswordRulesflowHttpMethod -->"+registrationPasswordRulesflowHttpMethod);
		System.out.println("registrationPasswordRulesflowMediaType -->"+registrationPasswordRulesflowMediaType);
		
		System.out.println("registrationPasswordRulesFailflow -->"+registrationPasswordRulesFailflow);
		System.out.println("registrationPasswordRulesFailflowEndPoint -->"+registrationPasswordRulesFailflowEndPoint);
		System.out.println("registrationPasswordRulesFailflowHttpMethod -->"+registrationPasswordRulesFailflowHttpMethod);
		System.out.println("registrationPasswordRulesFailflowMediaType -->"+registrationPasswordRulesFailflowMediaType);
		
		System.out.println("registrationPasswordRulesSuccessflow -->"+registrationPasswordRulesSuccessflow);
		System.out.println("registrationPasswordRulesSuccessflowEndPoint -->"+registrationPasswordRulesSuccessflowEndPoint);
		System.out.println("registrationPasswordRulesSuccessflowHttpMethod -->"+registrationPasswordRulesSuccessflowHttpMethod);
		System.out.println("registrationPasswordRulesSuccessflowMediaType -->"+registrationPasswordRulesSuccessflowMediaType);
		
		System.out.println("wiApplyRegistrationRules -->"+wiApplyRegistrationRules);
		System.out.println("wiProcessFailedRegistration -->"+wiProcessFailedRegistration);
		System.out.println("wiEncryptKeyFields -->"+wiEncryptKeyFields);
		*/
		
		/*System.out.println("registrationPersistBPMWorkflow -->"+registrationPersistBPMWorkflow);
		System.out.println("registrationPersistBPMWorkflowEndPoint -->"+registrationPersistBPMWorkflowEndPoint);
		System.out.println("registrationPersistBPMWorkflowHttpMethod -->"+registrationPersistBPMWorkflowHttpMethod);
		System.out.println("registrationPersistBPMWorkflowMediaType -->"+registrationPersistBPMWorkflowMediaType);
		System.out.println("=====================END SQRESTfulPostWorkItemHandler Post Constructor======================================");*/
	}
	
	@Override
	public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
		
		
		System.out.println("Work Item: "+ workItem.getName() +", Work Item Id: "+ workItem.getId() + " Started::");
		//Commented for DivergeTesting
		UserVo userVo =  new UserVo();
		Utilities utils = new Utilities();
		
		//Retrieve json representation of UserVo and UserVo POJO
		JSONObject jsonRequest = (JSONObject) workItem.getParameter("PAYLOADJSON");
		
		UserVo bpmUserVo = (UserVo) workItem.getParameter("USERVO");
		try{
			jsonRequest  = utils.convertPojoToJSONObj(bpmUserVo);
		}catch(Exception ex){
			System.out.println("Something went wrong while convering POJO to JSON");
		}
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
				System.out.println("WORK ITEM 1 STARTED");
				
				//@TODO remove HttpMethod.POST hard coding in the following line, use parameter - Mallesh 
				ResponseEntity<UserVo> respEntity = 
						restTeamplate.exchange(getRegistrationPasswordRulesflowEndPoint(),HttpMethod.POST, entity, UserVo.class);
				Boolean nextStep = new Boolean(false);
				//TODO Handle all other HTTP Responses
				if(respEntity.getStatusCode() == HttpStatus.OK){
					UserVo resultedUserVo = respEntity.getBody();
					bpmUserVo.setFailCategory(resultedUserVo.getFailCategory());
					bpmUserVo.setCategory(resultedUserVo.getCategory());
					if(resultedUserVo.getCategory()==UserVo.Category.REGISTRATION_SUCCESS){
						nextStep = true;
					}
				}
				
				Map<String, Object> results = new HashMap<String, Object>();
		        Map<String,Object> processedResults = new HashMap<String, Object>();
		        processedResults.put("USERVO", bpmUserVo);
		        
		        //Process Workflow control logic. Very bad but until i get clarity on how jBPM handles WorkItem.Id, 
		        //for now i'm not happy with jBPM Work Item handling
		        String workFlowStep = sqBpmConfig.getRegistrationRulesFailed();
		        if(nextStep){
		        	workFlowStep = sqBpmConfig.getRegistrationRulesSuccess();
		        }
		        processedResults.put("NEXTSTEP", workFlowStep);
		        results.put("Result", processedResults);
		        results.put("USERVO", bpmUserVo);
		        try{ 
		        	results.put("PAYLOADJSON", utils.convertPojoToJSONObj(bpmUserVo)); 
		        }catch(Exception ex){ 
		        	System.out.println(ex); 
		        }
		        results.put("isRegRulesPassed", nextStep);
		        results.put("isEncryptionSuccess", new Boolean(false));
		        results.put("isPersistenceSuccess",new Boolean(false));
		        results.put("NEXTSTEP",workFlowStep);
		        
		        manager.completeWorkItem(workItem.getId(), results);
		        System.out.println("WORK ITEM 1 END");
			}
			
			if(workItem.getId()>1){
				String nextStepInfo 	= (String) workItem.getParameter("NEXTSTEP");
				
				if(nextStepInfo!=null){
					if(nextStepInfo.equalsIgnoreCase(sqBpmConfig.getRegistrationRulesSuccess())){
						
						HttpEntity<String> entity2 = new HttpEntity<String>(jsonRequest.toString() ,httpHeaders);
						
						//@TODO remove HttpMethod.POST hard coding in the following line, use parameter - Mallesh 
						ResponseEntity<UserVo> respEntity = 
								restTeamplate.exchange(getRegistrationPasswordRulesSuccessflowEndPoint(),HttpMethod.POST, entity2, UserVo.class);
						Boolean nextStep = new Boolean(false);
						
						//TODO Handle all other HTTP Responses
						if(respEntity.getStatusCode() == HttpStatus.OK){
							UserVo encryptedUserVo = respEntity.getBody();
							bpmUserVo.setPassword(encryptedUserVo.getPassword());
							nextStep = true;
						}
						
						Map<String, Object> results = new HashMap<String, Object>();
				        Map<String,Object> processedResults = new HashMap<String, Object>();
				        processedResults.put("USERVO", bpmUserVo);
				        
				        //Process Workflow control logic. Very bad but until i get clarity on how jBPM handles WorkItem.Id, 
				        //for now i'm not happy with jBPM Work Item handling
				        String workFlowStep = sqBpmConfig.getRegistrationEncryptionFailed();
				        if(nextStep){
				        	workFlowStep = sqBpmConfig.getRegistrationEncryptionSuccess();
				        }
				        processedResults.put("NEXTSTEP", workFlowStep);
				        results.put("Result", processedResults);
				        results.put("USERVO", bpmUserVo);
				        try{ 
				        	results.put("PAYLOADJSON", utils.convertPojoToJSONObj(bpmUserVo)); 
				        }catch(Exception ex){ 
				        	System.out.println(ex); 
				        }
				        results.put("isRegRulesPassed", new Boolean(true));
				        results.put("isEncryptionSuccess", nextStep );
				        results.put("isPersistenceSuccess",new Boolean(false));
				        results.put("NEXTSTEP",workFlowStep);
				        manager.completeWorkItem(workItem.getId(), results);
				        System.out.println("WORK ITEM "+ workItem.getId() +" [Registration Rules Success Process END");
				        
						
					}else if(nextStepInfo.equalsIgnoreCase(sqBpmConfig.getRegistrationRulesFailed())){
						
						HttpEntity<String> entity2 = new HttpEntity<String>(jsonRequest.toString() ,httpHeaders);
						//@TODO remove HttpMethod.POST hard coding in the following line, use parameter - Mallesh 
						ResponseEntity<UserVo> respEntity = 
								restTeamplate.exchange(getRegistrationPasswordRulesFailflowEndPoint(),HttpMethod.POST, entity2, UserVo.class);
						Boolean nextStep = new Boolean(false);
						
						//TODO Handle all other HTTP Responses
						if(respEntity.getStatusCode() == HttpStatus.OK){
							nextStep = true; //future use this flag to convey calling service that failed request is processed successfully
						}
						
						Map<String, Object> results = new HashMap<String, Object>();
				        Map<String,Object> processedResults = new HashMap<String, Object>();
				        processedResults.put("USERVO", bpmUserVo);
				        
				        processedResults.put("NEXTSTEP", sqBpmConfig.getRegistrationProcessFlowEnd());
				        results.put("Result", processedResults);
				        results.put("USERVO", bpmUserVo);
				        try{ 
				        	results.put("PAYLOADJSON", utils.convertPojoToJSONObj(bpmUserVo)); 
				        }catch(Exception ex){ 
				        	System.out.println(ex); 
				        }
				        results.put("isRegRulesPassed", new Boolean(false));
				        results.put("isEncryptionSuccess", new Boolean(false) );
				        results.put("isPersistenceSuccess",new Boolean(false));
				        manager.completeWorkItem(workItem.getId(), results);
				        results.put("NEXTSTEP",sqBpmConfig.getRegistrationProcessFlowEnd());
				        System.out.println("WORK ITEM "+ workItem.getId() +" [Process Registration Failures Process END");
						
					}else if(nextStepInfo.equalsIgnoreCase(sqBpmConfig.getRegistrationEncryptionFailed())){
						System.out.println("WORK ITEM "+ workItem.getId() +" [Process Encryption Failures Process START");
						
						Map<String, Object> results = new HashMap<String, Object>();
				        Map<String,Object> processedResults = new HashMap<String, Object>();
				        processedResults.put("USERVO", bpmUserVo);
				        processedResults.put("NEXTSTEP", sqBpmConfig.getRegistrationProcessFlowEnd()); //End the Flow
				        results.put("Result", processedResults);
				        results.put("USERVO", bpmUserVo);
				        try{ 
				        	results.put("PAYLOADJSON", utils.convertPojoToJSONObj(bpmUserVo)); 
				        }catch(Exception ex){ 
				        	System.out.println(ex); 
				        }
				        results.put("isRegRulesPassed", new Boolean(true));
				        results.put("isEncryptionSuccess", new Boolean(false) );
				        results.put("isPersistenceSuccess",new Boolean(false));
				        results.put("NEXTSTEP",sqBpmConfig.getRegistrationProcessFlowEnd());
				        manager.completeWorkItem(workItem.getId(), results);
				        
						System.out.println("WORK ITEM "+ workItem.getId() +" [Process Encryption Failures Process END");
						
					}else if(nextStepInfo.equalsIgnoreCase(sqBpmConfig.getRegistrationEncryptionSuccess())){
						
						System.out.println("WORK ITEM "+ workItem.getId() +" [Process Encryption Success Process START");
						
						HttpEntity<String> entity2 = new HttpEntity<String>(jsonRequest.toString() ,httpHeaders);
						
						//@TODO remove HttpMethod.POST hard coding in the following line, use parameter - Mallesh 
						ResponseEntity<UserVo> respEntity = 
								restTeamplate.exchange(getRegistrationPersistBPMWorkflowEndPoint(),HttpMethod.POST, entity2, UserVo.class);
						Boolean nextStep = new Boolean(false);
						
						//TODO Handle all other HTTP Responses
						if(respEntity.getStatusCode() == HttpStatus.OK){
							UserVo encryptedUserVo = respEntity.getBody();
							bpmUserVo.setCreatedBy(encryptedUserVo.getCreatedBy());
							bpmUserVo.setCreatedOn(encryptedUserVo.getCreatedOn());
							bpmUserVo.setId(encryptedUserVo.getId());
							bpmUserVo.setUserId(encryptedUserVo.getUserId());
							nextStep = true;
						}
						
						Map<String, Object> results = new HashMap<String, Object>();
				        Map<String,Object> processedResults = new HashMap<String, Object>();
				        processedResults.put("USERVO", bpmUserVo);
				        
				        //Process Workflow control logic. Very bad but until i get clarity on how jBPM handles WorkItem.Id, 
				        //for now i'm not happy with jBPM Work Item handling
				        String workFlowStep = sqBpmConfig.getRegistrationPersistenceFailed();
				        if(nextStep){
				        	workFlowStep = sqBpmConfig.getRegistrationPersistenceSuccess();
				        }
				        processedResults.put("NEXTSTEP", workFlowStep);
				        results.put("Result", processedResults);
				        results.put("USERVO", bpmUserVo);
				        try{ 
				        	results.put("PAYLOADJSON", utils.convertPojoToJSONObj(bpmUserVo)); 
				        }catch(Exception ex){ 
				        	System.out.println(ex); 
				        }
				        results.put("isRegRulesPassed", new Boolean(false));
				        results.put("isEncryptionSuccess", new Boolean(false) );
				        results.put("isPersistenceSuccess", nextStep);
				        results.put("NEXTSTEP",workFlowStep);
				        manager.completeWorkItem(workItem.getId(), results);
				        System.out.println("WORK ITEM "+ workItem.getId() +" [Process Encryption Success Process END");
						
					}else if(nextStepInfo.equalsIgnoreCase(sqBpmConfig.getRegistrationPersistenceFailed())){
						
						System.out.println("WORK ITEM "+ workItem.getId() +" [Process Persistence Failures Process START");
						
						Map<String, Object> results = new HashMap<String, Object>();
				        Map<String,Object> processedResults = new HashMap<String, Object>();
				        processedResults.put("USERVO", bpmUserVo);
				        processedResults.put("NEXTSTEP", sqBpmConfig.getRegistrationProcessFlowEnd()); //End the Flow
				        results.put("Result", processedResults);
				        results.put("USERVO", bpmUserVo);
				        try{ 
				        	results.put("PAYLOADJSON", utils.convertPojoToJSONObj(bpmUserVo)); 
				        }catch(Exception ex){ 
				        	System.out.println(ex); 
				        }
				        results.put("isRegRulesPassed", new Boolean(true));
				        results.put("isEncryptionSuccess", new Boolean(false) );
				        results.put("isPersistenceSuccess",new Boolean(false));
				        results.put("NEXTSTEP",sqBpmConfig.getRegistrationProcessFlowEnd());
				        manager.completeWorkItem(workItem.getId(), results);
				        
						System.out.println("WORK ITEM "+ workItem.getId() +" [Process Persistence Failures Process END");
						
					}else if(nextStepInfo.equalsIgnoreCase(sqBpmConfig.getRegistrationPersistenceSuccess())){
						
						System.out.println("WORK ITEM "+ workItem.getId() +" [Process Persistence Success Process START");
						
						Map<String, Object> results = new HashMap<String, Object>();
				        Map<String,Object> processedResults = new HashMap<String, Object>();
				        processedResults.put("USERVO", bpmUserVo);
				        processedResults.put("NEXTSTEP", sqBpmConfig.getRegistrationProcessFlowEnd()); //End the Flow
				        results.put("Result", processedResults);
				        results.put("USERVO", bpmUserVo);
				        try{ 
				        	results.put("PAYLOADJSON", utils.convertPojoToJSONObj(bpmUserVo)); 
				        }catch(Exception ex){ 
				        	System.out.println(ex); 
				        }
				        results.put("isRegRulesPassed", new Boolean(true));
				        results.put("isEncryptionSuccess", new Boolean(true) );
				        results.put("isPersistenceSuccess",new Boolean(true));
				        results.put("NEXTSTEP",sqBpmConfig.getRegistrationProcessFlowEnd());
				        manager.completeWorkItem(workItem.getId(), results);
				        
						System.out.println("WORK ITEM "+ workItem.getId() +" [Process Persistence Success Process END");
						
					}else if(nextStepInfo.equalsIgnoreCase(sqBpmConfig.getRegistrationProcessFlowEnd())){
						System.out.println("::Registration Process End::");
					}else{
						System.out.println("Somethign Screwed up in NEXTSTEP of jBPM Workflow...[00]");
					}
				}else{
					System.out.println("Something went wrong in jBPM Workflow......");
				}
				
			}//jBPM Workflow > Step 1 End if
		}else{
			System.out.println("Invalid JSON Payload Received.......");
		}
		System.out.println("Work Item: "+ workItem.getName() +", Work Item Id: "+ workItem.getId() + " End::");
	}
	

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
	 * @return the registrationPersistBPMWorkflowEndPoint
	 */
	public String getRegistrationPersistBPMWorkflowEndPoint() {
		return registrationPersistBPMWorkflowEndPoint;
	}

	/**
	 * @param registrationPersistBPMWorkflowEndPoint the registrationPersistBPMWorkflowEndPoint to set
	 */
	public void setRegistrationPersistBPMWorkflowEndPoint(String registrationPersistBPMWorkflowEndPoint) {
		this.registrationPersistBPMWorkflowEndPoint = registrationPersistBPMWorkflowEndPoint;
	}

	/**
	 * @return the registrationPersistBPMWorkflowHttpMethod
	 */
	public HttpMethod getRegistrationPersistBPMWorkflowHttpMethod() {
		return registrationPersistBPMWorkflowHttpMethod;
	}

	/**
	 * @param registrationPersistBPMWorkflowHttpMethod the registrationPersistBPMWorkflowHttpMethod to set
	 */
	public void setRegistrationPersistBPMWorkflowHttpMethod(HttpMethod registrationPersistBPMWorkflowHttpMethod) {
		this.registrationPersistBPMWorkflowHttpMethod = registrationPersistBPMWorkflowHttpMethod;
	}

	/**
	 * @return the registrationPersistBPMWorkflowMediaType
	 */
	public MediaType getRegistrationPersistBPMWorkflowMediaType() {
		return registrationPersistBPMWorkflowMediaType;
	}

	/**
	 * @param registrationPersistBPMWorkflowMediaType the registrationPersistBPMWorkflowMediaType to set
	 */
	public void setRegistrationPersistBPMWorkflowMediaType(MediaType registrationPersistBPMWorkflowMediaType) {
		this.registrationPersistBPMWorkflowMediaType = registrationPersistBPMWorkflowMediaType;
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

}
