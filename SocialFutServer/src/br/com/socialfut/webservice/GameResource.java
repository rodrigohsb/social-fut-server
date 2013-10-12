package br.com.socialfut.webservice;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import br.com.socialfut.persistence.Player;
import br.com.socialfut.push.GCMSender;
import br.com.socialfut.utils.Constants;

import com.google.android.gcm.server.Message;
import com.google.gson.Gson;

@Path("/game")
public class GameResource
{
    GameWS gameWS = new GameWS();

    @GET
    @Path("/addPlayerToGame/{userId}/{gameId}")
    public String addPlayerToGame(@PathParam("gameId") long gameId, @PathParam("userId") long userId)
    {
        return gameWS.addPlayerToGame(gameId, userId);
    }

    @GET
    @Path("/removePlayerFromGame/{userId}/{gameId}")
    public ResponseBuilder removePlayerFromGame(@PathParam("gameId") long gameId, @PathParam("userId") long userId)
    {
        gameWS.removePlayerFromGame(gameId, userId);
        return Response.ok();
    }

    @GET
    @Path("/rateByGame/{userId}/{gameId}")
    @Produces("application/json")
    public float getRateByGame(@PathParam("userId") long userId, @PathParam("gameId") long gameId)
    {
        return gameWS.getRateByGame(gameId, userId);
    }

    @GET
    @Path("/getRatesByGame/{gameId}")
    @Produces("application/json")
    public Map<Long, Float> getRatesByGame(@PathParam("gameId") long gameId)
    {
        return gameWS.getRatesByGame(gameId);
    }

    @GET
    @Path("/updateRating/{userId}/{gameId}/{rating}")
    public ResponseBuilder updateRating(@PathParam("userId") long userId, @PathParam("gameId") long gameId,
            @PathParam("rating") float rating)
    {
        gameWS.updateRating(userId, gameId, rating);
        // TODO atualizar tambem a tabela player!!
        return Response.ok();
    }

    @GET
    @Path("/rates")
    @Produces("application/json")
    public List<Player> getRates()
    {
        return gameWS.getRates();
    }

    @GET
    @Path("/rateByUser/{id}")
    @Produces("application/json")
    public String getRateByUser(@PathParam("id") long id)
    {
        return new Gson().toJson(String.valueOf(gameWS.getRateByUser(id)));
    }

    @GET
    @Path("/confirmation/{from}/{gameId}")
    public String sendConfirmation(@PathParam("from") long userId, @PathParam("gameId") int gameId)
    {

        // incluir o jogador na tabela "Game_Player"
        gameWS.addPlayerToGame(gameId, userId);

        String msg = Constants.CONFIRMATION;

        // Todos jogadores que estao na partida
        List<Player> players = gameWS.getPlayersByGame(gameId);

        for (Player p : players)
        {
            Message message = new Message.Builder().addData("msg", userId + Constants.SEMICOLON + msg).build();
            GCMSender.sendMessage(p.getDeviceRegistrationId(), message);
        }
        return "OK";
    }

    @GET
    @Path("/desconfirmation/{from}/{gameId}")
    public String sendDesconfirmation(@PathParam("from") long userId, @PathParam("gameId") int gameId)
    {

        // Retirar o jogador da tabela "Game_Player"
        gameWS.removePlayerFromGame(gameId, userId);

        String msg = Constants.DESCONFIRMATION;

        // Todos jogadores que estao na partida
        List<Player> players = gameWS.getPlayersByGame(gameId);

        for (Player p : players)
        {
            Message message = new Message.Builder().addData("msg", userId + Constants.SEMICOLON + msg).build();
            GCMSender.sendMessage(p.getDeviceRegistrationId(), message);
        }
        return "OK";
    }
}
