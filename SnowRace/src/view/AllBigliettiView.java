package view;

import abstracts.Abstract_View;
import interfaces.View;
import util.Request;

public class AllBigliettiView extends Abstract_View implements View
{
    private static AllBigliettiView instance;
    private Request request;
    private String scelta;

    private AllBigliettiView()
    {}

    public static AllBigliettiView getInstance()
    {
        if (instance == null)
            instance = new AllBigliettiView();

        return instance;
    }

    @Override
    public void showResults(Request request)
    {
        if (request.get("PRINT2") != null)
        {
            System.out.println("I TUOI BIGLIETTI SONO : ");
            System.out.println(request.getString("PRINT2") + "\n");
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

