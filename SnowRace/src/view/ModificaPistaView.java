package view;

import abstracts.Abstract_View;
import interfaces.View;
import util.Request;

public class ModificaPistaView extends Abstract_View implements View
{
    private static ModificaPistaView instance;

    private ModificaPistaView()
    {}

    public static ModificaPistaView getInstance()
    {
        if (instance == null)
            instance = new ModificaPistaView();

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
