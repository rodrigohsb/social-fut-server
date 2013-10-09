package br.com.socialfut.webservice;

import java.util.List;

import br.com.socialfut.model.PlayerDAO;
import br.com.socialfut.persistence.Player;
import br.com.socialfut.persistence.Position;

public class PlayerWS
{
    public List<Player> getAllPlayers()
    {
        PlayerDAO playersDAO = new PlayerDAO();
        return playersDAO.getAllPlayers();
    }

    public void createPlayer(long userId, String deviceRegId)
    {
        PlayerDAO playerDAO = new PlayerDAO();
        playerDAO.createPlayer(userId, deviceRegId);
    }

    public void updateDevice(long userId, String deviceRegId)
    {
        Player player = this.buscar(userId);

        if (player.getDeviceRegistrationId() != deviceRegId)
        {
            PlayerDAO playerDAO = new PlayerDAO();
            playerDAO.updateDevice(userId, deviceRegId);
        }
    }

    public Player buscar(long id)
    {
        PlayerDAO playerDAO = new PlayerDAO();
        return playerDAO.getPlayer(id);
    }

    public void setPosition(long userId, int newPosition)
    {
        PlayerDAO playersDAO = new PlayerDAO();

        int positionId = this.getPosition(userId).getId();

        if (positionId != newPosition)
        {
            playersDAO.setPosition(userId, newPosition);
        }
    }

    public String getPositionName(long userId)
    {
        return this.getPosition(userId).getName();
    }

    private Position getPosition(long userId)
    {
        return new PlayerDAO().getPosition(userId);
    }

    public int getPositionId(long userId)
    {
        return this.getPosition(userId).getId();
    }

    public List<Player> getPlayersByGame(long gameId)
    {
        PlayerDAO playersDAO = new PlayerDAO();
        return playersDAO.getPlayersByGame(gameId);
    }
}
