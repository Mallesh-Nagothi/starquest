package com.starquest.usermgmt.kie.restful.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author mallesh
 * @since Jul/07/2017
 */

@Service
public class ProcessBPMWorkItemServiceFactory {

	@Autowired
	private List<ProcessBPMWorkItem> processBPMWorkItems;
	
	private static final Map<String, ProcessBPMWorkItem> processBPMWorkItemsCache = new HashMap<>();
	
	
	@PostConstruct
	public void initProcessBPMWorkItemsCache(){
		for(ProcessBPMWorkItem processBPMWorkItem: processBPMWorkItems){
			processBPMWorkItemsCache.put(processBPMWorkItem.getNameOfWorkItem(), processBPMWorkItem);
		}
	}
	
	
	public static ProcessBPMWorkItem getProcessBPMWorkItem(String bpmWorkItem){
		ProcessBPMWorkItem processBPMWorkItem = processBPMWorkItemsCache.get(bpmWorkItem);
		if(null==processBPMWorkItem) throw new RuntimeException("Unknown ProcessBPMWorkItem" + bpmWorkItem);
		return processBPMWorkItem;
	}
	
	
}
