package view;

import abstracts.Abstract_View;
import interfaces.View;
import singleton.MainDispatcher;
import test.Main;
import util.Request;

public class UtenteView extends Abstract_View implements View
{
    private static UtenteView instance;
    private String scelta;
    private Request request;

    private UtenteView()
    {}

    public static UtenteView getInstance()
    {
        if (instance == null)
            instance = new UtenteView();

        return instance;
    }

    @Override
    public void showResults(Request request)
    {
//
    }

    @Override
    public void showOption()
    {
        System.out.println("--- Opzioni utente ---");
        System.out.println("(M)odifica profilo");
        System.out.println("(V)isualizza tutti gli impianti");
        System.out.println("(A)cquistare un biglietto");
        System.out.println("(N)oleggio attrezzatura");
        System.out.println("(D)ati personali");
        System.out.println("Storico acquisto (B)iglietti");
        System.out.println("(S)torico noleggi");
        System.out.println("(F)iltra per data");
        System.out.println("Filtra per (I)mpianto");
        System.out.println("Filtra per (P)ista");
        System.out.println("(E)sci\n");

        scelta = getInput();
    }

    @Override
    public void submit()
    {
        request = new Request();

        switch (scelta.toUpperCase().trim())
        {
            case "M":
                MainDispatcher.getInstance().callAction("UTENTE", "UPDATE", null);
                break;
            case "V":
                request.put("UTENTE", "VISUALIZZA_IMPIANTI");
                MainDispatcher.getInstance().callAction("UTENTE", "MOSTRA_IMPIANTI", request);
                break;
            case "A":
                request.put("UTENTE", "ACQUISTO_BIGLIETTI");
                MainDispatcher.getInstance().callAction("UTENTE", "ACQUISTO_BIGLIETTO", request);

                break;
            case "N":
                request.put("UTENTE", "NOLEGGIO");
                MainDispatcher.getInstance().callAction("UTENTE", "NOLEGGIO", request);

                break;
            case "D":
                request.put("UTENTE", "PROFILO");
                MainDispatcher.getInstance().callAction("UTENTE", "DATI_PERSONALI", request);
                break;
            case "B":
                request.put("UTENTE", "STORICO_BIGLIETTI");
                MainDispatcher.getInstance().callAction("UTENTE", "STORICO_BIGLIETTI", request);
                break;
            case "S":
                request.put("UTENTE", "STORICO_NOLEGGI");
                MainDispatcher.getInstance().callAction("UTENTE", "STORICO_NOLEGGI", request);
                break;
            case "F":
                request.put("UTENTE", "FILTRO_DATA");
                MainDispatcher.getInstance().callAction("UTENTE", "FILTRO_DATA", null);
                break;
            case "I":
                request.put("UTENTE", "FILTRO_IMPIANTI");
                MainDispatcher.getInstance().callAction("UTENTE", "FILTRO_IMPIANTI", request);
                break;
            case "P":
                request.put("UTENTE", "FILTRO_PISTE");
                MainDispatcher.getInstance().callAction("UTENTE", "FILTRO_PISTE", request);
                break;
            default:
                request.put("UTENTE", "EXIT");
                break;
        }

//        MainDispatcher.getInstance().callAction("UTENTE", "DO_CONTROL", request);
    }
}
