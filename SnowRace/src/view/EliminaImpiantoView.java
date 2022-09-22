package view;

import abstracts.Abstract_View;
import interfaces.View;
import singleton.MainDispatcher;
import util.Request;

public class EliminaImpiantoView extends Abstract_View implements View
{
    private static EliminaImpiantoView instance;
    private String titolo, scelta;

    private EliminaImpiantoView()
    {}

    public static EliminaImpiantoView getInstance()
    {
        if (instance == null)
            instance = new EliminaImpiantoView();

        return instance;
    }

    @Override
    public void showResults(Request request)
    {
        if (request != null)
            System.out.println(request.getString("PRINT"));
    }

    @Override
    public void showOption()
    {
        System.out.println("Titolo da eliminare: ");
        titolo = getInput();

        if (titolo != null && !titolo.isBlank())
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
            request.put("TITOLO", titolo.trim());

            MainDispatcher.getInstance().callAction("IMPIANTO", "ELIMINA", request);
        }
    }
}
