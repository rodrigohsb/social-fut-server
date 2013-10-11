package br.com.socialfut.webservice;

import java.util.List;

import br.com.socialfut.model.PlayerDAO;
import br.com.socialfut.persistence.Player;

public class PlayerWS
{
    public void createPlayer(long userId, String deviceRegId, int position)
    {
        PlayerDAO playerDAO = new PlayerDAO();
        playerDAO.createPlayer(userId, deviceRegId, position);
    }

    public void updateDevice(long userId, String deviceRegId)
    {
        Player player = this.getPlayer(userId);

        if (player.getDeviceRegistrationId() != deviceRegId)
        {
            PlayerDAO playerDAO = new PlayerDAO();
            playerDAO.updateDevice(userId, deviceRegId);
        }
    }

    public Player getPlayer(long id)
    {
        PlayerDAO playerDAO = new PlayerDAO();
        return playerDAO.getPlayer(id);
    }

    public List<Player> getPlayersByGame(long gameId)
    {
        PlayerDAO playersDAO = new PlayerDAO();
        return playersDAO.getPlayersByGame(gameId);
    }
}
