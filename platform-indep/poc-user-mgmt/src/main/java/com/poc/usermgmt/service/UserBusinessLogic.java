package com.poc.usermgmt.service;

import java.util.List;

import com.poc.usermgmt.vo.UserVo;
import com.poc.usermgmt.vo.hateos.UserHo;

/**
 * @author mallesh
 *
 */
public interface UserBusinessLogic {
	
	public UserVo saveUser(UserVo userVo);
	
	public List<UserHo> findUserByLastName(String lastName);
	
	public UserHo findUserById(Integer id);
	
	public List<UserHo> findUserByEmail(String email);
	
	public List<UserHo> findAllUsers(int startIndx, int endIndx);
	
	

}
