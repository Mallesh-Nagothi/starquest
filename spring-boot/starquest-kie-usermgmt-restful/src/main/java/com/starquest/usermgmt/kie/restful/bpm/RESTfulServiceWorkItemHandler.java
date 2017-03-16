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

import com.starquest.usermgmt.vo.UserProfile;

import net.minidev.json.JSONObject;

/**
 * @author mallesh
 *
 */
public class RESTfulServiceWorkItemHandler implements WorkItemHandler {

	
	public RESTfulServiceWorkItemHandler() {
		super();
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see org.kie.api.runtime.process.WorkItemHandler#executeWorkItem(org.kie.api.runtime.process.WorkItem, org.kie.api.runtime.process.WorkItemManager)
	 */
	@Override
	public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
		
		
		String uri 				= (String) workItem.getParameter("URI");
		String port   			= (String) workItem.getParameter("PORT");
		String operation		= (String) workItem.getParameter("OPERATION");
		String mediaType 		= (String) workItem.getParameter("MEDIATYPE");
		String jSonString		= (String) workItem.getParameter("PAYLOADJSON");
		JSONObject jsonRequest 	= (JSONObject) workItem.getParameter("PAYLOADPOJO");
		
		
		System.out.println("WorkItem.getId() -->"+workItem.getId());
		System.out.println("WorkItem.getProcessInstanceId-->"+workItem.getProcessInstanceId());
		
		System.out.println("URI="+uri+" port="+port+" operation="+operation+" mediaType="+mediaType+" jSonString="+jSonString);
		Iterator<Entry<String, Object>> itr = jsonRequest.entrySet().iterator();
		while(itr.hasNext()){
			Map.Entry<String,Object> entry = (Map.Entry<String, Object>) itr.next();
			System.out.println("Key  = " + entry.getKey() + " Value=" + entry.getValue());
		}
		
		
		UserProfile userProfile = new UserProfile();
		
		// Notify manager that work item has been completed and return results  
        Map<String, Object> results = new HashMap<String, Object>();
        
        
        
        
        Map<String,Object> processedResults = new HashMap<String, Object>();
        processedResults.put("URI","[PROCESEED-BY-JBMP]"+uri);
        processedResults.put("PORT","[PROCESEED-BY-JBMP]"+port);
        processedResults.put("OPERATION","[PROCESEED-BY-JBMP]"+operation);
        processedResults.put("MEDIATYPE","[PROCESEED-BY-JBMP]"+mediaType);
        processedResults.put("PAYLOADJSON","[PROCESEED-BY-JBMP]"+jSonString);
        processedResults.put("PAYLOADJSON","[PROCESEED-BY-JBMP]"+jsonRequest);
                
        /*processedResults.put("from", new String(from+" Processed by jBPM"));
        processedResults.put("to", new String(to+" Processed by jBPM"));
        processedResults.put("message", new String(message+" Processed by jBPM"));
        processedResults.put("priority", new String(priority+" Processed by jBPM"));
        
        results.put("Result", processedResults);
         */        
        
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		
		HttpEntity<String> entity = new HttpEntity<String>(jsonRequest.toString() ,httpHeaders);
		
		RestTemplate restTeamplate = new RestTemplate();
		ResponseEntity<UserProfile> respEntity = 
				restTeamplate.exchange(uri,HttpMethod.POST, entity, UserProfile.class);
		
		if(respEntity.getStatusCode() == HttpStatus.OK){
			UserProfile filteredNewUserProfile = respEntity.getBody();
			userProfile.setUserProfileStatus(filteredNewUserProfile.getUserProfileStatus());
		}
		
		
		processedResults.put("USERPROFILE", userProfile);
        
        
        
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
