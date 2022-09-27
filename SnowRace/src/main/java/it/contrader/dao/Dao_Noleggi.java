package it.contrader.dao;

import com.mysql.cj.jdbc.ha.NdbLoadBalanceExceptionChecker;
import it.contrader.converter.AttrezzaturaConverter;
import it.contrader.converter.BigliettoConverter;
import it.contrader.dto.AttrezzaturaDTO;
import it.contrader.dto.BigliettoDTO;
import it.contrader.model.Attrezzatura;
import it.contrader.model.Biglietto;
import it.contrader.model.Noleggio;
import it.contrader.model.Utente;
import it.contrader.service.AttrezzatureService;
import it.contrader.service.BigliettiService;
import it.contrader.utils.LinkDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//singleton
public class Dao_Noleggi implements DAO<Noleggio>
{
    private static Dao_Noleggi instance;
    private static String QUERY_ALL = "SELECT * FROM noleggi";
    private final String QUERY_CREATE = "INSERT INTO noleggi (id_attrezzatura, id_biglietto) VALUES (?, ?)";
    private final String QUERY_READ = "SELECT * FROM noleggi WHERE id = ?";
    private final String QUERY_LAST_RENTAL_INSERTED = "SELECT LAST_INSERT_ID()";
    private final String QUERY_UPDATE = "UPDATE INTO noleggi SET id_attrezzatura = ?, id_biglietto = ? WHERE id = ?";
    private final String QUERY_DELETE = "DELETE FROM noleggi WHERE id = ?";
    private final String QUERY_ATTREZZATURA_BY_NOLEGGIO = "SELECT * FROM attrezzature WHERE id = ?";
    private final String QUERY_BIGLIETTO_BY_NOLEGGIO = "SELECT * biglietti WHERE id = ?";
    private final String QUERY_FILTER_BY_ID_BIGLIETTO = "SELECT * FROM noleggi WHERE id_biglietto = ?";
    private final String QUERY_NOLEGGI_BY_UTENTE = "SELECT * FROM noleggi.id AS id_noleggi, attrezzature.id AS id_attrezzature, biglietti.id AS id_biglietti " +
            "JOIN biglietti ON biglietti.id = noleggi.id_biglietto JOIN utenti on utenti.id = biglietti.id_utente " +
            "WHERE utenti.id = ?";
    private Connection connection;

    private Dao_Noleggi(){

    }

    public static Dao_Noleggi getInstance()
    {
        if (instance == null)
            instance = new Dao_Noleggi();

        return instance;
    }

