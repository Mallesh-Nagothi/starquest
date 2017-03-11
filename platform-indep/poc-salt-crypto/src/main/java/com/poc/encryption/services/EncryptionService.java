package com.poc.encryption.services;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.poc.encryption.exception.InvalidSeed;
import com.poc.encryption.exception.UnsupportedAlgorithm;

/**
 * 
 * @author mallesh
 * @since Feb 27, 2017-8:01:18 PM
 * 
 */
public interface EncryptionService {

	public String encryptPassword(String password, SaltType saltType, String encryptAlgorithm, int seedNumOfBytes)
			throws NoSuchAlgorithmException, InvalidSeed, UnsupportedEncodingException, UnsupportedAlgorithm;

}
