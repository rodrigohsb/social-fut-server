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
        return gamePlayerDAO.addPlayerToGame(gameId, userId);
    }

    public void removePlayerFromGame(long gameId, long userId)
    {
        GamePlayerDAO gamePlayerDAO = new GamePlayerDAO();
        gamePlayerDAO.removePlayerFromGame(gameId, userId);
    }

    public float getRateByGame(long gameId, long userId)
    {
        GamePlayerDAO gamePlayerDAO = new GamePlayerDAO();
        return gamePlayerDAO.getRateByGame(gameId, userId);
    }

    public Map<Long, Float> getRatesByGame(long gameId)
    {
        return new GamePlayerDAO().getRatesByGame(gameId);
    }

    public void updateRating(long userId, long gameId, float rate)
    {
        new GamePlayerDAO().updateRating(userId, gameId, rate);
    }

    public float getRateByUser(long userId)
    {
        return new GamePlayerDAO().getRateByUser(userId);
    }

    public List<Player> getRates()
    {
        GamePlayerDAO gamePlayerDAO = new GamePlayerDAO();
        return gamePlayerDAO.getRates();
    }
}
