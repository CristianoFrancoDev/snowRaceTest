package it.contrader.dao;

import it.contrader.converter.AttrezzaturaConverter;
import it.contrader.dto.AttrezzaturaDTO;
import it.contrader.model.Attrezzatura;
import it.contrader.utils.LinkDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//singleton
public class Dao_Attrezzature implements DAO<Attrezzatura>
{
    private final String LAST_ROW_ID = "SELECT LAST_INSERT_ID()";
    private final String QUERY_ALL = "SELECT * FROM attrezzature";
    private final String QUERY_CREATE = "INSERT INTO attrezzature (articolo) VALUES (?)";
    private final String QUERY_READ = "SELECT * FROM attrezzature WHERE id = ?";
    private final String QUERY_UPDATE = "UPDATE attrezzature SET articolo = ? WHERE id = ?";
    private final String QUERY_DELETE = "DELETE attrezzature WHERE id = ?";
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

    @Override
    public List<Attrezzatura> getAll()
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

    @Override
    public Attrezzatura read(int id)
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

    @Override
    public boolean insert(Attrezzatura attrezzatura)
    {
        PreparedStatement statement;
        boolean response = true;

        connection = LinkDB.getConnection();

        if (connection == null)
            response = false;
        else
        {
            try
            {
                //creazione attrezzatura
                statement = connection.prepareStatement(QUERY_CREATE);
                statement.setString(1, attrezzatura.getArticolo());

                statement.executeUpdate();

                Statement stmt = connection.createStatement();
                stmt.execute(LAST_ROW_ID);

                ResultSet resultSet = stmt.getResultSet();

                if (resultSet.next())
                    attrezzatura.setId(resultSet.getInt(1));
                else
                    response = false;

                resultSet.close();
                stmt.close();
            }
            catch (SQLException e)
            {
                response = false;
                e.printStackTrace();
            }
        }

        return response;
    }

    @Override
    public boolean update(Attrezzatura attrezzatura)
    {
        PreparedStatement statement;
        boolean response = true;

        connection = LinkDB.getConnection();

        if (connection == null)
            response = false;
        else
        {
            try
            {
                //modifica attrezzatura
                statement = connection.prepareStatement(QUERY_UPDATE);
                statement.setString(1, attrezzatura.getArticolo());
                statement.setInt(2, attrezzatura.getId());

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

    @Override
    public boolean delete(int id)
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
                statement.setInt(1, id);

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
