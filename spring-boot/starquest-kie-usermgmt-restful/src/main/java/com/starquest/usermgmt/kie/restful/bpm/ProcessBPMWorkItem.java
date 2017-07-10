package com.starquest.usermgmt.kie.restful.bpm;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;
import org.springframework.http.ResponseEntity;

import com.starquest.usermgmt.vo.UserVo;

/**
 * 
 * @author mallesh
 * @Since Jul/07/2017
 * 
 */
public interface ProcessBPMWorkItem {
	
	public String getNameOfWorkItem();

	public ResponseEntity<UserVo> callRestfulEndPoint(WorkItem workItem) throws Exception;
	
	public void prepareForNextStep
	(UserVo objToPass, Boolean nextStep,  WorkItem workItem, WorkItemManager manager) throws Exception;
		
}
