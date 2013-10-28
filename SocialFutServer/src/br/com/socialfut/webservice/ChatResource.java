package br.com.socialfut.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import br.com.socialfut.persistence.Player;
import br.com.socialfut.push.GCMSender;
import br.com.socialfut.utils.Constants;

import com.google.android.gcm.server.Message;

@Path(Constants.SLASH + Constants.CHAT)
public class ChatResource
{

    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/{from}/{to}")
    public String sendMessage(@PathParam("from") long from, @PathParam("to") long to, String msg)
    {

        PlayerWS playerWS = new PlayerWS();
        Player player = playerWS.getPlayer(to);
        for (Player p : Constants.players)
        {
            if (p.getId() == to)
            {
                player.setDeviceRegistrationId(p.getDeviceRegistrationId());
            }
        }

        Message message = new Message.Builder().addData("msg", from + Constants.SEMICOLON + msg).build();
        GCMSender.sendMessage(player.getDeviceRegistrationId(), message);
        return "OK";
    }
}
