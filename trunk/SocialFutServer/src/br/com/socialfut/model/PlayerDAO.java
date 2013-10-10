package br.com.socialfut.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.socialfut.persistence.Player;
import br.com.socialfut.persistence.Position;

@XmlRootElement
public class PlayerDAO
{
    private Player player = new Player();

    public List<Player> getAllPlayers()
    {
        return null;
    }

    public Player getPlayer(long id)
    {
        return null;
    }

    public void updateDevice(long id, String devRegId)
    {

    }

    public void createPlayer(long id, String devRegId)
    {

    }

    public float getRate(long id)
    {
        return id;
    }

    public Position getPosition(long userId)
    {
        return null;
    }

    public void setPosition(long userId, int position)
    {

    }

    public List<Player> getPlayersByGame(long gameId)
    {
        return null;
    }

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

}
