package dao;

import model.Biglietto;
import model.Impianto;
import model.Pista;
import model.Utente;
import singleton.LinkDB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//singleton
public class Dao_Biglietti
{
    private final String QUERY_CREATE = "INSERT INTO biglietti (id_utente, id_pista, data) VALUES (?, ?, ?)";
    private final String QUERY_READ = "SELECT * FROM biglietti WHERE id = ?";
    private final String QUERY_READ_LAST_TICKET_INSERTED = "SELECT LAST_INSERT_ID()";
    private final String QUERY_READ_BY_ID_USER = "SELECT * FROM biglietti WHERE id_utente = ?";
    private final String QUERY_UPDATE = "UPDATE biglietti SET id_utente = ?, id_pista = ?, data = ? WHERE id = ?";
    private final String QUERY_DELETE = "DELETE FROM biglietti WHERE id = ?";
    private final String QUERY_FILTER_BY_DATE = "SELECT * FROM biglietti WHERE id_utente = ? AND DATE(data) BETWEEN ? AND ?";
    private final String QUERY_FILTER_BY_RACETRACK = "SELECT * FROM biglietti WHERE id_pista = ? AND id_utente = ?";
    private static Connection connection;
    private static Dao_Biglietti instance;

    private Dao_Biglietti()
    {
    }

    public static Dao_Biglietti getInstance()
    {
        if (instance == null)
            instance = new Dao_Biglietti();

        return instance;
    }

    public Biglietto findById(int id)
    {
        Biglietto response = null;

        connection = LinkDB.getConnection();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                response = new Biglietto(resultSet.getInt(1),
                        Dao_Utenti.getInstance().findById(resultSet.getInt(2)),
                        Dao_Piste.getInstance().findById(resultSet.getInt(3)),
                        resultSet.getDate(4).toLocalDate());
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

    public boolean save(Biglietto biglietto)
    {
        PreparedStatement statement;
        boolean response = true;

        connection = LinkDB.getConnection();

        try
        {
            if (biglietto.getId() == 0)
            {
                //creazione biglietto
                statement = connection.prepareStatement(QUERY_CREATE);
                statement.setInt(1, biglietto.getUtente().getId());
                statement.setInt(2, biglietto.getPista().getId());
                statement.setDate(3, Date.valueOf(biglietto.getData()));

                statement.executeUpdate();

                Statement stmt = connection.createStatement();
                stmt.execute(QUERY_READ_LAST_TICKET_INSERTED);

                ResultSet resultSet = stmt.getResultSet();

                if (resultSet.next())
                    biglietto.setId(resultSet.getInt(1));
                else
                    response = false;

                resultSet.close();
                stmt.close();
            }
            else
            {
                //modifica biglietto
                statement = connection.prepareStatement(QUERY_UPDATE);
                statement.setInt(1, biglietto.getUtente().getId());
                statement.setInt(2, biglietto.getPista().getId());
                statement.setDate(3, Date.valueOf(biglietto.getData()));
                statement.setInt(4, biglietto.getId());

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

    public List<Biglietto> getAll()
    {
        List<Biglietto> response = new ArrayList<>();

        connection = LinkDB.getConnection();

        String sql = "SELECT * FROM biglietti";

        try
        {
            Statement statement = connection.createStatement();
            statement.execute(sql);

            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next())
            {
                response.add(new Biglietto(resultSet.getInt(1),
                        Dao_Utenti.getInstance().findById(resultSet.getInt(2)),
                        Dao_Piste.getInstance().findById(resultSet.getInt(3)),
                        resultSet.getDate(4).toLocalDate()));
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

    public List<Biglietto> findByUser(Utente utente)
    {
        List<Biglietto> response = new ArrayList<>();

        connection =  LinkDB.getConnection();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ_BY_ID_USER);
            preparedStatement.setInt(1, utente.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                response.add(new Biglietto(resultSet.getInt(1),
                        Dao_Utenti.getInstance().findById(resultSet.getInt(2)),
                        Dao_Piste.getInstance().findById(resultSet.getInt(3)),
                        resultSet.getDate(4).toLocalDate()));
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

    public ArrayList<Biglietto> filterBigliettiByData(Utente utente, LocalDate date1, LocalDate date2)
    {
        boolean response = true;
        ArrayList<Biglietto> listaBiglietti = new ArrayList<>();

        connection =  LinkDB.getConnection();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_FILTER_BY_DATE);
            preparedStatement.setInt(1,utente.getId());
            preparedStatement.setDate(2, Date.valueOf(date1));
            preparedStatement.setDate(3, Date.valueOf(date2));
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                Biglietto biglietto = new Biglietto(
                        resultSet.getInt("id"),
                        Dao_Utenti.getInstance().findUser(utente.getNome()),
                        Dao_Piste.getInstance().findById(resultSet.getInt("id_pista")),
                        resultSet.getDate(4).toLocalDate());
                listaBiglietti.add(biglietto);
            }

            preparedStatement.close();
            LinkDB.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaBiglietti;
    }

    public ArrayList<Biglietto> filterByPista(Pista pista, Utente utente)
    {
        ArrayList<Biglietto> response = new ArrayList<Biglietto>();

        connection =  LinkDB.getConnection();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_FILTER_BY_RACETRACK);
            preparedStatement.setInt(1, pista.getId());
            preparedStatement.setInt(2,utente.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                response.add(new Biglietto(resultSet.getInt(1),
                        Dao_Utenti.getInstance().findById(resultSet.getInt(2)),
                        Dao_Piste.getInstance().findById(resultSet.getInt(3)),
                        resultSet.getDate(4).toLocalDate()));
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

    public ArrayList<Biglietto> filterByImpianto(String titolo, Utente utente)
    {
        ArrayList<Biglietto> listaBiglietti = new ArrayList<Biglietto>();
        ArrayList<Biglietto> listaBiglietti2 = new ArrayList<Biglietto>();

        Impianto impianto = Dao_Impianti.getInstance().findByName(titolo);
        Biglietto biglietto = new Biglietto();

        List<Pista> listpiste = Dao_Impianti.getInstance().getPiste(impianto);

        for(int i=0; i<listpiste.size(); i++)
        {
            Pista pista = new Pista(listpiste.get(i).getId());
            listaBiglietti = filterByPista(pista, utente);

            for(int j=0; j<listaBiglietti.size(); j++)
            {
                biglietto = listaBiglietti.get(j);
                listaBiglietti2.add(biglietto);
            }
        }

        return listaBiglietti2;
    }
}
