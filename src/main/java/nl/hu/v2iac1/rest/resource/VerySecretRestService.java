package nl.hu.v2iac1.rest.resource;

import nl.hu.v2iac1.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl;


@Path("/verysecret")
@Produces(MediaType.TEXT_PLAIN)
public class VerySecretRestService extends AbstractRestService {
	
	String clientID = "670282150821-0kd0pjsm09re6mtvjdm5s9ejek3hc4a0.apps.googleusercontent.com";
	String clientsecret = "wEWpecZ0wi2Npw44WS8xceof";

	
    @GET
    @Path("/")
    public Response getSecret(@Context UriInfo uriInfo) throws URISyntaxException {
//    	String link = uriInfo.getRequestUri().toString();
//    	URI uri = new URI("http://localhost:8080/rest/verysecret/authenticate");
//    	if(!(link.equals("http://localhost:8080/rest/verysecret#"))){
//    		return Response.temporaryRedirect(uri).build();
//    	}
        String output = "This is very secret: " + configuration.getValue(Configuration.Key.VERYSECRET);
        return Response.status(200).entity(output).build();

    }
    @GET
    @Path("/authenticate")
    @Produces("text/html")
    public Response authenticate(){
    	URI uri = null;
		try {
			uri = new URI("https://accounts.google.com/o/oauth2/v2/auth?"+
		"client_id=670282150821-0kd0pjsm09re6mtvjdm5s9ejek3hc4a0.apps.googleusercontent.com&"+
		"response_type=code"+
		"&scope=openid%20email&"+
		"state=http://localhost:8080&"+
		"acces_type=online&"+
		"redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Frest%2Fverysecret%2Fauthorize");
		} catch (URISyntaxException e) {
			 
			e.printStackTrace();
		}
		return Response.temporaryRedirect(uri).build();
		
    	
		
		
    }
    @GET
    @Path("/authorize")
    public Response authorize(@QueryParam("code")String code,@QueryParam("state")String state,@Context Request request,@Context UriInfo uriInfo) throws URISyntaxException{
    	
    	URI uri = new URI("http://localhost:8080");
		String link = uriInfo.getRequestUri().toString();
		
    	
    	
    	
    	if(link.contains("error")){
    		//denied
    		
    		return Response.temporaryRedirect(uri).build();
    	}
    	
    		//request token

    	
//    	try {
//			uri = new URI("https://www.googleapis.com/oauth2/v4/token?"+
//    	"code=4/EDxem0UwFLAT2xY8eMeO9nmNO9z-f6rD7zlkxAj5g3o"+
//		"client_id=670282150821-0kd0pjsm09re6mtvjdm5s9ejek3hc4a0.apps.googleusercontent.com"+
//    	"client-secret=wEWpecZ0wi2Npw44WS8xceof"+
//		"redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Frest%2Fverysecret"+
//    	"grant_type=authorization_code");
//		
//		} catch (URISyntaxException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//
//    	return Response.temporaryRedirect(uri).build();
    	uri = new URI("http://localhost:8080/rest/verysecret");
    	return Response.temporaryRedirect(uri).build();
}

    
    /*
     https://accounts.google.com/o/oauth2/v2/auth?client_id=670282150821-0kd0pjsm09re6mtvjdm5s9ejek3hc4a0.apps.googleusercontent.com&response_type=code&scope=openid%20email&state=http://localhost:8080/rest/verysecret&acces_type=offline&redirect_uri=http%3A%2F%2Flocalhost:8080%2Frest%2Fverysecret    	 
    	 
    code=4/EDxem0UwFLAT2xY8eMeO9nmNO9z-f6rD7zlkxAj5g3o
    
    
    
    	 */
}