/**
 * 
 */
package com.starquest.usermgmt.kie.restful.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.runtime.process.WorkflowProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starquest.registration.config.SQRESTfulEndPoints;
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
	
	private SQRESTfulEndPoints sqRESTfulEndpoints;
	
	
	@Autowired
	public UserRegistrationKieServiceImpl(KieContainer kieContainer) {
		this.kieContainer  = kieContainer;
		
	}
	
	@PostConstruct
	private void init(){
		
		//Assign all jBPM related configuration
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
		
		sqRESTfulEndpoints = new SQRESTfulEndPoints();
		this.sqRESTfulEndpoints.setSqEndPoints(sqBpmConfig.getEndPoints());
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
		
		userRegFlowParams.put("PAYLOADJSON", jsonRequest);
		userRegFlowParams.put("USERVO", userVo);
		userRegFlowParams.put("SQBPMConfiguration", sqBpmConfig);
		
		
		ProcessInstance processInstance = kieSession.startProcess(registrationBPMProcessflowFulllName,userRegFlowParams);
		//@TODO Remove Result in Process as well as in java code
		Map<String, Object> results = (HashMap<String, Object>)((WorkflowProcessInstance) processInstance).getVariable("Result");
		System.out.println("JBPM Processed and returned objects size::"+results.size());
		
		userVo = (UserVo)((WorkflowProcessInstance) processInstance).getVariable("USERVO"); 
		if(null!= userVo){
			System.out.println("Bpm Processed User Profile with User.FName=..."+userVo.getFirstName());
			System.out.println("Bpm Processed User Profile with User.LName=..."+userVo.getLastName());
			System.out.println("Bpm Processed User Profile with User.ENCRYPTEDPWD=..."+userVo.getPassword());
			System.out.println("Bpm Processed User Profile with User.Email=..."+userVo.getEmailAddress());
			System.out.println("Bpm Processed User Profile with User.FAILCATEGORY="+userVo.getFailCategory());
			System.out.println("Bpm Processed User Profile with User.CATEGORY=..."+userVo.getCategory());
			System.out.println("Bpm Processed User Profile with User.ISBADEMAIL="+userVo.isBadEmail());
			System.out.println("Bpm Processed User Profile with User.ISBADLNAME=..."+userVo.isBadLastName());
			System.out.println("Bpm Processed User Profile with User.ISBADFNAME="+userVo.isBadFirstName());
			System.out.println("Bpm Processed User Profile with User.ISBADPASSWORD=..."+userVo.isBadPassword());
			
		}
		
		 //@TODO Remove Result in Process as well as in java code
		 Iterator<Entry<String, Object>> itr = results.entrySet().iterator();
		 while(itr.hasNext()){
			 Map.Entry<String,Object> entry = (Map.Entry<String, Object>) itr.next();
			 System.out.println("Key  = " + entry.getKey() + " Value=" + entry.getValue());
			 
			 if(entry.getKey().equals("USERVO")){
				 UserVo tempUserProfile = (UserVo) entry.getValue();
				 if(null!=tempUserProfile){
					 userVo.setFailCategory(tempUserProfile.getFailCategory());
					 userVo.setCategory(tempUserProfile.getCategory());
					 userVo.setBadEmail(tempUserProfile.isBadEmail());
					 userVo.setBadLastName(tempUserProfile.isBadLastName());
					 userVo.setBadFirstName(tempUserProfile.isBadFirstName());
					 userVo.setBadPassword(tempUserProfile.isBadPassword());
					 /*System.out.println("Bpm and Rules Processed User Profile with User.FAILCATEGORY="+tempUserProfile.getFailCategory());
					 System.out.println("Bpm and Rules Processed User Profile with User.CATEGORY=..."+tempUserProfile.getCategory());
					 System.out.println("Bpm and Rules Processed User Profile with User.ISBADEMAIL="+tempUserProfile.isBadEmail());
					 System.out.println("Bpm and Rules Processed User Profile with User.ISBADLNAME=..."+tempUserProfile.isBadLastName());
					 System.out.println("Bpm and Rules Processed User Profile with User.ISBADFNAME="+tempUserProfile.isBadFirstName());
					 System.out.println("Bpm and Rules Processed User Profile with User.ISBADPASSWORD=..."+tempUserProfile.isBadPassword());
					 System.out.println("Bpm and Rules Processed User Profile with User.FName=..."+tempUserProfile.getFirstName());
					 System.out.println("Bpm and Rules Processed User Profile with User.ENCRYPTEDPWD=..."+tempUserProfile.getPassword());*/
					 
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
	

}
