package br.com.socialfut.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.socialfut.jdbc.ConnectionFactory;
import br.com.socialfut.persistence.GamePlayer;
import br.com.socialfut.persistence.Player;

public class GamePlayerDAO
{
    private Connection conn;

    public GamePlayerDAO()
    {
        this.conn = ConnectionFactory.getConnection();
    }

    /**
     * 
     * FEITO!
     * 
     * @param userId
     * @return
     */
    public float getRateByUser(long userId)
    {
        
        StringBuilder query = new StringBuilder("select value,qnt_rates from game_player(nolock)");
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

                int qntRates = rs.getInt("qnt_rates");
                totalQntRates += qntRates;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        closeAll(conn, stmt, rs);

        return (totalValue == 0 || totalQntRates == 0) ? 0 : (totalValue / totalQntRates);
    }

    // TODO Fazer!!
    public List<Player> getRates()
    {
        /** Levantamento da quantidade a ser processada. */
        StringBuilder query = new StringBuilder("select * from game_player(nolock) gp");
        query.append(" inner join player(nolock) p");
        query.append(" on p.id = gp.player_id");

        Statement stmt = null;
        ResultSet rs = null;

        List<Player> rates = new ArrayList<Player>();

        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query.toString());

            while (rs.next())
            {

            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        closeAll(conn, stmt, rs);

        return rates;
    }

    /**
     * 
     * FEITO!
     * 
     * @param id
     * @return
     */
    public float getRateByGame(long gameId, long userId)
    {
        /** Levantamento da quantidade a ser processada. */
        StringBuilder query = new StringBuilder("select * from game_player(nolock)");
        query.append(" where player_id = " + userId);
        query.append(" and game_id = " + gameId);

        Statement stmt = null;
        ResultSet rs = null;

        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query.toString());

            if (rs != null)
            {
                float value = rs.getFloat("value");
                int qntRates = rs.getInt("qnt_rates");

                if (value != 0 && qntRates != 0)
                {
                    return (value / qntRates);
                }
            }
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
     * FEITO!
     * 
     * @param id
     * @return
     */
    public Map<Long, Float> getRatesByGame(long gameId)
    {
        StringBuilder query = new StringBuilder("select player_id,value,qnt_rates from game_player(nolock)");
        query.append(" where game_id = " + gameId);

        Statement stmt = null;
        ResultSet rs = null;

        Map<Long, Float> rates = new HashMap<Long, Float>();

        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query.toString());

            while (rs.next())
            {
                long player_id = rs.getLong("player_id");
                float totalValue = rs.getFloat("value");
                int qntRates = rs.getInt("qnt_rates");

                if (totalValue != 0 && qntRates != 0)
                {
                    float value = (totalValue / qntRates);
                    rates.put(player_id, value);
                }
            }
            return rates;
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
     * FEITO!
     * 
     * @param userId
     * @param gameId
     * @param rate
     */
    public void updateRating(long userId, long gameId, float rate)
    {
        GamePlayer gp = new GamePlayerDAO().getByIDAndPlayer(gameId, userId);

        float totalValue = gp.getValue();
        int qntRate = gp.getQntRates();

        StringBuilder query = new StringBuilder("update game_player");
        query.append(" set value = " + (totalValue + rate));
        query.append(" set qnt_rates = " + (++qntRate));
        query.append(" where player_id = " + userId);
        query.append("and game_id = " + gameId);

        Statement stmt = null;

        try
        {
            stmt = conn.createStatement();
            stmt.executeUpdate(query.toString());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        closeAll(conn, stmt, null);
    }

    /**
     * 
     * FEITO!
     * 
     * @param gameId
     * @param userId
     * @return
     */
    public GamePlayer getByIDAndPlayer(long gameId, long userId)
    {
        StringBuilder query = new StringBuilder("select value,qnt_rates from game_player");
        query.append(" where player_id = " + userId);
        query.append(" where game_id = " + gameId);

        Statement stmt = null;
        ResultSet rs = null;
        GamePlayer gp = new GamePlayer();

        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query.toString());
            if (rs != null)
            {
                float value = rs.getFloat("value");
                int qntRates = rs.getInt("qnt_rates");

                gp.setGameId(gameId);
                gp.setValue(value);
                gp.setQntRates(qntRates);

                return gp;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        closeAll(conn, stmt, null);

        return null;
    }

    /**
     * 
     * FEITO!
     * 
     * @param gameId
     * @param userId
     * @return
     */
    public String addPlayerToGame(long gameId, long userId)
    {

        StringBuilder query = new StringBuilder("insert into game_player");
        query.append(" values(" + userId + "," + gameId + "," + 0 + "," + 0 + ")");

        Statement stmt = null;
        boolean inseriu = false;

        try
        {
            stmt = conn.createStatement();
            inseriu = stmt.execute(query.toString());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        closeAll(conn, stmt, null);

        if (inseriu)
        {
            return "Escalado com sucesso!";
        }
        return "Problemas ao escalar jogador!";
    }

    /**
     * 
     * FEITO!
     * 
     * @param gameId
     * @param userId
     * @return
     */
    public String removePlayerFromGame(long gameId, long userId)
    {
        /** Levantamento da quantidade a ser processada. */
        StringBuilder query = new StringBuilder("delete from game_player");
        query.append(" where game_id = " + gameId);
        query.append(" and player_id =" + userId);

        Statement stmt = null;
        boolean deleted = false;

        try
        {
            stmt = conn.createStatement();
            deleted = stmt.execute(query.toString());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        closeAll(conn, stmt, null);

        if (deleted)
        {
            return "Removido com sucesso!";
        }
        return "Problemas ao remover jogador!";
    }
    
    public List<Player> getPlayersByGame(long gameId)
    {
        return null;
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
