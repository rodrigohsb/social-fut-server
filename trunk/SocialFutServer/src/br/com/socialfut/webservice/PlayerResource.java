package br.com.socialfut.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/player")
@Produces(MediaType.APPLICATION_JSON)
public class PlayerResource
{

    @GET
    @Path("/insert/{userId}/{deviceRegId}/{position}")
    public String insert(@PathParam("userId") long userId, @PathParam("deviceRegId") String deviceRegId,
            @PathParam("position") int position)
    {
        new PlayerWS().createPlayer(userId, deviceRegId, position);
        return "OK"; 
    }

    @GET
    @Path("/updateDevice/{userId}/{deviceRegId}")
    public String updateDevice(@PathParam("userId") long userId, @PathParam("deviceRegId") String deviceRegId)
    {
        new PlayerWS().updateDevice(userId, deviceRegId);
        return "OK";
    }

    @GET
    @Path("/getRatingAndPosition/{userId}")
    @Produces("application/json")
    public String getRatingAndPositionById(@PathParam("userId") long userId)
    {
        return new PlayerWS().getRatingAndPosition(userId);
    }

    @GET
    @Path("/getRating/{userId}")
    @Produces("application/json")
    public String getRating(@PathParam("userId") long userId)
    {
        return new PlayerWS().getRating(userId);
    }

    @GET
    @Path("/getPosition/{userId}")
    @Produces("application/json")
    public String getPosition(@PathParam("userId") long userId)
    {
        return new PlayerWS().getPosition(userId);
    }
}
