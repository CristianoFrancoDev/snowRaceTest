package dao;

import model.Attrezzatura;
import model.Biglietto;
import model.Noleggio;
import model.Utente;
import singleton.LinkDB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Dao_Noleggi
{
    private Connection connection;

    private final String QUERY_CREATE = "INSERT INTO noleggi (id_attrezzatura, id_biglietto) VALUES (?, ?)";
    private final String QUERY_READ = "SELECT * FROM noleggi WHERE id = ?";
    private final String QUERY_LAST_RENTAL_INSERTED = "SELECT LAST_INSERT_ID()";
    private final String QUERY_UPDATE = "UPDATE INTO noleggi SET id_attrezzatura = ?, id_biglietto = ? WHERE id = ?";
    private final String QUERY_DELETE = "DELETE FROM noleggi WHERE id = ?";
    private final String QUERY_ATTREZZATURA_BY_NOLEGGIO = "SELECT * FROM attrezzature WHERE id = ?";
    private final String QUERY_BIGLIETTO_BY_NOLEGGIO = "SELECT * biglietti WHERE id = ?";
    private final String QUERY_FILTER_BY_ID_BIGLIETTO = "SELECT * FROM noleggi WHERE id_biglietto = ?";

    /**
     * Costruttore vuoto
     */
    public Dao_Noleggi(){

    }
    public Noleggio findById(int id)
    {
        Dao_Attrezzature daoAttrezzature = new Dao_Attrezzature();
        Dao_Biglietti daoBiglietti = new Dao_Biglietti();
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
                            daoAttrezzature.findById(resultSet.getInt(2)),
                            daoBiglietti.findById(resultSet.getInt(3)));

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

    public boolean delete(Noleggio noleggio)
    {
        boolean response = true;

        connection = LinkDB.getConnection();

        try
        {
            PreparedStatement statement = connection.prepareStatement(QUERY_DELETE);
            statement.setInt(1, noleggio.getId());
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
        Dao_Utenti daoUtenti = new Dao_Utenti();
        Dao_Biglietti daoBiglietti = new Dao_Biglietti();
        Dao_Piste daoPiste = new Dao_Piste();
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
                        daoUtenti.findById(resultSet.getInt(2)),
                        daoPiste.findById(resultSet.getInt(3)),
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
        Dao_Attrezzature daoAttrezzature = new Dao_Attrezzature();
        Dao_Biglietti daoBiglietti = new Dao_Biglietti();
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
                        daoAttrezzature.findById(resultSet.getInt(2)),
                        daoBiglietti.findById(resultSet.getInt(3)));
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

        Dao_Utenti daoUtenti = new Dao_Utenti();
        Dao_Biglietti daoBiglietti = new Dao_Biglietti();
        Dao_Noleggi dao_noleggi = new Dao_Noleggi();

        utente = daoUtenti.findById(utente.getId());
        listBiglietti = daoBiglietti.findByUser(utente);

        for(int i=0; i<listBiglietti.size(); i++)
        {
            Biglietto biglietto = new Biglietto();
            biglietto = listBiglietti.get(i);
            listNoleggio2 = dao_noleggi.findByIdBiglietto(biglietto);

            for(int j=0; j<listNoleggio2.size(); j++){
                noleggio = listNoleggio2.get(j);
                listNoleggio.add(noleggio);
            }
        }

        return listNoleggio;
    }
}
