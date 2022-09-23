package view;

import abstracts.Abstract_View;
import dto.*;
import interfaces.View;
import model.Impianto;
import service.*;
import singleton.MainDispatcher;

import util.Request;
import util.VariabiliGlobali;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class NoleggioView extends Abstract_View implements View
{
    private static NoleggioView instance;
    private Scanner scanner ;
    private String titoloPista, nomeImpianto;
    private ImpiantoDTO impiantoDTO;
    private PistaDTO pistaDTO;
    private List<PistaDTO> listaPiste;
    private List<ImpiantoDTO> impiantoDTOList;
    private List<NoleggioDTO> noleggioDTOList;
    private List<AttrezzaturaDTO> attrezzaturaDTOList;
    private String data1,data2;

    int id_Ticket,id_Attrezzo;
    private BigliettoDTO bigliettoDTO;
    private NoleggioView()
    {}

    public static NoleggioView getInstance()
    {
        if (instance == null)
            instance = new NoleggioView();

        return instance;
    }

    @Override
    public void showResults(Request request)
    {
        if (request != null)
        {
            System.out.println(request.getString("PRINT9") + "\n");
        }
    }

    @Override
    public void showOption()
    {
        System.out.println("---- NOLEGGIO ATTREZZATURA ----");

//        System.out.println("Inserisci la prima Data (YYYY-MM-AA)");
//        data1 = getInput();
//        System.out.println("Inserisci la seconda Data (YYYY-MM-AA)");
//        data2 = getInput();

        BigliettiService bigliettiService = new BigliettiService();
        List<BigliettoDTO> storicoBigliettiList = bigliettiService.findByLoggedUser();

        for (int i = 0; i < storicoBigliettiList.size(); i++) {
            BigliettoDTO bigliettoDTO = (storicoBigliettiList.get(i));
            System.out.println(bigliettoDTO.toString() + "ID DA SELEZIONARE : " + bigliettoDTO.getId());
        }

        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Seleziona ID biglietto da associare : ");
        id_Ticket = scanner1.nextInt();

        attrezzaturaDTOList = AttrezzatureService.getInstance().getAll();
        for (int i = 0; i < attrezzaturaDTOList.size(); i++) {
            AttrezzaturaDTO attrezzaturaDTO = attrezzaturaDTOList.get(i);
            System.out.println(attrezzaturaDTO.toString() + " ID DA SELEZIONARE : " + attrezzaturaDTO.getId());
        }

        System.out.println("Seleziona ID articolo da noleggiare : ");
        id_Attrezzo = scanner1.nextInt();


        //        bigliettoDTO = new BigliettoDTO(0, UtentiService.getInstance().findByName(VariabiliGlobali.userName),pistaDTO,localDate);
//        System.out.println(bigliettoDTO.toString());
    }

    @Override
    public void submit()
    {
        Request request = new Request();
        request.put("Id_Ticket", id_Ticket);
        request.put("Id_Attrezzatura",id_Attrezzo);
        MainDispatcher.getInstance().callAction("UTENTE", "ACQUISTO_NOLEGGIO", request);
    }
}
