package com.poc.encryption.services;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.poc.encryption.exception.InvalidSeed;

/**
 * 
 * @author mallesh Service for generating Salt, Hashing values
 * 
 * 
 */
public interface SaltGeneratorService {

	String MD5 = "MD5";
	String MD1 = "MD1";
	String SHA1 = "SHA1";
	String SHA512 = "SHA-512";
	String SHA256 = "SHA-256";
	String SHA384 = "SHA-384";
	String SHA1PRNG = "SHA1PRNG";

	public String getCryptoHash(String algorithm, String hashString, byte[] salt)
			throws NoSuchAlgorithmException, InvalidSeed, UnsupportedEncodingException;

	public byte[] getCryptoSalt(int seedNumOfBytes) throws InvalidSeed;

}
