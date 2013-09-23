package teste;

import java.io.IOException;

import push.GCMSender;
import utils.Constants;

import com.google.android.gcm.server.Message;

public class TestSendToDevice
{
    public static void main(String[] args) throws IOException
    {
        long facebookId = 100002895406629l;
        
        String msg = "Opa 2!";

        Message message = new Message.Builder().addData("msg", facebookId + Constants.SEMICOLON + msg).build();
        GCMSender.send(Constants.DEVICE_REGISTRATION_ID, message);
    }
}
