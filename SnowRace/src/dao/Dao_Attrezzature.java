package dao;

import model.Attrezzatura;
import model.Biglietto;
import model.Noleggio;
import singleton.LinkDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Dao_Attrezzature
{
    private Connection connection;

    public Attrezzatura findById(int id)
    {
        Attrezzatura response = null;

        connection = LinkDB.getConnection();

        if (connection != null)
        {
            String sql = "SELECT * FROM attrezzature WHERE id = ?";

            try
            {
                PreparedStatement statement = connection.prepareStatement(sql);

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

        String sql = "SELECT * FROM attrezzature";

        try
        {
            Statement statement = connection.createStatement();
            statement.execute(sql);

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

                    sql = "INSERT INTO attrezzature (articolo) VALUES (?)";

                    statement = connection.prepareStatement(sql);
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

                    sql = "UPDATE attrezzature SET articolo = ? WHERE id = ?";

                    statement = connection.prepareStatement(sql);
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
            String sql = "DELETE FROM attrezzature WHERE id = ?";

            try
            {
                PreparedStatement statement = connection.prepareStatement(sql);
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
