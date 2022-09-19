package dao;

import model.Impianto;
import model.Pista;
import singleton.LinkDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dao_Impianti
{
    private  Connection connection;

    public Impianto findById(int id)
    {
        Impianto impianto = null;

        String sql = "SELECT * FROM impianti WHERE id = ?";

        connection = LinkDB.getConnection();

        if (connection != null)
        {
            try
            {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, id);
                statement.execute();

                ResultSet resultSet = statement.getResultSet();

                if (resultSet != null && resultSet.next())
                {
                    impianto = new Impianto(resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getDouble(5));
                }

                resultSet.close();
                statement.close();
                LinkDB.closeConnection();
            }
            catch (Exception ex)
            {
                impianto = null;
                ex.printStackTrace();
            }
        }

        return impianto;
    }

    public List<Impianto> findAll()
    {
        List<Impianto> response = new ArrayList<>();

        connection = LinkDB.getConnection();

        String sql = "SELECT * FROM impianti";

        try
        {
            Statement statement = connection.createStatement();
            statement.execute(sql);

            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next())
            {
                response.add(new Impianto(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDouble(5)));
            }

            resultSet.close();
            statement.close();
            LinkDB.closeConnection();
        }
        catch (Exception ex)
        {
            response = null;
            ex.printStackTrace();
        }

        return response;
    }

    public boolean save(Impianto impianto)
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
                if (impianto.getId() == 0)
                {
                    //creazione impianto

                    sql = "INSERT INTO impianti (titolo, descrizione, foto, prezzo) VALUES (?, ?, ?, ?)";

                    statement = connection.prepareStatement(sql);

                    statement.setString(1, impianto.getTitolo());
                    statement.setString(2, impianto.getDescrizione());
                    statement.setString(3, impianto.getFoto());
                    statement.setDouble(4, impianto.getPrezzo());

                    statement.executeUpdate();

                    sql = "SELECT LAST_INSERT_ID()";

                    Statement stmt = connection.createStatement();
                    stmt.execute(sql);

                    ResultSet resultSet = stmt.getResultSet();

                    if (resultSet.next())
                        impianto.setId(resultSet.getInt(1));
                    else
                        response = false;

                    resultSet.close();
                    stmt.close();
                }
                else
                {
                    //modifica impianto

                    sql = "UPDATE impianti SET titolo = ?, descrizione = ?, foto = ?, prezzo = ? WHERE id = ?";

                    statement = connection.prepareStatement(sql);

                    statement.setString(1, impianto.getTitolo());
                    statement.setString(2, impianto.getDescrizione());
                    statement.setString(3, impianto.getFoto());
                    statement.setDouble(4, impianto.getPrezzo());
                    statement.setInt(5, impianto.getId());

                    statement.executeUpdate();
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

    public boolean delete(Impianto impianto)
    {
        boolean response = true;

        connection = LinkDB.getConnection();

        if (connection == null)
            response = false;
        else
        {
            String sql = "DELETE FROM impianti WHERE id = ?";

            try
            {
                PreparedStatement statement = connection.prepareStatement(sql);

                statement.setInt(1, impianto.getId());
                statement.executeUpdate();

                statement.close();
                LinkDB.closeConnection();
            }
            catch (SQLException e)
            {
                response = false;
                e.printStackTrace();
            }
        }

        return  response;
    }

    public List<Pista> getPiste(Impianto impianto)
    {
        Pista pista;
        List<Pista> response = new ArrayList<>();

        connection = LinkDB.getConnection();

        if (connection != null)
        {
            String sql = "SELECT * FROM piste WHERE id_impianto = ?";

            try
            {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, impianto.getId());

                statement.execute();

                ResultSet resultSet = statement.getResultSet();

                while(resultSet.next())
                {
                    pista = new Pista(resultSet.getInt(1), resultSet.getString(2), impianto);
                    response.add(pista);
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
}
