package br.com.socialfut.teste;

import java.io.IOException;

import br.com.socialfut.webservice.ChatResource;

public class TestSendToDevice
{
    public static void main(String[] args) throws IOException
    {
        long from = 100002895406629l;

        long to = 583633830;

        String msg = "asoifmsd  !!!!";

        ChatResource cr = new ChatResource();
        cr.sendMessage(from, to, msg);
    }
}
