package br.com.socialfut.webservice;

import java.util.Date;
import java.util.List;

import br.com.socialfut.model.GameDAO;
import br.com.socialfut.model.GamePlayerDAO;
import br.com.socialfut.persistence.Game;
import br.com.socialfut.persistence.Player;

import com.google.gson.Gson;

public class GameWS
{

    public void createGame(String title, String address, Date startDate, Date finishDate)
    {
        try
        {
            new GameDAO().createGame(title, address, startDate, finishDate);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public String addPlayerToGame(long gameId, long userId)
    {
        try
        {
            new GamePlayerDAO().addPlayerToGame(gameId, userId);
        }
        catch (Exception e)
        {
            return "NOK";
        }
        return "OK";
    }

    public String removePlayerFromGame(long gameId, long userId)
    {
        try
        {
            new GamePlayerDAO().removePlayerFromGame(gameId, userId);
        }
        catch (Exception e)
        {
            return "NOK";
        }
        return "OK";
    }

    public String getOldGames(long id)
    {
        List<Game> oldGames = new GamePlayerDAO().getOldGames(id);
        Gson json = new Gson();
        return json.toJson(oldGames);
    }

    public String getNewGames(long id)
    {
        List<Game> newGames = new GamePlayerDAO().getNewGames(id);
        Gson json = new Gson();
        return json.toJson(newGames);
    }

    public String getRateByGame(long gameId, long userId)
    {
        float rating = new GamePlayerDAO().getRateByGame(gameId, userId);
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
        return new GamePlayerDAO().getPlayersByGame(gameId);
    }
}
