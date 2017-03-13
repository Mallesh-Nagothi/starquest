package com.starquest.usermgmt.web.hateos;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.starquest.usermgmt.service.UserBusinessLogic;
import com.starquest.usermgmt.service.UserProfileService;
import com.starquest.usermgmt.utils.StarQuestUtils;
import com.starquest.usermgmt.vo.UserProfile;
import com.starquest.usermgmt.vo.UserVo;
import com.starquest.usermgmt.vo.hateos.UserHo;

/**
 * @author mallesh
 * @version 1.0
 * @since 03/05/2017
 * Registring a new StarQuest user using Spring boot- HATEOS
 * JERSEY or RESTEASY for JAX-RS need to be implemented. Need to remove Spring for 
 * make it more independent
 *  
 *
 */
@RestController
@RequestMapping("/starquest")
public class UserController {

	
	Logger logger = Logger.getLogger(UserController.class);
	
	UserBusinessLogic userBusinessLogic;
	UserProfileService userProfileService;
	
	@Autowired
	public UserController(UserBusinessLogic userBusinessLogic,UserProfileService userProfileService) {
		this.userBusinessLogic = userBusinessLogic;
		this.userProfileService = userProfileService;
	}
	
	
	@RequestMapping(path="/userByLastName/{lastName}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<UserHo>> getUser(
		@PathVariable String lastName){
		
		//TODO 
		//come up with nice way of reporting back requester
		HttpStatus status = HttpStatus.OK;
		List<UserHo> listOfUserHos = userBusinessLogic.findUserByLastName(lastName);
		for(UserHo userHo: listOfUserHos){
			Link selfLink = ControllerLinkBuilder.linkTo(
					ControllerLinkBuilder.methodOn(UserController.class).getUser(lastName)).withSelfRel();
			userHo.add(selfLink);
		}
		
		if(listOfUserHos.isEmpty()){
			status = HttpStatus.NO_CONTENT;
		}
		return new ResponseEntity<List<UserHo>>(listOfUserHos, status);
	}
	
	
	@RequestMapping(path="/userById/{id}", method = RequestMethod.GET)
	public HttpEntity<UserHo> findUserById(
		@PathVariable String id){
		
		//TODO 
		//come up with nice way of reporting back requester
		HttpStatus status = HttpStatus.OK;
		Integer intId = new Integer(0);
		if(null != id && StarQuestUtils.isNumeric(id)){
			intId = new Integer(id);
		}
		UserHo userHo = userBusinessLogic.findUserById(intId);
		userHo.add(ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(
						UserController.class).findUserById(id)).withSelfRel());
		
