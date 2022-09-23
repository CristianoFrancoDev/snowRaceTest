package view;

import abstracts.Abstract_View;
import dto.PistaDTO;
import interfaces.View;
import service.PisteService;
import singleton.MainDispatcher;
import util.Request;
import java.util.ArrayList;
import java.util.List;

public class FilterByPistaView extends Abstract_View implements View
{
    private static FilterByPistaView instance;
    private PistaDTO pistaDTO;
    private FilterByPistaView()
    {
    }

    public static FilterByPistaView getInstance()
    {
        if (instance == null)
            instance = new FilterByPistaView();

        return instance;
    }

    @Override
    public void showResults(Request request)
    {
        if (request.getString("PRINT_BY_PISTA") != null)
        {
            pistaDTO = null;
            System.out.println(request.getString("PRINT_BY_PISTA") + "\n");
        }
    }

    @Override
    public void showOption()
    {

        System.out.println("---- RICERCA I TUOI BIGLIETTI PER UNA DETERMINATA PISTA ----");
        List<PistaDTO> listaPiste = new ArrayList<>();
        listaPiste = PisteService.getInstance().getAll();

        System.out.println("Tutte le piste presenti: ");
        for (int i = 0; i < listaPiste.size(); i++) {
            System.out.print(listaPiste.get(i));
        }

        System.out.println("\nSeleziona una pista: ");
        String nome = getInput().toUpperCase();

        for (int i = 0; i < listaPiste.size(); i++)
        {
            if(listaPiste.get(i).getTitolo().toUpperCase().equals(nome))
                pistaDTO = listaPiste.get(i);
        }
        if (pistaDTO==null){
            System.out.println("Pista inesistente...!"+"\n");
        }

    }

    @Override
    public void submit()
    {
     if (pistaDTO!=null) {
         Request request = new Request();
         request.put("PISTA_SELEZIONATA", pistaDTO);
         MainDispatcher.getInstance().callAction("UTENTE", "FILTER_BY_PISTA", request);
     }
     else {
         MainDispatcher.getInstance().callView("UTENTE_VIEW",null);
     }
    }
}
