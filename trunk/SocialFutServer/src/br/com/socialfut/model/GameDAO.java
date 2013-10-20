package br.com.socialfut.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import br.com.socialfut.jdbc.ConnectionFactory;

public class GameDAO
{
    private Connection conn;

    public GameDAO()
    {
        this.conn = ConnectionFactory.getConnection();
    }

    /**
     * 
     * FEITO!
     * 
     * @param gameId
     * @param userId
     * @return
     */
    public void createGame(String title, String address, Date startDate, Date finishDate) throws Exception
    {

        StringBuilder query = new StringBuilder("insert into game");
        query.append(" values(" + title + "," + address + "," + new Date() + "," + startDate + "," + finishDate + ")");

        Statement stmt = null;

        stmt = conn.createStatement();
        stmt.execute(query.toString());
        closeAll(conn, stmt, null);

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
