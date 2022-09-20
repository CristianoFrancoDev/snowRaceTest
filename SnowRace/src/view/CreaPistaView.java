package view;

import abstracts.Abstract_View;
import interfaces.View;
import util.Request;

public class CreaPistaView extends Abstract_View implements View
{
    private static CreaPistaView instance;

    private CreaPistaView()
    {}

    public static CreaPistaView getInstance()
    {
        if (instance == null)
            instance = new CreaPistaView();

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
