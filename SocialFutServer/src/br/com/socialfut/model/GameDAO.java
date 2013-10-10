package br.com.socialfut.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.com.socialfut.jdbc.ConnectionFactory;
import br.com.socialfut.persistence.Game;

public class GameDAO
{
    private Connection conn;

    public GameDAO()
    {
        this.conn = ConnectionFactory.getConnection();
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
        /** Levantamento da quantidade a ser processada. */
        StringBuilder query = new StringBuilder("select * from game_player(nolock)");
        query.append(" where player_id = " + userId);

        Statement stmt = null;
        ResultSet rs = null;

        float totalValue = 0;
        int totalQntRates = 0;

        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query.toString());

            while (rs.next())
            {
                float value = rs.getFloat("value");
                totalValue += value;

                int qntRates = rs.getInt("qntRates");
                totalQntRates += qntRates;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        closeAll(conn, stmt, rs);
        return (totalValue / totalQntRates);

    }

    /**
     * 
     * Obtem a qualificacao de todos.
     * 
     * @return
     */
    public List<Integer> getRates()
    {
        /** Levantamento da quantidade a ser processada. */
        StringBuilder query = new StringBuilder("select count(*) from mtg.mediaoutputinstance moi(nolock)");
        query.append(" inner join media m(nolock) on m.id = moi.media_id and mediatype_id <> 'RBTS'");
        query.append(" where filename is null");

        Statement stmt = null;
        ResultSet rs = null;

        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query.toString());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        closeAll(conn, stmt, rs);
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
        /** Levantamento da quantidade a ser processada. */
        StringBuilder query = new StringBuilder("select count(*) from mtg.mediaoutputinstance moi(nolock)");
        query.append(" inner join media m(nolock) on m.id = moi.media_id and mediatype_id <> 'RBTS'");
        query.append(" where filename is null");

        Statement stmt = null;
        ResultSet rs = null;

        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query.toString());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        closeAll(conn, stmt, rs);
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
        /** Levantamento da quantidade a ser processada. */
        StringBuilder query = new StringBuilder("select count(*) from mtg.mediaoutputinstance moi(nolock)");
        query.append(" inner join media m(nolock) on m.id = moi.media_id and mediatype_id <> 'RBTS'");
        query.append(" where filename is null");

        Statement stmt = null;
        ResultSet rs = null;

        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query.toString());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        closeAll(conn, stmt, rs);
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
        /** Levantamento da quantidade a ser processada. */
        StringBuilder query = new StringBuilder("select count(*) from mtg.mediaoutputinstance moi(nolock)");
        query.append(" inner join media m(nolock) on m.id = moi.media_id and mediatype_id <> 'RBTS'");
        query.append(" where filename is null");

        Statement stmt = null;
        ResultSet rs = null;

        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query.toString());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        closeAll(conn, stmt, rs);
        return null;
    }

    public void updateRating(long userId, long gameId, float rate)
    {

    }

    public void creatGame(long id)
    {

    }

    public String addPlayerToGame(long gameId, long userId)
    {
        return null;
    }

    public void removePlayerFromGame(long gameId, long userId)
    {
        /** Levantamento da quantidade a ser processada. */
        StringBuilder query = new StringBuilder("select count(*) from mtg.mediaoutputinstance moi(nolock)");
        query.append(" inner join media m(nolock) on m.id = moi.media_id and mediatype_id <> 'RBTS'");
        query.append(" where filename is null");

        Statement stmt = null;
        ResultSet rs = null;

        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query.toString());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        closeAll(conn, stmt, rs);
    }

    private static void closeAll(Connection conn, Statement ps, ResultSet rs)
    {
        try
        {
            if (conn != null)
            {
                conn.close();
            }

            if (ps != null)
            {
                ps.close();
            }

            if (rs != null)
            {
                rs.close();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
