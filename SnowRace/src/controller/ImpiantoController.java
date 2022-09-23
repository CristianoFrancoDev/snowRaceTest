package controller;

import dto.ImpiantoDTO;
import interfaces.Controller;
import service.ImpiantiService;
import singleton.MainDispatcher;
import util.Request;

import java.util.List;

public class ImpiantoController implements Controller
{
    private static ImpiantoController instance;
    private ImpiantiService impiantiService;
    private boolean ok;

    private ImpiantoController()
    {
        impiantiService = ImpiantiService.getInstance();
    }

    public static ImpiantoController getInstance()
    {
        if (instance == null)
            instance =new ImpiantoController();

        return instance;
    }

    @Override
    public void doControl(Request request)
    {
        if (request != null)
        {
            ImpiantoDTO impiantoDTO;

            switch (request.getString("OPERATION"))
            {
                case "FIND":
                    impiantoDTO = impiantiService.findByTitolo(request.getString("TITOLO"));
                    request.put("DATI", impiantoDTO);
                    break;
                case "CREATE":
                    impiantoDTO = impiantiService.findByTitolo(((ImpiantoDTO)request.get("DATI")).getTitolo());

                    if (impiantoDTO == null)
                    {
                        ok = impiantiService.insert((ImpiantoDTO) request.get("DATI"));

                        request = new Request();

                        if (ok)
                            request.put("PRINT", "Impianto creato correttamente.");
                        else
                            request.put("PRINT", "Errore nella creazione dell'impianto");
                    }
                    else
                    {
                        request = new Request();
                        request.put("PRINT", "Impianto gia' esistente!");
                    }

                    MainDispatcher.getInstance().callAction("IMPIANTO", "PRINT", request);

                    break;
                case "UPDATE":
                    ok = impiantiService.update((ImpiantoDTO) request.get("DATI"));

                    if (ok)
                        request.put("PRINT", "Impianto modificato correttamente");
                    else
                        request.put("PRINT", "Errore nel salvataggio dell'impianto");

                    break;
                case "DELETE":
                    impiantoDTO = impiantiService.findByTitolo(request.getString("TITOLO"));

                    if (impiantoDTO == null)
                    {
                        request.put("PRINT", "Nessun impianto col nome specificato!");
                    }
                    else
                    {
                        if(impiantiService.delete(impiantoDTO))
                            request.put("PRINT", "Impianto eliminato correttamente");
                        else
                            request.put("PRINT", "Errore nell'eliminazione dell'impianto!");
                    }

                    break;
                case "GET_IMPIANTI":
                    request.put("LISTA_IMPIANTI", impiantiService.getAll());
                    break;
                case "GET_ALL":
                    request = new Request();
                    request.put("LISTA_IMPIANTI", impiantiService.getAll());

                    MainDispatcher.getInstance().callAction("IMPIANTO", "GET_LIST", request);

                    break;
            }
        }
    }
}
