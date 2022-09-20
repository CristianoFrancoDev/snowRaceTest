package view;

import abstracts.Abstract_View;
import interfaces.View;
import singleton.MainDispatcher;
import util.Request;

public class AmministratoreView extends Abstract_View implements View
{
    private static AmministratoreView instance;
    private String scelta;
    private Request request;

    private AmministratoreView()
    {}

    public static AmministratoreView getInstance()
    {
        if (instance == null)
            instance = new AmministratoreView();

        return instance;
    }

    @Override
    public void showResults(Request request)
    {
        if (request != null)
        {
            System.out.println(request.getString("PRINT"));
        }
    }

    @Override
    public void showOption()
    {
        System.out.println("--- Opzioni amministratore ---");
        System.out.println("(I)mpianto");
        System.out.println("(U)tente");
        System.out.println("(P)ista");
        System.out.println("(E)sci\n");

        scelta = getInput();
    }

    @Override
    public void submit()
    {
        switch (scelta.toUpperCase().trim())
        {
            case "I":
                request = new Request();
                request.put("AMMINISTRATORE", "MENU_IMPIANTI");
                MainDispatcher.getInstance().callAction("AMMINISTRATORE", "MENU_IMPIANTI", request);
                break;
            case "U":
                request = new Request();
                request.put("AMMINISTRATORE", "MENU_UTENTI");
                MainDispatcher.getInstance().callAction("AMMINISTRATORE", "MENU_UTENTI", request);
                break;
            case "P":
                request = new Request();
                request.put("AMMINISTRATORE", "MENU_PISTE");
                MainDispatcher.getInstance().callAction("AMMINISTRATORE", "MENU_PISTE", request);
                break;
            default:
                request = new Request();
                request.put("AMMINISTRATORE", "EXIT");
                MainDispatcher.getInstance().callAction("AMMINISTRATORE", "EXIT", request);
                break;
        }

    }
}
