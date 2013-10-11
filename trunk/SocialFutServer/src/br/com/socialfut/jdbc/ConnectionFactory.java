package br.com.socialfut.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory
{

    private static String host = null;

    private static String dbName = null;

    private static String userName = null;

    private static String password = null;

    private static String driver = null;

    private static Properties prop;

    private static Connection conn = null;

    public static Connection getConnection()
    {
        try
        {
            if (conn == null)
            {
                // prop = new Properties();
                // prop.load(ConnectionFactory.class.getResourceAsStream("db_data.properties"));
                // host = prop.getProperty("app.host");
                // dbName = prop.getProperty("app.dbName");
                // userName = prop.getProperty("app.username");
                // password = prop.getProperty("app.password");
                // driver = prop.getProperty("app.driver");

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
