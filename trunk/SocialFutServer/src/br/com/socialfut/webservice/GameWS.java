package br.com.socialfut.webservice;

import java.util.List;
import java.util.Map;

import br.com.socialfut.model.GamePlayerDAO;
import br.com.socialfut.persistence.Player;

public class GameWS
{
    public String addPlayerToGame(long gameId, long userId)
    {
        GamePlayerDAO gamePlayerDAO = new GamePlayerDAO();
        try
        {
            gamePlayerDAO.addPlayerToGame(gameId, userId);
        }
        catch (Exception e)
        {
            return "NOK";
        }
        return "OK";
    }

    public String removePlayerFromGame(long gameId, long userId)
    {
        GamePlayerDAO gamePlayerDAO = new GamePlayerDAO();
        try
        {
            gamePlayerDAO.removePlayerFromGame(gameId, userId);
        }
        catch (Exception e)
        {
            return "NOK";
        }
        return "OK";
    }

    public String getRateByGame(long gameId, long userId)
    {
        GamePlayerDAO gamePlayerDAO = new GamePlayerDAO();
        float rating = gamePlayerDAO.getRateByGame(gameId, userId);
        return String.valueOf(rating);
    }

    public void updateRating(long userId, long gameId, float rate)
    {
        new GamePlayerDAO().updateRating(userId, gameId, rate);
    }

    public float getRatingByUser(long userId)
    {
        return new GamePlayerDAO().getRatingByUser(userId);
    }

    public List<Player> getPlayersByGame(long gameId)
    {
        GamePlayerDAO gamePlayerDAO = new GamePlayerDAO();
        return gamePlayerDAO.getPlayersByGame(gameId);
    }
}
