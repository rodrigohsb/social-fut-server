package br.com.socialfut.push;

import java.io.IOException;

import br.com.socialfut.utils.Constants;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Sender;

public class GCMSender
{

    public static void sendMessage(String deviceId, Message msg)
    {
        Sender sender = new Sender(Constants.API_KEY);
        try
        {
            sender.send(msg, deviceId, 5);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("Mensagem enviada: " + msg.getData().get("msg"));
    }
}
