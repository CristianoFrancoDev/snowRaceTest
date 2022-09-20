package view;

import abstracts.Abstract_View;
import interfaces.View;
import util.Request;

public class ModificaImpiantoView extends Abstract_View implements View
{
    private static ModificaImpiantoView instance;

    private ModificaImpiantoView()
    {}

    public static ModificaImpiantoView getInstance()
    {
        if (instance == null)
            instance = new ModificaImpiantoView();

        return instance;
    }

    @Override
    public void showResults(Request request)
    {

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
