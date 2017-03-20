/**
 * 
 */
package com.starquest.usermgmt.kie.restful.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.runtime.process.WorkflowProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.starquest.registration.config.SQEndPoint;
import com.starquest.usermgmt.kie.restful.bpm.SQRESTfulPostWorkItemHandler;
import com.starquest.usermgmt.kie.restful.config.SQBPMConfiguration;
import com.starquest.usermgmt.kie.restful.utils.Utilities;
import com.starquest.usermgmt.vo.UserVo;

import net.minidev.json.JSONObject;

/**
 * @author mallesh
 *
 */
@Service
public class UserRegistrationKieServiceImpl implements UserRegistrationKieService {

	private static final Logger logger = Logger.getLogger(UserRegistrationKieServiceImpl.class);
	private final KieContainer  kieContainer;
	
	@Autowired
	private SQBPMConfiguration	sqBpmConfig;
	
	//BPM Related Properties
	private String registrationKIESession;
	private String registrationBPMProcessflowName;
	private String registrationBPMProcessflowFulllName;
	private String registrationPasswordRulesKSession;
	
	//Password Rules Related Properties
	private String registrationPasswordRulesflow;
	private String registrationPasswordRulesflowEndPoint;
	private HttpMethod registrationPasswordRulesflowHttpMethod;
	private MediaType registrationPasswordRulesflowMediaType;
	
	@Autowired
	public UserRegistrationKieServiceImpl(KieContainer kieContainer) {
		this.kieContainer  = kieContainer;
		
	}
	
	@PostConstruct
	private void init(){
		if(null!=sqBpmConfig.getRegistrationKIESession()){
			this.registrationKIESession = sqBpmConfig.getRegistrationKIESession();
		}
		if(null!=sqBpmConfig.getRegistrationBPMProcessflowFulllName()){
			this.registrationBPMProcessflowFulllName = sqBpmConfig.getRegistrationBPMProcessflowFulllName();
		}
		if(null!=sqBpmConfig.getRegistrationBPMProcessflowName()){
			this.registrationBPMProcessflowName = sqBpmConfig.getRegistrationBPMProcessflowName();
		}
		if(null!=sqBpmConfig.getRegistrationPasswordRulesKSession()){
			this.registrationPasswordRulesKSession = sqBpmConfig.getRegistrationPasswordRulesKSession();
		}
		if(null!=sqBpmConfig.getRegistrationPasswordRulesflow()){
			this.registrationPasswordRulesflow = sqBpmConfig.getRegistrationPasswordRulesflow();
		}
		
		List<SQEndPoint> sqEndPoints = sqBpmConfig.getEndPoints();
		if(sqEndPoints.size()>0){
			for(SQEndPoint sqEndPoint: sqEndPoints){
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
			}
		}
		
		System.out.println("registrationKIESession -->"+registrationKIESession);
		System.out.println("registrationBPMProcessflowName -->"+registrationBPMProcessflowName);
		System.out.println("registrationBPMProcessflowFulllName -->"+registrationBPMProcessflowFulllName);
		System.out.println("registrationPasswordRulesKSession -->"+registrationPasswordRulesKSession);
		System.out.println("registrationPasswordRulesflow -->"+registrationPasswordRulesflow);
		System.out.println("registrationPasswordRulesflowEndPoint -->"+registrationPasswordRulesflowEndPoint);
		System.out.println("registrationPasswordRulesflowHttpMethod -->"+registrationPasswordRulesflowHttpMethod);
		System.out.println("registrationPasswordRulesflowMediaType -->"+registrationPasswordRulesflowMediaType);
		
		
		
	}

	
	/* (non-Javadoc)
	 * @see com.starquest.usermgmt.kie.restful.service.UserRegistrationKieService#startRegistrationBPMWorkflow(com.starquest.usermgmt.vo.UserVo)
	 */
	@Override
	public UserVo startRegistrationBPMWorkflow(UserVo userVo) throws Exception {
		
		
		System.out.println("START startRegistrationBPMWorkflow()");
		KieSession kieSession = kieContainer.newKieSession(registrationKIESession);
		kieSession.getWorkItemManager().registerWorkItemHandler(registrationBPMProcessflowName, new SQRESTfulPostWorkItemHandler());
		
		Utilities utils = new Utilities();
		
		//Preparing request payload to BPM Flow
		JSONObject jsonRequest = utils.convertPojoToJSONObj(userVo);
		
		Map<String, Object> userRegFlowParams = new HashMap<String, Object>();
		userRegFlowParams.put("URL", getRegistrationPasswordRulesflowEndPoint());
		userRegFlowParams.put("OPERATION", getRegistrationPasswordRulesflowHttpMethod());
		userRegFlowParams.put("MEDIATYPE", getRegistrationPasswordRulesflowMediaType());
		userRegFlowParams.put("PAYLOADJSON", jsonRequest);
		userRegFlowParams.put("USERVO", userVo);
		
		
		ProcessInstance processInstance = kieSession.startProcess(registrationBPMProcessflowFulllName,userRegFlowParams);
		
		Map<String, Object> results = (HashMap<String, Object>)((WorkflowProcessInstance) processInstance).getVariable("Result");
		 System.out.println("JBPM Processed and returned objects size::"+results.size());
		 
		 
		 Iterator<Entry<String, Object>> itr = results.entrySet().iterator();
		 while(itr.hasNext()){
			 Map.Entry<String,Object> entry = (Map.Entry<String, Object>) itr.next();
			 System.out.println("Key  = " + entry.getKey() + " Value=" + entry.getValue());
			 
			 if(entry.getKey().equals("USERVO")){
				 UserVo tempUserProfile = (UserVo) entry.getValue();
				 if(null!=tempUserProfile){
					 userVo.setFailCategory(tempUserProfile.getFailCategory());
					 userVo.setCategory(tempUserProfile.getCategory());
					 System.out.println("Bpm and Rules Processed User Profile with User.FAILCATEGORY="+tempUserProfile.getFailCategory());
					 System.out.println("Bpm and Rules Processed User Profile with User.CATEGORY=..."+tempUserProfile.getCategory());
				 }
			 }
		 }
		 System.out.println("startNewUserRegistrationBPMProcess() END");
		
		return userVo;
	}

