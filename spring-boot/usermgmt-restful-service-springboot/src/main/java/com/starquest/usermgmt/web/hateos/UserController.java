package com.starquest.usermgmt.web.hateos;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.starquest.usermgmt.service.UserBusinessLogic;
import com.starquest.usermgmt.utils.StarQuestUtils;
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
	
	@Autowired
	public UserController(UserBusinessLogic userBusinessLogic) {
		this.userBusinessLogic = userBusinessLogic;
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
	
}
