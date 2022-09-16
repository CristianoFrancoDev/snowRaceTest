package test;

import controller.UtenteController;
import dao.Dao_Impianti;
import model.Impianto;
import model.Utente;
import view.Menu;

public class Main
{
    public static void main(String[] args)
    {
        String nome;

        //////////////////////////////////////
//        Utente temp = new Utente(5,"Aldo2", null, "Bn", Ruolo.USER, "12345678", false);
//        Dao_Users.deleteUser(temp);
//        Impianto impianto = new Impianto(1,"matese", "neve", "yyy", 10.0);
//        Dao_Impianti.save(impianto);
        /////////////////////////////////////

        Menu menu = new Menu();

        nome = menu.execLogin();




    }
}
