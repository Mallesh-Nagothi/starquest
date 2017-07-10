package com.starquest.usermgmt.kie.restful.service;

import java.util.HashMap;
import java.util.Map;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.runtime.process.WorkflowProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starquest.usermgmt.kie.restful.bpm.SQRESTfulLoginProcessPostWorkItemHandler;
import com.starquest.usermgmt.kie.restful.config.SQBPMConfiguration;
import com.starquest.usermgmt.kie.restful.utils.Utilities;
import com.starquest.usermgmt.vo.UserVo;

import net.minidev.json.JSONObject;

@Service
public class UserLoginKieServiceImpl implements UserLoginKieService {

	private final KieContainer  kieContainer;
	
	@Autowired
	private SQBPMConfiguration	sqBpmConfig;
	
	
	public UserLoginKieServiceImpl(KieContainer  kieContainer){
		this.kieContainer = kieContainer;
	}
	
	@Override
	public UserVo startUserLoginBPMWorkFlow(UserVo userVo) throws Exception {
		KieSession kieSession = kieContainer.newKieSession(sqBpmConfig.getLoginKIESession());
		kieSession.getWorkItemManager().registerWorkItemHandler(sqBpmConfig.getLoginBPMProcessFlowName(), new SQRESTfulLoginProcessPostWorkItemHandler(sqBpmConfig));
		
		
		Utilities utils = new Utilities();
		
		//Preparing request payload to BPM Flow
		JSONObject jsonRequest = utils.convertPojoToJSONObj(userVo);
		
		Map<String, Object> loginFlowParams = new HashMap<String, Object>();
		
		loginFlowParams.put("PAYLOADJSON", jsonRequest);
		loginFlowParams.put("CUSTOMOBJ", userVo);
		loginFlowParams.put("URL", "");
		loginFlowParams.put("OPERATION", "");
		loginFlowParams.put("MEDIATYPE", "");
		loginFlowParams.put("IsNextStep", new Boolean(true));
		loginFlowParams.put("NEXTSTEP", "");
		
		
		
		
		ProcessInstance processInstance = kieSession.startProcess(sqBpmConfig.getLoginBPMProcessFlowNameFullName(),loginFlowParams);
		//@TODO Remove Result in Process as well as in java code
		Map<String, Object> results = (HashMap<String, Object>)((WorkflowProcessInstance) processInstance).getVariable("Result");
		System.out.println("JBPM Processed and returned objects size::"+results.size());
		
		userVo = (UserVo)((WorkflowProcessInstance) processInstance).getVariable("CUSTOMOBJ"); 
		
		return userVo;
	}

}
