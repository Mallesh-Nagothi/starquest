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
		
		
		//workItem.s
		
		// Notify manager that work item has been completed and return results  
        Map<String, Object> results = new HashMap<String, Object>();
        
        Map<String,Object> processedResults = new HashMap<String, Object>();
        
        /*processedResults.put("from", new String(from+" Processed by jBPM"));
        processedResults.put("to", new String(to+" Processed by jBPM"));
        processedResults.put("message", new String(message+" Processed by jBPM"));
        processedResults.put("priority", new String(priority+" Processed by jBPM"));
        
        results.put("Result", processedResults);
         */        
        
        
		
        manager.completeWorkItem(workItem.getId(), results);  
		
		
		
		

	}

	/* (non-Javadoc)
	 * @see org.kie.api.runtime.process.WorkItemHandler#abortWorkItem(org.kie.api.runtime.process.WorkItem, org.kie.api.runtime.process.WorkItemManager)
	 */
	@Override
	public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
		// TODO Auto-generated method stub

	}

}
