package view;

import abstracts.Abstract_View;
import dto.UtenteDTO;
import interfaces.View;
import model.Ruolo;
import singleton.MainDispatcher;
import util.CryptoHelper;
import util.Request;

//singleton
public class CreaUtenteView extends Abstract_View implements View
{
    private static CreaUtenteView instance;
    private UtenteDTO utenteDTO;

    private CreaUtenteView()
    {}

    public static CreaUtenteView getInstance()
    {
        if (instance == null)
            instance = new CreaUtenteView();

        return instance;
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
    public void showOption()
    {
        String nome, indirizzo, luogo, password;

        System.out.println("--- Registrazione nuovo utente ---\n");

        //richiesta nome
        do
        {
            System.out.println("Inserire nome: ");
            nome = getInput();

            if (nome != null)
            {
                if (!nome.isBlank())
                {
                    nome = nome.trim().toLowerCase();
                    break;
                }
                else
                    System.out.println("Nome incorretto!\n");
            }
            else
                System.out.println("Nome incorretto!\n");
        }
        while (true);

        //richiesta indirizzo
        System.out.println("Inserire indirizzo: ");

        indirizzo = scanner.nextLine();

        if (indirizzo == null || indirizzo.isBlank())
            indirizzo = null;
        else
            indirizzo = indirizzo.trim().toLowerCase();

        //richiesta luogo di nascita
        System.out.println("Inserire luogo di nascita: ");

        luogo = scanner.nextLine();

        if (luogo == null || luogo.isBlank())
            luogo = null;
        else
            luogo = luogo.trim().toLowerCase();

        //RICHIESTA RUOLO
        System.out.println("Inserire Ruolo: ");
        String ruolo = null;
        ruolo = scanner.nextLine().toUpperCase();
        Ruolo.valueOf(ruolo);

        //richiesta password
        do
        {
            System.out.println("Inserire password: ");
            password = scanner.nextLine();

            if (password != null)
            {
                if (!password.isBlank())
                {
                    if (password.trim().length() >= 8)
                    {
                        password = password.trim();
                        break;
                    }
                    else
                        System.out.println("La password deve contenere almeno 8 caratteri!");
                }
                else
                    System.out.println("La password non puo' essere vuota!\n");
            }
            else
                System.out.println("La password non puo' essere vuota!\n");
        }
        while (true);

        utenteDTO = new UtenteDTO(0,nome, indirizzo, luogo, Ruolo.valueOf(ruolo), password, false);
    }

    @Override
    public void submit()
    {
        Request request = new Request();
        request.put("DATI_UTENTE", utenteDTO);
        MainDispatcher.getInstance().callAction("UTENTE", "CREATE", request);
    }
}
