package com.poc.encryption;

/**
 * 
 * @author Mallesh
 * @since 02/26/2017
 * @version ver 1.0
 * 
 */
public abstract class NativeSaltGenerator {

	/**
	 * 
	 * @return Salt - Salt Generation logic hidden intentionally
	 * 
	 */

	public static final native int getSalt();

	static {
		//System.loadLibrary("SaltGenerator");

	}

}
