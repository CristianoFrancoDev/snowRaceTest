package dao;

import model.Attrezzatura;
import model.Noleggio;
import singleton.LinkDB;

import java.sql.*;
import java.util.Set;

public class Dao_Attrezzatura
{
    private static Connection connection;

    public static Attrezzatura findByIdAttrezzatura(int id)
    {
        Attrezzatura response = null;

        connection = LinkDB.getConnection();

        if (connection != null)
        {
            String sql = "SELECT * FROM attrezzature WHERE id = ?";

            try
            {
                PreparedStatement statement = connection.prepareStatement(sql);

                statement.setInt(1, id);
                statement.execute();

                ResultSet resultSet = statement.getResultSet();

                if (resultSet != null)
                {
                    if (resultSet.next())
                        response = new Attrezzatura(resultSet.getInt(1), resultSet.getString(2));
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

    public static boolean saveAttrezzatura(Attrezzatura attrezzatura)
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
                if (attrezzatura.getId() == 0)
                {
                    //creazione attrezzatura

                    sql = "INSERT INTO attrezzature (articolo) VALUES (?)";

                    statement = connection.prepareStatement(sql);
                    statement.setString(1, attrezzatura.getArticolo());

                    statement.executeUpdate();

                    sql = "SELECT LAST_INSERT_ID()";

                    Statement stmt = connection.createStatement();
                    stmt.execute(sql);

                    ResultSet resultSet = stmt.getResultSet();

                    if (resultSet.next())
                        attrezzatura.setId(resultSet.getInt(1));
                    else
                        response = false;

                    resultSet.close();
                    stmt.close();
                }
                else
                {
                    //modifica attrezzatura

                    sql = "UPDATE attrezzature set articolo = ?";

                    statement = connection.prepareStatement(sql);
                    statement.setString(1, attrezzatura.getArticolo());

                    statement.execute();
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

    public static boolean deleteAttrezzatura(Attrezzatura attrezzatura)
    {
        boolean response = true;

        connection = LinkDB.getConnection();

        if (connection == null)
            response = false;
        else
        {
            String sql = "DELETE FROM attrezzature WHERE id = ?";

            try
            {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, attrezzatura.getId());

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

    public static Set<Noleggio> getNoleggi(Attrezzatura attrezzatura)
    {
        Set<Noleggio> response = null;

        connection = LinkDB.getConnection();


        return response;
    }
}
