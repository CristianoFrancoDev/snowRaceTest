package view;

import abstracts.Abstract_View;
import interfaces.View;
import util.Request;

public class CreaUtenteView extends Abstract_View implements View
{
    private static CreaUtenteView instance;

    private CreaUtenteView()
    {}

    public static CreaUtenteView getInstance()
    {
        if (instance == null)
            instance = new CreaUtenteView();

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
