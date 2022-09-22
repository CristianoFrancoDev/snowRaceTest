package view;

import abstracts.Abstract_View;
import interfaces.View;
import util.Request;

public class UtenteProfiloView extends Abstract_View implements View
{
    private static UtenteProfiloView instance;
    private Request request;
    private String scelta;

    private UtenteProfiloView()
    {}

    public static UtenteProfiloView getInstance()
    {
        if (instance == null)
            instance = new UtenteProfiloView();

        return instance;
    }

    @Override
    public void showResults(Request request)
    {
        if (request.get("PRINT") != null)
        {
            System.out.println(request.getString("PRINT") + "\n");
        }
    }

    @Override
    public void showOption()
    {
        System.out.println("--- PAGINA PROFILO PERSONALE ---");

    }

    @Override
    public void submit()
    {

    }
}

