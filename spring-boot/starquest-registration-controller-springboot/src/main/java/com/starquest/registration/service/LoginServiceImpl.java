package com.starquest.registration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.starquest.registration.config.SQRegistrationAppKieProperties;
import com.starquest.registration.utils.Utilities;
import com.starquest.usermgmt.vo.UserVo;

import net.minidev.json.JSONObject;


/**
 * 
 * @author mallesh
 * @since Jul/01st/2017
 * 
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private SQRegistrationAppKieProperties sqRegAppKieProperties;
	
	
	@Override
	public UserVo callLogMeInBPMService(UserVo loginUser) throws Exception {

		Utilities util = new Utilities();
		
		if(!util.isValidString(loginUser.getEmailAddress()) && !util.isValidString(loginUser.getPassword()) ){
			throw new Exception("Invalid User Name/Password");
		}
		
		
		JSONObject jsonRequest = util.convertPojoToJSONObj(loginUser);
		HttpHeaders httpHeaders = new HttpHeaders();
		MediaType loginBPMWorkflowMediaType = MediaType.APPLICATION_JSON;
		httpHeaders.setContentType(loginBPMWorkflowMediaType);
		HttpEntity<String> entity = new HttpEntity<String>(jsonRequest.toString() ,httpHeaders);
		HttpMethod loginBPMWorkflowHttpMethod = HttpMethod.POST;
		
		
		RestTemplate restTeamplate = new RestTemplate();
		ResponseEntity<UserVo> respEntity = 
				restTeamplate.exchange(sqRegAppKieProperties.getLoginBPMWorkFlowEndPointDetails().getUrl() ,loginBPMWorkflowHttpMethod, entity, UserVo.class);
		
		
		loginUser.setFailCategory(UserVo.FailCategory.LOGIN_FAILED);
		if(respEntity.getStatusCode() == HttpStatus.OK){
			loginUser = (UserVo)respEntity.getBody();
			//if badPassword is on redirect to a different page. do that in controller not here
		}else{
			loginUser.setFailCategory(UserVo.FailCategory.LOGIN_FAILED);
		}
		loginUser.setPassword(null);
		return loginUser;
	}
	public SQRegistrationAppKieProperties getSqRegAppKieProperties() {
		return sqRegAppKieProperties;
	}
	public void setSqRegAppKieProperties(SQRegistrationAppKieProperties sqRegAppKieProperties) {
		this.sqRegAppKieProperties = sqRegAppKieProperties;
	}
	

}
