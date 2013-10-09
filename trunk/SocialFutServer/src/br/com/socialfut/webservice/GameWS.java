package br.com.socialfut.webservice;

import java.util.List;

import br.com.socialfut.model.GameDAO;

public class GameWS
{
    public void creatGame(long id)
    {
        GameDAO gameDAO = new GameDAO();
        gameDAO.creatGame(id);
    }

    public void addPlayerToGame(long gameId, long userId)
    {
        GameDAO gameDAO = new GameDAO();
        gameDAO.addPlayerToGame(gameId, userId);
    }

    public void removePlayerFromGame(long gameId, long userId)
    {
        GameDAO gameDAO = new GameDAO();
        gameDAO.removePlayerFromGame(gameId, userId);
    }

    public float getRateByGame(long gameId, long userId)
    {
        GameDAO gameDAO = new GameDAO();
        return gameDAO.getRateByGame(gameId, userId);
    }

    public List<Integer> getRatesByGame(long gameId)
    {
        GameDAO gameDAO = new GameDAO();
        return gameDAO.getRatesByGame(gameId);
    }

    public void updateRating(long userId, long gameId, float rate)
    {
        GameDAO gameDAO = new GameDAO();
        gameDAO.updateRating(userId, gameId, rate);
    }

    public float getRateByUser(long userId)
    {
        GameDAO gameDAO = new GameDAO();
        return gameDAO.getRateByUser(userId);
    }

    public List<Integer> getRates()
    {
        GameDAO gameDAO = new GameDAO();
        return gameDAO.getRates();
    }
}
