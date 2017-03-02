package hello;

import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("/")
public class GreetingResource{

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GET
	@Path("/greeting")
	@Produces(MediaType.APPLICATION_JSON)
	public String greeting(@QueryParam("name") String name){
		if(name == null || name.equals("")){
			name = "World";
		}
		return "{\"id:\"" + counter.incrementAndGet()+ ",\"content\":" + String.format(template, name) + "}";
	}
}
