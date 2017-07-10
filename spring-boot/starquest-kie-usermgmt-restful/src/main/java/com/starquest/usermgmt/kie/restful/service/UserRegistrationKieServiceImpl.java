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

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.runtime.process.WorkflowProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.starquest.registration.config.SQEndPoint;
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
public class UserRegistrationKieServiceImpl implements UserRegistrationKieService  {
	
	private final KieContainer  kieContainer;
	
	@Autowired
	private SQBPMConfiguration	sqBpmConfig;
	
	/** jBPM congiration for Registration  moved to abstract class**/
	private String registrationKIESession;
	private String registrationBPMProcessflowName;
	private String registrationBPMProcessflowFulllName;
	private String registrationPasswordRulesKSession;
	
	/** ESB End points configuration**/
	private String registrationRulesFailedNotification; 
	private String registrationRulesFailedNotificationEndPoint;
	private HttpMethod registrationRulesFailedNotificationHttpMethod;
	private MediaType registrationRulesFailedNotificationMediaType;
	
	private String registrationEncryptionFailedNotification;
	private String registrationEncryptionFailedNotificationEndPoint;
	private HttpMethod registrationEncryptionFailedNotificationHttpMethod;
	private MediaType registrationEncryptionFailedNotificationMediaType;
	
	private String registrationPersistenceFailedNotification;
	private String registrationPersistenceFailedNotificationEndPoint;
	private HttpMethod registrationPersistenceFailedNotificationHttpMethod;
	private MediaType registrationPersistenceFailedNotificationMediaType;
	
	
	private SQRESTfulEndPoints sqRESTfulEndpoints;
	
	
	
	@Autowired
	public UserRegistrationKieServiceImpl(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
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
		
		if(null!=sqBpmConfig.getRegistrationRulesFailedNotification()){
			this.registrationRulesFailedNotification = sqBpmConfig.getRegistrationRulesFailedNotification();
		}
		if(null!=sqBpmConfig.getRegistrationEncryptionFailedNotification()){
			this.registrationEncryptionFailedNotification = sqBpmConfig.getRegistrationEncryptionFailedNotification();
		}
		if(null!=sqBpmConfig.getRegistrationPersistenceFailedNotification()){
			this.registrationPersistenceFailedNotification = sqBpmConfig.getRegistrationPersistenceFailedNotification();
		}
		List<SQEndPoint> sqEndPoints = sqBpmConfig.getEndPoints();
		if(sqEndPoints.size()>0){
			for(SQEndPoint sqEndPoint: sqEndPoints){
		
				//configuring end points for rules fail notification ESB
				if(this.registrationRulesFailedNotification.equalsIgnoreCase(sqEndPoint.getEndPoint())){
					this.registrationRulesFailedNotificationEndPoint	= sqEndPoint.getUrl();
					
					if(null!=sqEndPoint.getOperation() 
							&& sqEndPoint.getOperation().equalsIgnoreCase(sqBpmConfig.getGlobalOperationPost())){
						this.registrationRulesFailedNotificationHttpMethod	= HttpMethod.POST;
					}
					if(null!=sqEndPoint.getMediaType() && 
							sqEndPoint.getMediaType().equalsIgnoreCase(sqBpmConfig.getGlobalMediaTypeJson())){
						this.registrationRulesFailedNotificationMediaType	= MediaType.APPLICATION_JSON;
					}
				}
				
				//configuring end points for encryption fail notification ESB
				if(this.registrationEncryptionFailedNotification.equalsIgnoreCase(sqEndPoint.getEndPoint())){
					this.registrationEncryptionFailedNotificationEndPoint	= sqEndPoint.getUrl();
					
					if(null!=sqEndPoint.getOperation() 
							&& sqEndPoint.getOperation().equalsIgnoreCase(sqBpmConfig.getGlobalOperationPost())){
						this.registrationEncryptionFailedNotificationHttpMethod	= HttpMethod.POST;
					}
					if(null!=sqEndPoint.getMediaType() && 
							sqEndPoint.getMediaType().equalsIgnoreCase(sqBpmConfig.getGlobalMediaTypeJson())){
						this.registrationEncryptionFailedNotificationMediaType	= MediaType.APPLICATION_JSON;
					}
				}
				
				//configuring end points for persistence fail notification ESB
				if(this.registrationPersistenceFailedNotification.equalsIgnoreCase(sqEndPoint.getEndPoint())){
					this.registrationPersistenceFailedNotificationEndPoint	= sqEndPoint.getUrl();
					
					if(null!=sqEndPoint.getOperation() 
							&& sqEndPoint.getOperation().equalsIgnoreCase(sqBpmConfig.getGlobalOperationPost())){
						this.registrationPersistenceFailedNotificationHttpMethod	= HttpMethod.POST;
					}
					if(null!=sqEndPoint.getMediaType() && 
							sqEndPoint.getMediaType().equalsIgnoreCase(sqBpmConfig.getGlobalMediaTypeJson())){
						this.registrationPersistenceFailedNotificationMediaType	= MediaType.APPLICATION_JSON;
					}
				}
				
			}
		}
		
		sqRESTfulEndpoints = new SQRESTfulEndPoints();
		this.sqRESTfulEndpoints.setSqEndPoints(sqBpmConfig.getEndPoints());
	}

	
	
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
			//verify UserVo after jBPM Process
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
	
	
	
	
	
	
	/* (non-Javadoc)
	 * @see com.starquest.usermgmt.kie.restful.service.UserRegistrationKieService#processRegistrationRulesFail(com.starquest.usermgmt.vo.UserVo)
	 */
	@Override
	public boolean processRegistrationRulesFail(UserVo userVo) throws Exception {
		System.out.println("::RegistrationRulesFail Process START::");
		boolean rulesFailProcessSuccess = false;
		try{
			Utilities utils = new Utilities();
			JSONObject jsonRequest = utils.convertPojoToJSONObj(userVo);
			
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity<String> entity = new HttpEntity<String>(jsonRequest.toString() ,httpHeaders);
			RestTemplate restTeamplate = new RestTemplate();
			
			
			//@TODO remove HttpMethod.POST hard coding in the following line, use parameter - Mallesh 
			ResponseEntity<UserVo> respEntity = 
					restTeamplate.exchange(registrationRulesFailedNotificationEndPoint,HttpMethod.POST, entity, UserVo.class);
			if(respEntity.getStatusCode() == HttpStatus.OK){
				rulesFailProcessSuccess = true;
			}
			
		}catch(Exception ex){
			System.out.println(ex);
	
		}
		
		System.out.println("::RegistrationRulesFail Process END::");
		return rulesFailProcessSuccess;
	}

