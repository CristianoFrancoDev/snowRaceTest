package view;

import abstracts.Abstract_View;
import interfaces.View;
import singleton.MainDispatcher;
import util.Request;

//classe singleton
public class StartView extends Abstract_View implements View
{
    private static StartView instance;
    private String scelta;

    private StartView()
    {}

    public static StartView getInstance()
    {
        if (instance == null)
            instance = new StartView();

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
        do
        {
            System.out.println("---- Menu principale ----");
            System.out.println("(L)ogin");
            System.out.println("(R)egistrazione utente");
            System.out.println("(E)sci");

            scelta = getInput();
        }
        while (scelta == null || scelta.isBlank());
    }

    @Override
    public void submit()
    {
        switch (scelta.toUpperCase().trim())
        {
            case "L":
                MainDispatcher.getInstance().callAction("LOGIN", "DO_CONTROL", null);
                break;
            case "R":
                MainDispatcher.getInstance().callAction("REGISTRAZIONE", "DO_CONTROL", null);
                break;
            default:

                break;
        }
    }
}
