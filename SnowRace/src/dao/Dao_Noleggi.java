package dao;

import model.Attrezzatura;
import model.Biglietto;
import model.Noleggio;
import singleton.LinkDB;

import java.sql.*;
import java.time.LocalDate;

public class Dao_Noleggi
{
    private Connection connection;
    private String sql;

    public Noleggio findById(int id)
    {
        Dao_Attrezzature daoAttrezzature = new Dao_Attrezzature();
        Dao_Biglietti daoBiglietti = new Dao_Biglietti();
        Noleggio response = null;

        connection = LinkDB.getConnection();

        if (connection != null)
        {
            String sql = "SELECT * FROM noleggi WHERE id = ?";

            try
            {
                PreparedStatement statement = connection.prepareStatement(sql);
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

                sql = "INSERT INTO noleggi (id_attrezzatura, id_biglietto) VALUES (?, ?)";

                statement = connection.prepareStatement(sql);
                statement.setInt(1, noleggio.getAttrezzatura().getId());
                statement.setInt(2, noleggio.getBiglietto().getId());

                statement.executeUpdate();

                sql = "SELECT LAST_INSERT_ID()";

                Statement stmt = connection.createStatement();
                stmt.execute(sql);

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
                sql = "UPDATE INTO noleggi SET id_attrezzatura = ?, id_biglietto = ? WHERE id = ?";

                statement = connection.prepareStatement(sql);
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

        String sql = "DELETE FROM noleggi WHERE id = ?";

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);
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

        String sql= "SELECT * FROM attrezzature WHERE id = ?";

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);
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

        String sql= "SELECT * biglietti WHERE id = ?";

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);
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
}