	/* (non-Javadoc)
	 * @see com.starquest.usermgmt.kie.restful.service.UserRegistrationKieService#applySQPasswordRules(com.starquest.usermgmt.vo.UserVo)
	 */
	@Override
	public UserVo applySQPasswordRules(UserVo userVo) throws Exception {

		//Mallesh Dont Load all rules....Load Only SSN Rules. 
		KieSession kieSession = kieContainer.newKieSession(registrationPasswordRulesKSession);
		
		kieSession.insert(userVo);
		
		int firedRules = kieSession.fireAllRules();
		System.out.println("Total Rules Fired..."+firedRules);
		
		Collection<? extends Object> kieFilteredObjects = kieSession.getObjects();
		for(Object obj :  kieFilteredObjects){
			
			if(obj.getClass() == UserVo.class){
				UserVo  lVo = (UserVo) obj;
				System.out.println("Kie Object Data :: START");
				System.out.println("Password Failed Category::"+lVo.getFailCategory());
				System.out.println("User Category::"+lVo.getCategory());
				System.out.println("Kie Object Data :: END");
			}
		}
		return userVo;
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
	 * @return the kieContainer
	 */
	public KieContainer getKieContainer() {
		return kieContainer;
	}

	/**
	 * @return the sqBpmConfig
	 */
	public SQBPMConfiguration getSqBpmConfig() {
		return sqBpmConfig;
	}

	/**
	 * @param sqBpmConfig the sqBpmConfig to set
	 */
	public void setSqBpmConfig(SQBPMConfiguration sqBpmConfig) {
		this.sqBpmConfig = sqBpmConfig;
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

	
	
	

}
