package br.com.socialfut.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.socialfut.exception.NoContentException;
import br.com.socialfut.persistence.Player;

@Path("/player")
public class PlayerResource
{
    PlayerWS playerWS = new PlayerWS();

    @GET
    @Path("/search/{userId}")
    @Produces("application/json")
    public Player getPlayer(@PathParam("userId") long userId)
    {
        Player player = playerWS.getPlayer(userId);

        if (player == null)
        {
            throw new NoContentException("Player not found!");
        }
        return player;
    }

    @GET
    @Path("/insert/{userId}/{deviceRegId}/{position}")
    public String insert(@PathParam("facebookId") long userId, @PathParam("deviceRegId") String deviceRegId,
            @PathParam("position") int position)
    {
        playerWS.createPlayer(userId, deviceRegId, position);
        return "OK";
    }

    @GET
    @Path("/updateDevice/{userId}/{deviceRegId}")
    public String updateDevice(@PathParam("userId") long userId, @PathParam("deviceRegId") String deviceRegId)
    {
        playerWS.updateDevice(userId, deviceRegId);
        return "OK";
    }

    @GET
    @Path("/updateRating/{userId}/{rating}")
    public String updateRating(@PathParam("userId") long userId, @PathParam("rating") float rating)
    {
        playerWS.updateRating(userId, rating);
        return "OK";
    }

    @GET
    @Path("/getRating/{userId}")
    public String getRating(@PathParam("userId") long userId)
    {
        playerWS.getRating(userId);
        return "OK";
    }

    @GET
    @Path("/getPosition/{userId}")
    public String getPosition(@PathParam("userId") long userId)
    {
        playerWS.getPosition(userId);
        return "OK";
    }
}
