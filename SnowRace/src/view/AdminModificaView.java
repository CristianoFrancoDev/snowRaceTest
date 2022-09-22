package view;

import abstracts.Abstract_View;
import dto.UtenteDTO;
import interfaces.View;
import singleton.MainDispatcher;
import util.Request;

public class AdminModificaView extends Abstract_View implements View
{
    private static AdminModificaView instance;
    private String nome, indirizzo, luogo, password;

    private AdminModificaView()
    {}

    public static AdminModificaView getInstance()
    {
        if (instance == null)
            instance = new AdminModificaView();

        return instance;
    }

    @Override
    public void showResults(Request request)
    {
        if (request != null)
            System.out.println(request.getString("PRINT"));
    }

    @Override
    public void showOption()
    {
        do
        {
            System.out.println("Nome: ");
            nome = getInput();
        }
        while (nome == null || nome.isBlank());

        System.out.println("Indirizzo: ");
        indirizzo = getInput();

        System.out.println("Luogo: ");
        luogo = getInput();

        do
        {
            System.out.println("Password: ");
            password = getInput();
        }
        while (password == null || password.isBlank());
    }

    @Override
    public void submit()
    {
        Request request = new Request();
        request.put("NOME", nome.trim());

        if (indirizzo == null || indirizzo.isBlank())
            request.put("INDIRIZZO", null);
        else
            request.put("INDIRIZZO", indirizzo.trim());

        if (luogo == null || luogo.isBlank())
            request.put("LUOGO", null);
        else
            request.put("LUOGO", luogo.trim());

        request.put("PASSWORD", password.trim());

        MainDispatcher.getInstance().callAction("ADMIN", "UPDATE_ACCOUNT", request);
    }
}
