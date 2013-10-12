package br.com.socialfut.webservice;

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

        PlayerDAO playerDAO = new PlayerDAO();
        Player player = playerDAO.getPlayerById(userId);

        if (player.getDeviceRegistrationId() != deviceRegId)
        {
            playerDAO.updateDevice(userId, deviceRegId);
        }
    }

    public void updateRating(long userId, float rating)
    {
        new PlayerDAO().updateRating(userId, rating);
    }

    public Player getPlayer(long id)
    {
        PlayerDAO playerDAO = new PlayerDAO();
        return playerDAO.getPlayerById(id);
    }

    public float getRating(long userId)
    {
        PlayerDAO playerDAO = new PlayerDAO();
        return playerDAO.getRating(userId);
    }

    public int getPosition(long userId)
    {
        PlayerDAO playerDAO = new PlayerDAO();
        return playerDAO.getPosition(userId);
    }

}
