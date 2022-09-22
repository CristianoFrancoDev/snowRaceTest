package view;

import abstracts.Abstract_View;
import singleton.MainDispatcher;
import util.Request;

public class CrudView extends Abstract_View implements interfaces.View
{
    private static CrudView instance;
    private String scelta;
    private String fromView;
    private String section;

    private CrudView()
    {}

    public static CrudView getInstance()
    {
        if (instance == null)
            instance = new CrudView();

        return instance;
    }

    public String getSection()
    {
        return section;
    }

    public void setSection(String section)
    {
        this.section = section;
    }

    public String getFromView()
    {
        return fromView;
    }

    public void setFromView(String fromView)
    {
        this.fromView = fromView;
    }

    @Override
    public void showResults(Request request)
    {
        if (request != null)
        {
            System.out.println(request.getString("PRINT"));
        }
    }

    @Override
    public void showOption()
    {
        if (fromView == "AMMINISTRATORE_VIEW")
            System.out.println("---- Opzioni amministratore ----");
        else
            System.out.println("---- Opzioni admin ----");

        System.out.println("(C)rea " + getSection().toLowerCase());
        System.out.println("(M)odifica " + getSection().toLowerCase());
        System.out.println("(V)isualizza " + getSection().toLowerCase());
        System.out.println("(E)limina " + getSection().toLowerCase());
        System.out.println("E(S)ci\n");

        scelta = getInput();
    }

    @Override
    public void submit()
    {
        Request request = new Request();
        request.put("SECTION", getSection().toUpperCase());

        switch (getFromView())
        {
            case "AMMINISTRATORE_VIEW":
            switch (scelta.toUpperCase().trim())
            {
                case "C":
                    MainDispatcher.getInstance().callAction("AMMINISTRATORE", "CREA", request);
                    break;
                case "M":
                    MainDispatcher.getInstance().callAction("AMMINISTRATORE", "MODIFICA", request);
                    break;
                case "V":
                    MainDispatcher.getInstance().callAction("AMMINISTRATORE", "VISUALIZZA", request);
                    break;
                case "E":
                    MainDispatcher.getInstance().callAction("AMMINISTRATORE", "ELIMINA", request);
                    break;
                default:
                    MainDispatcher.getInstance().callView("LOGIN", null);
                    break;
            }

            break;
        case "ADMIN_VIEW":
                switch (scelta.toUpperCase().trim())
                {
                    case "C":
                        MainDispatcher.getInstance().callAction("ADMIN", "CREA", request);
                        break;
                    case "M":
                        MainDispatcher.getInstance().callAction("ADMIN", "MODIFICA", request);
                        break;
                    case "V":
                        MainDispatcher.getInstance().callAction("ADMIN", "VISUALIZZA", request);
                        break;
                    case "E":
                        MainDispatcher.getInstance().callAction("ADMIN", "ELIMINA", request);
                        break;
                    default:
                        MainDispatcher.getInstance().callView("LOGIN", null);
                        break;
                }

                break;
        }
    }
}
