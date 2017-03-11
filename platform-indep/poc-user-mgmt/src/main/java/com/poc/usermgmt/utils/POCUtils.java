/**
 * 
 */
package com.poc.usermgmt.utils;

/**
 * @author mallesh
 *
 */
public class POCUtils {

	
	public static final boolean isNumeric(String strValue){
		boolean isNum = false;
		if (null!=strValue){
			isNum = strValue.matches("[-+]?\\.?\\d+");
		}
		return isNum;
	}
}
