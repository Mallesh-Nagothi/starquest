/**
 * 
 */
package com.starquest.encryption.restservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.starquest.encryption.PasswordEncryption;
import com.starquest.encryption.services.EncryptionService;
import com.starquest.encryption.services.SaltGeneratorService;
import com.starquest.encryption.services.SaltType;

/**
 * @author mallesh
 * @since  Feb 27 2017
 * @version 1.0
 * 
 * 
 */

@Path("encryption")
public class EncryptionRESTfulService {

	@GET
	@Path("/{param}")
	public Response getEncryptedValue(@PathParam("param") String rawValue){
		
		/* inject validator here and do all validations for password,
		 * possibly add one more parameter that tells type of rawValue whether its password or not
	    */
		if(rawValue == null){
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		
		try{
			EncryptionService encryptionService = new PasswordEncryption();
			rawValue = encryptionService.encryptPassword(rawValue, SaltType.NON_NATIVE, SaltGeneratorService.SHA1, 32);
		}catch(Exception ex){
			//bad way of programming
			rawValue = ex.toString();
			System.out.println(ex.toString());
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return Response.status(200).entity(rawValue).build();
	}
	
}
