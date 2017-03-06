package com.starquest.usermgmt.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.starquest.usermgmt.domain.Login;
import com.starquest.usermgmt.domain.User;
import com.starquest.usermgmt.repositories.LoginRepository;
import com.starquest.usermgmt.repositories.UserRepository;
import com.starquest.usermgmt.vo.UserVo;
import com.starquest.usermgmt.vo.hateos.UserHo;

/**
 * @author mallesh
 *
 */
@Service
public class UserBusinessLogicImpl implements UserBusinessLogic {

	Logger logger = Logger.getLogger(UserBusinessLogicImpl.class);
	
	private UserRepository userRepository;
	private LoginRepository loginRepository;
	
	
	@Autowired
	public UserBusinessLogicImpl(UserRepository userRepository, LoginRepository loginRepository) {
		this.userRepository = userRepository;
		this.loginRepository = loginRepository;
	}
	
	/* (non-Javadoc)
	 * @see com.starquest.usermgmt.bizlogic.UserBusinessLogic#saveUser(com.starquest.usermgmt.vo.UserVo)
	 */
	@Override
	public UserVo saveUser(UserVo userVo) {
		
		logger.debug("Entrypoint :: /saveUser");
		
		//TODO
		//validate for duplicates before proceeding
		
		RestTemplate restTeamplate = new RestTemplate();
		String passwordHash = restTeamplate.getForObject("http://localhost:8080/encryption/"+userVo.getPassword(), String.class);
		if(null==passwordHash){
        	passwordHash = userVo.getPassword();
        }
        User user = new User();
		user.setUserId(userVo.getUserId());
		user.setFirstName(userVo.getFirstName());
		user.setLastName(userVo.getLastName());
		user.setEmailAddress(userVo.getEmailAddress());
		user.setCreatedBy(userVo.getCreatedBy());
		user.setCreatedOn(new java.sql.Date(new java.util.Date().getTime()));
		user = userRepository.save(user);
		
		Login login = new Login();
		login.setPasswordSalt("NoSaltYet");
		login.setPasswordHash(passwordHash);
		login.setCreatedBy(userVo.getCreatedBy());
		login.setCreatedOn(new java.sql.Date(new java.util.Date().getTime()));
		login.setUserId(user.getId());
		login.setUser(user);
		loginRepository.save(login);
		logger.debug("Exitpoint :: /saveUser");
		return userVo;
	}

	/* (non-Javadoc)
	 * @see com.starquest.usermgmt.service.UserBusinessLogic#findUserByLastName(java.lang.String)
	 */
	@Override
	public List<UserHo> findUserByLastName(String lastName) {
		
		return convertEntityToHATEOSList(userRepository.findByLastName(lastName));
		
	}

	/* (non-Javadoc)
	 * @see com.starquest.usermgmt.service.UserBusinessLogic#findUserById(java.lang.Integer)
	 */
	@Override
	public UserHo findUserById(Integer id) {
		
		return convertEntityToHATEOSObj(userRepository.findOne(id));
		
	}

	/* (non-Javadoc)
	 * @see com.starquest.usermgmt.service.UserBusinessLogic#findUserByEmail(java.lang.String)
	 */
	@Override
	public List<UserHo> findUserByEmail(String emailAddress) {
		
		return convertEntityToHATEOSList(userRepository.findUserByEmailAddress(emailAddress));
		
	}
	

	/* (non-Javadoc)
	 * @see com.starquest.usermgmt.service.UserBusinessLogic#findAllUsers(int, int)
	 */
	@Override
	public List<UserHo> findAllUsers(int startIndx, int endIndx) {

		//TODO implement Sort Later
		Page<User> users = userRepository.findAll(new PageRequest(startIndx, endIndx));
		return convertEntityToHATEOSList(users.getContent());
	}

	
	//TODO Make it more generic
	private List<UserHo> convertEntityToHATEOSList(List<User> listOfUsers){
		
		List<UserHo> listOfUserHos = new ArrayList<UserHo>();
		for(User user: listOfUsers){
			UserHo userHo = new UserHo(user.getId(),
					user.getUserId(), 
					user.getFirstName(), 
					user.getLastName(),
					user.getEmailAddress(), 
					user.getCreatedBy(), 
					user.getCreatedOn());
			listOfUserHos.add(userHo);
		}
		return listOfUserHos;
	}
	
	
	//TODO Make it more generic
	private UserHo convertEntityToHATEOSObj(User user){
		
		return new UserHo(user.getId(),
						user.getUserId(), 
						user.getFirstName(), 
						user.getLastName(),
						user.getEmailAddress(), 
						user.getCreatedBy(), 
						user.getCreatedOn());
			
			
		}
			
					
	

}
