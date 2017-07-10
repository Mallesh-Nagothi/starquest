package com.starquest.usermgmt.kie.restful.bpm;

import java.util.HashMap;
import java.util.Map;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.starquest.registration.config.SqEndPoints;
import com.starquest.usermgmt.kie.restful.config.SQBPMConfiguration;
import com.starquest.usermgmt.kie.restful.utils.Utilities;
import com.starquest.usermgmt.vo.UserVo;

import net.minidev.json.JSONObject;

@Component
public class CredentialsFailBPMWorkItem implements ProcessBPMWorkItem {

	private final String workItemName = SqEndPoints.VALIDATE_CREDENTIALS_FAIL_ENDPOINT.name();
	
	@Autowired
	private SQBPMConfiguration	sqBpmConfig;
	
	@Override
	public ResponseEntity<UserVo> callRestfulEndPoint(WorkItem workItem) throws Exception {
		// @TODO remove hard coding here use mediaType Parameter here -Mallesh 
		//Please fix me guys
		Utilities utils = new Utilities();
		JSONObject jsonRequest = (JSONObject) workItem.getParameter("PAYLOADJSON");
		UserVo bpmUserVo = (UserVo) workItem.getParameter("CUSTOMOBJ");
		
		try{
			jsonRequest = utils.convertPojoToJSONObj(bpmUserVo);
			
		}catch(Exception ex){
			System.out.println("exception during conversion to JSON in workitem..");
		}
		
        HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(jsonRequest.toString() ,httpHeaders);
		RestTemplate restTeamplate = new RestTemplate();
		
		return restTeamplate.exchange(
				sqBpmConfig.getCredentialsFailFlowEndPointDetails().getUrl(),HttpMethod.POST, entity, UserVo.class);
	}

	@Override
	public void prepareForNextStep(UserVo objToPass, Boolean nextStep, WorkItem workItem,
			WorkItemManager manager) throws Exception {
		Map<String, Object> results = new HashMap<String, Object>();
        Map<String,Object> processedResults = new HashMap<String, Object>();
        Utilities utils = new Utilities();

        //no need to check nextstep..just end it
        String workFlowStep = sqBpmConfig.getLoginFlowEnd();
        
        processedResults.put("NEXTSTEP", workFlowStep);
        results.put("Result", processedResults);
        results.put("CUSTOMOBJ", objToPass);
        try{ 
        	results.put("PAYLOADJSON", utils.convertPojoToJSONObj(objToPass)); 
        }catch(Exception ex){ 
        	System.out.println(ex); 
        }
        results.put("isNextStep", nextStep );
        results.put("NEXTSTEP",workFlowStep);
        manager.completeWorkItem(workItem.getId(), results);

	}

	@Override
	public String getNameOfWorkItem() {
		return workItemName;
	}

	public String getWorkItemName() {
		return workItemName;
	}
	

}
