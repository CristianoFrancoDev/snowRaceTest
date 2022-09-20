package dao;

import model.Impianto;
import model.Pista;
import singleton.LinkDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Dao_Piste
{
    private final String QUERY_CREATE= "INSERT INTO piste (titolo, id_impianto) VALUES (?, ?)";
    private final String QUERY_READ = "SELECT * FROM piste WHERE id = ?";
    private final String QUERY_READ_LAST_RACETRACK_INSERTED = "SELECT LAST_INSERT_ID()";
    private final String QUERY_UPDATE = "UPDATE piste SET titolo = ?, id_impianto = ? WHERE id = ?";
    private final String QUERY_DELETE = "DELETE FROM piste WHERE id = ?";
    private final String QUERY_IMPIANTO_BY_PISTA = "SELECT * FROM impianti WHERE id = ?";
    private Connection connection;

    /**
     * Costruttore vuoto
     */
    public Dao_Piste(){

    }
    public Pista findById(int id)
    {
        Dao_Impianti daoImpianti = new Dao_Impianti();
        Pista response = null;

        connection = LinkDB.getConnection();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
            {
                response = new Pista(resultSet.getInt(1),
                        resultSet.getString(2),
                        daoImpianti.findById(resultSet.getInt(3)));
            }

            resultSet.close();
            preparedStatement.close();
            LinkDB.closeConnection();
        }
        catch (Exception ex)
        {
            response = null;
            ex.printStackTrace();
        }

        return response;
    }

    public boolean save(Pista pista)
    {
        PreparedStatement statement;
        String sql;
        boolean response = true;

        connection = LinkDB.getConnection();

        try
        {
            if (pista.getId() == 0)
            {
                statement = connection.prepareStatement(QUERY_CREATE);
                statement.setString(1, pista.getTitolo());
                statement.setInt(2, pista.getImpianto().getId());
                statement.executeUpdate();


                Statement stmt = connection.createStatement();
                stmt.execute(QUERY_READ_LAST_RACETRACK_INSERTED);

                ResultSet resultSet = stmt.getResultSet();

                if (resultSet.next())
                    pista.setId(resultSet.getInt(1));
                else
                    response = false;

                resultSet.close();
                stmt.close();
            }
            else
            {
                statement = connection.prepareStatement(QUERY_UPDATE);
                statement.setString(1, pista.getTitolo());
                statement.setInt(2, pista.getImpianto().getId());
                statement.setInt(3, pista.getId());

                statement.executeUpdate();
            }

            statement.close();
            LinkDB.closeConnection();
        }
        catch (Exception e)
        {
            response = false;
            e.printStackTrace();
        }

        return response;
    }

    public boolean delete(int id)
    {
        boolean response = true;

        connection = LinkDB.getConnection();


        try
        {
            PreparedStatement statement = connection.prepareStatement(QUERY_DELETE);
            statement.setInt(1, id);

            statement.execute();

            statement.close();
            LinkDB.closeConnection();
        }
        catch (Exception ex)
        {
            response = false;
            ex.printStackTrace();
        }

        return response;
    }

    public Impianto findImpianto(Pista pista)
    {
        Impianto response = null;

        connection = LinkDB.getConnection();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_IMPIANTO_BY_PISTA);
            preparedStatement.setInt(1, pista.getImpianto().getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
                response = new Impianto(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDouble(5));

            resultSet.close();
            preparedStatement.close();
            LinkDB.closeConnection();
        }
        catch (Exception ex)
        {
            response = null;
            ex.printStackTrace();
        }

        return response;
    }
}
