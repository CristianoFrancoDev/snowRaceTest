package view;

import abstracts.Abstract_View;
import dto.ImpiantoDTO;
import interfaces.View;
import singleton.MainDispatcher;
import util.Request;

public class CreaImpiantoView extends Abstract_View implements View
{
    private static CreaImpiantoView instance;
    private String titolo, descrizione, foto, prezzo;
    private ImpiantoDTO impiantoDTO;

    private CreaImpiantoView()
    {}

    public static CreaImpiantoView getInstance()
    {
        if (instance == null)
            instance = new CreaImpiantoView();

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
        System.out.println("---- Nuovo impianto ----");

        System.out.println("Titolo:");
        titolo = getInput();

        System.out.println("Descrizione:");
        descrizione = getInput();

        System.out.println("Percorso foto:");
        foto = getInput();

        System.out.println("Prezzo:");
        prezzo = getInput();

        impiantoDTO = new ImpiantoDTO(0, titolo, descrizione, foto, Double.parseDouble(prezzo));
    }

    @Override
    public void submit()
    {
        Request request = new Request();
        request.put("DATI", impiantoDTO);
        MainDispatcher.getInstance().callAction("IMPIANTO", "CREATE", request);
    }
}
