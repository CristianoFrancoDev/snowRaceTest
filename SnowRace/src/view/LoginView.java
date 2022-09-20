package view;

import abstracts.Abstract_View;
import interfaces.View;
import singleton.MainDispatcher;
import util.Request;

public class LoginView extends Abstract_View implements View
{
    private static LoginView instance;
    private MainDispatcher mainDispatcher;
    private Request request;
    private String user, password;

    private LoginView()
    {
        mainDispatcher = MainDispatcher.getInstance();
    }

    public static LoginView getInstance()
    {
        if (instance == null)
            instance = new LoginView();

        return instance;
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
        System.out.println("Nome utente:");
        user = getInput();

        System.out.println("Password:");
        password = getInput();
    }

    @Override
    public void submit()
    {
        if (user != null && !user.isBlank() && password != null && !password.isBlank())
        {
            request = new Request();

            request.put("USER", user);
            request.put("PASSWORD", password);

            mainDispatcher.callAction("HOME_CONTROLLER", "GET_RUOLO", request);
        }
        else
        {
            Request request = new Request();
            request.put("PRINT", "Nome utente o password errati!");
            showResults(request);
            showOption();
        }
    }
}
