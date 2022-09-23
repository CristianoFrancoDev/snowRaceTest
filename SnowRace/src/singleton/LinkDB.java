package singleton;

import java.sql.Connection;
import java.sql.DriverManager;

public class LinkDB
{
    private static final String USER_DB = "root";
    private static final String PASS_DB = "root";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String urlDB = "jdbc:mysql://localhost:3306/snowrace";
    private static Connection connection = null;

    static
    {
        try
        {
            Class.forName(driver);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static Connection getConnection()
    {
        Connection response = null;

        try
        {
            if (connection == null || connection.isClosed())
                response = DriverManager.getConnection(urlDB, USER_DB, PASS_DB);
            else
                response = connection;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return response;
    }

    public static boolean closeConnection()
    {
        boolean response = true;

        try
        {
            if (connection != null && !connection.isClosed())
                connection.close();
        }
        catch (Exception ex)
        {
            response = false;
            ex.printStackTrace();
        }

        return response;
    }
}
