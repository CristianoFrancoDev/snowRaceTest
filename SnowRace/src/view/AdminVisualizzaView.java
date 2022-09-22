package view;

import abstracts.Abstract_View;
import dto.UtenteDTO;
import interfaces.View;
import util.Request;

public class AdminVisualizzaView extends Abstract_View implements View
{
    private static AdminVisualizzaView instance;

    private AdminVisualizzaView()
    {}

    public static AdminVisualizzaView getInstance()
    {
        if (instance == null)
            instance = new AdminVisualizzaView();

        return instance;
    }

    @Override
    public void showResults(Request request)
    {
        UtenteDTO adminDTO = (UtenteDTO) request.get("DATI");
        System.out.println("---- Profilo admin ----");
        System.out.println("Nome: " + adminDTO.getNome());
        System.out.println("Indirizzo: " + adminDTO.getIndirizzo());
        System.out.println("Luogo di nascita: " + adminDTO.getLuogo());
        System.out.println();
    }

    @Override
    public void showOption()
    {

    }

    @Override
    public void submit()
    {

    }
}
