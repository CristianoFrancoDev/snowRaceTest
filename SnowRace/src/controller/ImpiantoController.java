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
        impiantiService = new ImpiantiService();
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
            switch (request.getString("OPERATION"))
            {
                case "CREATE":
                    ok = impiantiService.insert((ImpiantoDTO) request.get("DATI"));

                    request = new Request();

                    if (ok)
                        request.put("PRINT", "Impianto creato correttamente.");
                    else
                        request.put("PRINT", "Errore nella creazione dell'impianto");

                    MainDispatcher.getInstance().callAction("IMPIANTO", "PRINT", request);
                    break;
                case "MODIFY":
                    impiantiService.update((ImpiantoDTO) request.get("DATI"));
                    break;
                case "DELETE":
                    impiantiService.delete((ImpiantoDTO) request.get("DATI"));
                    break;
                case "GET_ALL":
                    List<ImpiantoDTO> response = impiantiService.getAll();
                    break;
            }
        }
    }
}
