package hello;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

public class Main{

	public static void main(String[] args) throws Exception{
		Swarm swarm = new Swarm();
		swarm.start();

		JAXRSArchive archive = ShrinkWrap.create(JAXRSArchive.class);
		archive.addResource(GreetingResource.class);
		swarm.deploy(archive);
	}
}
