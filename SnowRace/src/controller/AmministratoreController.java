package controller;

import dto.UtenteDTO;
import interfaces.Controller;
import util.Request;
import util.VariabiliGlobali;


public class AmministratoreController implements Controller
{
    private static AmministratoreController instance;
    private boolean ok;

    private AmministratoreController()
    {}

    public static AmministratoreController getInstance()
    {
        if (instance == null)
            instance = new AmministratoreController();

        return instance;
    }

    @Override
    public void doControl(Request request)
    {
        if (request != null)
        {
            switch (request.getString("OPERATION"))
            {
                case "EDIT":
                    UtenteDTO utenteDTO = (UtenteDTO) request.get("DATI");

                    ok = UtenteController.getInstance().updateUtente(utenteDTO);

                    if (ok)
                    {
                        request.put("PRINT", "Modifica avvenuta correttamente.");
                    }
                    else
                        request.put("PRINT", "Errore nella modifica del profilo!");
                    break;
            }
        }
    }
}
