package view;

import abstracts.Abstract_View;
import dto.PistaDTO;
import interfaces.View;
import singleton.MainDispatcher;
import util.Request;

//singleton
public class CreaPistaView extends Abstract_View implements View
{
    private static CreaPistaView instance;
    private String titolo, nomeImpianto;
    private PistaDTO pistaDTO;

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
        if (request != null)
        {
            System.out.println(request.getString("PRINT") + "\n");
        }
    }

    @Override
    public void showOption()
    {
        System.out.println("---- Nuova Pista ----");

        System.out.println("Titolo Pista:");
        titolo = getInput();

        System.out.println("Inserisci nome impianto Associato");
        nomeImpianto = getInput();

    }

    @Override
    public void submit()
    {
        Request request = new Request();
        request.put("NOME_PISTA", titolo);
        request.put("NOME_IMPIANTO", nomeImpianto);
        MainDispatcher.getInstance().callAction("PISTA", "CREATE", request);
    }
}
