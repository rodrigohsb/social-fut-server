package br.com.socialfut.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.socialfut.jdbc.ConnectionFactory;
import br.com.socialfut.persistence.Game;
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
    public float getRatingByUser(long userId)
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

    public List<Game> getNewGames(long userId)
    {

        List<Game> newGames = new ArrayList<Game>();
        StringBuilder query = new StringBuilder("select g.* from game_player(nolock) gp");
        query.append(" inner join game(nolock)g on g.id = gp.game_id");
        query.append(" where player_id = " + userId);
        query.append(" where d.STARTED_DATE > " + new Date());

        Statement stmt = null;
        ResultSet rs = null;

        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query.toString());

            if (rs != null)
            {
                long id = rs.getLong("ID");
                String address = rs.getString("ADDRESS");
                String title = rs.getString("TITLE");
                Date createdDate = rs.getDate("CREATED_DATE");
                Date startedDate = rs.getDate("STARTED_DATE");
                Date finishDate = rs.getDate("FINISH_DATE");

                Game g = new Game(id, title, address, createdDate, startedDate, finishDate);
                newGames.add(g);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        closeAll(conn, stmt, rs);
        return newGames;
    }

    public List<Game> getOldGames(long userId)
    {

        List<Game> newGames = new ArrayList<Game>();
        StringBuilder query = new StringBuilder("select g.* from game_player(nolock) gp");
        query.append(" inner join game(nolock)g on g.id = gp.game_id");
        query.append(" where player_id = " + userId);
        query.append(" where d.STARTED_DATE < " + new Date());

        Statement stmt = null;
        ResultSet rs = null;

        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query.toString());

            if (rs != null)
            {
                long id = rs.getLong("ID");
                String address = rs.getString("ADDRESS");
                String title = rs.getString("TITLE");
                Date createdDate = rs.getDate("CREATED_DATE");
                Date startedDate = rs.getDate("STARTED_DATE");
                Date finishDate = rs.getDate("FINISH_DATE");

                Game g = new Game(id, title, address, createdDate, startedDate, finishDate);
                newGames.add(g);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        closeAll(conn, stmt, rs);
        return newGames;
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
        // /** Levantamento da quantidade a ser processada. */
        // StringBuilder query = new
        // StringBuilder("select * from game_player(nolock)");
        // query.append(" where player_id = " + userId);
        // query.append(" and game_id = " + gameId);
        //
        // Statement stmt = null;
        // ResultSet rs = null;
        //
        // try
        // {
        // stmt = conn.createStatement();
        // rs = stmt.executeQuery(query.toString());
        //
        // if (rs != null)
        // {
        // float value = rs.getFloat("value");
        // int qntRates = rs.getInt("qnt_rates");
        //
        // if (value != 0 && qntRates != 0)
        // {
        // return (value / qntRates);
        // }
        // }
        // }
        // catch (SQLException e)
        // {
        // e.printStackTrace();
        // }
        // closeAll(conn, stmt, rs);
        // return 0;

        return 2.5f;
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

                gp.setGame_id(gameId);
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
    public void addPlayerToGame(long gameId, long userId) throws Exception
    {

        StringBuilder query = new StringBuilder("insert into game_player");
        query.append(" values(" + userId + "," + gameId + "," + 0 + "," + 0 + ")");

        Statement stmt = null;

        stmt = conn.createStatement();
        stmt.execute(query.toString());
        closeAll(conn, stmt, null);
    }

    /**
     * 
     * FEITO!
     * 
     * @param gameId
     * @param userId
     * @return
     * @throws Exception
     */
    public void removePlayerFromGame(long gameId, long userId) throws Exception
    {
        /** Levantamento da quantidade a ser processada. */
        StringBuilder query = new StringBuilder("delete from game_player");
        query.append(" where game_id = " + gameId);
        query.append(" and player_id =" + userId);

        Statement stmt = null;

        stmt = conn.createStatement();
        stmt.execute(query.toString());
        closeAll(conn, stmt, null);
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
