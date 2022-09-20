package view;

import abstracts.Abstract_View;
import interfaces.View;
import util.Request;

public class ModificaUtenteView extends Abstract_View implements View
{
    private static ModificaUtenteView instance;

    private ModificaUtenteView()
    {}

    public static ModificaUtenteView getInstance()
    {
        if (instance == null)
            instance = new ModificaUtenteView();

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
