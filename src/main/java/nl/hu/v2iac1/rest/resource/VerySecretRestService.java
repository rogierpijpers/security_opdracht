package nl.hu.v2iac1.rest.resource;

import nl.hu.v2iac1.Configuration;



import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/verysecret")
@Produces(MediaType.TEXT_PLAIN)
public class VerySecretRestService extends AbstractRestService {
	
	String clientID = "670282150821-0kd0pjsm09re6mtvjdm5s9ejek3hc4a0.apps.googleusercontent.com";
	String clientsecret = "wEWpecZ0wi2Npw44WS8xceof";
	String scope = "https://www.googleapis.com/auth/drive.metadata.readonly";
	String redirectURI = "http:localhost:8080/oauth2callback";
	
    @GET
    @Path("/")
    public Response getSecret() {
//    	if(!("credentials" in session)){
    		
//    	}
    	
        String output = "This is very secret: " + configuration.getValue(Configuration.Key.VERYSECRET);
        return Response.status(200).entity(output).build();

    }
    @GET
    @Path("/oauth2callback")
    public Response callBack(){
    	
		return null;
    	
    }
    

    
    /*
     https://accounts.google.com/o/oauth2/v2/auth?
     client_id=670282150821-0kd0pjsm09re6mtvjdm5s9ejek3hc4a0.apps.googleusercontent.com
     &response_type=code
     &scope=openid%20email
     &state=http://localhost:8080/rest/verysecret
     &acces_type=offline
     &redirect_uri=http%3A%2F%2Flocalhost:8080%2Frest%2Fverysecret
    	 
    	 */
}