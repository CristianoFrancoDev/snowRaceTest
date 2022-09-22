package controller;

import dto.BigliettoDTO;
import dto.NoleggioDTO;
import dto.PistaDTO;
import dto.UtenteDTO;
import interfaces.Controller;
import service.BigliettiService;
import service.NoleggiService;
import service.PisteService;
import service.UtentiService;
import singleton.MainDispatcher;
import util.Request;
import util.VariabiliGlobali;

import java.util.ArrayList;
import java.util.List;

public class UtenteController implements Controller
{
    private static UtenteController instance;
    private UtentiService utentiService;
    private List<UtenteDTO>  listaUtentiDTO;
    private boolean ok;

    private UtenteController()
    {
        utentiService = UtentiService.getInstance();
    }

    public static UtenteController getInstance()
    {
        if (instance == null)
            instance = new UtenteController();

        return instance;
    }

    public UtenteDTO getUser(String username)
    {
        return UtentiService.getInstance().findByName(username);
    }

    //controllo esistenza utente
    public boolean userExists(String nome, String passwordDecrypted)
    {
        return !(utentiService.login(nome, passwordDecrypted) == null);
    }

    public boolean createUtente(UtenteDTO utenteDTO)
    {
        return utentiService.insert(utenteDTO);
    }

    public boolean updateUtente(UtenteDTO utenteDTO)
    {
        return utentiService.update(utenteDTO);
    }

    public boolean deleteUtente(UtenteDTO utenteDTO)
    {
        return utentiService.delete(utenteDTO.getId());
    }

    public boolean checkUserIsActive(UtenteDTO utenteDTO)
    {
        return !utenteDTO.isCancellato();
    }

    public List<UtenteDTO> getListaUtentiDTO()
    {
        listaUtentiDTO = utentiService.getAll();

        return listaUtentiDTO;
    }

    @Override
    public void doControl(Request request)
    {
        if (request != null)
        {
            UtenteDTO utenteDTO;

            switch (request.getString("OPERATION"))
            {
                case "CREATE":
                    ok = utentiService.insert((UtenteDTO) request.get("DATI_UTENTE"));

                    request = new Request();

                    if (ok)
                        request.put("PRINT", "Utente creato correttamente.");
                    else
                        request.put("PRINT", "Errore nella creazione dell'utente");

                    MainDispatcher.getInstance().callAction("UTENTE", "PRINT", request);
                    break;
                case "EDIT":
                    utenteDTO = (UtenteDTO) request.get("DATI");

                    ok = UtenteController.getInstance().updateUtente(utenteDTO);

                    if (ok)
                    {
                        VariabiliGlobali.userName = utenteDTO.getNome();
                        request.put("PRINT", "Modifica avvenuta correttamente.");
                    }
                    else
                        request.put("PRINT", "Errore nella modifica del profilo!");

                    break;
                case "DELETE":
                    utenteDTO = utentiService.findByName(request.getString("NOME"));
                    utentiService.delete(utenteDTO.getId());
                    break;
                case "GET_ALL_UTENTI":
                    request= new Request();
                    request.put("LISTA_UTENTI", utentiService.getAll());

                    MainDispatcher.getInstance().callAction("UTENTE","GET_LIST", request );

                    break;
                case "VISUALIZZA_IMPIANTI":
                    List<PistaDTO> pistaDTOList = new ArrayList<>();
                    PisteService pisteService = PisteService.getInstance();
                    pistaDTOList = pisteService.getAll();

                    Request request3 = new Request();
                    request3.put("PRINT4", pistaDTOList);
                    MainDispatcher.getInstance().callAction("UTENTE", "PRINT4", request3);
                    break;
                case "PROFILO":
                    utenteDTO = UtentiService.getInstance().findByName(VariabiliGlobali.userName);
                    request.put("PRINT", utenteDTO.toString());
                    MainDispatcher.getInstance().callAction("UTENTE", "PRINT5", request);
                    break;
                case "STORICO_BIGLIETTI":
                    BigliettiService bigliettiService = new BigliettiService();
                    List<BigliettoDTO> storicoBigliettiList = bigliettiService.findByLoggedUser();

                    Request request1 = new Request();
                    request1.put("PRINT2", storicoBigliettiList);

                    MainDispatcher.getInstance().callAction("UTENTE", "PRINT6", request1);
                    break;
                case "STORICO_NOLEGGI":
                    NoleggiService noleggiService = new NoleggiService();

                    List<NoleggioDTO> storicoNoleggiList = noleggiService.getAllByUser();

                    Request request2 = new Request();
                    request2.put("PRINT3", storicoNoleggiList);
                    MainDispatcher.getInstance().callAction("UTENTE", "PRINT7", request2);
                    break;
            }
        }
    }
}
