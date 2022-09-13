package db;
import classi.Ruoli;
import classi.Utenti;

import java.sql.*;
import java.util.Scanner;

public class TestDB
{
    public static void main(String[] args) {

        Connection con = null;
        String username = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/snowracedb?serverTimezone=UTC", username, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("use snowracedb");
            stmt = (Statement) con.createStatement();
            Scanner s = new Scanner(System.in);

            System.out.print("Nome: ");
            String nome = s.next();
            System.out.print("Indirizzo: ");
            String ind = s.next();
            System.out.print("Luogo: ");
            String luogo = s.next();
            System.out.print("Password: ");
            String pass = s.next();
            Ruoli.tipo r = Ruoli.tipo.USER;
            String v = "'";
            Utenti utente = new Utenti(nome,ind,luogo,r,pass,true);
            int i = 0;
            String query1 = "INSERT INTO utenti (nome, indirizzo, luogo, ruolo, password, cancellato)\n"+
                            "VALUES (nome, 'Tom B. Erichsen', 'Skagen 21', 'USER', '4006', '0')";

            stmt.executeUpdate(query1);

            System.out.println("Record has been updated in the table successfully....");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}