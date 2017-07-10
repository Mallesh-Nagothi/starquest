package com.starquest.registration.config;

public enum SqEndPoints {
	
	PASSWORD_DECRYPT_ENDPOINT("passwordDecryptFlow"),
	PASSWORD_DECRYPT_FAIL_ENDPOINT("passwordDecryptFailFlow"),
	PASSWORD_DECRYPT_SUCCESS_ENDPOINT("validateCredentialsFlow"),
	VALIDATE_CREDENTIALS_FAIL_ENDPOINT("loginCredentialsFailFlow"),
	VALIDATE_CREDENTIALS_SUCCESS_ENDPOINT("loginCredentialsSucessFlow"),
	LOGIN_BPM_WORKFLOW_ENDPOINT ("loginBPMWorkflow");
	
	
	//move all other junk also here 
	//and have SQConfig specific getters, so that busy code will be in SQConfig file instead of 
	//spreading into other business logic classes. thanks
	
	
	private String endPoint;
	
	SqEndPoints(String endPoint){
		this.endPoint = endPoint;
	}
	
	public String endPoint(){
		return endPoint;
	}

}
