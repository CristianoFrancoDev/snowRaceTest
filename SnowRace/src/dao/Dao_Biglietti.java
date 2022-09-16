package dao;

import model.Biglietto;
import model.Utente;
import singleton.LinkDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dao_Biglietti
{
    private Connection connection;

    public Biglietto findById(int id)
    {
        Dao_Utenti daoUtenti = new Dao_Utenti();
        Dao_Piste daoPiste = new Dao_Piste();
        Biglietto response = null;

        connection = LinkDB.getConnection();

        String sql = "SELECT * FROM biglietti WHERE id = ?";

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                response = new Biglietto(resultSet.getInt(1),
                        daoUtenti.findById(resultSet.getInt(2)),
                        daoPiste.findById(resultSet.getInt(3)),
                        resultSet.getDate(4).toLocalDate());
            }

            resultSet.close();
            preparedStatement.close();
            LinkDB.closeConnection();
        }
        catch (Exception ex)
        {
            response = null;
            throw new RuntimeException(ex);
        }

        System.out.println(response.toString());

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

                String sql = "INSERT INTO biglietti (id_utente, id_pista, data) VALUES (?, ?, ?)";

                statement = connection.prepareStatement(sql);
                statement.setInt(1, biglietto.getUtente().getId());
                statement.setInt(2, biglietto.getPista().getId());
                statement.setDate(3, Date.valueOf(biglietto.getData()));

                statement.executeUpdate();

                sql = "SELECT LAST_INSERT_ID()";

                Statement stmt = connection.createStatement();
                stmt.execute(sql);

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

                String sql = "UPDATE biglietti SET id_utente = ?, id_pista = ?, data = ? WHERE id = ?";

                statement = connection.prepareStatement(sql);
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

    public boolean delete(Biglietto biglietto)
    {
        boolean response = true;

        connection = LinkDB.getConnection();

        if (connection == null)
            response = false;
        else
        {
            String sql = "DELETE FROM biglietti WHERE id = ?";

            try
            {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, biglietto.getId());

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

    public List<Biglietto> findByUser(Utente utente)
    {
        Dao_Utenti daoUtenti = new Dao_Utenti();
        Dao_Piste daoPiste = new Dao_Piste();
        List<Biglietto> response = new ArrayList<>();

        String sql = "SELECT * FROM biglietti WHERE id_utente = ?";

        connection =  LinkDB.getConnection();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,utente.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                response.add(new Biglietto(resultSet.getInt(1),
                        daoUtenti.findById(resultSet.getInt(2)),
                        daoPiste.findById(resultSet.getInt(3)),
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
}
