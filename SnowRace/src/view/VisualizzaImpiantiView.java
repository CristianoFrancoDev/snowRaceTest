package view;

import abstracts.Abstract_View;
import dto.ImpiantoDTO;
import interfaces.View;
import util.Request;

import java.util.List;

public class VisualizzaImpiantiView extends Abstract_View implements View
{
    private static VisualizzaImpiantiView instance;

    private VisualizzaImpiantiView()
    {}

    public static VisualizzaImpiantiView getInstance()
    {
        if (instance == null)
            instance = new VisualizzaImpiantiView();

        return instance;
    }

    @Override
    public void showResults(Request request)
    {
        List<ImpiantoDTO> lista = (List<ImpiantoDTO>) request.get("LISTA_IMPIANTI");

        System.out.println("--- Lista impianti ---");

        for (ImpiantoDTO impiantoDTO : lista)
        {
            System.out.println("Titolo: " + impiantoDTO.getTitolo());
            System.out.println("Descrizione: " + impiantoDTO.getDescrizione());
            System.out.println("Percorso foto: " + impiantoDTO.getFoto());
            System.out.println("Prezzo biglietto: " + impiantoDTO.getPrezzo());
            System.out.println("--------------------\n");
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
