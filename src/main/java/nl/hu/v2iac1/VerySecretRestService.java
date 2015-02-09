package nl.hu.v2iac1;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/verysecret")
public class VerySecretRestService {

    @GET
    @Path("/")
    public Response getSecret() {

        String output = "This is very secret!";
        return Response.status(200).entity(output).build();

    }

}