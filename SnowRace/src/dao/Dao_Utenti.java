package dao;

import model.Biglietto;
import model.Ruolo;
import model.Utente;
import singleton.LinkDB;
import util.CryptoHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//singleton
public class Dao_Utenti
{
    private static Dao_Utenti instance;
    private final String QUERY_ALL = "SELECT * FROM utenti WHERE cancellato = 0";
    private final String QUERY_CREATE = "INSERT INTO utenti (nome, indirizzo, luogo, ruolo, password, cancellato) VALUES (?, ?, ?, ?, ?, ?)";
    private final String QUERY_READ = "SELECT * FROM utenti WHERE id = ?";
    private final String QUERY_READ_BY_NAME = "SELECT * FROM utenti WHERE nome = ? AND cancellato = 0";
    private final String QUERY_READ_BY_NAME_AND_PASSWORD = "SELECT * FROM utenti WHERE nome = ? AND password = ? AND cancellato = 0";
    private final String QUERY_READ_LAST_USER_INSERTED = "SELECT LAST_INSERT_ID()";
    private final String QUERY_UPDATE = "UPDATE utenti SET nome = ?, indirizzo = ?, luogo = ?, ruolo = ?, password = ?, cancellato = ? WHERE id = ?";
    private final String QUERY_DELETE = "UPDATE utenti SET cancellato = ? WHERE id = ?";
    private final String QUERY_TICKETS_BY_USER_ID = "SELECT * FROM biglietti WHERE id_utente = ?";
    private Connection connection;

    /**
     * Costruttore vuoto
     */
    private Dao_Utenti()
    {
    }

    public static Dao_Utenti getInstance()
    {
        if (instance == null)
            instance = new Dao_Utenti();

        return instance;
    }

    public Utente findById(int id)
    {
        Utente response = null;

        connection = LinkDB.getConnection();

        if (connection != null)
        {
            try
            {
                PreparedStatement statement = connection.prepareStatement(QUERY_READ);
                statement.setInt(1, id);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next())
                {
                    response = new Utente(resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            Ruolo.valueOf(resultSet.getString(5).toUpperCase()),
                            CryptoHelper.decode(resultSet.getString(6)),
                            resultSet.getBoolean(7));
                }

                resultSet.close();
                statement.close();
                LinkDB.closeConnection();
            }
            catch (Exception ex)
            {
                response = null;
            }
        }

        return response;
    }

    public Utente findUser(String nome)
    {
        Utente response = null;

        connection = LinkDB.getConnection();

        if (connection != null)
        {
            try
            {
                PreparedStatement statement = connection.prepareStatement(QUERY_READ_BY_NAME);
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
                                Ruolo.valueOf(resultSet.getString(5).toUpperCase()),
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

    public Utente findByNomeAndPassword(String nome, String password)
    {
        Utente response = null;

        connection = LinkDB.getConnection();

        if (connection != null)
        {
            try
            {
                PreparedStatement statement = connection.prepareStatement(QUERY_READ_BY_NAME_AND_PASSWORD);
                statement.setString(1, nome);
                statement.setString(2, CryptoHelper.encode(password));

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next())
                {
                    response = new Utente(resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            Ruolo.valueOf(resultSet.getString(5).toUpperCase()),
                            CryptoHelper.decode(resultSet.getString(6)),
                            resultSet.getBoolean(7));
                }

                resultSet.close();
                statement.close();
                LinkDB.closeConnection();
            }
            catch (Exception ex)
            {
                response = null;
            }
        }

        return response;
    }

    public List<Utente> findAll()
    {
        List<Utente> response = new ArrayList<>();

        connection = LinkDB.getConnection();

        try
        {
            Statement statement = connection.createStatement();
            statement.execute(QUERY_ALL);

            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next())
            {
                response.add(new Utente(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        Ruolo.valueOf(resultSet.getString(5).toUpperCase()),
                        resultSet.getString(6),
                        resultSet.getBoolean(7)));
            }

            resultSet.close();
            statement.close();
            LinkDB.closeConnection();
        }
        catch (Exception ex)
        {
            response = null;
        }

        return response;
    }

    public boolean save(Utente utente)
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
                    statement = connection.prepareStatement(QUERY_CREATE);
                    statement.setString(1, utente.getNome());
                    statement.setString(2, utente.getIndirizzo());
                    statement.setString(3, utente.getLuogo());
                    statement.setString(4, utente.getRuolo().name().toLowerCase());
                    statement.setString(5, CryptoHelper.encode(utente.getPassword()));
                    statement.setBoolean(6, utente.isCancellato());
                    statement.executeUpdate();

                    Statement stmt = connection.createStatement();
                    stmt.execute(QUERY_READ_LAST_USER_INSERTED);

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
                    statement = connection.prepareStatement(QUERY_UPDATE);

                    statement.setString(1, utente.getNome());
                    statement.setString(2, utente.getIndirizzo());
                    statement.setString(3, utente.getLuogo());
                    statement.setString(4, utente.getRuolo().name().toLowerCase());
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
            }
        }

        return response;
    }

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
                statement.setBoolean(1, true);
                statement.setInt(2, id);

                statement.executeUpdate();

                statement.close();
                LinkDB.closeConnection();
            }
            catch (Exception ex)
            {
                response = false;
            }
        }

        return response;
    }

    public List<Biglietto> getBiglietti(Utente utente)
    {
        Biglietto biglietto;
        List<Biglietto> response = null;

        connection = LinkDB.getConnection();

        if (connection != null)
        {
            response = new ArrayList<>();

            try
            {
                PreparedStatement statement = connection.prepareStatement(QUERY_TICKETS_BY_USER_ID);
                statement.setInt(1, utente.getId());

                statement.execute();

                ResultSet resultSet = statement.getResultSet();

                while (resultSet.next())
                {
                    biglietto = new Biglietto(resultSet.getInt(1),
                            utente,
                            Dao_Piste.getInstance().findById(resultSet.getInt(3)),
                            resultSet.getDate(4).toLocalDate());
                    response.add(biglietto);
                }

                resultSet.close();
                statement.close();
                LinkDB.closeConnection();
            }
            catch (SQLException e)
            {
                response = null;
            }
        }

        return response;
    }
}
