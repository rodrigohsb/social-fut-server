package br.com.socialfut.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import br.com.socialfut.persistence.Player;
import br.com.socialfut.push.GCMSender;
import br.com.socialfut.utils.Constants;

import com.google.android.gcm.server.Message;

@Path("/gcm")
public class GCMResource
{

    @GET
    @Path("/chat/{from}/{to}/{msg}")
    public String sendMessage(@PathParam("from") long from, @PathParam("to") long to, @PathParam("msg") String msg)
    {

        PlayerWS playerWS = new PlayerWS();
        Player player = playerWS.getPlayer(to);

        Message message = new Message.Builder().addData("msg", from + Constants.SEMICOLON + msg).build();
        GCMSender.sendMessage(player.getDeviceRegistrationId(), message);
        return "OK";
    }
}
