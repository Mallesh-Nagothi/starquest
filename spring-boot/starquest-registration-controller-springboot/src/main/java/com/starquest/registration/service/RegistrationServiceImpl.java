/**
 * 
 */
package com.starquest.registration.service;


import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
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
import com.starquest.registration.config.SQRegistrationAppKieProperties;
import com.starquest.registration.utils.Utilities;
import com.starquest.usermgmt.vo.UserVo;

import net.minidev.json.JSONObject;

/**
 * @author mallesh
 * @version 1.0
 * @since Mar/18/2017
 * @see com.startquest.registration.service.RegistrationService
 *
 */
@Service
public class RegistrationServiceImpl implements RegistrationService  {

	private static final Logger logger = Logger.getLogger(RegistrationServiceImpl.class); 
	
	@Autowired
	private SQRegistrationAppKieProperties sqRegAppKieProperties;
	
	private String registrationBPMWorkflow;
	private String registrationBPMWorkflowEndPoint;
	private HttpMethod registrationBPMWorkflowHttpMethod;
	private MediaType registrationBPMWorkflowMediaType;
	
	 
	
	@PostConstruct
	private void init(){
		if(null!=sqRegAppKieProperties.getRegistrationBPMWorkflow()){
			this.registrationBPMWorkflow = sqRegAppKieProperties.getRegistrationBPMWorkflow();
		}
		List<SQEndPoint> sqEndPoints = sqRegAppKieProperties.getEndPoints();
		if(sqEndPoints.size()>0){
			for(SQEndPoint sqEndPoint: sqEndPoints){
				if(this.registrationBPMWorkflow.equalsIgnoreCase(sqEndPoint.getEndPoint())){
					this.registrationBPMWorkflowEndPoint	= sqEndPoint.getUrl();
					
					if(null!=sqEndPoint.getOperation() 
							&& sqEndPoint.getOperation().equalsIgnoreCase(sqRegAppKieProperties.getGlobalOperationPost())){
						this.registrationBPMWorkflowHttpMethod	= HttpMethod.POST;
					}
					if(null!=sqEndPoint.getMediaType() && 
							sqEndPoint.getMediaType().equalsIgnoreCase(sqRegAppKieProperties.getGlobalMediaTypeJson())){
						this.registrationBPMWorkflowMediaType	= MediaType.APPLICATION_JSON;
					}
					
				}
			}
		}
		
		System.out.println("End of PostConstruct");
		System.out.println("End of PostConstruct -->registrationBPMWorkflow() -->"+registrationBPMWorkflow);
		System.out.println("End of PostConstruct -->registrationBPMWorkflowEndPoint -->"+registrationBPMWorkflowEndPoint);
		
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see com.starquest.registration.service.RegistrationService#callNewRegistrationService(com.starquest.usermgmt.vo.UserVo)
	 */
	@Override
	public UserVo callNewRegistrationService(UserVo newUser) throws Exception {
		
		logger.debug("Start callNewRegistrationService()");
		Utilities util = new Utilities();
		
		if(!util.isValidString(registrationBPMWorkflow) && 
				!util.isValidString(registrationBPMWorkflowEndPoint) && 
				null == registrationBPMWorkflowHttpMethod  &&
				null == registrationBPMWorkflowMediaType){
			throw new Exception("Invalid Endpoint Settings");
		}
		
		
		JSONObject jsonRequest = util.convertPojoToJSONObj(newUser);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(registrationBPMWorkflowMediaType);
		HttpEntity<String> entity = new HttpEntity<String>(jsonRequest.toString() ,httpHeaders);
		
		RestTemplate restTeamplate = new RestTemplate();
		ResponseEntity<UserVo> respEntity = 
				restTeamplate.exchange(registrationBPMWorkflowEndPoint ,registrationBPMWorkflowHttpMethod, entity, UserVo.class);
		
		newUser.setCategory(UserVo.Category.REGISTRATION_FAILED);
		newUser.setFailCategory(UserVo.FailCategory.TOO_WEAK);
		
		if(respEntity.getStatusCode() == HttpStatus.OK){
			UserVo retUserVo = (UserVo)respEntity.getBody();
			if(null!=retUserVo){
				newUser.setFailCategory(retUserVo.getFailCategory());
				newUser.setCategory(retUserVo.getCategory());
			}
		}
		newUser.setPassword(null);
		logger.debug("End callNewRegistrationService()");
		return newUser;
	}


	/**
	 * @return the sqRegAppKieProperties
	 */
	public SQRegistrationAppKieProperties getSqRegAppKieProperties() {
		return sqRegAppKieProperties;
	}
	/**
	 * @param sqRegAppKieProperties the sqRegAppKieProperties to set
	 */
	public void setSqRegAppKieProperties(SQRegistrationAppKieProperties sqRegAppKieProperties) {
		this.sqRegAppKieProperties = sqRegAppKieProperties;
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
	 * @return the registrationBPMWorkflowEndPoint
	 */
	public String getRegistrationBPMWorkflowEndPoint() {
		return registrationBPMWorkflowEndPoint;
	}




	/**
	 * @param registrationBPMWorkflowEndPoint the registrationBPMWorkflowEndPoint to set
	 */
	public void setRegistrationBPMWorkflowEndPoint(String registrationBPMWorkflowEndPoint) {
		this.registrationBPMWorkflowEndPoint = registrationBPMWorkflowEndPoint;
	}




	/**
	 * @return the registrationBPMWorkflowHttpMethod
	 */
	public HttpMethod getRegistrationBPMWorkflowHttpMethod() {
		return registrationBPMWorkflowHttpMethod;
	}




	/**
	 * @param registrationBPMWorkflowHttpMethod the registrationBPMWorkflowHttpMethod to set
	 */
	public void setRegistrationBPMWorkflowHttpMethod(HttpMethod registrationBPMWorkflowHttpMethod) {
		this.registrationBPMWorkflowHttpMethod = registrationBPMWorkflowHttpMethod;
	}




	/**
	 * @return the registrationBPMWorkflowMediaType
	 */
	public MediaType getRegistrationBPMWorkflowMediaType() {
		return registrationBPMWorkflowMediaType;
	}




	/**
	 * @param registrationBPMWorkflowMediaType the registrationBPMWorkflowMediaType to set
	 */
	public void setRegistrationBPMWorkflowMediaType(MediaType registrationBPMWorkflowMediaType) {
		this.registrationBPMWorkflowMediaType = registrationBPMWorkflowMediaType;
	}



}