	/* (non-Javadoc)
	 * @see com.starquest.usermgmt.kie.restful.service.UserRegistrationKieService#processRegistrationEncryptionFail(com.starquest.usermgmt.vo.UserVo)
	 */
	@Override
	public boolean processRegistrationEncryptionFail(UserVo userVo) throws Exception {
		System.out.println("::RegistrationEncryptionFail Process START::");
		boolean encryptionFailProcessSuccess = false;
		try{
			Utilities utils = new Utilities();
			JSONObject jsonRequest = utils.convertPojoToJSONObj(userVo);
			
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity<String> entity = new HttpEntity<String>(jsonRequest.toString() ,httpHeaders);
			RestTemplate restTeamplate = new RestTemplate();
			
			
			//@TODO remove HttpMethod.POST hard coding in the following line, use parameter - Mallesh 
			ResponseEntity<UserVo> respEntity = 
					restTeamplate.exchange(registrationEncryptionFailedNotificationEndPoint,HttpMethod.POST, entity, UserVo.class);
			if(respEntity.getStatusCode() == HttpStatus.OK){
				encryptionFailProcessSuccess = true;
			}
		}catch(Exception ex){
			System.out.println(ex);
		}
		
		System.out.println("::RegistrationEncryptionFail Process END::");
		return encryptionFailProcessSuccess;
	}

	/* (non-Javadoc)
	 * @see com.starquest.usermgmt.kie.restful.service.UserRegistrationKieService#processRegistrationPersistFail(com.starquest.usermgmt.vo.UserVo)
	 */
	@Override
	public boolean processRegistrationPersistFail(UserVo userVo) throws Exception {
		System.out.println("::RegistrationPersistFail Process START::");
		boolean persistenceFailProcessSuccess = false;
		try{
			Utilities utils = new Utilities();
			JSONObject jsonRequest = utils.convertPojoToJSONObj(userVo);
			
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity<String> entity = new HttpEntity<String>(jsonRequest.toString() ,httpHeaders);
			RestTemplate restTeamplate = new RestTemplate();
			
			//@TODO remove HttpMethod.POST hard coding in the following line, use parameter - Mallesh 
			ResponseEntity<UserVo> respEntity = 
					restTeamplate.exchange(registrationPersistenceFailedNotificationEndPoint,HttpMethod.POST, entity, UserVo.class);
			if(respEntity.getStatusCode() == HttpStatus.OK){
				persistenceFailProcessSuccess = true;
			}
		}catch(Exception ex){
			System.out.println(ex);
		}		
		
		System.out.println("::RegistrationPersistFail Process END::");
		return persistenceFailProcessSuccess;
	}
}
