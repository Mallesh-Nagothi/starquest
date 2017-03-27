/**
 * 
 */
package com.starquest.usermgmt.kie.restful.service;

import com.starquest.usermgmt.vo.UserVo;

/**
 * @author mallesh
 *
 */
public interface UserRegistrationKieService {

	
	public UserVo startRegistrationBPMWorkflow(UserVo userVo) throws Exception;
	public UserVo applySQPasswordRules(UserVo userVo) throws Exception;
	public boolean processRegistrationRulesFail(UserVo userVo) throws Exception;
	public boolean processRegistrationEncryptionFail(UserVo userVo) throws Exception;
	public boolean processRegistrationPersistFail(UserVo userVo) throws Exception;
	
	
}
