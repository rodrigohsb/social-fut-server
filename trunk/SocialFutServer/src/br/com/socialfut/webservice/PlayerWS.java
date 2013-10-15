package br.com.socialfut.webservice;

import br.com.socialfut.model.PlayerDAO;
import br.com.socialfut.persistence.Player;

import com.google.gson.Gson;

public class PlayerWS
{
    public void createPlayer(long userId, String deviceRegId, int position)
    {
        new PlayerDAO().createPlayer(userId, deviceRegId, position);
    }

    public void updateDevice(long userId, String deviceRegId)
    {

        PlayerDAO playerDAO = new PlayerDAO();
        Player player = playerDAO.getPlayerById(userId, true);

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
        return new PlayerDAO().getPlayerById(id, false);
    }

    public String getRating(long userId)
    {
        Player p = new PlayerDAO().getRating(userId);
        Gson json = new Gson();
        return json.toJson(p);
    }

    public String getPosition(long userId)
    {
        Player p = new PlayerDAO().getPosition(userId);
        Gson json = new Gson();
        return json.toJson(p);
    }

    public String getRatingAndPosition(long userId)
    {
        Player p = new PlayerDAO().getRatingAndPosition(userId);
        Gson json = new Gson();
        return json.toJson(p);
    }
}
