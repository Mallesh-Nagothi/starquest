/**
 * 
 */
package com.starquest.usermgmt.kie.restful.utils;

import org.apache.log4j.Logger;

import com.starquest.usermgmt.vo.UserVo;

import net.minidev.json.JSONObject;

/**
 * @author mallesh
 *
 */
public class Utilities {
	
	private static final Logger logger = Logger.getLogger(Utilities.class);
	
	public JSONObject convertPojoToJSONObj(Object object) throws Exception{
		logger.debug("START Utilities.convertPojoToJSONObj()");
		JSONObject jSonObj = new JSONObject();
		if(object instanceof UserVo){
			UserVo userVo = (UserVo) object;
			
			//TODO Reflection is not working
			/*
			Field[] fields = UserVo.class.getDeclaredFields();
			for(int k=0; k<fields.length; k++){
				Field field = fields[k];
				field.setAccessible(true);
				jSonObj.put(field.getName(), field.get(userVo).toString());
			}
			
			jSonObj.put(userVo.getEmailAddress(),userVo.getEmailAddress());
			*/
			
			jSonObj.put("firstName", userVo.getFirstName());
			jSonObj.put("lastName", userVo.getLastName());
			jSonObj.put("userId", userVo.getUserId());
			jSonObj.put("emailAddress", userVo.getEmailAddress());
			jSonObj.put("password", userVo.getPassword());
			jSonObj.put("salt", userVo.getSalt());
			jSonObj.put("failCategory", userVo.getFailCategory());
			jSonObj.put("ctegory", userVo.getCategory());
			
		}
		logger.debug("END Utilities.convertPojoToJSONObj()");
		return jSonObj;
	}

	public boolean isValidString(String string){
		boolean isValidString = false;
		if(null!=string && string.length()>0){
			return true;
		}
		return isValidString;
	}
}
