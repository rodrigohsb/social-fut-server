package br.com.socialfut.teste;

import java.io.IOException;

import br.com.socialfut.push.GCMSender;
import br.com.socialfut.utils.Constants;
import br.com.socialfut.webservice.ChatResource;

import com.google.android.gcm.server.Message;

public class TestSendToDevice
{
    public static void main(String[] args) throws IOException
    {
        Message message = null;
        // message = chat();
//        message = confirmation();
//         message = desconfirmation();
         message = invite();
        GCMSender
                .sendMessage(
                        "APA91bF-S7EIrLnmw09hu70VYPRIt-pAuq7zvgJLs87nMIzMAyR-HafA9ejC_2ut5C3X3j-9lXvoXLNissgCFu7UgXeWJednYGmtoFyCmfKrTtR2hG4dX2plptmlAHKXcQ1nqM7iqXrv",
                        message);
    }

    private static void chat()
    {
        long from = 100002895406629l;

        long to = 583633830;

        String msg = "asoifmsd  !!!!";

        ChatResource cr = new ChatResource();
        cr.sendMessage(from, to, msg);
    }

    private static Message confirmation()
    {
        Message message = new Message.Builder().addData("msg",
                583633830 + Constants.SEMICOLON + Constants.CONFIRMATION + Constants.SEMICOLON + 2).build();
        return message;
    }

    private static Message desconfirmation()
    {
        Message message = new Message.Builder().addData("msg",
                583633830 + Constants.SEMICOLON + Constants.DESCONFIRMATION + Constants.SEMICOLON + 2).build();
        return message;
    }

    private static Message invite()
    {
        Message message = new Message.Builder().addData("msg",
                583633830 + Constants.SEMICOLON + Constants.INVITATION + Constants.SEMICOLON + 2).build();
        return message;
    }
}
