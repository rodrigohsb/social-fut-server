package br.com.socialfut.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import br.com.socialfut.persistence.Player;
import br.com.socialfut.push.GCMSender;
import br.com.socialfut.utils.Constants;

import com.google.android.gcm.server.Message;

@Path("/gcm")
public class GCMResource
{

    @GET
    @Path("/chat/{from}/{to}/{msg}")
    public ResponseBuilder sendMessage(@PathParam("from") long from, @PathParam("to") long to,
            @PathParam("msg") String msg)
    {

        PlayerWS playerWS = new PlayerWS();
        Player player = playerWS.buscar(to);

        Message message = new Message.Builder().addData("msg", from + Constants.SEMICOLON + msg).build();
        GCMSender.sendMessage(player.getDeviceRegistrationId(), message);
        return Response.ok();
    }

    @GET
    @Path("/confirmation/{from}/{gameId}")
    public ResponseBuilder sendConfirmation(@PathParam("from") long userId, @PathParam("gameId") int gameId)
    {

        String msg = Constants.CONFIRMATION;

        // TODO incluir o jogador na tabela "Game_Player"

        PlayerWS playerWS = new PlayerWS();
        List<Player> players = playerWS.getPlayersByGame(gameId);

        for (Player p : players)
        {
            Message message = new Message.Builder().addData("msg", userId + Constants.SEMICOLON + msg).build();
            GCMSender.sendMessage(p.getDeviceRegistrationId(), message);
        }
        return Response.ok();
    }

    @GET
    @Path("/desconfirmation/{from}/{gameId}")
    public ResponseBuilder sendDesconfirmation(@PathParam("from") long userId, @PathParam("gameId") int gameId)
    {

        String msg = Constants.DESCONFIRMATION;

        // TODO Retirar o jogador da tabela "Game_Player"

        PlayerWS playerWS = new PlayerWS();
        List<Player> players = playerWS.getPlayersByGame(gameId);

        for (Player p : players)
        {
            Message message = new Message.Builder().addData("msg", userId + Constants.SEMICOLON + msg).build();
            GCMSender.sendMessage(p.getDeviceRegistrationId(), message);
        }
        return Response.ok();
    }

}
