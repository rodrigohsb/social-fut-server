package push;

import java.io.IOException;

import utils.Constants;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Sender;

public class GCMSender
{
    /**
     * 
     * Faz o pedido p/ o GCM de envio de msg para o device.
     * 
     * @param deviceId
     * @param msg
     */
    public static void send(String deviceId, Message msg)
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
