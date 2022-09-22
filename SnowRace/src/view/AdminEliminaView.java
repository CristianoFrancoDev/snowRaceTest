package view;

import abstracts.Abstract_View;
import interfaces.View;
import singleton.MainDispatcher;
import util.Request;

public class AdminEliminaView extends Abstract_View implements View
{
    private static AdminEliminaView instance;
    private String scelta;

    private AdminEliminaView()
    {}

    public static AdminEliminaView getInstance()
    {
        if (instance == null)
            instance = new AdminEliminaView();

        return instance;
    }

    @Override
    public void showResults(Request request)
    {
        if (request != null)
            System.out.println(request.get("PRINT"));
    }

    @Override
    public void showOption()
    {
        System.out.println("Sicuri di voler eliminare il profilo ? (Y/N) ");

        do
        {
            scelta = getInput();
        }
        while (scelta == null || scelta.isBlank());
    }

    @Override
    public void submit()
    {
        if (scelta.toUpperCase().trim().equals("Y"))
            {
                MainDispatcher.getInstance().callAction("ADMIN", "DELETE_ACCOUNT", null);
            }
    }
}
