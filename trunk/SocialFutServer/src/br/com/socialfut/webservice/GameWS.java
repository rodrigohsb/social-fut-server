package br.com.socialfut.webservice;

import java.util.List;

import com.google.gson.Gson;

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
        Player p = new Player();
        p.setId(userId);
        p.setRating(rating);
        Gson json = new Gson();
        return json.toJson(p);
    }

    public void updateRating(long userId, long gameId, float rate)
    {
        new GamePlayerDAO().updateRating(userId, gameId, rate);
    }

    public float getRatingByUser(long userId)
    {
        return new GamePlayerDAO().getRatingByUser(userId);
    }

    public String getPlayersByGame(long gameId)
    {
        List<Player> players = this.getListPlayersByGame(gameId);
        Gson json = new Gson();
        return json.toJson(players);
    }

    public List<Player> getListPlayersByGame(long gameId)
    {
        GamePlayerDAO gamePlayerDAO = new GamePlayerDAO();
        return gamePlayerDAO.getPlayersByGame(gameId);
    }
}
