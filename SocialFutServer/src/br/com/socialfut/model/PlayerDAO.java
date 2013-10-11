package br.com.socialfut.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.com.socialfut.jdbc.ConnectionFactory;
import br.com.socialfut.persistence.Player;

public class PlayerDAO
{
    private Connection conn;

    public PlayerDAO()
    {
        this.conn = ConnectionFactory.getConnection();
    }

    public Player getPlayer(long userId)
    {
        StringBuilder query = new StringBuilder("select position, rating from player(nolock)");
        query.append(" where id = " + userId);

        Statement stmt = null;
        ResultSet rs = null;

        Player p = new Player();

        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query.toString());

            while (rs.next())
            {
                float rating = rs.getFloat("rating");
                int position = rs.getInt("position");
                p.setRating(rating);
                p.setPosition(position);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        closeAll(conn, stmt, rs);

        return p;
    }

    private Player getPlayerById(long userId)
    {
        StringBuilder query = new StringBuilder("select * from player(nolock)");
        query.append(" where id = " + userId);

        Statement stmt = null;
        ResultSet rs = null;

        Player p = new Player();

        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query.toString());

            while (rs.next())
            {
                float rating = rs.getFloat("rating");
                int position = rs.getInt("position");
                p.setRating(rating);
                p.setPosition(position);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        closeAll(conn, stmt, rs);

        return p;
    }

    public void updateDevice(long userId, String devRegId)
    {
        StringBuilder query = new StringBuilder("update player");
        query.append(" set deviceRegistrationId = " + devRegId);
        query.append(" where id = " + userId);

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

    public void createPlayer(long userId, String devRegId, int position)
    {
        StringBuilder query = new StringBuilder("insert into player");
        query.append(" values(" + userId + "," + devRegId + "," + position + "," + 0);

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

    public float getRating(long userId)
    {
        Player p = this.getPlayerById(userId);
        return p.getRating();
    }

    public int getPosition(long userId)
    {
        Player p = this.getPlayerById(userId);
        return p.getPosition();
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
