/**
 * 
 */
package com.starquest.encryption.restservices;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

import com.starquest.encryption.CryptoPrgSecureRandomNumberGenerator;
import com.starquest.encryption.NativeSaltGenerator;
import com.starquest.encryption.PasswordEncryption;
import com.starquest.encryption.exception.InvalidSeed;
import com.starquest.encryption.exception.UnsupportedAlgorithm;
import com.starquest.encryption.services.EncryptionService;
import com.starquest.encryption.services.SaltGeneratorService;
import com.starquest.encryption.services.SaltType;

/**
 * @author mallesh
 *
 */
public class WildflySwarmEntry  {

	public static void main(String[] args) throws Exception {
		
		/*Package packages = Package.getPackage("com.starquest.encryption");*/
		Swarm swarm = new Swarm();
		
		JAXRSArchive archive = ShrinkWrap.create(JAXRSArchive.class);
		archive.addResource(EncryptionRESTfulService.class);/*.addPackages(true, packages);*/
		archive.addClass(CryptoPrgSecureRandomNumberGenerator.class);
		archive.addClass(NativeSaltGenerator.class);
		archive.addClass(PasswordEncryption.class);
		archive.addClass(InvalidSeed.class);
		archive.addClass(UnsupportedAlgorithm.class);
		archive.addClass(EncryptionService.class);
		archive.addClass(SaltGeneratorService.class);
		archive.addClass(SaltType.class);
		swarm.start();
		swarm.deploy(archive);
	}
	
}
