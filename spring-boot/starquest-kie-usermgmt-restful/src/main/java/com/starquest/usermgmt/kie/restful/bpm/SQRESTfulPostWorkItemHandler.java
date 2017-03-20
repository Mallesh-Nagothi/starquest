/**
 * 
 */
package com.starquest.usermgmt.kie.restful.bpm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.starquest.usermgmt.vo.UserVo;

import net.minidev.json.JSONObject;

/**
 * @author mallesh
 *
 */
public class SQRESTfulPostWorkItemHandler implements WorkItemHandler {

	/**
	 * Constructor
	 */
	public SQRESTfulPostWorkItemHandler() {
		super();
	}
	
	
	/* (non-Javadoc)
	 * @see org.kie.api.runtime.process.WorkItemHandler#executeWorkItem(org.kie.api.runtime.process.WorkItem, org.kie.api.runtime.process.WorkItemManager)
	 */
	@Override
	public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {

		String uri 				= (String) workItem.getParameter("URL");
		HttpMethod operation	= (HttpMethod) workItem.getParameter("OPERATION");
		MediaType mediaType 	= (MediaType) workItem.getParameter("MEDIATYPE");
		JSONObject jsonRequest 	= (JSONObject) workItem.getParameter("PAYLOADJSON");
		UserVo bpmUserVo			= (UserVo) workItem.getParameter("USERVO");
		
		
		System.out.println("WorkItem.getId() -->"+workItem.getId());
		System.out.println("WorkItem.getProcessInstanceId-->"+workItem.getProcessInstanceId());
		
		//System.out.println("URL="+uri+" operation="+operation+" mediaType="+mediaType);
		Iterator<Entry<String, Object>> itr = jsonRequest.entrySet().iterator();
		while(itr.hasNext()){
			Map.Entry<String,Object> entry = (Map.Entry<String, Object>) itr.next();
			System.out.println("Key  = " + entry.getKey() + " Value=" + entry.getValue());
		}
		
		// Notify manager that work item has been completed and return results
		UserVo userVo =  new UserVo();
        Map<String, Object> results = new HashMap<String, Object>();
        Map<String,Object> processedResults = new HashMap<String, Object>();
		
		// @TODO remove hard coding here use mediaType Parameter here -Mallesh
        HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);  
		
		HttpEntity<String> entity = new HttpEntity<String>(jsonRequest.toString() ,httpHeaders);
		
		RestTemplate restTeamplate = new RestTemplate();
		
		//@TODO remove HttpMethod.POST hardcoding in the following line, use parameter - Mallesh 
		ResponseEntity<UserVo> respEntity = 
				restTeamplate.exchange(uri,HttpMethod.POST, entity, UserVo.class);
		
		if(respEntity.getStatusCode() == HttpStatus.OK){
			UserVo filteredNewUserProfile = respEntity.getBody();
			userVo.setFailCategory(filteredNewUserProfile.getFailCategory());
			userVo.setCategory(filteredNewUserProfile.getCategory());
		}
		
		
		processedResults.put("USERVO", userVo);
        results.put("Result", processedResults);
        System.out.println("Results Size="+results.size());
		manager.completeWorkItem(workItem.getId(), results);  
		System.out.println("Handler Completed..........................");
		
	}

	/* (non-Javadoc)
	 * @see org.kie.api.runtime.process.WorkItemHandler#abortWorkItem(org.kie.api.runtime.process.WorkItem, org.kie.api.runtime.process.WorkItemManager)
	 */
	@Override
	public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
		// TODO Auto-generated method stub

	}

}
