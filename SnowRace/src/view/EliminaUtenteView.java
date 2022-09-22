package view;

import abstracts.Abstract_View;
import interfaces.View;
import singleton.MainDispatcher;
import util.Request;

public class EliminaUtenteView extends Abstract_View implements View
{
    private static EliminaUtenteView instance;
    private String nome,scelta;

    private EliminaUtenteView()
    {}

    public static EliminaUtenteView getInstance()
    {
        if (instance == null)
            instance = new EliminaUtenteView();
        return  instance;
    }

    @Override
    public void showResults(Request request)
    {

    }

    @Override
    public void showOption()
    {
        System.out.println("Utente da eliminare: ");
        nome = getInput();

        if (nome != null && !nome.isBlank())
        {
            System.out.println("Sicuri di volerlo eliminare? (Y/N)");
            scelta = getInput();
        }

    }

    @Override
    public void submit()
    {
        if (scelta != null && scelta.toUpperCase().trim().equals("Y"))
        {
            Request request = new Request();
            request.put("NOME", nome.trim());
            MainDispatcher.getInstance().callAction("UTENTE","ELIMINA", request);
        }

    }
}
