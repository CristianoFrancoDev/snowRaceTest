package dao;

import model.Biglietto;
import model.Ruolo;
import model.Utente;
import singleton.LinkDB;
import util.CryptoHelper;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class Dao_Utenti
{
    private static Connection connection;

    public static Utente findUser(String nome)
    {
        Utente response = null;

        connection = LinkDB.getConnection();

        if (connection != null)
        {
            String sql = "SELECT * FROM utenti WHERE nome = ?";

            try
            {
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
                LinkDB.closeConnection();
            }
            catch (Exception ex)
            {
                response = null;
                ex.printStackTrace();
            }
        }

        return response;
    }

    public static boolean saveUser(Utente utente)
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
                if (utente.getId() == 0)
                {
                    //creazione utente

                    String sql = "INSERT INTO utenti (nome, indirizzo, luogo, ruolo, password, cancellato) VALUES (?, ?, ?, ?, ?, ?)";

                        statement = connection.prepareStatement(sql);

                        statement.setString(1, utente.getNome());
                        statement.setString(2, utente.getIndirizzo());
                        statement.setString(3, utente.getLuogo());
                        statement.setString(4, utente.getRuolo().name());
                        statement.setString(5, CryptoHelper.encode(utente.getPassword()));
                        statement.setBoolean(6, utente.isCancellato());

                        statement.executeUpdate();

                        sql = "SELECT LAST_INSERT_ID()";

                        Statement stmt = connection.createStatement();
                        stmt.execute(sql);

                        ResultSet resultSet = stmt.getResultSet();

                        if (resultSet.next())
                            utente.setId(resultSet.getInt(1));
                        else
                            response = false;

                        resultSet.close();
                        stmt.close();
                }
                else
                {
                    //modifica utente

                    String sql = "UPDATE utenti SET nome = ?, indirizzo = ?, luogo = ?, ruolo = ?, password = ?, cancellato = ? ";
                    sql += "WHERE id = ?";

                        statement = connection.prepareStatement(sql);

                        statement.setString(1, utente.getNome());
                        statement.setString(2, utente.getIndirizzo());
                        statement.setString(3, utente.getLuogo());
                        statement.setString(4, utente.getRuolo().name());
                        statement.setString(5, CryptoHelper.encode(utente.getPassword()));
                        statement.setBoolean(6, false);
                        statement.setInt(7, utente.getId());

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

    public static boolean deleteUser(Utente utente)
    {
        boolean response = true;

        String sql = "UPDATE utenti SET cancellato = ? WHERE id = ?";

        connection = LinkDB.getConnection();

        if (connection == null)
            response = false;
        else
        {
            try
            {
                PreparedStatement statement = connection.prepareStatement(sql);

                statement.setBoolean(1, true);
                statement.setInt(2, utente.getId());

                statement.executeUpdate();

                statement.close();
                LinkDB.closeConnection();
            }
            catch (Exception ex)
            {
                response = false;
                ex.printStackTrace();
            }
        }

        return response;
    }

    public static Set<Biglietto> getBiglietti(Utente utente)
    {
        Set<Biglietto> biglietti = null;

        String sql = "SELECT * FROM biglietti WHERE id_utente = ?";

        connection = LinkDB.getConnection();

        if (connection != null)
        {
            try
            {
                PreparedStatement statement = connection.prepareStatement(sql);

                statement.setInt(1, utente.getId());

                statement.execute();

                ResultSet resultSet = statement.getResultSet();

                if (resultSet != null)
                {
                    Biglietto biglietto;

                    biglietti = new HashSet<>();

                    while (resultSet.next())
                    {
//                        biglietto = new Biglietto(resultSet.getInt(1),);











                    }
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        return biglietti;
    }
}
