package com.starquest.encryption;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.starquest.encryption.exception.InvalidSeed;
import com.starquest.encryption.exception.UnsupportedAlgorithm;

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
