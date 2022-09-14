package dao;

import model.Ruolo;
import model.Utente;
import test.Main;
import util.CryptoHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Dao_Users
{
    private static Connection connection;

    public static Utente findUser(String nome)
    {
        Utente response = null;

        String sql = "SELECT * FROM utenti WHERE nome = ?";

        try
        {
            connection = Main.linkDB.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nome);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet != null)
            {
                if (resultSet.next())
                {
                    response = new Utente(resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            Ruolo.valueOf(resultSet.getString(5)),
                            CryptoHelper.decode(resultSet.getString(6)),
                            resultSet.getBoolean(7));
                }
            }

            resultSet.close();
            statement.close();
            Main.linkDB.closeConnection();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return response;
    }

    public static boolean saveUser(Utente utente)
    {
        boolean response = true;

        if (utente.getId() == 0)
        {
            //creazione utente

            String sql = "INSERT INTO utenti (nome, indirizzo, luogo, ruolo, password, cancellato) VALUES (?, ?, ?, ?, ?, ?)";

            try
            {
                connection = Main.linkDB.getConnection();

                PreparedStatement statement = connection.prepareStatement(sql);

                statement.setString(1, utente.getNome());
                statement.setString(2, utente.getIndirizzo());
                statement.setString(3, utente.getLuogo());
                statement.setString(4, utente.getRuolo().name());
                statement.setString(5, CryptoHelper.encode(utente.getPassword()));
                statement.setBoolean(6, utente.isCancellato());

                statement.executeUpdate();

                sql = "SELECT LAST_INSERT_ID()";

                Statement stmt = connection.createStatement();
                stmt.executeQuery(sql);

                ResultSet resultSet = stmt.getResultSet();

                if (resultSet.next())
                    utente.setId(resultSet.getInt(1));

                stmt.close();
                statement.close();

                Main.linkDB.closeConnection();
            }
            catch (Exception ex)
            {
                response = false;
                ex.printStackTrace();
            }
        }
        else
        {
            //modifica utente

            String sql = "UPDATE utenti SET nome = ?, indirizzo = ?, luogo = ?, ruolo = ?, password = ?, cancellato = ? ";
            sql += "WHERE id = ?";

            try
            {
                connection = Main.linkDB.getConnection();

                PreparedStatement statement = connection.prepareStatement(sql);

                statement.setString(1, utente.getNome());
                statement.setString(2, utente.getIndirizzo());
                statement.setString(3, utente.getLuogo());
                statement.setString(4, utente.getRuolo().name());
                statement.setString(5, CryptoHelper.encode(utente.getPassword()));
                statement.setBoolean(6, false);
                statement.setInt(7, utente.getId());

                statement.executeUpdate();

                statement.close();
                Main.linkDB.closeConnection();
            }
            catch (Exception ex)
            {
                response = false;
                ex.printStackTrace();
            }
        }

        return response;
    }

    public static boolean deleteUser(Utente utente)
    {
        boolean response = true;

        String sql = "UPDATE utenti SET cancellato = ? WHERE id = ?";

        try
        {
            connection = Main.linkDB.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setBoolean(1, true);
            statement.setInt(2, utente.getId());

            statement.execute();

            statement.close();
            Main.linkDB.closeConnection();
        }
        catch (Exception ex)
        {
            response = false;
            ex.printStackTrace();
        }

        return response;
    }
}
