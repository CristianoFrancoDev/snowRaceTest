package testDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class test {
    public static void main(String[] args) {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/snow?serverTimezone=UTC","root","Contrader96");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("use snow;");
            stmt = (Statement) con.createStatement();
            System.out.println("Connected");
            String query1=  "INSERT INTO utenti (nome, indirizzo, luogo, ruolo, password, cancellato)\n" +
                    "            VALUES ('c', 'Tom B. Erichsen', 'Skagen 21', 'user', 'cc', '0');";
            stmt.executeUpdate(query1);


        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }
}
