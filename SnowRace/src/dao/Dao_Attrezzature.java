package dao;

import model.Attrezzatura;
import singleton.LinkDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//singleton
public class Dao_Attrezzature
{
    private final String QUERY_ALL = "SELECT * FROM attrezzature";
    private final String QUERY_CREATE = "INSERT INTO attrezzature (articolo) VALUES (?)";
    private final String QUERY_READ = "SELECT * FROM attrezzature WHERE id = ?";
    private final String QUERY_UPDATE = "UPDATE attrezzature SET articolo = ? WHERE id = ?";
    private final String QUERY_DELETE = "UPDATE attrezzature SET articolo = ? WHERE id = ?";
    private static Dao_Attrezzature instance;
    private Connection connection;

    private Dao_Attrezzature()
    {
    }

    public static Dao_Attrezzature getInstance()
    {
        if (instance == null)
            instance = new Dao_Attrezzature();

        return instance;
    }

    public Attrezzatura findById(int id)
    {
        Attrezzatura response = null;

        connection = LinkDB.getConnection();

        if (connection != null)
        {
            try
            {
                PreparedStatement statement = connection.prepareStatement(QUERY_READ);
                statement.setInt(1, id);
                statement.execute();

                ResultSet resultSet = statement.getResultSet();

                if (resultSet != null)
                {
                    if (resultSet.next())
                        response = new Attrezzatura(resultSet.getInt(1), resultSet.getString(2));
                }

                resultSet.close();
                statement.close();
                LinkDB.closeConnection();
            }
            catch (SQLException e)
            {
                response = null;
                e.printStackTrace();
            }
        }

        return response;
    }

    public List<Attrezzatura> findAll()
    {
        List<Attrezzatura> response = new ArrayList<>();
        connection = LinkDB.getConnection();

        try
        {
            Statement statement = connection.createStatement();
            statement.execute(QUERY_ALL);
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next())
            {
                response.add(new Attrezzatura(resultSet.getInt(1),
                        resultSet.getString(2)));
            }

            resultSet.close();
            statement.close();
            LinkDB.closeConnection();
        }
        catch (SQLException e)
        {
            response = null;
            e.printStackTrace();
        }

        return response;
    }

    public boolean save(Attrezzatura attrezzatura)
    {
        String sql;
        PreparedStatement statement;
        boolean response = true;

        connection = LinkDB.getConnection();

        if (connection == null)
            response = false;
        else
        {
            try
            {
                if (attrezzatura.getId() == 0)
                {
                    //creazione attrezzatura
                    statement = connection.prepareStatement(QUERY_CREATE);
                    statement.setString(1, attrezzatura.getArticolo());

                    statement.executeUpdate();

                    sql = "SELECT LAST_INSERT_ID()";

                    Statement stmt = connection.createStatement();
                    stmt.execute(sql);

                    ResultSet resultSet = stmt.getResultSet();

                    if (resultSet.next())
                        attrezzatura.setId(resultSet.getInt(1));
                    else
                        response = false;

                    resultSet.close();
                    stmt.close();
                }
                else
                {
                    //modifica attrezzatura
                    statement = connection.prepareStatement(QUERY_UPDATE);
                    statement.setString(1, attrezzatura.getArticolo());
                    statement.setInt(2, attrezzatura.getId());

                    statement.execute();
                }

                statement.close();
                LinkDB.closeConnection();
            }
            catch (SQLException e)
            {
                response = false;
                e.printStackTrace();
            }
        }

        return response;
    }

    public boolean delete(Attrezzatura attrezzatura)
    {
        boolean response = true;

        connection = LinkDB.getConnection();

        if (connection == null)
            response = false;
        else
        {
            try
            {
                PreparedStatement statement = connection.prepareStatement(QUERY_DELETE);
                statement.setInt(1, attrezzatura.getId());

                statement.execute();

                statement.close();
                LinkDB.closeConnection();
            }
            catch (SQLException e)
            {
                response = false;
                e.printStackTrace();
            }
        }

        return response;
    }
}
