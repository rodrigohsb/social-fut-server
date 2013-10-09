package br.com.socialfut.model;

import java.util.List;

import br.com.socialfut.persistence.Game;

public class GameDAO
{
    private Game game = new Game();

    /**
     * 
     * Obtem a qualificacao de um jogador.
     * 
     * @param userId
     * @return
     */
    public float getRateByUser(long userId)
    {
        return 0;
    }

    /**
     * 
     * Obtem a qualificacao de todos.
     * 
     * @return
     */
    public List<Integer> getRates()
    {
        return null;
    }

    /**
     * 
     * Obtem a qualificacao de um jogador
     * 
     * @param id
     * @return
     */
    public int getRateByGame(long gameId, long userId)
    {
        return 0;
    }

    /**
     * 
     * Obtem o rating final do jogador.
     * 
     * @param id
     * @return
     */
    public float getUserRate(long id)
    {
        List<Game> games = this.getAllGamesByUser();

        float value = 0;
        int count = 0;

        for (Game g : games)
        {
            value += g.getValue();
            count += g.getQntRates();
        }

        if (value == 0 || count == 0)
        {
            return 0;
        }

        return value / count;
    }

    /**
     * 
     * Obtem todos os jogos que o jogador participou.
     * 
     * @return
     */
    public List<Game> getAllGamesByUser()
    {
        return null;
    }

    /**
     * 
     * Obtem as qualificacoes de todos os participantes do jogo.
     * 
     * @param id
     * @return
     */
    public List<Integer> getRatesByGame(long gameId)
    {
        return null;
    }

    public void updateRating(long userId, long gameId, float rate)
    {

    }

    public void creatGame(long id)
    {

    }

    public Game getGameById(long gameId)
    {
        return game;
    }

    public void addPlayerToGame(long gameId, long userId)
    {

    }

    public void removePlayerFromGame(long gameId, long userId)
    {

    }

}
