package nl.hu.v2iac1.rest.resource;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import auth.TokenGen;
import auth.User;
import auth.UserService;
import mail.SendMail;
import nl.hu.v2iac1.Configuration;

@Path("/topsecret")
@Produces(MediaType.TEXT_PLAIN)
public class TopSecretRestService extends AbstractRestService {



	@POST
	@Path("/authenticate")
	public Response authorize(@FormParam("username") String username, @FormParam("password") String password)
			throws URISyntaxException {
		UserService service = new UserService();

		URI uri = new URI("http://localhost:8080/login.html");

		if (service.authenticate(username, password)) {
			TokenGen gen = TokenGen.getInstance();
			String token = gen.generateToken();

			User user = service.getUser(username);

			SendMail.send(user, token);

			return Response.temporaryRedirect(uri).build();
		}

		uri = new URI("http://localhost:8080");
		return Response.temporaryRedirect(uri).build();
	}
	@POST
	 @Path("/{token}")
	 public Response getSecret(@FormParam("token") String token) throws URISyntaxException {

	  URI uri = new URI("http://localhost:8080/rest/topsecret/");
	  
	  TokenGen gen = TokenGen.getInstance();
	  
	  if (!gen.cashToken(token)) {  
	   return Response.temporaryRedirect(uri).build();
	  }
	  
	  String output = "This is very secret: " + configuration.getValue(Configuration.Key.VERYSECRET);
	  return Response.status(200).entity(output).build();

	 }

	@GET
	@Path("/")
	@Produces("text/html")
	public Response authenticate() {
		URI uri = null;
		try {
			uri = new URI("http://localhost:8080/login.html");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return Response.temporaryRedirect(uri).build();

	}

}