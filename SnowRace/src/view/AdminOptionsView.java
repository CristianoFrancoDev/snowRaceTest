package view;

import abstracts.Abstract_View;
import interfaces.View;
import singleton.MainDispatcher;
import util.Request;

//classe singleton
public class AdminOptionsView extends Abstract_View implements View
{
    private static AdminOptionsView instance;
    private String scelta;

    private AdminOptionsView()
    {}

    public static AdminOptionsView getInstance()
    {
        if (instance == null)
            instance = new AdminOptionsView();

        return instance;
    }

    @Override
    public void showResults(Request request)
    {

    }

    @Override
    public void showOption()
    {
        do
        {
            System.out.println("---- Opzioni account admin ----");
            System.out.println("(M)odificare account");
            System.out.println("(V)isualizzare account");
            System.out.println("(E)liminare account");
            System.out.println("E(S)ci");

            scelta = getInput();
        }
        while (scelta == null || scelta.isBlank());
    }

    @Override
    public void submit()
    {
        switch (scelta.toUpperCase().trim())
        {
            case "M":
                MainDispatcher.getInstance().callAction("ADMIN", "MODIFICA_ACCOUNT", null);
                break;
            case "V":
                MainDispatcher.getInstance().callAction("ADMIN", "VISUALIZZA_ACCOUNT", null);
                break;
            case "E":
                MainDispatcher.getInstance().callAction("ADMIN", "ELIMINA_ACCOUNT", null);
                break;
            default:

                break;
        }
    }
}