		if(null == userHo.getUserId()){
			status = HttpStatus.NO_CONTENT;
		}
		return new ResponseEntity<UserHo>(userHo, status);
	}
	
	
	@RequestMapping(path="/userByIdJSON/{id}", method = RequestMethod.GET, 
			consumes={"text/plain"}, 
			produces={"application/json"})
	@CrossOrigin(origins = "http://localhost:9000")
	public UserVo findUserByIdJSON(
		@PathVariable String id){
		
		//TODO 
		//come up with nice way of reporting back requester
		HttpStatus status = HttpStatus.OK;
		Integer intId = new Integer(0);
		if(null != id && StarQuestUtils.isNumeric(id)){
			intId = new Integer(id);
		}
		UserHo userHo = userBusinessLogic.findUserById(intId);
		userHo.add(ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(
						UserController.class).findUserById(id)).withSelfRel());
		
		if(null == userHo.getUserId()){
			status = HttpStatus.NO_CONTENT;
		}
		
		UserVo userVo = new UserVo();
		userVo.setCreatedBy(userHo.getCreatedBy());
		userVo.setCreatedOn(new java.sql.Date(userHo.getCreatedOn().getTime()));
		userVo.setFirstName(userHo.getFirstName());
		userVo.setLastName(userHo.getLastName());
		userVo.setEmailAddress(userHo.getEmailAddress());
		userVo.setUserId(userHo.getUserId());
		
		
		
		
		return userVo;
	}
	
	
	@RequestMapping(path="/userByEmailAddress/{emailAddress}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<UserHo>> findUserByEmailAddress(
		@PathVariable String emailAddress){
		
		//TODO 
		//come up with nice way of reporting back requester
		HttpStatus status = HttpStatus.OK;
		List<UserHo> listOfUserHos = userBusinessLogic.findUserByEmail(emailAddress);
		for(UserHo userHo: listOfUserHos){
			Link selfLink = ControllerLinkBuilder.linkTo(
					ControllerLinkBuilder.methodOn(UserController.class).findUserByEmailAddress(emailAddress)).withSelfRel();
			userHo.add(selfLink);
		}
		
		if(listOfUserHos.isEmpty()){
			status = HttpStatus.NO_CONTENT;
		}
		return new ResponseEntity<List<UserHo>>(listOfUserHos, status);
	}
	
	
	@RequestMapping(path="/findAllUsers/{startIndx}/{endIndx}", method = RequestMethod.GET)
	public HttpEntity<List<UserHo>> findAllUsers(
		@PathVariable int startIndx, @PathVariable int endIndx ){
		
		//TODO 
		//come up with nice way of reporting back requester
		HttpStatus status = HttpStatus.OK;
		List<UserHo> listOfUserHos = userBusinessLogic.findAllUsers(startIndx, endIndx);
		for(UserHo userHo: listOfUserHos){
			Link selfLink = ControllerLinkBuilder.linkTo(
					ControllerLinkBuilder.methodOn(UserController.class).findAllUsers(startIndx,endIndx)).withSelfRel();
			userHo.add(selfLink);
		}
		
		if(listOfUserHos.isEmpty()){
			status = HttpStatus.NO_CONTENT;
		}
		return new ResponseEntity<List<UserHo>>(listOfUserHos, status);
		
		
	}
	
	
	@RequestMapping(value="/getUserProfiles/{size}", method= {RequestMethod.GET})
	public ResponseEntity<List<UserProfile>> getUserProfiles(@PathVariable String size){
		
		Integer totRecordsFetchSize = Integer.valueOf(size);
		
		return new ResponseEntity<List<UserProfile>>(userProfileService.getUserProfileById(totRecordsFetchSize.intValue()), HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value = "/createUserProfile", method = { RequestMethod.POST }, 
			consumes = { "application/json"}, produces = { "application/json",})
	public ResponseEntity<UserProfile> createUserProfile(@RequestBody UserProfile userProfile){
		logger.debug("Entrypoint :: /createUserProfile");
		
		
		
		//Save user profile
		userProfileService.createUserProfile(userProfile);
		
		
		logger.debug("Exitpoint :: /createUserProfile");
		return new ResponseEntity<UserProfile>(userProfile, HttpStatus.OK);
		
	}
	
	
	
	@RequestMapping(value = "/createHouseHold", method = { RequestMethod.POST }, 
			consumes = { "application/json"}, produces = { "application/json",})
	public ResponseEntity<UserProfile> createHouseHold(@RequestBody UserProfile userProfile){
		logger.debug("Entrypoint :: /createHouseHold");
		
		
		
		//Save user profile
		userProfileService.createUserProfile(userProfile);
		
		
		logger.debug("Exitpoint :: /createHouseHold");
		return new ResponseEntity<UserProfile>(userProfile, HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value = "/createMemberIncome", method = { RequestMethod.POST }, 
			consumes = { "application/json"}, produces = { "application/json",})
	public ResponseEntity<UserProfile> createMemberIncome(@RequestBody UserProfile userProfile){
		logger.debug("Entrypoint :: /createMemberIncome");
		
		
		
		//Save user profile
		userProfileService.createUserProfile(userProfile);
		
		
		logger.debug("Exitpoint :: /createMemberIncome");
		return new ResponseEntity<UserProfile>(userProfile, HttpStatus.OK);
		
	}
	
}
