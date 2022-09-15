package dao;

import model.Impianto;
import model.Pista;
import test.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dao_Piste {

    private static Connection connection;


    public static Pista findPistaById(int id){

        Pista response = null;

        String sql = "SELECT * FROM piste WHERE id = ?";

        connection = Main.linkDB.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            Impianto impianto = new Impianto();

            if (resultSet != null){
                if (resultSet.next())
                {
                    response = new Pista(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            Dao_Impianti.findImpiantoById(resultSet.getInt(3))
                    );
                }
            }

            resultSet.close();
            preparedStatement.close();
            Main.linkDB.closeConnection();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return response;
    }

    public static boolean createPista (Pista pista){

        boolean response = true ;

        String sql = "INSERT INTO piste (titolo,id_impianto) VALUES (?,?) ";

        try {
            connection = Main.linkDB.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1,pista.getTitolo());
            statement.setInt(2,pista.getImpianto().getId());

            statement.executeUpdate();

            statement.close();

            Main.linkDB.closeConnection();

        } catch (SQLException e) {
            response = false;
            throw new RuntimeException(e);
        }


        return response;
    };

    public static boolean  updatePista ( int id, String titoloNuovo ){

        boolean response = true;

        String sql = "UPDATE piste SET titolo = ? WHERE id = ? ";

        try {
            connection = Main.linkDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1,titoloNuovo);
            statement.setInt(2,id);

            statement.executeUpdate();
            statement.close();
            Main.linkDB.closeConnection();

        } catch (SQLException e) {
            response= false;
            throw new RuntimeException(e);
        }


        return response;

    }

    public static boolean deletePista(int id ) {

         boolean response = true;

        String sql = "DELETE FROM piste WHERE id = ? ";


        try {
        connection = Main.linkDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1,id);

            statement.execute();

            statement.close();
            Main.linkDB.closeConnection();

        } catch (SQLException e) {
            response = false;

            throw new RuntimeException(e);
        }
        return response;
    }
}
