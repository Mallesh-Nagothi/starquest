package com.starquest.encryption.test;

import com.starquest.encryption.CryptoPrgSecureRandomNumberGenerator;
import com.starquest.encryption.PasswordEncryption;
import com.starquest.encryption.SaltGeneratorService;
import com.starquest.encryption.SaltType;

/**
 * 
 * @author mallesh This class for testing Salt Generation
 *
 */
public class SaltGeneratorTest {

	public static void main(String[] args) {
		try {
			// System.out.println("Slat::"+NativeSaltGenerator.getSalt());

			CryptoPrgSecureRandomNumberGenerator cPrng = new CryptoPrgSecureRandomNumberGenerator();
			System.out.println("CryptoSlat::" + cPrng.getCryptoSalt(32));

			System.out.println("CryptoHash-SHA1::"
					+ cPrng.getCryptoHash(SaltGeneratorService.SHA1, "Mallesh", cPrng.getCryptoSalt(32)));
			System.out.println("CryptoHash-SHA256::"
					+ cPrng.getCryptoHash(SaltGeneratorService.SHA256, "Mallesh", cPrng.getCryptoSalt(32)));
			System.out.println("CryptoHash-SHA384::"
					+ cPrng.getCryptoHash(SaltGeneratorService.SHA384, "Mallesh", cPrng.getCryptoSalt(32)));
			System.out.println("CryptoHash-SHA512::"
					+ cPrng.getCryptoHash(SaltGeneratorService.SHA512, "Mallesh", cPrng.getCryptoSalt(32)));

			/*
			 * System.out.println("CryptoHash-MD2::"+cPrng.getCryptoHash(
			 * SaltGenerators.MD1, "Mallesh"));
			 * System.out.println("CryptoHash-MD5::"+cPrng.getCryptoHash(
			 * SaltGenerators.MD5, "Mallesh"));
			 */

			PasswordEncryption passwordEncryption = new PasswordEncryption();
			System.out.println("Password(c0nt1nEnt**)  Encrypted As(" + passwordEncryption
					.encryptPassword("c0nt1nEnt**", SaltType.NON_NATIVE, SaltGeneratorService.SHA256, 32) + ")");

		} catch (Exception ex) {
			System.out.print(ex.toString());
		}

	}

}