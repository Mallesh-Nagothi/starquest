package com.starquest.decryption.services;

/**
 * 
 * @author mallesh
 * @since Jul/07/2017
 * 
 */
public interface DecryptionService {

	public String decryptPassword(String password, String encryptAlgorithm);
}
