package br.com.socialfut.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.socialfut.utils.Constants;

@Path(Constants.SLASH + Constants.PLAYER)
@Produces(MediaType.APPLICATION_JSON)
public class PlayerResource
{

    @GET
    @Path("/insert/{userId}/{deviceRegId}/{position}")
    public String insert(@PathParam("userId") long userId, @PathParam("deviceRegId") String deviceRegId,@PathParam("position") int position)
    {
        return new PlayerWS().createPlayer(userId, deviceRegId, position);
    }

    @GET
    @Path("/updateDevice/{userId}/{deviceRegId}")
    public String updateDevice(@PathParam("userId") long userId, @PathParam("deviceRegId") String deviceRegId)
    {
        return new PlayerWS().updateDevice(userId, deviceRegId);
    }

    @GET
    @Path("/getRatingAndPosition/{userId}")
    public String getRatingAndPositionById(@PathParam("userId") long userId)
    {
        return new PlayerWS().getRatingAndPosition(userId);
    }

    @GET
    @Path("/getRating/{userId}")
    public String getRating(@PathParam("userId") long userId)
    {
        return new PlayerWS().getRating(userId);
    }

    @GET
    @Path("/getPosition/{userId}")
    public String getPosition(@PathParam("userId") long userId)
    {
        return new PlayerWS().getPosition(userId);
    }
}
