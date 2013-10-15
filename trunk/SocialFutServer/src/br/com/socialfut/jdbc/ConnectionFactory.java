package br.com.socialfut.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory
{

    private static Connection conn = null;

    public static Connection getConnection()
    {
        try
        {
            if (conn == null)
            {
                Class.forName("com.mysql.jdbc.Driver");

                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/socialfut", "root", null);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return conn;
    }
}
