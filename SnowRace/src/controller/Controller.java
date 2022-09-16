package controller;

import dao.Dao_Utenti;
import model.Utente;
import java.sql.*;

public class Controller
{
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String urlDB = "jdbc:mysql://localhost:3306/snowracedb";
    private Connection connection;

    public Controller()
    {
        try
        {
            Class.forName(driver);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public boolean userExists(String nome)
    {
        return Dao_Utenti.findUser(nome) != null;
    }

    public boolean createUser(Utente utente)
    {
        boolean response = true;
        String sql = "INSERT INTO utenti (nome, indirizzo, luogo, ruolo, password, cancellato) VALUES (?, ?, ?, ?, ?, ?)";

        try
        {
            connection = DriverManager.getConnection(urlDB, "", "");

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, utente.getNome());
            statement.setString(2, utente.getIndirizzo());
            statement.setString(3, utente.getLuogo());
            statement.setString(4, utente.getRuolo().name());
            statement.setString(5, utente.getPassword());
            statement.setBoolean(6, utente.isCancellato());

            statement.executeUpdate();

            statement.close();
            connection.close();
        }
        catch (Exception ex)
        {
            response = false;
        }

        return response;
    }

    public boolean checkUserIsActive(String nome)
    {
        Utente utente = Dao_Utenti.findUser(nome);

        return !utente.isCancellato();
    }

    public boolean checkPassword(String nome, String passwordDecrypted)
    {
        Utente utente = Dao_Utenti.findUser(nome);

        return passwordDecrypted.equals(utente.getPassword());
    }
}
