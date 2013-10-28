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

    public String createGame(String title, String address, Date startDate, Date finishDate)
    {
        try
        {
            Game g = new GameDAO().createGame(title, address, startDate, finishDate);
            return String.valueOf(g.getId());
        }
        catch (Exception e)
        {
            return "NOK";
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

    public String getOldGames(long userId)
    {
        List<Game> oldGames = new GamePlayerDAO().getOldGames(userId);
        Gson json = new Gson();
        return json.toJson(oldGames);
    }

    public String getNewGames(long userId)
    {
        List<Game> newGames = new GamePlayerDAO().getNewGames(userId);
        Gson json = new Gson();
        return json.toJson(newGames);
    }

    public String getGameById(long id)
    {
        Game game = new GameDAO().getGameById(id);
        Gson json = new Gson();
        return json.toJson(game);
    }

    public String getRateByGame(long gameId, long userId)
    {
        float rating = new GamePlayerDAO().getRateByGame(gameId, userId);
        Player p = new Player();
        p.setRating(rating);
        Gson json = new Gson();
        return json.toJson(rating);
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
