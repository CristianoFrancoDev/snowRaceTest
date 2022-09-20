package singleton;

import controller.HomeController;
import controller.ImpiantoController;
import interfaces.Dispatcher;
import util.Request;
import view.*;

public class MainDispatcher implements Dispatcher
{
    private static MainDispatcher instance;

    private MainDispatcher()
    {
    }

    public static MainDispatcher getInstance()
    {
        if (instance == null)
            instance = new MainDispatcher();

        return instance;
    }

    @Override
    public void callView(String view, Request request)
    {
        switch (view)
        {
            case "LOGIN_VIEW":
                LoginView loginView = LoginView.getInstance();
                loginView.showResults(request);
                loginView.showOption();
                loginView.submit();
                break;
            case "ADMIN_VIEW":
                AdminView adminView = AdminView.getInstance();
//                adminView.showResults(request);
                adminView.showOption();
                adminView.submit();
                break;
            case "AMMINISTRATORE_VIEW":
                AmministratoreView amministratoreView = AmministratoreView.getInstance();
                amministratoreView.showOption();
                amministratoreView.submit();
                break;
            case "UTENTE_VIEW":
                UtenteView utenteView = UtenteView.getInstance();
                utenteView.showOption();
                utenteView.submit();
                break;
            case "CRUD_VIEW":
                CrudView crudView = CrudView.getInstance();
                crudView.setFromView(request.getString("FROM"));
                crudView.setSection(request.getString("SECTION"));
                crudView.showOption();
                crudView.submit();
                break;
            case "CREA_IMPIANTO_VIEW":
                CreaImpiantoView creaImpiantoView = CreaImpiantoView.getInstance();
                creaImpiantoView.showOption();
                creaImpiantoView.submit();
                break;
            case "CREA_UTENTE_VIEW":
                CreaUtenteView creaUtenteView = CreaUtenteView.getInstance();
                creaUtenteView.showOption();
                creaUtenteView.submit();
                break;
            case "CREA_PISTA_VIEW":
                CreaPistaView creaPistaView = CreaPistaView.getInstance();
                creaPistaView.showOption();
                creaPistaView.submit();
                break;
        }
    }

    public void callAction(String controller, String action, Request request)
    {
        switch (controller)
        {
            case "LOGIN":
                switch (action)
                {
                    case "DO_CONTROL":

                        break;
                    case "PRINT":
                        LoginView.getInstance().showResults(request);
                        break;
                }

                break;
            case "HOME_CONTROLLER":
                // vai al controller della home

                HomeController homeController = HomeController.getInstance();

                switch (action)
                {
                    case "GET_RUOLO":
                        homeController.doControl(request);
                        break;
                }

                break;
            case "AMMINISTRATORE":
                switch (action)
                {
                    case "MENU_IMPIANTI":
                        request = new Request();
                        request.put("FROM", "AMMINISTRATORE_VIEW");
                        request.put("SECTION", "IMPIANTI");
                        callView("CRUD_VIEW", request);
                        break;
                    case "MENU_UTENTI":
                        request = new Request();
                        request.put("FROM", "AMMINISTRATORE_VIEW");
                        request.put("SECTION", "UTENTI");
                        callView("CRUD_VIEW", request);
                        break;
                    case "MENU_PISTE":
                        request = new Request();
                        request.put("FROM", "AMMINISTRATORE_VIEW");
                        request.put("SECTION", "PISTE");
                        callView("CRUD_VIEW", request);
                        break;
                    case "EXIT":
                        callView("LOGIN", null);
                        break;
                    case "CREA":
                        switch (request.getString("SECTION"))
                        {
                            case "IMPIANTI":
                                callView("CREA_IMPIANTO_VIEW", null);

                                break;
                            case "UTENTI":
                                callView("CREA_UTENTE_VIEW", null);
                                break;
                            case "PISTE":
                                callView("CREA_PISTA_VIEW", null);
                                break;
                        }
                        break;
                    case "MODIFICA":

                        break;
                    case "VISUALIZZA":

                        break;
                    case "ELIMINA":

                        break;
                }

                break;
            case "IMPIANTO":
                switch (action)
                {
                    case "CREATE":
                        ImpiantoController impiantoController = ImpiantoController.getInstance();
                        request.put("OPERATION", "CREATE");
                        impiantoController.doControl(request);
                        callView("AMMINISTRATORE_VIEW", null);
                        break;
                    case "PRINT":
                        CreaImpiantoView.getInstance().showResults(request);
                        break;
                }

                break;
        }
    }
}
