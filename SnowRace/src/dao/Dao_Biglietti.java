package dao;

import model.Biglietto;
import model.Pista;
import model.Utente;
import test.Main;

import java.sql.*;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Dao_Biglietti {

    private static Connection connection;

    public static boolean acquistoBiglietto(Utente utente, Pista pista, LocalDate data) {

        boolean response = true;


        connection = Main.linkDB.getConnection();
        String sql = "INSERT INTO biglietti (id_utente,id_pista,data) VALUES (?,?,?) ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, utente.getId());
            preparedStatement.setInt(2, pista.getId());
            preparedStatement.setString(3, data.toString());

        preparedStatement.execute();

            preparedStatement.close();
            Main.linkDB.closeConnection();


        } catch (SQLException e) {
            response = false;
            throw new RuntimeException(e);
        }


        return response;
    }


    public static ArrayList<Biglietto> findBigliettoByUserId(Utente utente){

        boolean response = true;
        ArrayList<Biglietto> listaBiglietti = new ArrayList<>();

        String sql = "SELECT * FROM biglietti WHERE id_utente = ? ";

        connection =  Main.linkDB.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,utente.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            LocalDate dateX ;
            while(resultSet.next() ){
//                listaBiglietti.add(resultSet.toString());
            Biglietto biglietto = new Biglietto(resultSet.getInt("id"),Dao_Users.findUser(utente.getNome()),
                    Dao_Piste.findPistaById(resultSet.getInt("id_pista")), resultSet.getObject("data", LocalDate.class));


            listaBiglietti.add(biglietto);


            }

            preparedStatement.close();
            Main.linkDB.closeConnection();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for( int i = 0; i < listaBiglietti.size(); i++){

        System.out.println(listaBiglietti.get(i).toString());
        }

        return listaBiglietti;
    }
}
