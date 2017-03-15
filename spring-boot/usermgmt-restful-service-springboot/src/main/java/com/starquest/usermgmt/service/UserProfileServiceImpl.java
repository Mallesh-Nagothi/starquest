package com.starquest.usermgmt.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.starquest.usermgmt.vo.UserProfile;

import net.minidev.json.JSONObject;


@Service
public class UserProfileServiceImpl implements UserProfileService {

	Logger logger = Logger.getLogger(UserProfileServiceImpl.class);
	
	public UserProfile createUserProfile(UserProfile userProfile){
		
		
		//Useless coding... moved it to business rules called over a micros service
		//call database here persist
		//userProfile.setUserProfileStatus(UserProfileStatus.GOOD_SSN);
	
		//Apply Business Rules for SSN
		//Above comment is too old :) Moved to jBPM Workflow.
		logger.debug("Entrypoint :: /createUserProfile");
		
		//String urlStringForSSNRules = new String("http://localhost:8282/starquest/userregrules/validateSSN");
		String urlStringForSSNRules = new String("http://localhost:8282/starquest/userregrules/startRegistrationProcess");
		
		
		JSONObject jsonRequest = new JSONObject();
		jsonRequest.put("","");
		jsonRequest.put("ssn", userProfile.getSsn());
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		
		HttpEntity<String> entity = new HttpEntity<String>(jsonRequest.toString() ,httpHeaders);
		
		RestTemplate restTeamplate = new RestTemplate();
		ResponseEntity<UserProfile> respEntity = 
				restTeamplate.exchange(urlStringForSSNRules,HttpMethod.POST, entity, UserProfile.class);
		
		if(respEntity.getStatusCode() == HttpStatus.OK){
			UserProfile filteredNewUserProfile = respEntity.getBody();
			userProfile.setUserProfileStatus(filteredNewUserProfile.getUserProfileStatus());
		}
		
		logger.debug("Exitpoint :: /createUserProfile");
		
		userProfile.setNyhxId("DX0000001");
		return userProfile;
	}
	
	public List<UserProfile> getUserProfileById(int size){
		
		
		return UserProfileServiceImpl.ceateDummyPayLoad(size);
	}
	
	
	private static List<UserProfile> ceateDummyPayLoad(int size){
		
		List<UserProfile> lstUsrPforile=new ArrayList<UserProfile>();
		for(int i=0;i<size; i++) {
			UserProfile userProfile =new UserProfile();
			userProfile.setId(i);
			userProfile.setFirstName("POCFirstName"+i);
			userProfile.setLastName("POCLasttName"+i);
			userProfile.setAccountId("AC000011211"+i);
			userProfile.setCinNumber("ABCD000"+i);
			userProfile.setCreatedBy("SYSTEM");
			userProfile.setUpdatedBy("SYSTEM");
			userProfile.setDateOfBirth(new java.util.Date());
			Date createdDate = new Date();
			userProfile.setCreatedDate(createdDate);
			userProfile.setUpdatedDate(createdDate);
			userProfile.setDontHaveSSNIND("0");
			userProfile.setEmail("sbc" + i + "@abc.com");
			userProfile.setGender("M");
			userProfile.setIdentitySuccessInd("Y");
			userProfile.setIdProofingCompletedInd("0");
			userProfile.setMiddleName("M");
			userProfile.setSsn("518518890"+i);
			userProfile.setNoSSNReasonCD("NA");
			userProfile.setNyhxId("DX000024095"+i);
			userProfile.setPreferredLangauge("ENGLISH");
			userProfile.setPreferredSpokenLangauge("ENGLISH");
			userProfile.setPreferredWrittenLangauge("ENGLISH");
			userProfile.setState("NY");
			userProfile.setUserName("POCUserName"+i);
			userProfile.setSuffix("Mr.");
			userProfile.setUserModuleRoleId("i");
			lstUsrPforile.add(userProfile);	
			
		}
		
		return lstUsrPforile;
		
	}
	public static Date convertStrngToDate(String string) {
		try {
			if (string == null || string.length() == 0) {
				return null;
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			dateFormat.setLenient(false);
			return dateFormat.parse(string);
		} catch (Exception e) {
			
			return null;
		}
	}

}
 