package dao;

import model.Impianto;
import model.Pista;
import test.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class Dao_Impianti
{
    private static Connection connection;

    public static Impianto findImpiantoById(int id)
    {
        Impianto impianto = null;

        String sql = "SELECT * FROM impianti WHERE id = ?";

        try
        {
            connection = Main.linkDB.getConnection();

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
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return impianto;
    }

    public static boolean saveImpianto(Impianto impianto)
    {
        boolean response = true;
        String sql;
        PreparedStatement statement;

        connection = Main.linkDB.getConnection();

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

                statement.execute();
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
            Main.linkDB.closeConnection();
        }
        catch (SQLException e)
        {
            response = false;
            e.printStackTrace();
        }

        return response;
    }

    public static boolean deleteImpianto(Impianto impianto)
    {
        boolean response = true;



        return  response;
    }

    public static Set<Pista> getPiste(Impianto impianto)
    {
        Set<Pista> response = null;



        return  response;
    }
}
