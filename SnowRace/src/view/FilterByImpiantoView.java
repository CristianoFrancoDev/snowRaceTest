package view;

import abstracts.Abstract_View;
import dto.ImpiantoDTO;
import interfaces.View;
import service.ImpiantiService;
import singleton.MainDispatcher;
import util.Request;
import java.util.ArrayList;
import java.util.List;

public class FilterByImpiantoView extends Abstract_View implements View
{
    private static FilterByImpiantoView instance;
    private String impianto;

    private boolean controller;
    private FilterByImpiantoView()
    {
    }

    public static FilterByImpiantoView getInstance()
    {
        if (instance == null)
            instance = new FilterByImpiantoView();

        return instance;
    }

    @Override
    public void showResults(Request request)
    {
        if (request.getString("PRINT_BY_IMPIANTO") != null)
        {
            controller = false;
            System.out.println(request.getString("PRINT_BY_IMPIANTO") + "\n");
        }
    }

    @Override
    public void showOption() {
        System.out.println("--- FILTRA I TUOI BIGLIETTI PER IMPIANTO ---");
        List<ImpiantoDTO> listImpianto = new ArrayList<>();

        listImpianto = ImpiantiService.getInstance().getAll();

        System.out.println("I NOSTRI IMPIANTI \n");
        for (int i = 0; i < listImpianto.size(); i++) {
            System.out.print(listImpianto.get(i));
        }   //stampa TUTTI GLI IMPIANTI

        System.out.println("--- INSERISCI UN IMPIANTO: ");
         impianto = getInput().toUpperCase();

        for (int i = 0; i < listImpianto.size(); i++)
        {
            if(listImpianto.get(i).getTitolo().toUpperCase().equals(impianto)){
                controller = true;
                break;
            }
        }
        if (!controller){
            System.out.println("IMPIANTO  inesistente...!"+"\n");
        }

    }

    @Override
    public void submit()
    {
        if (controller) {
            Request request = new Request();
            request.put("IMPIANTO_SELEZIONATO", impianto);
            MainDispatcher.getInstance().callAction("UTENTE", "FILTER_IMPIANTO", request);
        } else {
            MainDispatcher.getInstance().callView("UTENTE_VIEW",null);
        }
    }
}
