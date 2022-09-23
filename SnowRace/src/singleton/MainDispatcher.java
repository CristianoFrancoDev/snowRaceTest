package singleton;

import controller.*;
import dto.UtenteDTO;
import interfaces.Dispatcher;
import util.Request;
import util.VariabiliGlobali;
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
            case "FILTROIMPIANTI_VIEW":
                FilterByImpiantoView filterByImpiantoView = FilterByImpiantoView.getInstance();
                filterByImpiantoView.showOption();
                filterByImpiantoView.submit();
                break;
            case "START_VIEW":
                StartView.getInstance().showOption();
                StartView.getInstance().submit();
                break;
            case "LOGIN_VIEW":
                LoginView loginView = LoginView.getInstance();
                loginView.showResults(request);
                loginView.showOption();
                loginView.submit();
                break;
            case "ADMIN_VIEW":
                AdminView adminView = AdminView.getInstance();
                adminView.showOption();
                adminView.submit();
                break;
            case "ADMINOPTIONS_VIEW":
                AdminOptionsView.getInstance().showOption();
                AdminOptionsView.getInstance().submit();
                break;
            case "ADMINMODIFICA_VIEW":
                AdminModificaView.getInstance().showOption();
                AdminModificaView.getInstance().submit();
                break;
            case "ADMINVISUALIZZA_VIEW":
                AdminVisualizzaView.getInstance().showResults(request);
                break;
            case "ADMINELIMINA_VIEW":
                AdminEliminaView.getInstance().showOption();
                AdminEliminaView.getInstance().submit();
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
            case "VISUALIZZAIMPIANTI_VIEW":
                VisualizzaImpiantiView.getInstance().showResults(request);
                break;
            case "ELIMINAIMPIANTO_VIEW":
                EliminaImpiantoView.getInstance().showOption();
                EliminaImpiantoView.getInstance().submit();
                break;
            case "CHECKIMPIANTO_VIEW":
                CheckImpiantoView.getInstance().showOption();
                CheckImpiantoView.getInstance().submit();
                break;
            case "MODIFICAIMPIANTO_VIEW":
                ModificaImpiantoView.getInstance().showOption();
                ModificaImpiantoView.getInstance().submit();
                break;
            case "VISUALIZZAUTENTI_VIEW" :
                VisualizzaUtentiView.getInstance().showResults(request);
                break;
            case "MODIFICAUTENTE_VIEW":
                ModificaUtenteView modificaUtenteView = ModificaUtenteView.getInstance();
                modificaUtenteView.setAskName((boolean) request.get("ASK_NAME"));
                modificaUtenteView.showOption();
                modificaUtenteView.submit();
                break;
            case "ACQUISTOBIGLIETTO_VIEW":
                AcquistoBigliettoView acquistoBigliettoView = AcquistoBigliettoView.getInstance();
                acquistoBigliettoView.showOption();
                acquistoBigliettoView.submit();
                break;
            case "NOLEGGIO_VIEW":
                NoleggioView noleggioView = NoleggioView.getInstance();
                noleggioView.showOption();
                noleggioView.submit();
                break;
            case "MODIFICAPISTA_VIEW":
                ModificaPistaView modificaPistaView = ModificaPistaView.getInstance();
                modificaPistaView.showOption();
                modificaPistaView.submit();
                break;
            case "ELIMINAUTENTE_VIEW" :
                EliminaUtenteView.getInstance().showOption();
                EliminaUtenteView.getInstance().submit();
                break;
            case "VISUALIZZAPISTE_VIEW" :
                VisualizzaPistaView.getInstance().showResults(request);
                break;
            case "ELIMINAPISTA_VIEW" :
                EliminaPistaView.getInstance().showOption();
                EliminaPistaView.getInstance().submit();
                break;
            case "REGISTRAZIONE_VIEW":
                RegistrazioneView.getInstance().showOption();
                RegistrazioneView.getInstance().submit();
                break;
            case "FILTROPISTE_VIEW":
                FilterByPistaView filterByPistaView = FilterByPistaView.getInstance();
                filterByPistaView.showOption();
                filterByPistaView.submit();
                break;
            case "FILTERBYDATA_VIEW":
                FilterByDataView.getInstance().showOption();
                FilterByDataView.getInstance().submit();
                break;
        }
    }

    public void callAction(String controller, String action, Request request)
    {
        UtenteDTO utenteDTO;
        boolean ok;

        switch (controller)
        {
            case "START":
                switch (action)
                {
                    case "DO_CONTROL":
                        callView("START_VIEW", null);
                        break;
                }

                break;
            case "REGISTRAZIONE":
                callView("REGISTRAZIONE_VIEW", null);
                callView("START_VIEW", null);
                break;
            case "LOGIN":
                switch (action)
                {
                    case "DO_CONTROL":
                        callView("LOGIN_VIEW", null);
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
            case "ADMIN":
                switch (action)
                {
                    case "MENU_PISTE":
                        request = new Request();
                        request.put("FROM", "ADMIN_VIEW");
                        request.put("SECTION", "PISTE");
                        callView("CRUD_VIEW", request);
                        break;
                    case "ACCOUNT":
                        callView("ADMINOPTIONS_VIEW", null);
                        break;
                    case "MODIFICA_ACCOUNT":
                        callView("ADMINMODIFICA_VIEW", null);
                        callView("ADMINOPTIONS_VIEW", null);
                        break;
                    case "UPDATE_ACCOUNT":
                        utenteDTO = UtenteController.getInstance().getUser(VariabiliGlobali.userName);

                        utenteDTO.setNome(request.getString("NOME"));
                        utenteDTO.setIndirizzo(request.getString("INDIRIZZO"));
                        utenteDTO.setLuogo(request.getString("LUOGO"));
                        utenteDTO.setPassword(request.getString("PASSWORD"));

                        ok = UtenteController.getInstance().updateUtente(utenteDTO);

                        if (ok)
                            {
                                VariabiliGlobali.userName = utenteDTO.getNome();
                                request.put("PRINT", "Modifica avvenuta correttamente.");
                            }
                        else
                            request.put("PRINT", "Errore nella modifica del profilo!");

                        AdminModificaView.getInstance().showResults(request);
                        break;
                    case "VISUALIZZA_ACCOUNT":
                        request = new Request();
                        UtenteDTO adminDTO = UtenteController.getInstance().getUser(VariabiliGlobali.userName);

                        request.put("DATI", adminDTO);

                        callView("ADMINVISUALIZZA_VIEW", request);
                        callView("ADMINOPTIONS_VIEW", null);
                        break;
                    case "ELIMINA_ACCOUNT":
                        callView("ADMINELIMINA_VIEW", null);
                        callView("START_VIEW", null);
                        break;
                    case "DELETE_ACCOUNT":
                        utenteDTO = UtenteController.getInstance().getUser(VariabiliGlobali.userName);
                        ok = UtenteController.getInstance().deleteUtente(utenteDTO);
                        request = new Request();

                        if (ok)
                            request.put("PRINT", "Profilo eliminato con successo.");
                        else
                            request.put("PRINT", "Errore nell'eliminazione del profilo!");

                        AdminEliminaView.getInstance().showResults(request);
                        break;
                    case "CREA":
                        callView("CREA_PISTA_VIEW", null);
                        callAction("ADMIN", "MENU_PISTE", null);
                        break;
                    case "MODIFICA":
                        callView("MODIFICAPISTA_VIEW", null);
                        callAction("ADMIN", "MENU_PISTE", null);
                        break;
                    case "VISUALIZZA":
                        request = new Request();
                        request.put("OPERATION", "GET_ALL_PISTE");
                        PistaController.getInstance().doControl(request);
                        callAction("ADMIN", "MENU_PISTE", null);
                        break;
                    case "ELIMINA":
                        callView("ELIMINAPISTA_VIEW", null);
                        callAction("ADMIN", "MENU_PISTE", null);
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
                                callAction("AMMINISTRATORE", "MENU_PISTE", null);
                                break;
                        }

                        break;
                    case "MODIFICA":
                        switch (request.getString("SECTION"))
                        {
                            case "IMPIANTI":
                                callView("MODIFICAIMPIANTO_VIEW", request);
                                callView("AMMINISTRATORE_VIEW", null);
                                break;
                            case "UTENTI":
                                request = new Request();
                                request.put("ASK_NAME", true);

                                callView("MODIFICAUTENTE_VIEW", request);
                                break;
                            case "PISTE":
                                callView("MODIFICAPISTA_VIEW", null);
                                callAction("AMMINISTRATORE", "MENU_PISTE", null);
                                break;
                        }

                        break;
                    case "VISUALIZZA":
                        switch (request.getString("SECTION"))
                        {
                            case "IMPIANTI":
                                request = new Request();
                                request.put("OPERATION", "GET_ALL");

                                ImpiantoController.getInstance().doControl(request);

                                break;
                            case "UTENTI" :
                                request = new Request();
                                request.put("OPERATION", "GET_ALL_UTENTI");
                                UtenteController.getInstance().doControl(request);
                                break;
                            case "PISTE" :
                                request = new Request();
                                request.put("OPERATION", "GET_ALL_PISTE");
                                PistaController.getInstance().doControl(request);
                                callAction("AMMINISTRATORE", "MENU_PISTE", null);
                                break;
                        }

                        break;
                    case "UPDATE_ACCOUNT":
                        utenteDTO = UtenteController.getInstance().getUser(request.getString("USERNAME_TO_EDIT"));
                        utenteDTO.setNome(request.getString("NOME"));
                        utenteDTO.setIndirizzo(request.getString("INDIRIZZO"));
                        utenteDTO.setLuogo(request.getString("LUOGO"));
                        utenteDTO.setPassword(request.getString("PASSWORD"));

                        request = new Request();
                        request.put("OPERATION", "EDIT");
                        request.put("DATI", utenteDTO);

                        AmministratoreController.getInstance().doControl(request);
                        ModificaUtenteView.getInstance().showResults(request);
                        callView("AMMINISTRATORE_VIEW", null);

                        break;
                    case "ELIMINA":
                        switch (request.getString("SECTION"))
                        {
                            case "IMPIANTI":
                                callView("ELIMINAIMPIANTO_VIEW", null);
                                break;
                            case "UTENTI" :
                                callView("ELIMINAUTENTE_VIEW", null);
                                break;
                            case "PISTE" :
                                callView("ELIMINAPISTA_VIEW", null);
                                callAction("AMMINISTRATORE", "MENU_PISTE", null);
                                break;
                        }
                        break;
                }

                break;
            case "IMPIANTO": //controller IMPIANTO ---------------------------
                switch (action)
                {
                    case "CREATE":
                        ImpiantoController impiantoController = ImpiantoController.getInstance();
                        request.put("OPERATION", "CREATE");
                        impiantoController.doControl(request);
                        callView("AMMINISTRATORE_VIEW", null);
                        break;
                    case "FIND":
                        request.put("OPERATION", "FIND");
                        ImpiantoController.getInstance().doControl(request);
                        callView("AMMINISTRATORE_VIEW", null);
                        break;
                    case "UPDATE":
                        request.put("OPERATION", "UPDATE");
                        ImpiantoController.getInstance().doControl(request);
                        ModificaImpiantoView.getInstance().showResults(request);
                        callView("AMMINISTRATORE_VIEW", null);
                        break;
                    case "PRINT":
                        CreaImpiantoView.getInstance().showResults(request);
                        break;
                    case "GET_ALL":
                        request.put("OPERATION", "GET_IMPIANTI");
                        ImpiantoController.getInstance().doControl(request);
                        break;
                    case "GET_LIST":
                        callView("VISUALIZZAIMPIANTI_VIEW", request);
                        callAction("AMMINISTRATORE", "MENU_IMPIANTI", null);
                        break;
                    case "ELIMINA":
                        request.put("OPERATION", "DELETE");
                        ImpiantoController.getInstance().doControl(request);
                        EliminaImpiantoView.getInstance().showResults(request);
                        callAction("AMMINISTRATORE", "MENU_IMPIANTI", null);

                        break;
                }

                break;
            case "UTENTE":
                switch (action)
                {
                    case "FILTRO_DATA":
                        callView("FILTERBYDATA_VIEW", null);
                        callView("UTENTE_VIEW", null);
                        break;
                    case "FILTER_BY_DATE":
                        request.put("OPERATION", "FILTER_DATE");
                        UtenteController.getInstance().doControl(request);
                        FilterByDataView.getInstance().showResults(request);
                        break;
                    case "REGISTRAZIONE":
                        request.put("OPERATION", "REGISTRAZIONE");
                        UtenteController.getInstance().doControl(request);
                        RegistrazioneView.getInstance().showResults(request);
                        break;
                    case "PRINT4":
                        AllImpiantiView.getInstance().showResults(request);
                        break;
                    case "CREATE":
                        request.put("OPERATION","CREATE");
                        UtenteController.getInstance().doControl(request);
                        callAction("AMMINISTRATORE", "MENU_UTENTI", null);
                        break;
                    case "UPDATE":
                        request = new Request();
                        request.put("ASK_NAME", false);

                        callView("MODIFICAUTENTE_VIEW", request);
                        callView("UTENTE_VIEW", null);
                        break;
                    case "UPDATE_ACCOUNT":
                        utenteDTO = UtenteController.getInstance().getUser(VariabiliGlobali.userName);
                        utenteDTO.setNome(request.getString("NOME"));
                        utenteDTO.setIndirizzo(request.getString("INDIRIZZO"));
                        utenteDTO.setLuogo(request.getString("LUOGO"));
                        utenteDTO.setPassword(request.getString("PASSWORD"));

                        request = new Request();
                        request.put("OPERATION", "EDIT");
                        request.put("DATI", utenteDTO);

                        UtenteController.getInstance().doControl(request);
                        ModificaUtenteView.getInstance().showResults(request);

                        break;
                    case "PRINT":
                        CreaUtenteView.getInstance().showResults(request);
                        break;
                    case "GET_LIST":
                        callView("VISUALIZZAUTENTI_VIEW", request);
                        callAction("AMMINISTRATORE", "MENU_UTENTI", null);
                        break;
                    case "ELIMINA":
                        request.put("OPERATION", "DELETE");
                        UtenteController.getInstance().doControl(request);
                        callAction("AMMINISTRATORE", "MENU_UTENTI", null);
                        break;
                    case "MOSTRA_IMPIANTI":
                        request.put("OPERATION", "VISUALIZZA_IMPIANTI");
                        UtenteController.getInstance().doControl(request);
                        callView("UTENTE_VIEW", null);
                        break;
                    case "DATI_PERSONALI":
                        request.put("OPERATION","PROFILO");
                        UtenteController.getInstance().doControl(request);
                        callView("UTENTE_VIEW", null);
                        break;
                    case "PRINT5":
                        UtenteProfiloView.getInstance().showResults(request);
                        break;
                    case "STORICO_BIGLIETTI":
                        request.put("OPERATION","STORICO_BIGLIETTI");
                        UtenteController.getInstance().doControl(request);
                        callView("UTENTE_VIEW", null);
                        break;
                    case "STORICO_NOLEGGI":
                        request.put("OPERATION","STORICO_NOLEGGI");
                        UtenteController.getInstance().doControl(request);
                        callView("UTENTE_VIEW", null);
                        break;
                    case "PRINT6":
                        AllBigliettiView.getInstance().showResults(request);
                        callView("UTENTE_VIEW", null);
                        break;
                    case "PRINT7":
                        AllNoleggiView.getInstance().showResults(request);
                        break;
                    case "ACQUISTO_BIGLIETTO":
                        callView("ACQUISTOBIGLIETTO_VIEW", null);
                        break;
                    case "ACQUISTO_TICKET":
                        request.put("OPERATION","ACQUISTO_BIGLIETTO");
                        UtenteController.getInstance().doControl(request);
                        break;
                    case "PRINT8":
                        AcquistoBigliettoView.getInstance().showResults(request);
                        callView("UTENTE_VIEW", null);
                        break;
                    case "NOLEGGIO":
                        callView("NOLEGGIO_VIEW", null);
                        break;
                    case "ACQUISTO_NOLEGGIO":
                        request.put("OPERATION","ACQUISTO_NOLEGGIO");
                        UtenteController.getInstance().doControl(request);
                        break;
                    case "PRINT9":
                        NoleggioView.getInstance().showResults(request);
                        callView("UTENTE_VIEW", null);
                        break;
                    case "FILTRO_PISTE":
                        callView("FILTROPISTE_VIEW", null);
                        break;
                    case "FILTER_BY_PISTA":
                        request.put("OPERATION","FILTER_BY_PISTA");
                        UtenteController.getInstance().doControl(request);
                        break;
                    case "PRINT_BY_PISTA":
                        FilterByPistaView.getInstance().showResults(request);
                        callView("UTENTE_VIEW", null);
                        break;
                    case "FILTRO_IMPIANTI":
                        callView("FILTROIMPIANTI_VIEW", null);
                        break;
                    case "FILTER_IMPIANTO":
                        request.put("OPERATION","FILTER_IMPIANTO");
                        UtenteController.getInstance().doControl(request);
                        break;
                    case "PRINT_BY_IMPIANTO":
                        FilterByImpiantoView.getInstance().showResults(request);
                        callView("UTENTE_VIEW", null);
                        break;
                }

                break;

            case "PISTA":
                switch (action)
                {
                    case "CREATE":
                        request.put("OPERATION","CREATE");
                        PistaController.getInstance().doControl(request);
                        break;
                    case "MODIFY":
                        request.put("OPERATION","MODIFY");
                        PistaController.getInstance().doControl(request);
                        break;
                    case "PRINT":
                        CreaPistaView.getInstance().showResults(request);
                        break;
                    case "GET_ALL" :
                        callView("VISUALIZZAPISTE_VIEW", request);
//                        callAction("AMMINISTRATORE", "MENU_PISTE", null);
                        break;
                    case "ELIMINA":
                        request.put("OPERATION", "DELETE");
                        PistaController.getInstance().doControl(request);
                        break;
                }

                break;
        }
    }
}
