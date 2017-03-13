/**
 * 
 */
package com.starquest.usermgmt.kie.restful.service;

import java.util.Collection;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starquest.usermgmt.vo.LoginVo;
import com.starquest.usermgmt.vo.UserProfile;
import com.starquest.usermgmt.vo.UserVo;

/**
 * @author mallesh
 *
 */
@Service
public class UserRegistrationRulesImpl implements UserRegistrationRules {

	
	
	private final KieContainer kieContainer;
	
	@Autowired
	public UserRegistrationRulesImpl(KieContainer kieContainer) {
		this.kieContainer  = kieContainer;
		
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
