package com.starquest.encryption;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import com.starquest.encryption.exception.InvalidSeed;

/**
 * 
 * @author mallesh
 * @since Feb 27, 2017-8:43:10 PM
 * @see com.starquest.encryption.SaltGeneratorService - Implementation for
 *      SaltGeneratorService
 */
public class CryptoPrgSecureRandomNumberGenerator implements SaltGeneratorService {

	
	public String getCryptoHash(String algorithm, String hashString, byte[] salt)
			throws NoSuchAlgorithmException, InvalidSeed, UnsupportedEncodingException {

		String hexString = "";
		MessageDigest md = MessageDigest.getInstance(algorithm);
		byte[] hashedString = md.digest(hashString.getBytes());
		md.reset();
		byte[] buffer = md.digest(hashedString);
		md.reset();
		md.update(buffer);
		md.update(salt);
		byte[] digest = md.digest();

		for (int i = 0; i < digest.length; i++) {
			hexString += Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1);
		}

		return hexString;
	}

	
	public byte[] getCryptoSalt(int seedNumOfBytes) throws InvalidSeed {
		if (seedNumOfBytes > 32) {
			throw new InvalidSeed();
		}
		return new SecureRandom().generateSeed(seedNumOfBytes);
	}

}
