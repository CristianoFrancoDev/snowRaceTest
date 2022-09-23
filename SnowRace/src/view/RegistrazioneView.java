package view;

import abstracts.Abstract_View;
import dto.UtenteDTO;
import interfaces.View;
import model.Ruolo;
import singleton.MainDispatcher;
import test.Main;
import util.Request;

//classe singleton
public class RegistrazioneView extends Abstract_View implements View
{
    private static RegistrazioneView instance;
    private String nome, indirizzo, luogo, password;

    private RegistrazioneView()
    {}

    public static RegistrazioneView getInstance()
    {
        if (instance == null)
            instance = new RegistrazioneView();

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
        System.out.println("---- Registrazione utente ----");

        do
        {
            System.out.println("Nome: ");
            nome = getInput();
        }
        while (nome == null || nome.isBlank());

        System.out.println("Indirizzo: ");
        indirizzo = getInput();

        System.out.println("Luogo di nascita: ");
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
        UtenteDTO utenteDTO = new UtenteDTO(0, nome, indirizzo, luogo, Ruolo.USER, password, false);
        Request request = new Request();
        request.put("DATI", utenteDTO);

        MainDispatcher.getInstance().callAction("UTENTE", "REGISTRAZIONE", request);
    }
}
