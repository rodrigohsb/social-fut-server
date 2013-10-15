package br.com.socialfut.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.socialfut.persistence.Player;
import br.com.socialfut.push.GCMSender;
import br.com.socialfut.utils.Constants;

import com.google.android.gcm.server.Message;
import com.google.gson.Gson;

@Path("/game")
@Produces(MediaType.APPLICATION_JSON)
public class GameResource
{
    @GET
    @Path("/ratingByGame/{userId}/{gameId}")
    public String getRatingByGame(@PathParam("userId") long userId, @PathParam("gameId") long gameId)
    {
        return new GameWS().getRateByGame(gameId, userId);
    }

    @GET
    @Path("/updateRating/{userId}/{gameId}/{rating}")
    public String updateRating(@PathParam("userId") long userId, @PathParam("gameId") long gameId,
            @PathParam("rating") float rating)
    {
        GameWS gameWS = new GameWS();

        gameWS.updateRating(userId, gameId, rating);
        Float userRating = gameWS.getRatingByUser(userId);

        new PlayerWS().updateRating(userId, userRating);

        return "OK";
    }

    @GET
    @Path("/rateByUser/{id}")
    public String getRatingByUser(@PathParam("id") long id)
    {
        return new Gson().toJson(String.valueOf(new GameWS().getRatingByUser(id)));
    }

    @GET
    @Path("/confirmation/{from}/{gameId}")
    public String sendConfirmation(@PathParam("from") long userId, @PathParam("gameId") int gameId)
    {

        // incluir o jogador na tabela "Game_Player"
        GameWS gameWS = new GameWS();
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

        GameWS gameWS = new GameWS();
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
