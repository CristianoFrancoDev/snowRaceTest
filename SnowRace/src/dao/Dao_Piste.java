package dao;

import converter.ImpiantoConverter;
import model.Impianto;
import model.Pista;
import service.ImpiantiService;
import singleton.LinkDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//singleton
public class Dao_Piste
{
    private static Dao_Piste instance;
    private final String QUERY_READ_BY_TITOLO = "SELECT * FROM piste WHERE titolo = ?";
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
    private Dao_Piste(){

    }

    public static Dao_Piste getInstance()
    {
        if (instance == null)
            instance = new Dao_Piste();

        return instance;
    }

    public Pista findById(int id)
    {
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
                        Dao_Impianti.getInstance().findById(resultSet.getInt(3)));
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

    public Pista findPista(String titolo)
    {
        Pista response = null;
        ImpiantiService impiantiService = new ImpiantiService();

        connection = LinkDB.getConnection();

        if (connection != null)
        {
            try
            {
                PreparedStatement statement = connection.prepareStatement(QUERY_READ_BY_TITOLO);
                statement.setString(1, titolo);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet != null)
                {
                    if (resultSet.next())
                    {
                        Impianto impianto = ImpiantoConverter.getInstance().toEntity(impiantiService.read(resultSet.getInt(3)));

                        response = new Pista(resultSet.getInt(1),
                                resultSet.getString(2),
                                impianto);
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

    public List<Pista> getAll()
    {
        List<Pista> response = new ArrayList<>();

        connection = LinkDB.getConnection();

        String sql = "SELECT * FROM piste";

        try
        {
            Statement statement = connection.createStatement();
            statement.execute(sql);

            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next())
            {
                response.add(new Pista(resultSet.getInt(1),
                        resultSet.getString(2),
                        Dao_Impianti.getInstance().findById(resultSet.getInt(3))));
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
