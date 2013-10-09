package br.com.socialfut.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("/game")
public class GameResource
{
    GameWS gameWS = new GameWS();

    @GET
    @Path("/creatGame/{gameId}")
    public ResponseBuilder creatGame(@PathParam("gameId") long gameId)
    {
        gameWS.creatGame(gameId);

        return Response.ok();
    }

    @GET
    @Path("/addPlayerToGame/{gameId}/{userId}")
    public ResponseBuilder addPlayerToGame(@PathParam("gameId") long gameId, @PathParam("userId") long userId)
    {
        gameWS.addPlayerToGame(gameId, userId);
        return Response.ok();
    }

    @GET
    @Path("/removePlayerFromGame/{gameId}/{userId}")
    public ResponseBuilder removePlayerFromGame(@PathParam("gameId") long gameId, @PathParam("userId") long userId)
    {
        gameWS.removePlayerFromGame(gameId, userId);
        return Response.ok();
    }

    @GET
    @Path("/rateByGame/{facebookId}/{gameId}")
    @Produces("application/json")
    public float getRateByGame(@PathParam("facebookId") long facebookId, @PathParam("gameId") long gameId)
    {
        return gameWS.getRateByGame(gameId, facebookId);
    }

    @GET
    @Path("/getRatesByGame/{gameId}")
    @Produces("application/json")
    public List<Integer> getRatesByGame(@PathParam("gameId") long gameId)
    {
        return gameWS.getRatesByGame(gameId);
    }

    @GET
    @Path("/updateRating/{facebookId}/{gameId}/{rate}")
    public ResponseBuilder updateRating(@PathParam("facebookId") long facebookId, @PathParam("gameId") long gameId,
            @PathParam("rate") float rate)
    {
        gameWS.updateRating(facebookId, gameId, rate);
        return Response.ok();
    }

    @GET
    @Path("/rateByUser/{id}")
    @Produces("application/json")
    public float getRateByUser(@PathParam("id") long id)
    {
        return gameWS.getRateByUser(id);
    }

    @GET
    @Path("/players")
    @Produces("application/json")
    public List<Integer> getPlayer()
    {
        return gameWS.getRates();
    }

    @GET
    @Path("/ratesByGame/{gameId}")
    @Produces("application/json")
    public List<Integer> updateRate(@PathParam("gameId") long gameId)
    {
        return gameWS.getRatesByGame(gameId);
    }

    @GET
    @Path("/rates")
    @Produces("application/json")
    public List<Integer> getRates()
    {
        return gameWS.getRates();
    }

}