    @Override
    public Noleggio read(int id)
    {
        Noleggio response = null;

        connection = LinkDB.getConnection();

        if (connection != null)
        {
            try
            {
                PreparedStatement statement = connection.prepareStatement(QUERY_READ);
                statement.setInt(1, id);
                statement.execute();

                ResultSet resultSet = statement.getResultSet();

                if (resultSet.next())
                    {
                        response = new Noleggio(resultSet.getInt(1),
                            Dao_Attrezzature.getInstance().read(resultSet.getInt(2)),
                            Dao_Biglietti.getInstance().read(resultSet.getInt(3)));
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

    public boolean delete(int id)
    {
        boolean response = true;

        connection = LinkDB.getConnection();

        try
        {
            PreparedStatement statement = connection.prepareStatement(QUERY_DELETE);
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
            LinkDB.closeConnection();
        }
        catch (Exception ex)
        {
            response= false;
            ex.printStackTrace();
        }

        return response;
    }

    @Override
    public List<Noleggio> getAll()
    {
        List<Noleggio> response = new ArrayList<>();

        connection = LinkDB.getConnection();

        if (connection != null)
        {
            try
            {
                Statement statement = connection.createStatement();
                statement.execute(QUERY_ALL);

                ResultSet resultSet = statement.getResultSet();

                while (resultSet.next())
                {
                    response.add(new Noleggio(resultSet.getInt(1),
                            Dao_Attrezzature.getInstance().read(resultSet.getInt(2)),
                            Dao_Biglietti.getInstance().read(resultSet.getInt(3))));
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

    @Override
    public boolean insert(Noleggio noleggio)
    {
        PreparedStatement statement;
        boolean response = true;

        connection = LinkDB.getConnection();

        try
        {
                //nuovo noleggio
                statement = connection.prepareStatement(QUERY_CREATE);
                statement.setInt(1, noleggio.getAttrezzatura().getId());
                statement.setInt(2, noleggio.getBiglietto().getId());
                statement.executeUpdate();

                Statement stmt = connection.createStatement();
                stmt.execute(QUERY_LAST_RENTAL_INSERTED);

                ResultSet resultSet = stmt.getResultSet();

                if (resultSet.next())
                    noleggio.setId(resultSet.getInt(1));
                else
                    response = false;

                resultSet.close();
                stmt.close();

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

    @Override
    public boolean update(Noleggio noleggio)
    {
        PreparedStatement statement;
        boolean response = true;

        connection = LinkDB.getConnection();

        if (connection != null)
        {
            try
            {
                    //modifica noleggio
                    statement = connection.prepareStatement(QUERY_UPDATE);
                    statement.setInt(1,noleggio.getAttrezzatura().getId());
                    statement.setInt(2,noleggio.getBiglietto().getId());
                    statement.setInt(3, noleggio.getId());

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

        return response;
    }

    public Attrezzatura getAttrezzatura(Noleggio noleggio)
    {
        Attrezzatura response = null;

        connection = LinkDB.getConnection();

        try
        {
            PreparedStatement statement = connection.prepareStatement(QUERY_ATTREZZATURA_BY_NOLEGGIO);
            statement.setInt(1, noleggio.getAttrezzatura().getId());

            statement.execute();

            ResultSet resultSet = statement.getResultSet();

            if (resultSet.next())
                response= new Attrezzatura(resultSet.getInt(1),
                        resultSet.getString(2));

            resultSet.close();
            statement.close();
            LinkDB.closeConnection();
        }
        catch (Exception ex)
        {
            response= null;
            ex.printStackTrace();
        }

        return response;
    }

    public Biglietto getBiglietto(Noleggio noleggio)
    {
        Biglietto response = null;

        connection = LinkDB.getConnection();

        try
        {
            PreparedStatement statement = connection.prepareStatement(QUERY_BIGLIETTO_BY_NOLEGGIO);
            statement.setInt(1, noleggio.getBiglietto().getId());
            statement.execute();

            ResultSet resultSet = statement.getResultSet();

            if (resultSet.next())
                response = new Biglietto(resultSet.getInt(1),
                        Dao_Utenti.getInstance().read(resultSet.getInt(2)),
                        Dao_Piste.getInstance().read(resultSet.getInt(3)),
                        resultSet.getDate(4).toLocalDate());

            resultSet.close();
            statement.close();
            LinkDB.closeConnection();
        }
        catch (SQLException e)
        {
            response= null;
            e.printStackTrace();
        }

        return response;
    }

    public List<Noleggio> findByIdBiglietto(Biglietto biglietto)
    {
        List<Noleggio> response = new ArrayList<Noleggio>();

        connection =  LinkDB.getConnection();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_FILTER_BY_ID_BIGLIETTO);
            preparedStatement.setInt(1, biglietto.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Noleggio noleggio = new Noleggio(resultSet.getInt(1),
                        Dao_Attrezzature.getInstance().read(resultSet.getInt(2)),
                        Dao_Biglietti.getInstance().read(resultSet.getInt(3)));

                response.add(noleggio);
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

    public List<Noleggio> getByUtente(Utente utente)
    {
        AttrezzaturaDTO attrezzaturaDTO;
        BigliettoDTO bigliettoDTO;
        List<Noleggio> response = null;

        connection = LinkDB.getConnection();

        if (connection != null)
        {
            try
            {
                response = new ArrayList<>();

                PreparedStatement preparedStatement = connection.prepareStatement(QUERY_NOLEGGI_BY_UTENTE);
                preparedStatement.setInt(1, utente.getId());

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next())
                {
                    attrezzaturaDTO = AttrezzatureService.getInstance().read(resultSet.getInt(2));
                    bigliettoDTO = BigliettiService.getInstance().read(resultSet.getInt(3));

                    response.add(new Noleggio(resultSet.getInt(1),
                            AttrezzaturaConverter.getInstance().toEntity(attrezzaturaDTO),
                            BigliettoConverter.getInstance().toEntity(bigliettoDTO)));
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

        }

        return response;
    }
}
