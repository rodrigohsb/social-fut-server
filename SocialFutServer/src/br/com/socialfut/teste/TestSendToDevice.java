package br.com.socialfut.teste;

import java.io.IOException;

import br.com.socialfut.push.GCMSender;
import br.com.socialfut.utils.Constants;

import com.google.android.gcm.server.Message;

public class TestSendToDevice
{
    public static void main(String[] args) throws IOException
    {
        long facebookId = 518163454l;

        String msg = "Mais um teste!";

        Message message = new Message.Builder().addData("msg", facebookId + Constants.SEMICOLON + msg).build();
        GCMSender.send(Constants.DEVICE_REGISTRATION_ID, message);
    }
}
