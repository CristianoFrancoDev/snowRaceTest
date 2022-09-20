package view;

import abstracts.Abstract_View;
import interfaces.View;
import singleton.MainDispatcher;
import util.Request;

public class AdminView extends Abstract_View implements View
{
    private static AdminView instance;
    private Request request;
    private String scelta;

    private AdminView()
    {}

    public static AdminView getInstance()
    {
        if (instance == null)
            instance = new AdminView();

        return instance;
    }

    @Override
    public void showResults(Request request)
    {
        //
    }

    @Override
    public void showOption()
    {
        System.out.println("--- Opzioni admin ---");
        System.out.println("(A)ccount");
        System.out.println("(P)ista");
        System.out.println("(E)sci");

        scelta = getInput();
    }

    @Override
    public void submit()
    {
        request = new Request();

        switch (scelta.toUpperCase().trim())
        {
            case "A":
                request.put("ADMIN", "ACCOUNT");
                break;
            case "P":
                request.put("ADMIN", "PISTA");
                break;
            default:
                request.put("ADMIN", "EXIT");
                break;
        }

        MainDispatcher.getInstance().callAction("ADMIN", "DO_CONTROL", request);
    }
}
