package view;

import abstracts.Abstract_View;
import interfaces.View;
import singleton.MainDispatcher;
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
            case "V":
                request.put("UTENTE", "VISUALIZZA_IMPIANTI");
                break;
            case "A":
                request.put("UTENTE", "ACQUISTO_BIGLIETTI");
                break;
            case "N":
                request.put("UTENTE", "NOLEGGIO");
                break;
            case "D":
                request.put("UTENTE", "PROFILO");
                break;
            case "B":
                request.put("UTENTE", "STORICO_BIGLIETTI");
                break;
            case "S":
                request.put("UTENTE", "STORICO_NOLEGGI");
                break;
            case "F":
                request.put("UTENTE", "FILTRO_DATA");
                break;
            case "I":
                request.put("UTENTE", "FILTRO_IMPIANTI");
                break;
            case "P":
                request.put("UTENTE", "FILTRO_PISTE");
                break;
            default:
                request.put("UTENTE", "EXIT");
                break;
        }

        MainDispatcher.getInstance().callAction("UTENTE", "DO_CONTROL", request);
    }
}
