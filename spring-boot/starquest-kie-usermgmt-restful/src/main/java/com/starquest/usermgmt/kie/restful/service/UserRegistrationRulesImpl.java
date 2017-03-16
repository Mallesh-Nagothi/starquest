/**
 * 
 */
package com.starquest.usermgmt.kie.restful.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.runtime.process.WorkflowProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starquest.usermgmt.kie.restful.bpm.RESTfulServiceWorkItemHandler;
import com.starquest.usermgmt.vo.UserProfile;
import com.starquest.usermgmt.vo.UserVo;

import net.minidev.json.JSONObject;

/**
 * @author mallesh
 *
 */
@Service
public class UserRegistrationRulesImpl implements UserRegistrationRules {

	//externalize this baby
	private String hostStringForSSNRules 	= new String("http://localhost:8282/starquest/userregrules/validateSSN");
	private String portStringForSSNRules 	= new String("8282");
	private String uriStringForSSNRules		= new String ("/starquest/userregrules/startRegistrationProcess");
	
	private final KieContainer kieContainer;
	
	@Autowired
	public UserRegistrationRulesImpl(KieContainer kieContainer) {
		this.kieContainer  = kieContainer;
		
	}
	
	/* (non-Javadoc)
	 * @see com.starquest.usermgmt.kie.restful.service.UserRegistrationRules#startNewUserRegistrationBPMProcess(com.starquest.usermgmt.vo.UserProfile)
	 */
	@Override
	public UserProfile startNewUserRegistrationBPMProcess(UserProfile userProfile) {
		
		System.out.println("START UserRegistrationRulesImpl-->startNewUserRegistrationBPMProcess()");
		KieSession kieSession = kieContainer.newKieSession("rules.starquest.userMgmtNotificationKSession");
		//kieSession.getWorkItemManager().registerWorkItemHandler("SqEmailNotification", new EmailNotificationWorkItemHandler());
		kieSession.getWorkItemManager().registerWorkItemHandler("SqRESTfulServiceEndPoint", new RESTfulServiceWorkItemHandler());
		
		//Preparing request payload to BPM Flow
		JSONObject jsonRequest = new JSONObject();
		jsonRequest.put("","");
		jsonRequest.put("ssn", userProfile.getSsn());
		jsonRequest.put("password", "fakePasswordBuddy");
		
		
		Map<String, Object> userRegFlowParams = new HashMap<String, Object>();
		userRegFlowParams.put("URI", hostStringForSSNRules);
		userRegFlowParams.put("PORT", portStringForSSNRules+uriStringForSSNRules); //--> TODO this is too worst too bad :)   
		userRegFlowParams.put("OPERATION", "POST");
		userRegFlowParams.put("MEDIATYPE", "APPLICATION_JSON");
		userRegFlowParams.put("PAYLOADJSON", userProfile.toString());
		userRegFlowParams.put("PAYLOADPOJO", jsonRequest);
		
		ProcessInstance processInstance = kieSession.startProcess("com.starquest.kie.process.userreg.PocRESTfulJsonObject",userRegFlowParams);
		
		System.out.println("Done with Process...Start printing...");
		
		Map<String, Object> results = (HashMap<String, Object>)((WorkflowProcessInstance) processInstance).getVariable("Result");
		 System.out.println("JBPM Processed and returned objects size::"+results.size());
		 
		 
		 Iterator<Entry<String, Object>> itr = results.entrySet().iterator();
		 while(itr.hasNext()){
			 Map.Entry<String,Object> entry = (Map.Entry<String, Object>) itr.next();
			 System.out.println("Key  = " + entry.getKey() + " Value=" + entry.getValue());
			 
			 if(entry.getKey().equals("USERPROFILE")){
				 UserProfile tempUserProfile = (UserProfile) entry.getValue();
				 if(null!=tempUserProfile){
					 System.out.println("Bpm and Rules Processed User Profile Received..."+tempUserProfile.getUserProfileStatus());
					 userProfile.setUserProfileStatus(tempUserProfile.getUserProfileStatus());
					 
				 }
			 }
		 }
		 
		 /*Map<String, Object> emailNotifyParams = new HashMap<String, Object>();
		 emailNotifyParams.put("From", "KIE-UserRegistrationRulesRESTful-WildflySwarm");
		 emailNotifyParams.put("To", "SYSOUT CONSOLE");
		 emailNotifyParams.put("Message", "Hello WildFly-Swarm...");
		 emailNotifyParams.put("Priority", "Lowest");
		 emailNotifyParams.put("PriorityInput", "Lowest");
		 
		 ProcessInstance processInstance = kieSession.startProcess("com.starquest.kie.process.userreg.SQNewNotification",emailNotifyParams);
		 
		 //String results = (String) ((WorkflowProcessInstance) processInstance).getVariable("Result");
		 
		 Map<String, Object> results = (HashMap<String, Object>)((WorkflowProcessInstance) processInstance).getVariable("Result");
		 System.out.println("JBPM Processed and returned objects size::"+results.size());
		 Iterator<Entry<String, Object>> itr = results.entrySet().iterator();
		 while(itr.hasNext()){
			 Map.Entry<String,Object> entry = (Map.Entry<String, Object>) itr.next();
			 System.out.println("Key  = " + entry.getKey() + " Value=" + entry.getValue());
		 }*/
		 System.out.println("startNewUserRegistrationBPMProcess() END");
		
		return userProfile;
	}	
	
	
	/* (non-Javadoc)
	 * @see com.starquest.usermgmt.kie.restful.service.UserRegistrationRules#applyNewUserRegistrationPasswordRules(com.starquest.usermgmt.vo.LoginVo)
	 */
	@Override
	public UserVo applyNewUserRegistrationPasswordRules(UserVo userVo) {
		
		KieSession kieSession = kieContainer.newKieSession("rules.starquest.userMgmtKSession");
		
		kieSession.insert(userVo);
		
		int firedRules = kieSession.fireAllRules();
		System.out.println("Total Rules Fired..."+firedRules);
		
		Collection<? extends Object> kieFilteredObjects = kieSession.getObjects();
		for(Object obj :  kieFilteredObjects){
			
			if(obj.getClass() == UserVo.class){
				UserVo  lVo = (UserVo) obj;
				System.out.println("Kie Object Data :: START");
				System.out.println("Passwoprd::"+lVo.getPassword());
				System.out.println("Passwoprd Leader::"+lVo.getPassword().length());
				System.out.println("Login User Fail Category"+lVo.getFailCategory());
				System.out.println("Kie Object Data :: END");
			}
		}
		return userVo;
	}

	/* (non-Javadoc)
	 * @see com.starquest.usermgmt.kie.restful.service.UserRegistrationRules#applyNewUserRegistrationSSNRules(com.starquest.usermgmt.vo.UserProfile)
	 */
	@Override
	public UserProfile applyNewUserRegistrationSSNRules(UserProfile userProfile) {

		//Mallesh Dont Load all rules....Load Only SSN Rules. 
		KieSession kieSession = kieContainer.newKieSession("rules.starquest.userMgmtSSNKSession");
		
		kieSession.insert(userProfile);
		
		int firedRules = kieSession.fireAllRules();
		System.out.println("Total Rules Fired..."+firedRules);
		
		Collection<? extends Object> kieFilteredObjects = kieSession.getObjects();
		for(Object obj :  kieFilteredObjects){
			
			if(obj.getClass() == UserProfile.class){
				UserProfile  lVo = (UserProfile) obj;
				System.out.println("Kie Object Data :: START");
				System.out.println("SSN::"+lVo.getSsn());
				System.out.println("SSN Length::"+lVo.getSsn().length());
				System.out.println("USer Profile SSN Fail Category"+lVo.getUserProfileStatus());
				System.out.println("Kie Object Data :: END");
			}
		}
		return userProfile;
	}
}
