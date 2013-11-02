package br.com.socialfut.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

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
     * FEITO!
     * 
     * @param gameId
     * @param userId
     * @return
     */
    public Game createGame(String title, String address, Date startDate, Date finishDate) throws Exception
    {

//        StringBuilder query = new StringBuilder("insert into game");
//        query.append(" values(" + title + "," + address + "," + new Date() + "," + startDate + "," + finishDate + ")");
//
//        Statement stmt = null;
//
//        stmt = conn.createStatement();
//        stmt.execute(query.toString());
//        closeAll(conn, stmt, null);
        return getGame(title, address, startDate, finishDate);
    }

    public Game getGame(String title, String address, Date startDate, Date finishDate)
    {

        Game g = new Game(1, "Titulo XPTO", "Rua Qualquer", new Date(), new Date(), new Date());
        return g;

        // StringBuilder query = new StringBuilder("select * from game");
        // query.append(" where title = "' + title +'");
        // query.append(" and address = "' + address +'");
        // query.append(" and startDate = " + startDate +");
        // query.append(" and finishDate = " + finishDate);
        //
        // Statement stmt = null;
        // ResultSet rs = null;
        // try
        // {
        // stmt = conn.createStatement();
        // rs = stmt.executeQuery(query.toString());
        // if (rs != null)
        // {
        // long id = rs.getLong("ID");
        // String title = rs.getString("title");
        // String address = rs.getString("address");
        // Date createdDate = rs.getDate("createdDate");
        // Date startDate = rs.getDate("startDate");
        // Date finishDate = rs.getDate("finishDate");
        //
        // Game g = new Game(id, title, address, createdDate, startDate,
        // finishDate);
        // return g;
        // }
        // }
        // catch (SQLException e)
        // {
        // e.printStackTrace();
        // }
        // closeAll(conn, stmt, null);
        //
        // return null;
    }

    public Game getGameById(long gameId)
    {

        Game g = new Game(1, "Titulo XPTO", "Rua Qualquer", new Date(), new Date(), new Date());
        return g;

        // StringBuilder query = new StringBuilder("select * from game");
        // query.append(" where id = " + gameId);
        //
        // Statement stmt = null;
        // ResultSet rs = null;
        // try
        // {
        // stmt = conn.createStatement();
        // rs = stmt.executeQuery(query.toString());
        // if (rs != null)
        // {
        // long id = rs.getLong("ID");
        // String title = rs.getString("title");
        // String address = rs.getString("address");
        // Date createdDate = rs.getDate("createdDate");
        // Date startDate = rs.getDate("startDate");
        // Date finishDate = rs.getDate("finishDate");
        //
        // Game g = new Game(id, title, address, createdDate, startDate,
        // finishDate);
        // return g;
        // }
        // }
        // catch (SQLException e)
        // {
        // e.printStackTrace();
        // }
        // closeAll(conn, stmt, null);
        //
        // return null;
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
