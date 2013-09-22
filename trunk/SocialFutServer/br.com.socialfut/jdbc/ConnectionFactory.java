package jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
                prop = new Properties();

                prop.load(ConnectionFactory.class.getResourceAsStream("../resources/db_data.properties"));

                host = prop.getProperty("app.host");
                dbName = prop.getProperty("app.dbName");
                userName = prop.getProperty("app.username");
                password = prop.getProperty("app.password");
                driver = prop.getProperty("app.driver");

                Class.forName(driver);

                StringBuilder builder = new StringBuilder();
                builder.append("jdbc:mysql://").append(host).append(":3306/").append(dbName);

                conn = DriverManager.getConnection(builder.toString(), userName, password);

                return conn;
            }
            return null;
        }
        catch (ClassNotFoundException | SQLException | IOException e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
