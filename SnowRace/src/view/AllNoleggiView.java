package view;

import abstracts.Abstract_View;
import interfaces.View;
import util.Request;

public class AllNoleggiView extends Abstract_View implements View
{
    private static AllNoleggiView instance;
    private Request request;


    private AllNoleggiView()
    {}

    public static AllNoleggiView getInstance()
    {
        if (instance == null)
            instance = new AllNoleggiView();

        return instance;
    }

    @Override
    public void showResults(Request request)
    {
        if (request.get("PRINT3") != null)
        {
            System.out.println("I TUOI NOLEGGI SONO : ");
            System.out.println(request.getString("PRINT3") + "\n");
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

