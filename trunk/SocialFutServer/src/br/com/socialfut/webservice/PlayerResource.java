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
    @Path("/search/{id}")
    @Produces("application/json")
    public Player getPlayer(@PathParam("id") long id)
    {
        Player player = playerWS.getPlayer(id);

        if (player == null)
        {
            throw new NoContentException("Player not found!");
        }
        return player;
    }

    @GET
    @Path("/insert/{facebookId}/{deviceRegId}/{position}")
    public String insert(@PathParam("facebookId") long facebookId, @PathParam("deviceRegId") String deviceRegId,
            @PathParam("position") int position)
    {
        playerWS.createPlayer(facebookId, deviceRegId, position);
        return "OK";
    }
}
