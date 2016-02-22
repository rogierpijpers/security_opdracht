package nl.hu.v2iac1.rest.resource;

import nl.hu.v2iac1.Configuration;
import java.net.URI;
import java.net.URISyntaxException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/verysecret")
@Produces(MediaType.TEXT_PLAIN)
public class VerySecretRestService extends AbstractRestService {

	String clientID = "670282150821-0kd0pjsm09re6mtvjdm5s9ejek3hc4a0.apps.googleusercontent.com";
	String clientsecret = "wEWpecZ0wi2Npw44WS8xceof";

	@GET
	@Path("/{token}")
	public Response getSecret(@PathParam("token") String token) throws URISyntaxException {

		URI uri = new URI("http://localhost:8080/rest/verysecret/authenticate");

		if (!token.equals(clientsecret)) {

			return Response.temporaryRedirect(uri).build();
		}
		String output = "This is very secret: " + configuration.getValue(Configuration.Key.VERYSECRET);
		return Response.status(200).entity(output).build();

	}

	@GET
	@Path("/authenticate")
	@Produces("text/html")
	public Response authenticate() {
		URI uri = null;
		try {
			uri = new URI("https://accounts.google.com/o/oauth2/v2/auth?"
					+ "client_id=670282150821-0kd0pjsm09re6mtvjdm5s9ejek3hc4a0.apps.googleusercontent.com&"
					+ "response_type=code" + "&scope=openid%20email&" + "state=http://localhost:8080&"
					+ "acces_type=online&"
					+ "redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Frest%2Fverysecret%2Fauthorize");
		} catch (URISyntaxException e) {

			e.printStackTrace();
		}
		return Response.temporaryRedirect(uri).build();

	}

	@GET
	@Path("/authorize")
	public Response authorize(@Context UriInfo uriInfo) throws URISyntaxException {

		URI uri = new URI("http://localhost:8080");
		String link = uriInfo.getRequestUri().toString();

		if (link.contains("error")) {
			// denied

			return Response.temporaryRedirect(uri).build();
		}

		uri = new URI("http://localhost:8080/rest/verysecret/" + clientsecret);
		return Response.temporaryRedirect(uri).build();
	}

}