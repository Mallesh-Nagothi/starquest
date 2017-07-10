package com.starquest.usermgmt.kie.restful.bpm;



import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.starquest.registration.config.SqEndPoints;
import com.starquest.usermgmt.kie.restful.config.SQBPMConfiguration;
import com.starquest.usermgmt.vo.UserVo;

public class SQRESTfulLoginProcessPostWorkItemHandler implements WorkItemHandler {

	private SQBPMConfiguration	sqBpmConfig;
	
	/**
	 * 
	 */
	public SQRESTfulLoginProcessPostWorkItemHandler() {
		super();
	}
	
	public SQRESTfulLoginProcessPostWorkItemHandler(SQBPMConfiguration	sqBpmConfig) {
		super();
		this.sqBpmConfig = sqBpmConfig;
	}
	
	
	@Override
	public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
		System.out.println("Login Process Work Item Started");
		UserVo resultedUserVo = new UserVo();

		if(1==workItem.getId()){
			
			try{
				ResponseEntity<UserVo> respEntity = ProcessBPMWorkItemServiceFactory.getProcessBPMWorkItem(
						SqEndPoints.PASSWORD_DECRYPT_ENDPOINT.name()).callRestfulEndPoint(workItem);
				
				
				Boolean nextStep = new Boolean(false);
				if(respEntity.getStatusCode() == HttpStatus.OK){
					if(respEntity.getBody() !=null){
						resultedUserVo =(UserVo)respEntity.getBody();
						if(!resultedUserVo.isBadPassword()){
							nextStep = true;
						}
					}
				}
		        ProcessBPMWorkItemServiceFactory.getProcessBPMWorkItem(
						SqEndPoints.PASSWORD_DECRYPT_ENDPOINT.name()).prepareForNextStep(resultedUserVo,  nextStep, workItem,manager);
				
			}catch(Exception ex){
				System.out.println("Something wrong in Work Item 1");
			}
	        System.out.println("WORK ITEM 1 END");
		}
		
		if(workItem.getId()>1){
			String nextStepInfo 	= (String) workItem.getParameter("NEXTSTEP");
			if(null!=nextStepInfo){
				//Steps if decrypt fails
				if(nextStepInfo.equalsIgnoreCase(sqBpmConfig.getPasswordDecryptFailFlow())){
					try{
						ResponseEntity<UserVo> respEntity = ProcessBPMWorkItemServiceFactory.getProcessBPMWorkItem(
								SqEndPoints.PASSWORD_DECRYPT_FAIL_ENDPOINT.name()).callRestfulEndPoint(workItem);
						
						
						Boolean nextStep = new Boolean(false);
						if(respEntity.getStatusCode() == HttpStatus.OK){
							if(respEntity.getBody() !=null){
								nextStep = true;
							}
						}else{
							//throw exception
						}
						
				        ProcessBPMWorkItemServiceFactory.getProcessBPMWorkItem(
								SqEndPoints.PASSWORD_DECRYPT_FAIL_ENDPOINT.name()).prepareForNextStep(resultedUserVo, nextStep, workItem,manager);
						
					}catch(Exception ex){
						System.out.println("Something wrong in Work Item 1");
					}
					
				}else if(nextStepInfo.equalsIgnoreCase(sqBpmConfig.getValidateCredentialsFlow())){ //Steps if decrypt success and ready for validating credentials
					
					try{
						ResponseEntity<UserVo> respEntity = ProcessBPMWorkItemServiceFactory.getProcessBPMWorkItem(
								SqEndPoints.PASSWORD_DECRYPT_SUCCESS_ENDPOINT.name()).callRestfulEndPoint(workItem);
						
						
						Boolean nextStep = new Boolean(false);
						if(respEntity.getStatusCode() == HttpStatus.OK){
							if(respEntity.getBody() !=null){
								resultedUserVo =(UserVo)respEntity.getBody();
								if(!resultedUserVo.isBadPassword() && !resultedUserVo.isBadEmail()){
									nextStep = true;
								}
							}
						}else{
							//throw exception
						}
						
				        ProcessBPMWorkItemServiceFactory.getProcessBPMWorkItem(
								SqEndPoints.PASSWORD_DECRYPT_SUCCESS_ENDPOINT.name()).prepareForNextStep(resultedUserVo, nextStep, workItem,manager);
						
					}catch(Exception ex){
						System.out.println("Something wrong in Work Item 1");
					}
					
					/*ResponseEntity<UserVo> respEntity = callRestfulEndPoint(workItem, bpmUserVo,
							sqBpmConfig.getPasswordDecryptSuccessFlowEndPointDetails().getEndPoint());*/
					
				}else if(nextStepInfo.equalsIgnoreCase(sqBpmConfig.getLoginCredentialsFailFlow())){ //Steps if bad credentials
					try{
						ResponseEntity<UserVo> respEntity = ProcessBPMWorkItemServiceFactory.getProcessBPMWorkItem(
								SqEndPoints.VALIDATE_CREDENTIALS_FAIL_ENDPOINT.name()).callRestfulEndPoint(workItem);
						
						
						Boolean nextStep = new Boolean(false);
						if(respEntity.getStatusCode() == HttpStatus.OK){
							if(respEntity.getBody() !=null){
								nextStep = true;
							}
						}else{
							//throw exception
						}
						
				        ProcessBPMWorkItemServiceFactory.getProcessBPMWorkItem(
								SqEndPoints.VALIDATE_CREDENTIALS_FAIL_ENDPOINT.name()).prepareForNextStep(resultedUserVo, nextStep, workItem,manager);
						
					}catch(Exception ex){
						System.out.println("Something wrong in Work Item 1");
					}
					/*
					ResponseEntity<UserVo> respEntity = callRestfulEndPoint(workItem, bpmUserVo,
							sqBpmConfig.getCredentialsFailFlowEndPointDetails().getEndPoint());*/
					
				}else if(nextStepInfo.equalsIgnoreCase(sqBpmConfig.getLoginCredentialsSucessFlow())){ //Steps if good credentials

					try{
						ResponseEntity<UserVo> respEntity = ProcessBPMWorkItemServiceFactory.getProcessBPMWorkItem(
								SqEndPoints.VALIDATE_CREDENTIALS_SUCCESS_ENDPOINT.name()).callRestfulEndPoint(workItem);
						
						
						Boolean nextStep = new Boolean(false);
						if(respEntity.getStatusCode() == HttpStatus.OK){
							if(respEntity.getBody() !=null){
								resultedUserVo = (UserVo)respEntity.getBody();
								nextStep = true;
							}
						}else{
							//throw exception
						}
						
				        ProcessBPMWorkItemServiceFactory.getProcessBPMWorkItem(
								SqEndPoints.VALIDATE_CREDENTIALS_SUCCESS_ENDPOINT.name()).prepareForNextStep(resultedUserVo, nextStep, workItem,manager);
						
					}catch(Exception ex){
						System.out.println("Something wrong in Work Item 1");
					}
				}
				
			}
		}

	}

	@Override
	public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
		// TODO Auto-generated method stub

	}

}
