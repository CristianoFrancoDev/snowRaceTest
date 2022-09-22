package view;

import abstracts.Abstract_View;
import interfaces.View;
import util.Request;

public class AllImpiantiView extends Abstract_View implements View
{
    private static AllImpiantiView instance;
    private Request request;

    private AllImpiantiView()
    {}

    public static AllImpiantiView getInstance()
    {
        if (instance == null)
            instance = new AllImpiantiView();

        return instance;
    }

    @Override
    public void showResults(Request request)
    {
        if (request.get("PRINT4") != null)
        {
            System.out.println("GLI IMPIANTI SONO: ");
            System.out.println(request.getString("PRINT4") + "\n");
        }
    }

    @Override
    public void showOption()
    {

    }

    @Override
    public void submit()
    {

    }
}

