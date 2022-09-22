package dao;

import model.Attrezzatura;
import model.Biglietto;
import model.Noleggio;
import model.Utente;
import singleton.LinkDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//singleton
public class Dao_Noleggi
{
    private static Dao_Noleggi instance;
    private Connection connection;
    private final String QUERY_CREATE = "INSERT INTO noleggi (id_attrezzatura, id_biglietto) VALUES (?, ?)";
    private final String QUERY_READ = "SELECT * FROM noleggi WHERE id = ?";
    private final String QUERY_LAST_RENTAL_INSERTED = "SELECT LAST_INSERT_ID()";
    private final String QUERY_UPDATE = "UPDATE INTO noleggi SET id_attrezzatura = ?, id_biglietto = ? WHERE id = ?";
    private final String QUERY_DELETE = "DELETE FROM noleggi WHERE id = ?";
    private final String QUERY_ATTREZZATURA_BY_NOLEGGIO = "SELECT * FROM attrezzature WHERE id = ?";
    private final String QUERY_BIGLIETTO_BY_NOLEGGIO = "SELECT * biglietti WHERE id = ?";
    private final String QUERY_FILTER_BY_ID_BIGLIETTO = "SELECT * FROM noleggi WHERE id_biglietto = ?";

    private Dao_Noleggi(){

    }

    public static Dao_Noleggi getInstance()
    {
        if (instance == null)
            instance = new Dao_Noleggi();

        return instance;
    }

    public Noleggio findById(int id)
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
                    response = new Noleggio(resultSet.getInt(1),
                            Dao_Attrezzature.getInstance().findById(resultSet.getInt(2)),
                            Dao_Biglietti.getInstance().findById(resultSet.getInt(3)));

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

    public boolean save(Noleggio noleggio)
    {
        PreparedStatement statement;
        boolean response = true;

        connection = LinkDB.getConnection();

        try
        {
            if (noleggio.getId() == 0)
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
            }
            else
            {
                //modifica noleggio
                statement = connection.prepareStatement(QUERY_UPDATE);
                statement.setInt(1,noleggio.getAttrezzatura().getId());
                statement.setInt(2,noleggio.getBiglietto().getId());
                statement.setInt(3, noleggio.getId());

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

    public List<Noleggio> getAll()
    {
        List<Noleggio> response = new ArrayList<>();

        connection = LinkDB.getConnection();

        String sql = "SELECT * FROM noleggi";

        try
        {
            Statement statement = connection.createStatement();
            statement.execute(sql);

            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next())
            {
                response.add(new Noleggio(resultSet.getInt(1),
                        Dao_Attrezzature.getInstance().findById(resultSet.getInt(2)),
                        Dao_Biglietti.getInstance().findById(resultSet.getInt(3))));
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
                        Dao_Utenti.getInstance().findById(resultSet.getInt(2)),
                        Dao_Piste.getInstance().findById(resultSet.getInt(3)),
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

    public ArrayList<Noleggio> findByIdBiglietto(Biglietto biglietto)
    {
        ArrayList<Noleggio> response = new ArrayList<Noleggio>();

        connection =  LinkDB.getConnection();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_FILTER_BY_ID_BIGLIETTO);
            preparedStatement.setInt(1, biglietto.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Noleggio noleggio = new Noleggio(resultSet.getInt(1),
                        Dao_Attrezzature.getInstance().findById(resultSet.getInt(2)),
                        Dao_Biglietti.getInstance().findById(resultSet.getInt(3)));

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

    public ArrayList<Noleggio> showAllNoleggiByUser(Utente utente){
        ArrayList<Noleggio> listNoleggio = new ArrayList<>();
        ArrayList<Biglietto> listBiglietti = new ArrayList<>();
        ArrayList<Noleggio> listNoleggio2 = new ArrayList<>();
        Noleggio noleggio = new Noleggio();

        utente = Dao_Utenti.getInstance().findById(utente.getId());
        listBiglietti = new ArrayList<>(Dao_Biglietti.getInstance().findByUser(utente));

        for(int i=0; i<listBiglietti.size(); i++)
        {
            Biglietto biglietto = new Biglietto();
            biglietto = listBiglietti.get(i);
            listNoleggio2 = findByIdBiglietto(biglietto);

            for(int j=0; j<listNoleggio2.size(); j++){
                noleggio = listNoleggio2.get(j);
                listNoleggio.add(noleggio);
            }
        }

        return listNoleggio;
    }
}
