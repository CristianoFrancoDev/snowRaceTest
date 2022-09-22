package dao;

import model.Impianto;
import model.Pista;
import singleton.LinkDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//singleton
public class Dao_Impianti
{
    public final String QUERY_ALL = "SELECT * FROM impianti";
    public final String QUERY_CREATE = "INSERT INTO impianti (titolo, descrizione, foto, prezzo) VALUES (?, ?, ?, ?)";
    public final String QUERY_READ = "SELECT * FROM impianti WHERE id = ?";
    public final String QUERY_READ_LAST_SKY_FACILITY = "SELECT LAST_INSERT_ID()";
    public final String QUERY_UPDATE = "UPDATE impianti SET titolo = ?, descrizione = ?, foto = ?, prezzo = ? WHERE id = ?";
    public final String QUERY_DELETE = "DELETE FROM impianti WHERE id = ?";
    public final String QUERY_FILTER_BY_NAME_SKY_FACILITY = "SELECT * FROM impianti WHERE titolo = ?";
    public final String QUERY_PISTE_BY_NOME_IMPIANTO = "SELECT * FROM impianti INNER JOIN piste ON impianti.id = piste.id_impianto WHERE impianti.id = ?";
    public final String QUERY_PISTE_BY_ID_IMPIANTO = "SELECT * FROM piste WHERE id_impianto = ?";
    private static Dao_Impianti instance;
    private  Connection connection;

    private Dao_Impianti()
    {

    }

    public static Dao_Impianti getInstance()
    {
        if (instance == null)
            instance = new Dao_Impianti();

        return instance;
    }

    public Impianto findById(int id)
    {
        Impianto impianto = null;

        connection = LinkDB.getConnection();

        if (connection != null)
        {
            try
            {
                PreparedStatement statement = connection.prepareStatement(QUERY_READ);
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

    public Impianto findByName(String name)
    {
        Impianto impianto = null;

        connection = LinkDB.getConnection();

        if (connection != null)
        {
            try
            {
                PreparedStatement statement = connection.prepareStatement(QUERY_FILTER_BY_NAME_SKY_FACILITY);
                statement.setString(1, name);
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

        try
        {
            Statement statement = connection.createStatement();
            statement.execute(QUERY_ALL);

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
                    statement = connection.prepareStatement(QUERY_CREATE);
                    statement.setString(1, impianto.getTitolo());
                    statement.setString(2, impianto.getDescrizione());
                    statement.setString(3, impianto.getFoto());
                    statement.setDouble(4, impianto.getPrezzo());
                    statement.executeUpdate();

                    Statement stmt = connection.createStatement();
                    stmt.execute(QUERY_READ_LAST_SKY_FACILITY);

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
                    statement = connection.prepareStatement(QUERY_UPDATE);
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
            try
            {
                PreparedStatement statement = connection.prepareStatement(QUERY_DELETE);

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
            try
            {
                PreparedStatement statement = connection.prepareStatement(QUERY_PISTE_BY_ID_IMPIANTO);
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

    public ArrayList<String> filterByName(String name)
    {
        Impianto impianto = findByName(name);

        ArrayList<Impianto> response1 = new ArrayList<Impianto>();
        ArrayList<String> response2 = new ArrayList<String>();
        ArrayList<String> response3 = new ArrayList<String>();

        connection = LinkDB.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_PISTE_BY_NOME_IMPIANTO);
            preparedStatement.setInt(1, impianto.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                if(response1.isEmpty()){
                    response1.add(new Impianto(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getDouble(5))
                    );
                }

                response2.add(resultSet.getString(7));
            }
            response3.add(response1 + "\n" + "Piste associate all'impianto: " + response2);
            resultSet.close();
            preparedStatement.close();
            LinkDB.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return response3;
    }
}
