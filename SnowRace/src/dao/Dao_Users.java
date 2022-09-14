package dao;

import model.Ruolo;
import model.Utente;
import util.CryptoHelper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dao_Users
{
    private static String urlDB = "jdbc:mysql://localhost:3306/snowracedb";
    private static Connection connection;

    public static Utente findUser(String nome)
    {
        Utente response = null;

        String sql = "SELECT * FROM utenti WHERE nome = ?";

        try
        {
            connection = DriverManager.getConnection(urlDB, "", "");

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nome);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet != null)
            {
                if (resultSet.next())
                {
                    response = new Utente(resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            Ruolo.valueOf(resultSet.getString(5)),
                            CryptoHelper.decode(resultSet.getString(6)),
                            resultSet.getBoolean(7));
                }
            }

            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return response;
    }

    public static void eliminaUser(Utente utente)
    {}
}
