package view;

import abstracts.Abstract_View;
import interfaces.View;
import singleton.MainDispatcher;
import util.Request;

public class EliminaPistaView extends Abstract_View implements View
{
    private static EliminaPistaView instance;
    private String titolo,scelta;

    private EliminaPistaView()
    {}

    public static EliminaPistaView getInstance()
    {
        if (instance == null)
            instance = new EliminaPistaView();
        return  instance;
    }

    @Override
    public void showResults(Request request)
    {

    }

    @Override
    public void showOption()
    {
        System.out.println("Pista da eliminare: ");
        titolo = getInput();

        if (titolo != null && !titolo.isBlank())
        {
            System.out.println("Sicuri di volerlo eliminare? (Y/N)");
            scelta = getInput();
        }

    }

    @Override
    public void submit()
    {
        if (scelta != null && scelta.toUpperCase().trim().equals("Y"))
        {
            Request request = new Request();
            request.put("TITOLO", titolo.trim());
            MainDispatcher.getInstance().callAction("PISTA","ELIMINA",request);
        }

    }
}
