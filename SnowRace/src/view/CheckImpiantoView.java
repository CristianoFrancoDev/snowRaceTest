package view;

import abstracts.Abstract_View;
import interfaces.View;
import singleton.MainDispatcher;
import util.Request;

public class CheckImpiantoView extends Abstract_View implements View
{
    private static  CheckImpiantoView instance;
    private String titolo;

    private CheckImpiantoView()
    {}

    public static CheckImpiantoView getInstance()
    {
        if (instance == null)
            instance = new CheckImpiantoView();

        return instance;
    }

    @Override
    public void showResults(Request request)
    {
        if (request.get("PRINT") != null)
            System.out.println(request.get("PRINT"));
    }

    @Override
    public void showOption()
    {
        System.out.println("---- Modifica impianto ----");
        System.out.println("Titolo: " );

        do
        {
            titolo = getInput();
        }
        while (titolo == null || titolo.isBlank());
    }

    @Override
    public void submit()
    {
        Request request = new Request();
        request.put("TITOLO", titolo.trim());
        MainDispatcher.getInstance().callAction("IMPIANTO", "FIND", request);

        if (request.get("DATI") != null)
        {
            MainDispatcher.getInstance().callView("MODIFICAIMPIANTO_VIEW", request);
        }
        else
        {
            request.put("PRINT", "L'impianto specificato non esiste!");
            ModificaImpiantoView.getInstance().showResults(request);
        }
    }
}
