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

                StringBuilder builder = new StringBuilder();
                builder.append("jdbc:mysql://").append("localhost").append(":3306/").append("socialfut");

                conn = DriverManager.getConnection(builder.toString(), "root", null);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return conn;
    }

}
