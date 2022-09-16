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
    private Connection connection;

    public Pista findById(int id)
    {
        Dao_Impianti daoImpianti = new Dao_Impianti();
        Pista response = null;

        String sql = "SELECT * FROM piste WHERE id = ?";

        connection = LinkDB.getConnection();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
                sql = "INSERT INTO piste (titolo, id_impianto) VALUES (?, ?)";

                statement = connection.prepareStatement(sql);

                statement.setString(1, pista.getTitolo());
                statement.setInt(2, pista.getImpianto().getId());

                statement.executeUpdate();

                sql = "SELECT LAST_INSERT_ID()";

                Statement stmt = connection.createStatement();
                stmt.execute(sql);

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
                sql = "UPDATE piste SET titolo = ?, id_impianto = ? WHERE id = ?";

                statement = connection.prepareStatement(sql);

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

        String sql = "DELETE FROM piste WHERE id = ?";

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);
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

        String sql = "SELECT * FROM impianti WHERE id = ?";

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
