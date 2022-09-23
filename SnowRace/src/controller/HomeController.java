package controller;

import interfaces.Controller;
import service.UtentiService;
import singleton.MainDispatcher;
import util.Request;
import util.VariabiliGlobali;

public class HomeController implements Controller
{
    private static HomeController instance;

    private HomeController()
    {}

    public static HomeController getInstance()
    {
        if (instance == null)
            instance = new HomeController();

        return instance;
    }

    @Override
    public void doControl(Request request)
    {
        if (request != null)
        {
            String user, password, userType;

            user = request.getString("USER");
            password = request.getString("PASSWORD");

            userType = UtentiService.getInstance().login(user, password);

            if (userType == null)
                {
                    VariabiliGlobali.userName = null;

                    request = new Request();
                    request.put("PRINT", "Nome utente o password errati!");
                    MainDispatcher.getInstance().callAction("LOGIN", "PRINT", request);
                }
            else
            {
                VariabiliGlobali.userName = user.trim();

                switch (userType)
                {
                    case "AMMINISTRATORE":
                        MainDispatcher.getInstance().callView("AMMINISTRATORE_VIEW", request);
                        break;
                    case "ADMIN":
                        MainDispatcher.getInstance().callView("ADMIN_VIEW", request);
                        break;
                    case "USER":
                        MainDispatcher.getInstance().callView("UTENTE_VIEW", request);
                        break;
                    default:
                        VariabiliGlobali.userName = null;

                        //ritorna alla login
                        MainDispatcher.getInstance().callView("LOGIN", null);
                        break;
                }
            }
        }
        else
        {
                VariabiliGlobali.userName = null;
                MainDispatcher.getInstance().callView("LOGIN", null);
        }
    }
}
