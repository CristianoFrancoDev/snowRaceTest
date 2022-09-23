package view;

import abstracts.Abstract_View;
import controller.UtenteController;
import dto.UtenteDTO;
import interfaces.View;
import model.Ruolo;
import singleton.MainDispatcher;
import util.CryptoHelper;
import util.Request;

import java.util.List;

public class ModificaUtenteView extends Abstract_View implements View
{
    private static ModificaUtenteView instance;
    String userToChange, nome, indirizzo, luogo, password;
    private boolean askName;

    private ModificaUtenteView()
    {
    }

    public static ModificaUtenteView getInstance()
    {
        if (instance == null)
            instance = new ModificaUtenteView();

        return instance;
    }

    public boolean isAskName()
    {
        return askName;
    }

    public void setAskName(boolean askName)
    {
        this.askName = askName;
    }

    @Override
    public void showResults(Request request)
    {
        if (request != null)
        {
            System.out.println(request.getString("PRINT") + "\n");
        }
    }

    @Override
    public void showOption() {
        {
            System.out.println("--- Modifica di un utente ---");

            if (askName)
            {
                //richiesta nome da modificare
                do
                {
                    System.out.println("Inserire nome utente di cui modificare il profilo: ");
                    userToChange = getInput();
                }
                while (userToChange == null || userToChange.isBlank());
            }

            //richiesta nome
            do
            {
                System.out.println("Inserire nome: ");
                nome = getInput();
            }
            while (nome == null || nome.isBlank());

            //richiesta indirizzo
            System.out.println("Inserire indirizzo: ");
            indirizzo = getInput();

            //richiesta luogo di nascita
            System.out.println("Inserire luogo di nascita: ");
            luogo = getInput();

            //richiesta password
            do
                {
                System.out.println("Inserire password: ");
                password = getInput();
            }
            while (password == null || password.isBlank());
        }
    }

    @Override
    public void submit()
    {
        Request request = new Request();

        if (askName)
            request.put("USERNAME_TO_EDIT", userToChange.trim());

        request.put("NOME", nome.trim());

        if (indirizzo == null || indirizzo.isBlank())
            request.put("INDIRIZZO", null);
        else
            request.put("INDIRIZZO", indirizzo.trim());

        if (luogo == null || luogo.isBlank())
            request.put("LUOGO", null);
        else
            request.put("LUOGO", luogo.trim());

        request.put("PASSWORD", password.trim());

        if (askName)
            MainDispatcher.getInstance().callAction("AMMINISTRATORE", "UPDATE_ACCOUNT", request);
        else
            MainDispatcher.getInstance().callAction("UTENTE", "UPDATE_ACCOUNT", request);
    }
}
