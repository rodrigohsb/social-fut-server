package br.com.socialfut.webservice;

import java.util.ArrayList;
import java.util.List;

import br.com.socialfut.persistence.Player;

public class PlayerWS
{
    private static PlayerWS instance;

    private List<Player> players = new ArrayList<Player>();

    public PlayerWS()
    {
        createPlayers();
    }

    public static PlayerWS getPlayerWSInstance()
    {
        if (instance == null) instance = new PlayerWS();
        return instance;
    }

    private void createPlayers()
    {
        Player player = new Player();
        player.setDeviceRegistrationId("34tgrb567j787lk78lkym");
        player.setId(583633830);
        players.add(player);

        for (int i = 0; i < 5; i++)
        {
            player = new Player();
            player.setDeviceRegistrationId(String.valueOf(10 * i));
            player.setId(5 * i);
            players.add(player);
        }
    }

    public List<Player> getAllPlayers()
    {
        return players;
    }

    public String insert(long facebookId, String deviceRegId)
    {
        return "Cliente inserido no banco com sucesso!";
    }

    public Player buscar(int id)
    {
        Player player = null;
        for (int i = 0; i < players.size(); i++)
        {
            if (players.get(i).getId() == id)
            {
                player = new Player();
                player = players.get(i);
                break;
            }
        }
        return player;
    }

    // private int getPosition(int id)
    // {
    // int pos = -1;
    // for (int i = 0; i < players.size(); i++)
    // {
    // if (players.get(i).getId() == id)
    // {
    // pos = i;
    // }
    // }
    // return pos;
    // }
}
