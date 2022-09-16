package dao;

import model.Attrezzatura;
import model.Biglietto;
import model.Noleggio;
import singleton.LinkDB;

import java.sql.*;

public class Dao_Noleggi
{
    private static Connection connection;
    private static String sql;

    public static Noleggio findById(int id)
    {
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
                                Dao_Attrezzatura.findByIdAttrezzatura(resultSet.getInt(2)),
                                Dao_Biglietti.findByIdBiglietto(resultSet.getInt(3)));

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

    public static boolean saveNoleggi(Noleggio noleggio)
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
                sql = "UPDATE INTO noleggi (id_attrezzatura, id_biglietto) VALUES (?, ?)";

                statement = connection.prepareStatement(sql);
                statement.setInt(1,noleggio.getAttrezzatura().getId());
                statement.setInt(2,noleggio.getBiglietto().getId());

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

    public static boolean deleteNoleggi(Noleggio noleggio)
    {
        boolean response = true;

        connection = LinkDB.getConnection();

        String sql = "DELETE FROM noleggi WHERE id=?";

        try
        {
            PreparedStatement statement= connection.prepareStatement(sql);
            statement.setInt(1,noleggio.getId());
            statement.executeUpdate();
            statement.close();
            LinkDB.closeConnection();
        }
        catch (SQLException e)
        {
            response= false;
            e.printStackTrace();
        }


        return response;
    }

    public static Attrezzatura getAttrezzatura(Noleggio noleggio)
    {
        Attrezzatura response = null;

        connection = LinkDB.getConnection();
        String sql= "SELECT * attrezzature WHERE id= ?";

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,noleggio.getAttrezzatura().getId());
            statement.execute();

            ResultSet resultSet= statement.getResultSet();
            if (resultSet.next())
                response= new Attrezzatura(resultSet.getInt(1),resultSet.getString(2));

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

    public static Biglietto getBiglietto(Noleggio noleggio)
    {
        Biglietto response = null;

        connection = LinkDB.getConnection();
        String sql= "SELECT * biglietti WHERE id= ?";

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,noleggio.getBiglietto().getId());
            statement.execute();

            ResultSet resultSet= statement.getResultSet();
            if (resultSet.next())
                response= new Biglietto(resultSet.getInt(1),
                        Dao_Biglietti.findById(resultSet.getInt(2)),
                        Dao_Piste.findById(resultSet.getInt(3)),
                        resultSet.getDate(4));

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
