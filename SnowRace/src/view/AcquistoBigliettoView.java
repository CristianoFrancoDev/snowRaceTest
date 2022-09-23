package view;

import abstracts.Abstract_View;
import dto.BigliettoDTO;
import dto.ImpiantoDTO;
import dto.PistaDTO;
import interfaces.View;
import model.Impianto;
import service.ImpiantiService;
import service.PisteService;
import service.UtentiService;
import singleton.MainDispatcher;

import util.Request;
import util.VariabiliGlobali;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;

public class AcquistoBigliettoView extends Abstract_View implements View
{
    private static AcquistoBigliettoView instance;

    private String titoloPista, nomeImpianto;
    private ImpiantoDTO impiantoDTO;
    private PistaDTO pistaDTO;
    private List<PistaDTO> listaPiste;
    private List<ImpiantoDTO> impiantoDTOList;
    private String data;

    private BigliettoDTO bigliettoDTO;
    private AcquistoBigliettoView()
    {}

    public static AcquistoBigliettoView getInstance()
    {
        if (instance == null)
            instance = new AcquistoBigliettoView();

        return instance;
    }

    @Override
    public void showResults(Request request)
    {
        if (request != null)
        {
            System.out.println(request.getString("PRINT8") + "\n");
        }
    }

    @Override
    public void showOption()
    {
        System.out.println("---- ACQUISTA BIGLIETTO ----");

        System.out.println("Inserisci la Data (YYYY-MM-AA)");
        data = getInput();

        PisteService.getInstance();
        ImpiantiService impiantiService = new ImpiantiService();
        impiantoDTOList = impiantiService.getAll();
        System.out.println("\n i nostri impianti: \n ");

        for (int i = 0; i < impiantoDTOList.size(); i++)
        {
            System.out.println( impiantoDTOList.get(i).getTitolo());
        }
        System.out.println("Inserisci nome Impianto ");
        nomeImpianto = getInput();


        impiantoDTO = impiantiService.findByName(nomeImpianto);
        System.out.println(impiantoDTO.getDescrizione());
        listaPiste = impiantiService.getPiste(impiantoDTO);
        System.out.println("\n ecco le piste dell'impianto \n " + nomeImpianto);
        for (int i = 0; i < listaPiste.size(); i++)
        {
            System.out.println( listaPiste.get(i).getTitolo());
        }

        System.out.println("Inserisci la Pista Pista:");
        titoloPista = getInput();

        for (int i = 0; i < listaPiste.size(); i++) {
            if (listaPiste.get(i).getTitolo().equals(titoloPista)){
                pistaDTO = listaPiste.get(i);
                break;
            }
        }


        LocalDate localDate = LocalDate.parse(data);
        bigliettoDTO = new BigliettoDTO(0, UtentiService.getInstance().findByName(VariabiliGlobali.userName),pistaDTO,localDate);
        System.out.println(bigliettoDTO.toString());
    }

    @Override
    public void submit()
    {
        Request request = new Request();
        request.put("NUOVO_BIGLIETTO", bigliettoDTO);
        MainDispatcher.getInstance().callAction("UTENTE", "ACQUISTO_TICKET", request);
    }
}
