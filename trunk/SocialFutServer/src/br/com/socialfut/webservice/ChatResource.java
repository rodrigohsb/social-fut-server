package br.com.socialfut.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import br.com.socialfut.push.GCMSender;
import br.com.socialfut.utils.Constants;

import com.google.android.gcm.server.Message;

@Path("/chat")
public class ChatResource
{

    @GET
    @Path("/{from}/{to}/{msg}")
    public String sendMessage(@PathParam("from") long from, @PathParam("to") long to, @PathParam("msg") String msg)
    {

//        PlayerWS playerWS = new PlayerWS();
//        Player player = playerWS.getPlayer(to);

        Message message = new Message.Builder().addData("msg", from + Constants.SEMICOLON + msg).build();
        // GCMSender.sendMessage(player.getDeviceRegistrationId(), message);
        GCMSender.sendMessage("APA91bH3IAGRvYFIvdctoAVD6DB8x4BmopIe2ULGp_n-1ejREXHaMERRWSGKRlPJKCrnMsVPyoLfabrbRRzjDj0RI8AIsapJCdhBWbML-1DEjhzwYm3FsyHFDKHjGDtNkdfFd8Ssl08m",message);
        return "OK";
    }
}
