package com.starquest.usermgmt.kie.restful.service;

import com.starquest.usermgmt.vo.UserVo;

/**
 * 
 * @author mallesh
 * @since Jun/07/2017
 */
public interface UserLoginKieService {
	
	public UserVo startUserLoginBPMWorkFlow(UserVo userVo) throws Exception;
	

}
