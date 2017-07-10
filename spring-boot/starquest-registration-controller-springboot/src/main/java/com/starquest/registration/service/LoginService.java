package com.starquest.registration.service;

import com.starquest.usermgmt.vo.UserVo;

/**
 * 
 * @author mallesh
 * @since  Jul/01st/2017
 * Provides Login related services
 */
public interface LoginService {
	
	public UserVo callLogMeInBPMService(UserVo loginUser) throws Exception;

}
