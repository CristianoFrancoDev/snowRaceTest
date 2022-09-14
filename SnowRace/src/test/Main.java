package test;

import controller.Controller;
import dao.Dao_Users;
import model.Ruolo;
import model.Utente;
import singleton.LinkDB;
import view.Menu;

public class Main
{
    public static LinkDB linkDB;

    public static void main(String[] args)
    {
        String nome;

        //istanziazione classe singleton
        linkDB = LinkDB.getInstance();

        //////////////////////////////////////
//        Utente temp = new Utente(5,"Aldo2", null, "Bn", Ruolo.USER, "12345678", false);
//        Dao_Users.deleteUser(temp);
        /////////////////////////////////////

        Menu menu = new Menu();

        nome = menu.execLogin();

        //scoprire se user esiste

        //se user esiste caricare i suoi dati

        //se non esiste chiedere la sua registrazione


//        menu.registerUser();
//        menu.askDate(null);

        Controller controller = new Controller();

        if (controller.userExists(nome))
        {
            if (controller.checkUserIsActive(nome))
            {
                boolean passOk = false;

                //controllo password
                for (int x = 0; x < 3; x++)
                {
                    //richiesta password
                    String password = menu.askPassword("Inserire la password: ");

                    if (controller.checkPassword(nome, password))
                    {
                        passOk = true;
                        break;
                    } else
                        System.out.println("Password errata!");
                }

                if (passOk)
                {
                    menu.askUser();
                }
            }
            else
                System.out.println("Utente inesistente!");
        }
        else
        {
            if (menu.askRegisterUser())
            {
                Utente utente = menu.registerUser();

                //salvataggio utente su DB
                if(controller.createUser(utente))
                    System.out.println("Registrazione utente eseguita.");
                else
                    System.out.println("Registrazione non avvenuta!");
            }
        }


    }
}
