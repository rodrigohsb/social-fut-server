package br.com.socialfut.webservice;

import java.util.ArrayList;

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
        Player player = playerWS.buscar(id);

        if (player == null)
        {
            throw new NoContentException("Player not found!");
        }
        return player;
    }

    @GET
    @Path("/buscarTodos")
    @Produces("application/json")
    public ArrayList<Player> getAllPlayers()
    {
        return (ArrayList<Player>) playerWS.getAllPlayers();
    }

    @GET
    @Path("/position/{id}/{position}")
    public String setPosition(@PathParam("id") long userId, @PathParam("position") int position)
    {
        playerWS.setPosition(userId, position);
        return "OK";
    }
    
    @GET
    @Path("/insert/{facebookId}/{deviceRegId}")
    public String insert(@PathParam("facebookId") long facebookId, @PathParam("deviceRegId") String deviceRegId)
    {
        playerWS.createPlayer(facebookId, deviceRegId);
        return "OK";
    }
}
