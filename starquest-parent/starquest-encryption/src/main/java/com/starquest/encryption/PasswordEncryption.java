package com.starquest.encryption;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.starquest.encryption.exception.InvalidSeed;
import com.starquest.encryption.exception.UnsupportedAlgorithm;

/**
 * 
 * @author Mallesh
 * @since Feb 27, 2017-8:04:37 PM
 */
public class PasswordEncryption implements EncryptionService {

	CryptoPrgSecureRandomNumberGenerator CPSRGenerator;

	/**
	 * Constructor
	 * 
	 * @param argCPSRGenerator
	 */
	public PasswordEncryption(CryptoPrgSecureRandomNumberGenerator argCPSRGenerator) {
		this.CPSRGenerator = argCPSRGenerator;
	}

	/**
	 * 
	 * Default Constructor
	 * 
	 */
	public PasswordEncryption() {
		if (null == CPSRGenerator) {
			this.CPSRGenerator = new CryptoPrgSecureRandomNumberGenerator();
		}
	}

	/**
	 * TODO 1) Move validations outside the actual implementation 2) Provide
	 * easy implementation of this method
	 */
	public String encryptPassword(String password, SaltType saltType, String encryptAlgorithm, int seedNumOfBytes)
			throws NoSuchAlgorithmException, InvalidSeed, UnsupportedEncodingException, UnsupportedAlgorithm {

		String encryptedPassword = null;

		if (SaltType.NON_NATIVE != saltType) {
			throw new InvalidSeed();
		}

		if (SaltGeneratorService.SHA1 != encryptAlgorithm) {
			throw new UnsupportedAlgorithm();
		}
		if (seedNumOfBytes > 32) {
			throw new InvalidSeed();
		}

		if (SaltType.NON_NATIVE == saltType && SaltGeneratorService.SHA1.equals(encryptAlgorithm)) {
			encryptedPassword = CPSRGenerator.getCryptoHash(SaltGeneratorService.SHA1, password,CPSRGenerator.getCryptoSalt(seedNumOfBytes));
		}

		return encryptedPassword;

	}

}
